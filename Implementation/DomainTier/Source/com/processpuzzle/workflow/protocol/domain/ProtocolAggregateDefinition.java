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
