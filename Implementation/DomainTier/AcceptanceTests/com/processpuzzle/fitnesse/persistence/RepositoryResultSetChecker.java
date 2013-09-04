package com.processpuzzle.fitnesse.persistence;


import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.processpuzzle.commons.compiler.SourceCodeTemplateEnhancer;
import com.processpuzzle.commons.compiler.StringCompiler;
import com.processpuzzle.commons.compiler.StringCompilerException;
import com.processpuzzle.commons.persistence.AggregateRoot;
import com.processpuzzle.commons.persistence.Entity;
import com.processpuzzle.commons.persistence.PersistentObject;
import com.processpuzzle.commons.persistence.RepositoryResultSet;

import fit.RowFixture;

public class RepositoryResultSetChecker extends RowFixture {
   private static final String SOURCE_CODE_FILE = "classpath:com/processpuzzle/fitnesse/persistence/DefaultPersistentObjectWrapper.java.template";
   private static final Logger logger = LoggerFactory.getLogger( RepositoryResultSetChecker.class );
   private static final List<String> COMPILER_OPTIONS = Arrays.asList( new String[] { "-target", "1.5" } );
   private StringCompiler<PersistentObjectWrapper<?>> compiler;
   private RepositoryResultSet<? extends PersistentObject> repositoryResultSet;
   private Class<? extends PersistentObject> persistentObjectClass;
   private Class<? extends PersistentObjectWrapper<?>> wrapperClass;
   private String sourceToCompile;
   private PersistentObjectWrapper<?>[] wrappedPersistentObjects;

   RepositoryResultSetChecker( RepositoryResultSet<?> repositoryResultSet, Class<? extends AggregateRoot> aggregateRootClass ) {
      super();
      this.repositoryResultSet = repositoryResultSet;
      persistentObjectClass = aggregateRootClass;
      wrapResultSet();
   }

   @Override
   public Class<?> getTargetClass() {
      return wrapperClass;
   }

   @Override
   public Object[] query() throws Exception {
      return wrappedPersistentObjects;
   }
   
   public ClassLoader getCompilerClassLoader() {
      return compiler.getClassLoader();
   }

   private void compileWrapperClass() {
      logger.trace( "Intending to compile:\n" + sourceToCompile );
      wrapperClass = null;

      try{
         wrapperClass = compiler.compile( sourceToCompile, null, new Class<?>[] { PersistentObjectWrapper.class } );
      }catch( ClassCastException e ){
         String message = "Type mismatch in compilation of 'DefaultPersistentObjectWrapper'";
         logger.error( message );
         throw new RepositoryTesterException( message, e );
      }catch( StringCompilerException e ){
         String message = "There was a problem in compiling 'DefaultPersistentObjectWrapper'.";
         logger.error( message, e );
         throw new RepositoryTesterException( message, e );
      }
   }

   private void instantiateRuntimeCompiler() {
      compiler = new StringCompiler<PersistentObjectWrapper<?>>( getClass().getClassLoader(), COMPILER_OPTIONS, compiler );
   }

   @SuppressWarnings( "unchecked" )
   private PersistentObjectWrapper<Entity> instantiateWrapper() {
      PersistentObjectWrapper<Entity> wrapper = null;
      try{
         wrapper = (PersistentObjectWrapper<Entity>) wrapperClass.newInstance();
      }catch( InstantiationException e ){
         String message = "There was a problem when instantiting '" + wrapperClass.getName() + "'";
         logger.error( message, e );
         throw new RepositoryTesterException( message, e );
      }catch( IllegalAccessException e ){
         String message = "Can't access when instantiting '" + wrapperClass.getName() + "'";
         logger.error( message, e );
         throw new RepositoryTesterException( message, e );
      }
      return wrapper;
   }

   private boolean isPublicConcreteGetter( Method property ) {
      boolean isGetter = false;
      isGetter = StringUtils.startsWith( property.getName(), "get" ) 
                 && Modifier.isPublic( property.getModifiers() )
                 && !property.getName().equals( "getClass" )
                 && !Modifier.isVolatile( property.getModifiers() )
                 && !Modifier.isAbstract( property.getModifiers() );
      return isGetter;
   }

   private void replaceExpressionsInSourceTemplate( SourceCodeTemplateEnhancer sourceEnhancer ) {
      String assignments = "";

      for( Method property : persistentObjectClass.getMethods() ){
         if( isPublicConcreteGetter( property ) ){
            assignments += "      " + sourceEnhancer.createFieldNameFromMethod( property ) + " = persistentObject." + property.getName() + "();\n";
            //assignments += "      " + "System.out.println( \"" + sourceEnhancer.createFieldNameFromMethod( property ) + " field was assigned.\" );\n";
         }
      }
      sourceEnhancer.insertExpressions( assignments );
   }

   private void replaceFieldsInSourceTemplate( SourceCodeTemplateEnhancer sourceEnhancer ) {
      for( Method property : persistentObjectClass.getMethods() ){
         if( isPublicConcreteGetter( property ) ){
            sourceEnhancer.insertFieldFromGetter( property );
         }
      }
   }

   private void replacePlaceholdersInSourceTemplate() {
      SourceCodeTemplateEnhancer sourceEnhancer = new SourceCodeTemplateEnhancer( SOURCE_CODE_FILE );

      sourceEnhancer.replaceGenericType( persistentObjectClass.getSimpleName() );
      sourceEnhancer.insertImportIntoSource( persistentObjectClass );

      replaceFieldsInSourceTemplate( sourceEnhancer );
      replaceExpressionsInSourceTemplate( sourceEnhancer );

      sourceEnhancer.finalizeSourceCode();
      sourceToCompile = sourceEnhancer.getSourceCodeTeamplate();
   }

   private void wrapResultSet() {
      wrappedPersistentObjects = new PersistentObjectWrapper[repositoryResultSet.size()];
   
      instantiateRuntimeCompiler();
      replacePlaceholdersInSourceTemplate();
      compileWrapperClass();
   
      for( int i = 0; i < repositoryResultSet.size(); i++ ){
         PersistentObjectWrapper<Entity> wrapper = instantiateWrapper();
         wrapper.wrapPersistentObject( (Entity) repositoryResultSet.getEntityAt( i ) );
         wrappedPersistentObjects[i] = wrapper;
      }
   }
}
