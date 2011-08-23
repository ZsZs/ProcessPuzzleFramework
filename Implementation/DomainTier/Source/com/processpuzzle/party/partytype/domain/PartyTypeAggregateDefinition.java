package com.processpuzzle.party.partytype.domain;

import com.processpuzzle.fundamental_types.domain.AggregateDefinition;
import com.processpuzzle.party.domain.PartyFactory;
import com.processpuzzle.party.domain.PartyRepository;
import com.processpuzzle.resource.resourcetype.domain.AssetType;

public class PartyTypeAggregateDefinition extends AggregateDefinition {

   public PartyTypeAggregateDefinition() {
      super();
   }

   @Override
   protected void defineFactoryClass() {
      factoryClass = PartyFactory.class;
   }

   @Override
   protected void defineManagedEntities() {
      managedEntities.add(Role.class);
      managedEntities.add(AssetType.class);
   }

   @Override
   protected void defineManagedValueObjects() {
   }

   @Override
   protected void defineReferencedNeighbourRoots() {
   }

   @Override
   protected void defineRepositoryClass() {
      repositoryClass = PartyRepository.class;
   }

   @Override
   protected void defineRootClass() {
      rootClass = PartyType.class;

   }

@Override
protected void defineAnyOtherRelatedClasses() {
	// TODO Auto-generated method stub
	
}

}
