package com.processpuzzle.persistence.typemapping.domain;

import static org.junit.Assert.assertEquals;
import hu.itkodex.litest.template.RepositoryTestTemplate;

import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.commons.persistence.UnitOfWork;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.domain.TestEntity;
import com.processpuzzle.persistence.domain.TestEntityRepository;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class TimePointTypeMappingTest extends RepositoryTestTemplate<TestEntityRepository, TimePointTypeMappingTestFixture, TestEntity> {

   public TimePointTypeMappingTest() {
      super( DomainTierTestConfiguration.FIXTURE_CONTAINER_DEFINITION_PATH );
   }

   @Ignore
   @Test
   public void testValueEquvivalence() {
      UnitOfWork work = new DefaultUnitOfWork( true );
      TestEntity retrievedTestEntity = repository.findById( work, fixture.getTestEntity().getId() );
      work.finish();
      
      assertEquals( fixture.getTestEntity().getTimePeriod(), retrievedTestEntity.getTimePeriod() );
   }

   @Override
   public void testAdd_ForOwnedAttributesAndComponents() {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void testAdd_ForReferencedAggregateRoots() {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void testDelete_ForOwnedAttributesAndComponents() {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void testFindAll_ForResultCount() {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void testFindById() {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void testFindById_ForEagerLoadedComponents() {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void testFindById_ForLazyLoadedComponents() {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void testFindByQuery_ForComponentAttributes() {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void testFindByQuery_ForDirectAttributes() {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void testUpdate_ForOwnedAttributesAndComponents() {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void testUpdate_ForReferencedAggregateRoots() {
      // TODO Auto-generated method stub
      
   }
}
