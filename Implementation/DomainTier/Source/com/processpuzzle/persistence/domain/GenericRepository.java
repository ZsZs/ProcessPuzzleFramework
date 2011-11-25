/*
Name: 
    - GenericRepository 

Description: 
    -  

Requires:
    - 
    
Provides:
    - 

Part of: ProcessPuzzle Framework, Domain and Business Model Ready Architecture. Provides content, workflow and social networking functionality. 
http://www.processpuzzle.com

ProcessPuzzle - Content and Workflow Management Integration Business Platform

Author(s): 
    - Zsolt Zsuffa

Copyright: (C) 2011 This program is free software: you can redistribute it and/or modify it under the terms of the 
GNU General Public License as published by the Free Software Foundation, either version 3 of the License, 
or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.processpuzzle.persistence.domain;

import hu.itkodex.commons.persistence.AggregateRoot;
import hu.itkodex.commons.persistence.PersistenceStrategy;
import hu.itkodex.commons.persistence.PersistentObject;
import hu.itkodex.commons.persistence.Repository;
import hu.itkodex.commons.persistence.RepositoryResultSet;
import hu.itkodex.commons.persistence.UnitOfWork;
import hu.itkodex.commons.persistence.query.IdentityExpression;
import hu.itkodex.commons.persistence.query.Query;

import java.lang.reflect.ParameterizedType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.fundamental_types.domain.OpAssertion;
import com.processpuzzle.persistence.query.domain.DefaultQuery;

public abstract class GenericRepository<E extends AggregateRoot> implements Repository<E>, QLConstants, Cloneable {
   protected static Logger log = LoggerFactory.getLogger( GenericRepository.class );
   protected PersistenceStrategy strategy;
   protected Class<E> supportedAggregateRootClass;
   protected Class<? extends Repository<E>> repositoryClass;
   protected ProcessPuzzleContext applicationContext;

   // Conscrutors
   @SuppressWarnings( "unchecked" )
   public GenericRepository( PersistenceStrategy strategy, ProcessPuzzleContext applicationContext ) {
      this.strategy = strategy;
      this.supportedAggregateRootClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
      this.repositoryClass = (Class<? extends Repository<E>>) this.getClass();
      this.applicationContext = applicationContext;
   }

   @Override public RepositoryResultSet<E> findAll( UnitOfWork work ) {
      OpAssertion.ppAssert( work.isStarted(), "UnitOfWork should be started before invoking add()" );
      log.trace( "Repository: " + repositoryClass.getSimpleName() + ".findAll() was called." );
      return strategy.findAll( work, supportedAggregateRootClass );
   }

   // Public accessors
   @Override public E findById( UnitOfWork work, Integer id ) {
      OpAssertion.ppAssert( work.isStarted(), "UnitOfWork should be started before invoking findById()" );
      log.trace( "Repository: " + repositoryClass.getSimpleName() + ".findById( " + id.toString() + ") was called." );
      return (E) strategy.findById( work, supportedAggregateRootClass, id );
   }

   @SuppressWarnings("unchecked")
   @Override public E findByIdentityExpression( UnitOfWork work, IdentityExpression<?> identity ) {
      OpAssertion.ppAssert( work.isStarted(), "UnitOfWork should be started before invoking findByIdentityExpression()" );
      log.trace( "Repository: " + repositoryClass.getSimpleName() + ".findByIddentityExpression( " + identity.toString() + ") was called." );
      
      RepositoryResultSet<? extends E> resultSet = null;
      try{
         resultSet = (RepositoryResultSet<? extends E>) strategy.findByQuery( work, identity );
         if( resultSet != null && resultSet.size() == 1 )
            return resultSet.getEntityAt( 0 );
      }catch( Exception e ){
         throw new RepositoryException( this, RepositoryAction.findByIdentityExpression, e );
      }
      return null;
   }

   @SuppressWarnings("unchecked")
   @Override public RepositoryResultSet<E> findByQuery( UnitOfWork work, Query query ) {
      OpAssertion.ppAssert( work.isStarted(), "UnitOfWork should be started before invoking findByQuery()" );
      log.trace( "Repository: " + repositoryClass.getSimpleName() + ".findByQuery( " + query.toString() + ") was called." );
      return (RepositoryResultSet<E>) strategy.findByQuery( work, query );
   }

   @SuppressWarnings( "unchecked" )
   public RepositoryResultSet<PersistentObject> findExternalByQuery( UnitOfWork work, Query query ) {
      OpAssertion.ppAssert( work.isStarted(), "UnitOfWork should be started before invoking findExternalByQuery()" );
      log.trace( "Repository: " + repositoryClass.getSimpleName() + ".findExternalByQuery( " + query.toString() + ") was called." );
      return (RepositoryResultSet<PersistentObject>) strategy.findByQuery( work, query );
   }

   // Public mutators
   @Override public Integer add( UnitOfWork work, E object ) {
      OpAssertion.ppAssert( work.isStarted(), "UnitOfWork should be started before invoking add()" );
      log.trace( "Repository: " + repositoryClass.getSimpleName() + ".add( " + object.toString() + ") was called." );
      return strategy.add( work, supportedAggregateRootClass, object );
   }

   @Override public void update( UnitOfWork work, E object ) {
      OpAssertion.ppAssert( work.isStarted(), "UnitOfWork should be started before invoking update()" );
      log.trace( "Repository: " + repositoryClass.getSimpleName() + ".update( " + object.toString() + ") was called." );
      strategy.update( work, supportedAggregateRootClass, object );
   }

   @Override public void delete( UnitOfWork work, E object ) {
      OpAssertion.ppAssert( work.isStarted(), "UnitOfWork should be started before invoking delete()" );
      log.trace( "Repository: " + repositoryClass.getSimpleName() + ".delete( " + object.toString() + ") was called." );
      strategy.delete( work, supportedAggregateRootClass, object );
   }

   public Object clone() {
      Object retValue = null;
      try{
         retValue = super.clone();
      }catch( CloneNotSupportedException e ){}
      return retValue;
   }

   // Properties
   public PersistenceStrategy getPersistenceStrategy() {
      return strategy;
   }

   public Class<? extends AggregateRoot> getSupportedAggregateRootClass() {
      return supportedAggregateRootClass;
   }

   @SuppressWarnings("unchecked")
   protected RepositoryResultSet<E> findAll( UnitOfWork work, Class<? extends E> entityClass ) {
      OpAssertion.ppAssert( work.isStarted(), "UnitOfWork should be started before invoking findAll()" );
      log.trace( "Repository: " + repositoryClass.getSimpleName() + ".findAll() was called." );
      return (RepositoryResultSet<E>) strategy.findAll( work, entityClass );
   }

   // Protected accessors: just a simple implementation of public abstract accessors
   @SuppressWarnings( "unchecked" )
   protected E findById( UnitOfWork work, Class entityClass, Integer id ) {
      OpAssertion.ppAssert( work.isStarted(), "UnitOfWork should be started before invoking findById()" );
      log.trace( "Repository: " + repositoryClass.getSimpleName() + ".findById( " + id.toString() + ") was called." );
      return (E) strategy.findById( work, entityClass, id );
   }

   @SuppressWarnings("unchecked")
   protected RepositoryResultSet<? extends E> findByQuery( UnitOfWork work, Class<? extends E> entityClass, DefaultQuery query ) {
      OpAssertion.ppAssert( work.isStarted(), "UnitOfWork should be started before invoking findByQuery()" );
      log.trace( "Repository: " + repositoryClass.getSimpleName() + ".findByQuery( " + query.toString() + ") was called." );
      return (RepositoryResultSet<? extends E>) strategy.findByQuery( work, query );
   }

   protected PersistentObject findExternalById( UnitOfWork work, Class<? extends PersistentObject> entityClass, Integer id ) {
      OpAssertion.ppAssert( work.isStarted(), "UnitOfWork should be started before invoking findByExternalId()" );
      return strategy.findById( work, entityClass, id );
   }

   // Protected mutators: just a simple implementation of public abstract mutators
   protected Integer add( UnitOfWork work, Class<? extends E> entityClass, E object ) {
      OpAssertion.ppAssert( work.isStarted(), "UnitOfWork should be started before invoking add()" );
      log.trace( "Repository: " + repositoryClass.getSimpleName() + ".add( " + object.toString() + ") was called." );
      return strategy.add( work, entityClass, object );
   }

   protected void update( UnitOfWork work, Class<? extends E> entityClass, E object ) {
      OpAssertion.ppAssert( work.isStarted(), "UnitOfWork should be started before invoking update()" );
      log.trace( "Repository: " + repositoryClass.getSimpleName() + ".update( " + object.toString() + ") was called." );
      strategy.update( work, entityClass, object );
   }

   protected void delete( UnitOfWork work, Class<? extends E> entityClass, E object ) {
      OpAssertion.ppAssert( work.isStarted(), "UnitOfWork should be started before invoking add()" );
      log.trace( "Repository: " + repositoryClass.getSimpleName() + ".delete( " + object.toString() + ") was called." );
      strategy.delete( work, entityClass, object );
   }

   // Proptected, private helper methods
   protected String getAggregateRootClassName() {
      return supportedAggregateRootClass.getName();
   }
}