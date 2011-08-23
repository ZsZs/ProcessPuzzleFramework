/*
 * Created on Nov 16, 2006
 */
package com.processpuzzle.party.domain;

import com.processpuzzle.party.partytype.domain.PartyType;
import com.processpuzzle.workflow.activity.domain.ProcessPlan;

/**
 * @author zsolt.zsuffa
 */
public class WorkflowTeam extends Team {
   public static String TEAM_NAME_POSTFIX = "-Team";
   private ProcessPlan plan;

   WorkflowTeam( ProcessPlan plan, PartyType workflowTeamType ) {
      super( new OrganizationName( plan.getName() + TEAM_NAME_POSTFIX ), workflowTeamType );
      this.plan = plan;
   }
   
   public ProcessPlan getPlan() { return plan; }
}
