package com.processpuzzle.workflow.activity.domain;


import com.processpuzzle.litest.template.RepositoryTestEnvironment;
import com.processpuzzle.litest.template.RepositoryTestFixture;
import com.processpuzzle.party.partytype.domain.PartyTypeRepository;

public class WorkflowRepositoryTestFixture extends RepositoryTestFixture<WorkflowRepository, Plan> {
   private PartyTypeRepository planTypeRepository = null;

   public PartyTypeRepository getPlanTypeRepository() {
      return planTypeRepository;
   }

   protected WorkflowRepositoryTestFixture( RepositoryTestEnvironment<WorkflowRepository, RepositoryTestFixture<WorkflowRepository, Plan>> testEnvironment ) {
      super( testEnvironment );
   }

   @Override
   protected Plan<?> createNewAggregate() throws Exception {
      Plan<?> aPlan = new Plan( "testPlan" );
      Plan<?> parent = new Plan( "parent" );
      SeparatePlan stype = new SeparatePlan();
      PartPlan ptype = new PartPlan();
      parent.setType( stype );
      repository.addPlan( setUpWork, parent );
      aPlan.setType( ptype, parent );

      return aPlan;
   }

   @Override
   protected void afterAggregateCreation() {
   }

   @Override
   protected void afterAggregateDeletion() {
   }

   @Override
   protected void beforeAggregateCreation() {
   }
}
