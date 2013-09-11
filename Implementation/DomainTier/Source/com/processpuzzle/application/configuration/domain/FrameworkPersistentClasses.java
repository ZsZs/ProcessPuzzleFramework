/*
Name: 
    - FrameworkPersistentClasses

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

package com.processpuzzle.application.configuration.domain;

import com.processpuzzle.address.artifact.SettlementDataSheet;
import com.processpuzzle.address.domain.Country;
import com.processpuzzle.address.domain.District;
import com.processpuzzle.address.domain.Settlement;
import com.processpuzzle.address.domain.ZipCode;
import com.processpuzzle.application.domain.ApplicationEvent;
import com.processpuzzle.application.domain.BackupEvent;
import com.processpuzzle.application.security.domain.AccessRight;
import com.processpuzzle.application.security.domain.DefaultAccessRight;
import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.artifact.artifact.ArtifactList;
import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.artifact.domain.ArtifactFolder;
import com.processpuzzle.artifact_type.domain.ArtifactMenu;
import com.processpuzzle.artifact_type.domain.ArtifactMenuCommand;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;
import com.processpuzzle.artifact_type.domain.PropertyDefinition;
import com.processpuzzle.artifact_type_group.domain.ArtifactTypeGroup;
import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.fundamental_types.uniqueidentifier.domain.LastIdNumber;
import com.processpuzzle.party.artifact.UserDataSheet;
import com.processpuzzle.party.artifact.UserList;
import com.processpuzzle.party.domain.Address;
import com.processpuzzle.party.domain.Party;
import com.processpuzzle.party.domain.PartyName;
import com.processpuzzle.party.domain.PartyRelationship;
import com.processpuzzle.party.domain.PartyRole;
import com.processpuzzle.party.domain.PartySummary;
import com.processpuzzle.party.domain.Person;
import com.processpuzzle.party.domain.RuleSet;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRelationshipConstraint;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRelationshipType;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRoleConstraint;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRoleType;
import com.processpuzzle.party.partytype.domain.PartyType;
import com.processpuzzle.persistence.domain.PersistentClassList;
import com.processpuzzle.persistence.query.domain.DefaultQuery;
import com.processpuzzle.util.domain.AutoIdentifier;
import com.processpuzzle.util.domain.HtmlAttributeFormat;

public class FrameworkPersistentClasses extends PersistentClassList {

   public FrameworkPersistentClasses() {
      super();
   }

   @Override
   protected void defineAggregateRoots() {
      
      //Artifact layer roots
      //aggregateRoots.add( ActionList.class );
     // aggregateRoots.add( ActionDataSheet.class );
      aggregateRoots.add( Artifact.class );
      aggregateRoots.add( ArtifactFolder.class );
      aggregateRoots.add( ArtifactList.class );
      //aggregateRoots.add( CommentList.class );
      //aggregateRoots.add( CompanyList.class );
      //aggregateRoots.add( CompanyDataSheet.class );
      //aggregateRoots.add( Document.class );
      //aggregateRoots.add( FileStorage.class );
      //aggregateRoots.add( ImageFile.class );
      //aggregateRoots.add( PartyDataSheet.class );
      //aggregateRoots.add( PersonalTodoList.class );
      //aggregateRoots.add( PersonList.class );
      //aggregateRoots.add( PersonDataSheet.class );
      //aggregateRoots.add( RootArtifactFolder.class );
      //aggregateRoots.add( SettlementList.class );
      aggregateRoots.add( SettlementDataSheet.class );
      aggregateRoots.add( UserList.class );
      aggregateRoots.add( UserDataSheet.class );

      
      //Domain layer roots
      aggregateRoots.add( ApplicationEvent.class );
      aggregateRoots.add( ArtifactType.class );
      aggregateRoots.add( ArtifactTypeGroup.class );
      aggregateRoots.add( AutoIdentifier.class );
      //aggregateRoots.add( BusinessObject.class );
      //aggregateRoots.add( Company.class );
      aggregateRoots.add( Country.class );
//      aggregateRoots.add( GenericAction.class );
//      aggregateRoots.add( HoldingType.class );
      aggregateRoots.add( LastIdNumber.class );
//      aggregateRoots.add( Organization.class );
//      aggregateRoots.add( OrganizationUnit.class );
      aggregateRoots.add( Party.class );
      aggregateRoots.add( PartyRelationshipType.class );
      aggregateRoots.add( PartyType.class );
      aggregateRoots.add( Person.class );
//      aggregateRoots.add( Plan.class );
//      aggregateRoots.add( Protocol.class );
//      aggregateRoots.add( Project.class );
      aggregateRoots.add( RuleSet.class );
   }

   @Override
   protected void defineEntities() {
      
//      entities.add( AbandonedAction.class );
      entities.add( AccessRight.class );
//      entities.add( Action.class );
//      entities.add( ActionDependency.class );
//      entities.add( ActionEvent.class );
//      entities.add( ActionReference.class );
//      entities.add( ActionStatus.class );
//      entities.add( Activity.class );
//      entities.add( ActivityProtocol.class );
      entities.add( Address.class );
//      entities.add( Affiliation.class );
      entities.add( ArtifactMenu.class );
      entities.add( ArtifactMenuCommand.class );
//      entities.add( ArtifactVersion.class );
      entities.add( ArtifactViewType.class );
      entities.add( BackupEvent.class );
//      entities.add( Comment.class );
//      entities.add( CompletedAction.class );
//      entities.add( CompositeProtocol.class );
      entities.add( DefaultAccessRight.class );
//      entities.add( Discipline.class );
      entities.add( District.class );
//      entities.add( EmailAddress.class );
//      entities.add( GeneralResourceAllocation.class );
      entities.add( GenericEntity.class );
//      entities.add( GeographicAddress.class );
//      entities.add( Group.class );
//      entities.add( Holding.class );
      entities.add( HtmlAttributeFormat.class );
//      entities.add( HTMLText.class );
//      entities.add( ImplementedAction.class );
//      entities.add( InventoryEntry.class );
//      entities.add( LifecyclePhaseProtocol.class );
//      entities.add( LifecycleProtocol.class );
//      entities.add( Location.class );
//      entities.add( Modification.class );
//      entities.add( OrganizationName.class );
      entities.add( PartyName.class );
      entities.add( PartyRelationship.class );
      entities.add( PartyRelationshipConstraint.class );
      entities.add( PartyRoleType.class );
      entities.add( PartySummary.class );
//      entities.add( PersonGroup.class );
//      entities.add( PersonName.class );
//      entities.add( ProcessPlan.class );
      entities.add( PropertyDefinition.class );
//      entities.add( ProposedAction.class );
//      entities.add( ProtocolDependency.class );
//      entities.add( ProtocolCallAction.class );
//      entities.add( ResourceAllocation.class );
      entities.add( Settlement.class );
//      entities.add( SimpleActionDependency.class );
//      entities.add( SimpleProtocolDependency.class );
//      entities.add( Suspension.class );
//      entities.add( TelecomAddress.class );
//      entities.add( TemporalResourceAllocation.class );
//      entities.add( Transfer.class );
      entities.add( User.class );
//      entities.add( WebPageAddress.class );
//      entities.add( WorkflowDetailProtocol.class );
      entities.add( ZipCode.class );
   }

   @Override
   protected void defineValueObjects() {
      valueObjects.add( PartyRole.class );
      valueObjects.add( PartyRoleConstraint.class );
      valueObjects.add( DefaultQuery.class );
   }
}
