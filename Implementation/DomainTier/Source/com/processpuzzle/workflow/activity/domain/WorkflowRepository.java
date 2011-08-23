/*
 * Created on 2005.07.19.
 */
package com.processpuzzle.workflow.activity.domain;

import hu.itkodex.commons.persistence.PersistenceStrategy;
import hu.itkodex.commons.persistence.UnitOfWork;

import java.util.Collection;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.domain.GenericRepository;
import com.processpuzzle.persistence.query.domain.DefaultQueryContext;

public class WorkflowRepository extends GenericRepository<Plan> {

   public WorkflowRepository( PersistenceStrategy strategy, ProcessPuzzleContext applicationContext ) {
      super( strategy, applicationContext );
   }

   @Deprecated
   public Action<?> findActionById( UnitOfWork work, String id ) {
      return (Action<?>) findById( work, new Integer( id ) );
   }

   @Deprecated
   public void addAction( UnitOfWork work, Action<?> action ) {}

   public void updatePlan( UnitOfWork work, Plan<?> plan ) {
      update( work, plan );
   }

   public Plan<?> findPlanById( UnitOfWork work, String id ) {
      return (Plan<?>) findById( work, Plan.class, new Integer( id ) );
   }

   public void addPlan( UnitOfWork work, Plan<?> plan ) {
      DefaultQueryContext context = new DefaultQueryContext();
      PlanIdentity identity = new PlanIdentity( context );
      identity.getQueryContext().addTextValueFor( "name", plan.getName().toLowerCase() );

      Plan<?> planIn = findByIdentityExpression( work, identity );
      if( planIn == null )
         add( work, plan );
   }

   @Deprecated
   public void updateAction( UnitOfWork work, Action<?> action ) {}

   @Deprecated
   public Collection<Action<?>> findActionByName( UnitOfWork work, String actionName ) {
      return null;
   }

   public ProcessPlan findProcessPlanByProject( DefaultUnitOfWork work, String projectId ) {
      DefaultQueryContext context = new DefaultQueryContext();
      context.addIntegerValueFor( "project.id", new Integer( projectId ) );
      ProcessPlanIdentity identity = new ProcessPlanIdentity( context );
      return (ProcessPlan) findByIdentityExpression( work, identity );
   }

   protected Object findByIdentityExpression( String identityExpression ) {
      return null;
   }
}
