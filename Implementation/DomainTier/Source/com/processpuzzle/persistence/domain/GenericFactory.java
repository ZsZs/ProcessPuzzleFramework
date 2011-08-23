/*
 * Created on Jul 2, 2006
 */
package com.processpuzzle.persistence.domain;

import hu.itkodex.commons.generics.GenericTypeParameterInvestigator;
import hu.itkodex.commons.persistence.Entity;
import hu.itkodex.commons.persistence.Repository;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;
import com.processpuzzle.user_session.domain.UserRequestManager;

/**
 * @author zsolt.zsuffa
 */
public abstract class GenericFactory<E extends Entity> implements EntityFactory<E> {
   protected static Repository<?> repository;
   protected ProcessPuzzleContext applicationContext;
   protected Class<E> entityClass;
   protected User creator;

   public Class<? extends Entity> getTargetEntityClass() { return entityClass; }
   
   @SuppressWarnings( "unchecked" )
   protected GenericFactory() {
      applicationContext = UserRequestManager.getInstance().getApplicationContext();
      entityClass = (Class<E>) GenericTypeParameterInvestigator.getTypeParameter( this.getClass(), 0 );
      creator = UserRequestManager.getInstance().currentUser();
   }

   protected static void checkEntityIdentityCollition( DefaultIdentityExpression<?> identityExpression ) {
      if( identityExpression != null ){
         DefaultUnitOfWork work = new DefaultUnitOfWork( true );

         repository = determineRepository( identityExpression.getTargetClass() );

         if( repository.findByIdentityExpression( work, identityExpression ) != null ){
            work.discard();
            throw new EntityIdentityCollitionException( identityExpression.getTargetClass().getName(), "identity expression = '"
                  + identityExpression + "'", "Factory" );
         }else
            work.finish();
      }
   }

   protected User determineCurrentUser() {
      creator = UserRequestManager.getInstance().currentUser();
      return creator;
   }

   protected static Repository<?> determineRepository( Class<? extends Entity> entityClass ) {
      ProcessPuzzleContext applicationContext = UserRequestManager.getInstance().getApplicationContext();
      Repository<?> repository = applicationContext.getRepositoryByEntityClass( entityClass );
      return repository;
   }

   protected static <R extends Repository<?>> R findRepository( Class<R> repositoryClass ) {
      ProcessPuzzleContext applicationContext = UserRequestManager.getInstance().getApplicationContext();
      R repository = applicationContext.getRepository( repositoryClass );
      return repository;
   }

   protected E instantiateEntity( Class<?>[] argumentClasses, Object[] arguments ) {
      return instantiateEntity( entityClass, argumentClasses, arguments );
   }

   @SuppressWarnings( "unchecked" )
   protected E instantiateEntity( Class<? extends Entity> entityClass, Class<?>[] argumentClasses, Object[] arguments ) {
      Constructor entityConstructor;
      E newEntity;

      try{
         entityConstructor = entityClass.getConstructor( argumentClasses );
         newEntity = (E) entityConstructor.newInstance( arguments );
      }catch( SecurityException e ){
         throw new EntityInstantiationException( entityClass, "configurationDescriptorPath", applicationContext.getConfigurationDescriptorPath(), e );
      }catch( NoSuchMethodException e ){
         throw new EntityInstantiationException( entityClass, "configurationDescriptorPath", applicationContext.getConfigurationDescriptorPath(), e );
      }catch( IllegalArgumentException e ){
         throw new EntityInstantiationException( entityClass, "configurationDescriptorPath", applicationContext.getConfigurationDescriptorPath(), e );
      }catch( InstantiationException e ){
         throw new EntityInstantiationException( entityClass, "configurationDescriptorPath", applicationContext.getConfigurationDescriptorPath(), e );
      }catch( IllegalAccessException e ){
         throw new EntityInstantiationException( entityClass, "configurationDescriptorPath", applicationContext.getConfigurationDescriptorPath(), e );
      }catch( InvocationTargetException e ){
         throw new EntityInstantiationException( entityClass, "configurationDescriptorPath", applicationContext.getConfigurationDescriptorPath(), e );
      }
      return newEntity;
   }
}
