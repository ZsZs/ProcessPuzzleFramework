/*
Name: 
    - ProtocolAggregateDefinition 

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

package com.processpuzzle.workflow.protocol.domain;

import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.fundamental_types.domain.AggregateDefinition;
import com.processpuzzle.workflow.activity.domain.ActionReference;

public class ProtocolAggregateDefinition extends AggregateDefinition {

   public ProtocolAggregateDefinition() {
      super();
   }

   @Override
   protected void defineFactoryClass() {
      factoryClass = ProtocolFactory.class;
   }

   @Override
   protected void defineRepositoryClass() {
      repositoryClass = ProtocolRepository.class;
   }

   @Override
   protected void defineRootClass() {
      rootClass = Protocol.class;
   }

   @Override
   protected void defineManagedEntities() {
      managedEntities.add(ActivityProtocol.class);
      managedEntities.add(ActionProtocol.class);
      managedEntities.add(CompositeProtocol.class);
      managedEntities.add(LifecycleProtocol.class);
      managedEntities.add(WorkflowDetailProtocol.class);
      managedEntities.add(Discipline.class);
      managedEntities.add(LifecyclePhaseProtocol.class);
      managedEntities.add(ProtocolCallAction.class);
   }

   @Override
   protected void defineManagedValueObjects() {
      managedValueObjects.add(SimpleProtocolDependency.class);
      managedValueObjects.add(ProtocolDependency.class);
   }

   @Override
   protected void defineReferencedNeighbourRoots() {
      referencedNeighbourRoots.add(ArtifactType.class);
      referencedNeighbourRoots.add(ActionReference.class);
   }

   @Override
   protected void defineAnyOtherRelatedClasses() {
      // TODO Auto-generated method stub
      
   }

}
