/*
Name: 
    - PartyAggregateDefinition 

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

import com.processpuzzle.fundamental_types.domain.AggregateDefinition;
import com.processpuzzle.fundamental_types.domain.TimePeriod;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRelationshipConstraint;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRelationshipType;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRoleConstraint;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRoleType;
import com.processpuzzle.party.partytype.domain.PartyType;
import com.processpuzzle.resource.domain.Asset;
import com.processpuzzle.resource.resourcetype.domain.AssetType;
import com.processpuzzle.resource.resourcetype.domain.ResourceType;

public class PartyAggregateDefinition extends AggregateDefinition {

   public PartyAggregateDefinition() {
      super();
   }

   @Override
   protected void defineFactoryClass() {
      factoryClass=PartyFactory.class;
   }

   @Override
   protected void defineManagedValueObjects() {
      managedValueObjects.add(TimePeriod.class);

   }

   @Override
   protected void defineManagedEntities() {

      managedEntities.add(PartyName.class);
      managedEntities.add(PartyRelationship.class);
      managedEntities.add(PartyRelationshipConstraint.class);
      managedEntities.add(PartyRoleConstraint.class);
      managedEntities.add(Address.class);
      managedEntities.add(PartyRole.class);
      managedEntities.add(RuleSet.class);
    
   }

   @Override
   protected void defineReferencedNeighbourRoots() {

      referencedNeighbourRoots.add(Asset.class);
      referencedNeighbourRoots.add(PartyRelationshipType.class);
      referencedNeighbourRoots.add(ResourceType.class);
      referencedNeighbourRoots.add(AssetType.class);
      referencedNeighbourRoots.add(PartyType.class);
      referencedNeighbourRoots.add(PartyRoleType.class);
   }

   @Override
   protected void defineRepositoryClass() {
      repositoryClass=PartyRepository.class;

   }

   @Override
   protected void defineRootClass() {
      rootClass=Party.class;
   }

   @Override
   protected void defineAnyOtherRelatedClasses() {
      // TODO Auto-generated method stub
      
   }

}
