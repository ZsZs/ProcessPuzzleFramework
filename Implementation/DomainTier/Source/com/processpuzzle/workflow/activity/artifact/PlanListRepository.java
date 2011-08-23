package com.processpuzzle.workflow.activity.artifact;

import hu.itkodex.commons.persistence.PersistenceStrategy;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.domain.GenericRepository;
import com.processpuzzle.persistence.query.domain.ComparisonOperators;
import com.processpuzzle.persistence.query.domain.DefaultQuery;
import com.processpuzzle.persistence.query.domain.TextAttributeCondition;

public class PlanListRepository extends GenericRepository<PlanList> {

   public PlanListRepository( PersistenceStrategy strategy, ProcessPuzzleContext applicationContext ) {
      super( strategy, applicationContext );
   }

   public void addPlanList( DefaultUnitOfWork work, PlanList planList ) {
      add( work, PlanList.class, planList );
   }

   public PlanList findPlanList() {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      PlanList planList = findPlanList( work );
      work.finish();
      return planList;
   }

   public PlanList findPlanList( DefaultUnitOfWork work ) {
      DefaultQuery q = new DefaultQuery( PlanList.class );
      TextAttributeCondition condition = new TextAttributeCondition( "name", PlanList.class.getSimpleName(), ComparisonOperators.EQUAL_TO );
      q.getQueryCondition().addAttributeCondition( condition );
      return (PlanList) findByQuery( work, q );
   }

}
