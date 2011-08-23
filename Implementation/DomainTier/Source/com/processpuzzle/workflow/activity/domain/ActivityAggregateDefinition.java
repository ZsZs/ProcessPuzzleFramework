package com.processpuzzle.workflow.activity.domain;

import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.fundamental_types.domain.AggregateDefinition;
import com.processpuzzle.party.domain.Address;
import com.processpuzzle.party.domain.Affiliation;
import com.processpuzzle.party.domain.Organization;
import com.processpuzzle.party.domain.OrganizationUnit;
import com.processpuzzle.party.domain.Party;
import com.processpuzzle.party.domain.PartyName;
import com.processpuzzle.party.domain.PartyRelationship;
import com.processpuzzle.party.domain.PartyRole;
import com.processpuzzle.party.domain.Project;
import com.processpuzzle.party.domain.WorkflowTeam;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRoleConstraint;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRoleType;
import com.processpuzzle.resource.domain.Asset;

public class ActivityAggregateDefinition extends AggregateDefinition {

   @Override
   protected void defineFactoryClass() {
      factoryClass = ActionFactory.class;
   }

   @Override
   protected void defineRepositoryClass() {
      repositoryClass = WorkflowRepository.class;
   }
      
   @Override
   protected void defineRootClass() {
      rootClass = Plan.class;      
   }

   @Override
   protected void defineManagedEntities() {
      managedEntities.add( Action.class );
      managedEntities.add( Activity.class );
      managedEntities.add( ProcessPlan.class );
      managedEntities.add( GenericAction.class );
      managedEntities.add( ProposedAction.class );
      managedEntities.add( ImplementedAction.class );
      managedEntities.add( AbandonedAction.class );
   }

   @Override
   protected void defineManagedValueObjects() {
//      managedValueObjects.add( Quantity.class );
      managedEntities.add( ActionReference.class );
      managedEntities.add( ActionDependency.class );
      managedEntities.add( SimpleActionDependency.class );
      managedEntities.add( ActionStatus.class );
   }

   @Override
   protected void defineReferencedNeighbourRoots() {
      referencedNeighbourRoots.add( Artifact.class );
      referencedNeighbourRoots.add( WorkflowTeam.class );
      referencedNeighbourRoots.add( Project.class );
//      referencedNeighbourRoots.add( Protocol.class );
   }

   @Override
   protected void defineAnyOtherRelatedClasses() {
      anyOtherRelatedClasses.add( Asset.class );
      anyOtherRelatedClasses.add( Party.class );
      anyOtherRelatedClasses.add( PartyRole.class );
      anyOtherRelatedClasses.add( PartyRoleType.class );
      anyOtherRelatedClasses.add( PartyRoleConstraint.class );
      anyOtherRelatedClasses.add( PartyRelationship.class );
      anyOtherRelatedClasses.add( PartyName.class );
      anyOtherRelatedClasses.add( Organization.class );
      anyOtherRelatedClasses.add( OrganizationUnit.class );
      anyOtherRelatedClasses.add( Affiliation.class );
      anyOtherRelatedClasses.add( Address.class );
   }
}
