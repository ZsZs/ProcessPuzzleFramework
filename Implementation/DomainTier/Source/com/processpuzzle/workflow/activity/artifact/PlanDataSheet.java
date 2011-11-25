/*
Name: 
    - PlanDataSheet 

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

package com.processpuzzle.workflow.activity.artifact;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.party.domain.Party;
import com.processpuzzle.party.domain.PartyRole;
import com.processpuzzle.party.domain.Person;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRoleType;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.workflow.activity.domain.Action;
import com.processpuzzle.workflow.activity.domain.Plan;
import com.processpuzzle.workflow.activity.domain.ProcessPlan;
import com.processpuzzle.workflow.protocol.domain.ActivityProtocol;

public class PlanDataSheet extends ActionDataSheet<PlanDataSheet> {
   protected PlanDataSheet() {}

   protected PlanDataSheet( String artifactName, ArtifactType type, Plan plan, User creator ) {
      super( artifactName, type, creator, plan );
   }

   public List<PartyRoleType> getPartyRoleTypes() {
      return getPlan().getPartyRoleTypes();
   }

   public Collection<PartyRoleType> getPerformerRoles() {
      List<PartyRoleType> partyRoleTypes = new ArrayList<PartyRoleType>();
      for( Iterator<Action<?>> iter = getPlan().findAllLeafActions().iterator(); iter.hasNext(); ){
         Action<?> action = iter.next();
         partyRoleTypes.add( ((ActivityProtocol) action.getProtocol()).getPerformerRole() );
      }
      return partyRoleTypes;
   }

   @SuppressWarnings("unchecked")
   public List<Person> getPersons() {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      List<Person> result = (List<Person>) personRepository.findAllPerson( work );
      work.finish();
      return result;
   }

   public Plan getPlan() {
      return (Plan) action;
   }

   public Collection<PartyRole> getRoleAssignments() {
      List<PartyRole> thePartyRoles = new ArrayList<PartyRole>();
      Plan plan = getPlan();
      if( plan instanceof ProcessPlan ){
         ProcessPlan processPlan = (ProcessPlan) plan;
         Set<PartyRole> partyRoles = processPlan.getProject().getRoles();
         DefaultUnitOfWork work = new DefaultUnitOfWork( true );
         for( Iterator<PartyRole> iter = partyRoles.iterator(); iter.hasNext(); ){
            PartyRole ownRole = iter.next();
            Party<?> otherParty = partyRepository.findOtherPartyByPartyRole( work, ownRole.getPartyRelationship().getId(), ownRole.getId() );
            PartyRole otherRole = Party.getRoleFromPartyById( otherParty, ownRole.getId() );
            thePartyRoles.add( otherRole );
         }
         work.finish();
      }
      return thePartyRoles;
   }
}