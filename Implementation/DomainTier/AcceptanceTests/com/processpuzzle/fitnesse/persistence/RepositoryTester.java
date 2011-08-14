package com.processpuzzle.fitnesse.persistence;

import hu.itkodex.commons.persistence.AggregateRoot;
import hu.itkodex.commons.persistence.PersistentObject;
import hu.itkodex.commons.persistence.Repository;
import hu.itkodex.commons.persistence.RepositoryResultSet;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.configuration.domain.RepositoryInstantiationException;
import com.processpuzzle.fundamental_types.domain.ParameterValueList;
import com.processpuzzle.persistence.domain.SimpleResultSet;
import com.processpuzzle.persistence.domain.TestEntity;
import com.processpuzzle.user_session.domain.UserRequestManager;

import fit.RowFixture;
import fitlibrary.DoFixture;

public class RepositoryTester extends DoFixture {
   private static final Logger logger = LoggerFactory.getLogger( RepositoryTester.class );
   private ProcessPuzzleContext applicationContext = null;
   private String repositoryClassName;
   private Class<? extends Repository<?>> repositoryClass;
   private Class<? extends AggregateRoot> aggregateRootClass;
   private Repository<?> repository;
   private RepositoryResultSet<?> repositoryResultSet;
   private ParameterValueList parameterValueList;
   
   //Constructors
   public RepositoryTester() {
      super();
      logger.debug( "New 'RepositoryTester' was instantiated." );
   }

   //Public accessors and mutators
   public void executeWithParameter( String methodName, String parameters ) throws NoSuchRepositoryMethodException, RepositoryMethodExecutionException, ClassNotFoundException, InstantiationException, IllegalAccessException {
      logger.debug( "Trying to execute method: '" + methodName + "' with parameters: '" + parameters + "'.");
      parameterValueList = parseParameters( parameters );
      Method repositoryMethod = determineRepositoryMethod( methodName, parameterValueList );
      repositoryResultSet = executeRepositoryMethod( repositoryMethod, parameterValueList );
      logger.debug( "'" + methodName + "' returned: '" + repositoryResultSet.size() + "' objects." );
   }
   
   public RowFixture expectedResultSet() {
      logger.debug( "Wrapping result set." );
      RepositoryResultSetChecker resultSetChecker = new RepositoryResultSetChecker( repositoryResultSet, aggregateRootClass ); 
      return resultSetChecker;
   }
   
   public void useRepository( String repositoryClassName ) {
      lazyInitialization();
      try{
         this.repositoryClassName = repositoryClassName;
         this.repositoryClass = determineRepositoryClass();
         this.repository = applicationContext.getRepository( repositoryClass );
         this.aggregateRootClass = this.repository.getSupportedAggregateRootClass() != null ? this.repository.getSupportedAggregateRootClass() : TestEntity.class;
      }catch( Exception e ){
         String message = "Repository '" + repositoryClassName + "' not found.";
         logger.error( message );
         throw new RepositoryTesterException( message, e );
      }
      logger.debug( "Repository '" + repositoryClassName + "' was selected." );
   }

   //Properties
   public ProcessPuzzleContext getApplicationContext() {
      return applicationContext;
   }

   public ParameterValueList getParameterValueList() {
      return parameterValueList;
   }
   
   public Repository<?> getRepository() {
      return repository;
   }
   
   public RepositoryResultSet<? extends PersistentObject> getRepositoryResultSet() {
      return repositoryResultSet;
   }

   public Class<? extends Repository<?>> getRepositoryClass() {
      return repositoryClass;
   }

   //Protected, private helper methods
   @SuppressWarnings("unchecked")
   private Class<? extends Repository<?>> determineRepositoryClass() {
      try{
         repositoryClass = (Class<? extends Repository<?>>) Class.forName( repositoryClassName );
      }catch( ClassNotFoundException e ){
         throw new RepositoryInstantiationException( repositoryClassName, null, e );
      }
      return repositoryClass;
   }

   private Method determineRepositoryMethod( String methodName, ParameterValueList parameterList ) throws NoSuchRepositoryMethodException {
      List<Method> foundMethods = new ArrayList<Method>();
      
      for( Method method : repositoryClass.getDeclaredMethods() ) {
         if( method.getName().equals( methodName ))
            foundMethods.add( method );
      }
      
      if( foundMethods.size() == 0 ) {
         throw new NoSuchRepositoryMethodException( repositoryClass, methodName );
      }else if( foundMethods.size() == 1 ) {
         return foundMethods.get( 0 );
      }else {
         Class<?>[] parameterTypes = parameterList.getParameterTypes();
         for( Method method : foundMethods ) {
            if( Arrays.equals( method.getParameterTypes(), parameterTypes )) {
               return method;
            }
         }
      }
      
      throw new NoSuchRepositoryMethodException( repositoryClass, methodName );
   }

   @SuppressWarnings("unchecked")
   private RepositoryResultSet<?> executeRepositoryMethod( Method repositoryMethod, ParameterValueList parameterList ) throws RepositoryMethodExecutionException {
      RepositoryResultSet<?> resultSet = null;
      List<Object> resultList = new ArrayList();

      try{
         Object returnValue = repositoryMethod.invoke( repository, parameterList.getValues() );
         if( repositoryMethod.getReturnType().isAssignableFrom( RepositoryResultSet.class ) ) {
            resultSet = (RepositoryResultSet<?>) returnValue;
         }else if( returnValue != null ){
            resultList.add( returnValue );
            resultSet = new SimpleResultSet( resultList );
         }else {
            resultSet = new SimpleResultSet( resultList );
         }
      }catch( IllegalArgumentException e ){
         throw new RepositoryMethodExecutionException( repositoryClass, repositoryMethod.getName(), parameterList.toString(), e );
      }catch( IllegalAccessException e ){
         throw new RepositoryMethodExecutionException( repositoryClass, repositoryMethod.getName(), parameterList.toString(), e );
      }catch( InvocationTargetException e ){
         throw new RepositoryMethodExecutionException( repositoryClass, repositoryMethod.getName(), parameterList.toString(), e );
      }
      return resultSet;
   }

   private void lazyInitialization() {
      if( applicationContext == null ) {
         applicationContext = UserRequestManager.getInstance().getApplicationContext();
         if( applicationContext == null ) throw new UnconfiguredApplicationException();
      }
   }

   private ParameterValueList parseParameters( String parameters ) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
      ParameterValueList parameterList = ParameterValueList.parse( parameters );
      return parameterList;
   }
}
