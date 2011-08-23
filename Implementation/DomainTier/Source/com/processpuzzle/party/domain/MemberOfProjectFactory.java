package com.processpuzzle.party.domain;

import java.util.Collection;
import java.util.Iterator;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRelationshipType;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRelationshipTypeRepository;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRoleType;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRoleTypeRepository;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.workflow.activity.domain.Action;
import com.processpuzzle.workflow.activity.domain.ProcessPlan;
import com.processpuzzle.workflow.activity.domain.WorkflowRepository;

public class MemberOfProjectFactory extends PartyRelationshipFactory {

   public MemberOfProjectFactory() {
      super();
   }

   public static void createMemberOfProject( Project project, Person employee, String partyRoleTypeName ) {
      WorkflowRepository actionRepository = (WorkflowRepository) ProcessPuzzleContext.getInstance().getRepository( WorkflowRepository.class );
      PartyRelationshipTypeRepository partyRelationshipRep = (PartyRelationshipTypeRepository) ProcessPuzzleContext.getInstance().getRepository(
            PartyRelationshipTypeRepository.class );
      PartyRoleTypeRepository partyRoleTypeRepository = (PartyRoleTypeRepository) ProcessPuzzleContext.getInstance().getRepository(
            PartyRoleTypeRepository.class );
      PartyRepository partyRep = (PartyRepository) ProcessPuzzleContext.getInstance().getRepository( PartyRepository.class );
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      PartyRoleType clientPartyRoleType = partyRoleTypeRepository.findPartyRoleTypeByName( work, project.getName() );
      PartyRoleType supplierPartyRoleType = partyRoleTypeRepository.findPartyRoleTypeByName( work, partyRoleTypeName );

      PartyRelationshipType memberOfProjectType = partyRelationshipRep.findByName( work, "MemberOf" + project.getName() );

      PartyRole clientPartyRole = MemberOfProjectFactory.createPartyRole( project.getName(), project.getType(), clientPartyRoleType );
      PartyRole supplierPartyRole = MemberOfProjectFactory.createPartyRole( partyRoleTypeName, employee.getType(), supplierPartyRoleType );

      employee.addPartyRole( supplierPartyRole );
      project.addPartyRole( clientPartyRole );

      MemberOfProject mem = MemberOfProjectFactory.createMemberOfProject( "MemberOf" + project.getName(), clientPartyRole, supplierPartyRole,
            memberOfProjectType );

      // partyRep.addMemberOfProject(work,mem);

      clientPartyRole.setPartyRelationship( mem );
      supplierPartyRole.setPartyRelationship( mem );

      partyRep.updateParty( work, project );
      partyRep.updateParty( work, employee );

      ProcessPlan plan = actionRepository.findProcessPlanByProject( work, project.getId().toString() );

      if( plan != null ){
         Collection<?> actions = plan.findActionInWaitingForResourceStatusByPerformerRole( partyRoleTypeName );
         for( Iterator<?> iter = actions.iterator(); iter.hasNext(); ){
            Action<?> action = (Action<?>) iter.next();
            action.allocateAssetTemporally( employee, null );
            actionRepository.updateAction( work, action );
         }
      }
      work.finish();
   }

   private static MemberOfProject createMemberOfProject( String name, PartyRole clientPartyRole, PartyRole supplierPartyRole,
         PartyRelationshipType memberOfProjectType ) {
      return new MemberOfProject( name, clientPartyRole, supplierPartyRole, memberOfProjectType );
   }

}
