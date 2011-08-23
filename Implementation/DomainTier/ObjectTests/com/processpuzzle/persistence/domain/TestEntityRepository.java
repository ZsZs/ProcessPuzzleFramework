/*
 * Created on Dec 6, 2005
 */
package com.processpuzzle.persistence.domain;

import hu.itkodex.commons.persistence.PersistenceStrategy;
import hu.itkodex.commons.persistence.RepositoryResultSet;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.persistence.query.domain.ComparisonOperators;
import com.processpuzzle.persistence.query.domain.DefaultQuery;
import com.processpuzzle.persistence.query.domain.TextAttributeCondition;

/**
 * @author zsolt.zsuffa
 */
public class TestEntityRepository extends GenericRepository<TestEntity> {

   // Constructors
   public TestEntityRepository( PersistenceStrategy strategy, ProcessPuzzleContext applicationContext ) {
      super( strategy, applicationContext );
   }

   // Public accessors
   public TestEntity findTestEntityByName( String name ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      TestEntity returnValue = this.findTestEntityByName( work, name );
      work.finish();
      return returnValue;
   }

   public TestEntity findTestEntityByName( DefaultUnitOfWork work, String name ) {
      // return (TestEntity) super.findByQuery("from TestEntity d where d.name = ${name}", new Object[] { "name", name });
      DefaultQuery query = new DefaultQuery( TestEntity.class );
      query.getQueryCondition().addAttributeCondition( new TextAttributeCondition( "name", name, ComparisonOperators.EQUAL_TO ) );
      RepositoryResultSet<TestEntity> result = findByQuery( work, query );
      if( result.size() == 1 )
         return result.getEntityAt( 0 );
      else
         return null;
   }

   public TestEntitySubclass findSubClassById( DefaultUnitOfWork work, Integer id ) {
      return (TestEntitySubclass) findById( work, TestEntitySubclass.class, id );
   }

   // Public mutators
   public void deleteByName( DefaultUnitOfWork work, String name ) {
      delete( work, findTestEntityByName( work, name ) );
   }

   public void addChildEntity( DefaultUnitOfWork work, TestEntitySubclass child ) {
      add( work, TestEntitySubclass.class, child );
   }

   // Private, proted helper methods
   protected void setSupportedAggregateRootClass() {
      supportedAggregateRootClass = TestEntity.class;
   }

   protected Object findByIdentityExpression( String identityExpression ) {
      return null;
   }

   // TestEntityComponent
   // public TestEntityComponent findTestEntityComponentById(Integer id) {
   // return (TestEntityComponent) findById( TestEntity.class, id );
   // }
   //
   // public TestEntityComponent findTestEntityComponentByName(String name) {
   // return (TestEntityComponent) findByQuery("from TestEntityComponent d where d.name = ${name}", new Object[] { "name", name });
   // }
   // 
   //
   // public void addEntityComponent(TestEntityComponent testEntityComponent) {
   // add(TestEntityComponent.class, testEntityComponent);
   // }
   //
   // public void updateTestEntityComponent(TestEntityComponent testEntityComponent) {
   // update(TestEntityComponent.class, testEntityComponent);
   // }
   // 
   // public void deleteTestEntityComponent(TestEntityComponent testEntityComponent) {
   // delete(TestEntityComponent.class, testEntityComponent);
   // }
   //
   // public void deleteTestEntityComponent(String name) {
   // deleteTestEntityComponent(findTestEntityComponentByName(name));
   // }
}