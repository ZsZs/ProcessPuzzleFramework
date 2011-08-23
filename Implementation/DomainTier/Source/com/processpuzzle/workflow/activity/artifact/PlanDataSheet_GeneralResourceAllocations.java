package com.processpuzzle.workflow.activity.artifact;

import java.util.Collection;

import com.processpuzzle.artifact_type.domain.ArtifactViewType;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRoleType;
import com.processpuzzle.workflow.activity.domain.GeneralResourceAllocation;

public class PlanDataSheet_GeneralResourceAllocations extends ActionDataSheet_GeneralResourceAllocations<PlanDataSheet> {

   public PlanDataSheet_GeneralResourceAllocations( PlanDataSheet artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );
   }

   public Collection<GeneralResourceAllocation> getGeneralResourceAllocations() {
      return parentArtifact.getPlan().getGeneralPartyRoleTypeAllocations();
   }

   public Collection<PartyRoleType> getPerformerRoles() { return parentArtifact.getPerformerRoles(); }

   public void initializeView() {

   }
}
