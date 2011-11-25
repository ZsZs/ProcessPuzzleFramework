/*
Name: 
    - MemberOfProjectFactory 

Description: 
    -  

Requires:
    - 
    
Provides:
    - 

Part of: ProcessPuzzle Framework, Domain and Business Model Ready Architecture. Provides content, workflow and social networking functionality. 
http://www.processpuzzle.com

ProcessPuzzle - Content and Workflow Management Integration Business Platform

Author(s): 
    - Zsolt Zsuffa

Copyright: (C) 2011 This program is free software: you can redistribute it and/or modify it under the terms of the 
GNU General Public License as published by the Free Software Foundation, either version 3 of the License, 
or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

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
