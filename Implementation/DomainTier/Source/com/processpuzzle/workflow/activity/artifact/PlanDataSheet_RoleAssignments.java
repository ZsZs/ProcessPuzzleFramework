package com.processpuzzle.workflow.activity.artifact;

import java.util.Collection;
import java.util.List;

import com.processpuzzle.artifact.domain.CustomFormView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;
import com.processpuzzle.party.domain.MemberOfProjectFactory;
import com.processpuzzle.party.domain.Party;
import com.processpuzzle.party.domain.PartyRelationshipFactory;
import com.processpuzzle.party.domain.PartyRole;
import com.processpuzzle.party.domain.Person;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRoleType;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.workflow.activity.domain.Plan;
import com.processpuzzle.workflow.activity.domain.ProcessPlan;

public class PlanDataSheet_RoleAssignments extends CustomFormView<PlanDataSheet> {
   private Person person = null;
   private PartyRoleType partyRoleType = null;
   private String roleAssignmentId = null;
   private Party<?> party = null;

   public PlanDataSheet_RoleAssignments( PlanDataSheet artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );
   }

   public Collection<PartyRole> getRoleAssignments() {
      return parentArtifact.getRoleAssignments();
   }

   public List<PartyRoleType> getPartyRoleTypes() {
      return parentArtifact.getPartyRoleTypes();
   }

   public List<Person> getPersons() {
      return parentArtifact.getPersons();
   }

   public void setPerson( String personId ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      if( personId != null && !personId.equals( "-1" ) )
         this.person = personRepository.findPersonById( work, new Integer( personId ) );
      work.finish();
   }

   public void setPartyRoleType( String partyRoleTypeId ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      if( (partyRoleTypeId != null && !partyRoleTypeId.equals( "-1" )) )
         this.partyRoleType = partyRoleTypeRepository.findPartyRoleTypeById( work, partyRoleTypeId );
      work.finish();
   }

   public void setRoleAssignmentId( String id ) {
      this.roleAssignmentId = id;
   }

   public void setPartyId( String id ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      this.party = partyRepository.findPartyById( work, new Integer( id ) );
      work.finish();
   }

   public void performAction() {
      if( method.equals( "newRoleAssignment" ) )
         newRoleAssignment();
      else if( method.equals( "removeRoleAssignment" ) )
         removeRoleAssignment();
      else if( method.equals( "setDefaultPerformer" ) )
         setDefaultPerformer();
   }

   private void newRoleAssignment() {
      if( (person != null) && (partyRoleType != null) ){
         ProcessPlan processPlan = null;
         Plan plan = ((PlanDataSheet) parentArtifact).getPlan();
         if( plan instanceof ProcessPlan ){
            processPlan = (ProcessPlan) plan;
         }

         MemberOfProjectFactory.createMemberOfProject( processPlan.getProject(), person, partyRoleType.getName() );
      }
   }

   private void removeRoleAssignment() {
      PartyRelationshipFactory.removePartyRelationship( ((ProcessPlan) ((PlanDataSheet) parentArtifact).getPlan()).getProject(),
            roleAssignmentId );
   }

   private void setDefaultPerformer() {
      if( (party != null) && (partyRoleType != null) ){
         Plan plan = ((PlanDataSheet) parentArtifact).getPlan();
         plan.updateDefaultPerformerOfRole( partyRoleType, party );
      }
   }

   @Override
   public void initializeView() {
   // TODO Auto-generated method stub
   }
}
