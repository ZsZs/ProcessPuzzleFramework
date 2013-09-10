package com.processpuzzle.workflow.activity.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.processpuzzle.litest.template.RepositoryTestTemplate;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class WorkflowRepositoryTest extends RepositoryTestTemplate<WorkflowRepository, WorkflowRepositoryTestFixture, Plan> {

   public WorkflowRepositoryTest() {
      super( DomainTierTestConfiguration.FIXTURE_CONTAINER_DEFINITION_PATH );
   }

   @Override
   @Test
   public void testAdd_ForOwnedAttributesAndComponents() {
      assertEquals( root.getName(), databaseSpy.retrieveColumnFromRow( "T_PLAN", root.getId(), String.class, "name" ) );
   }

   @Override
   @Test
   public void testAdd_ForReferencedAggregateRoots() {
      // UnitOfWork work=new UnitOfWork(true);
      assertEquals( "Parent is saved", root.getParentPlan().getName(), repository.findPlanById( testWork, root.getId().toString() ).getParentPlan().getName() );
      // work.finish();
   }

   @Override
   @Test
   public void testDelete_ForOwnedAttributesAndComponents() {
   // TODO Auto-generated method stub
   }

   @Override
   @Test
   public void testFindAll_ForResultCount() {
   // TODO Auto-generated method stub
   }

   @Override
   @Test
   public void testFindById() {
   // TODO Auto-generated method stub
   }

   @Override
   @Test
   public void testFindById_ForEagerLoadedComponents() {
   // TODO Auto-generated method stub
   }

   @Override
   @Test
   public void testFindById_ForLazyLoadedComponents() {
   // TODO Auto-generated method stub
   }

   @Override
   @Test
   public void testFindByQuery_ForComponentAttributes() {
   // TODO Auto-generated method stub
   }

   @Override
   @Test
   public void testFindByQuery_ForDirectAttributes() {
   // TODO Auto-generated method stub
   }

   @Override
   @Test
   public void testUpdate_ForOwnedAttributesAndComponents() {
   // TODO Auto-generated method stub
   }

   @Override
   @Test
   public void testUpdate_ForReferencedAggregateRoots() {
   // TODO Auto-generated method stub
   }
}
