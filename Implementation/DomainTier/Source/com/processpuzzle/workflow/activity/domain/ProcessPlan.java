package com.processpuzzle.workflow.activity.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import com.processpuzzle.party.domain.Project;
import com.processpuzzle.persistence.query.domain.DefaultQueryContext;
import com.processpuzzle.workflow.protocol.domain.ActivityProtocol;
import com.processpuzzle.workflow.protocol.domain.CompositeProtocol;

public class ProcessPlan extends Plan<ProcessPlan> {

   private Project project;
   private String subject;

   public ProcessPlan( String name, CompositeProtocol protocol ) {
      super( name, protocol );
   }

   public ProcessPlan( String name ) {
      this( name, null );
   }

   public ProcessPlan() {
      this( null, null );
   }

   public Project getProject() {
      return project;
   }

   public void setProject( Project project ) {
      this.project = project;
   }

   public Collection<Action<?>> findActionInWaitingForResourceStatusByPerformerRole( String performerRoleName ) {
      Collection<Action<?>> actions = new HashSet<Action<?>>();
      for( Iterator<ActionReference> iter = this.getActions().iterator(); iter.hasNext(); ){
         Action<?> action = iter.next().getAction();
         if( (action.getStatus() instanceof WaitingForResourceStatus)
               && (((ActivityProtocol) action.getProtocol()).getPerformerRole().getName().equals( performerRoleName )) ){
            actions.add( action );
         }
      }
      return actions;
   }

   public String getSubject() {
      return subject;
   }

   public void setSubject( String subject ) {
      this.subject = subject;
   }

   protected void defineIdentityExpressions() {
      DefaultQueryContext context = new DefaultQueryContext();
      defaultIdentity = new ProcessPlanIdentity( context );
      identities.add( defaultIdentity );
   }

}
