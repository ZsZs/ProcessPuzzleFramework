/*
Name: 
    - PlanDataSheet_GeneralResourceAllocations 

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
