package com.processpuzzle.application.configuration.domain;

import com.processpuzzle.address.artifact.SettlementDataSheet;
import com.processpuzzle.address.artifact.SettlementList;
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
import com.processpuzzle.artifact.domain.ArtifactVersion;
import com.processpuzzle.artifact.domain.Comment;
import com.processpuzzle.artifact.domain.CommentList;
import com.processpuzzle.artifact.domain.Document;
import com.processpuzzle.artifact.domain.FileStorage;
import com.processpuzzle.artifact.domain.HTMLText;
import com.processpuzzle.artifact.domain.ImageFile;
import com.processpuzzle.artifact.domain.Modification;
import com.processpuzzle.artifact.domain.RootArtifactFolder;
import com.processpuzzle.artifact_type.domain.ArtifactMenu;
import com.processpuzzle.artifact_type.domain.ArtifactMenuCommand;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;
import com.processpuzzle.artifact_type.domain.PropertyDefinition;
import com.processpuzzle.artifact_type_group.domain.ArtifactTypeGroup;
import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.fundamental_types.uniqueidentifier.domain.LastIdNumber;
import com.processpuzzle.inventory.domain.Holding;
import com.processpuzzle.inventory.domain.HoldingType;
import com.processpuzzle.inventory.domain.InventoryEntry;
import com.processpuzzle.inventory.domain.Location;
import com.processpuzzle.inventory.domain.Transfer;
import com.processpuzzle.party.artifact.CompanyDataSheet;
import com.processpuzzle.party.artifact.CompanyList;
import com.processpuzzle.party.artifact.PartyDataSheet;
import com.processpuzzle.party.artifact.PersonDataSheet;
import com.processpuzzle.party.artifact.PersonList;
import com.processpuzzle.party.artifact.UserDataSheet;
import com.processpuzzle.party.artifact.UserList;
import com.processpuzzle.party.domain.Address;
import com.processpuzzle.party.domain.Affiliation;
import com.processpuzzle.party.domain.Company;
import com.processpuzzle.party.domain.EmailAddress;
import com.processpuzzle.party.domain.GeographicAddress;
import com.processpuzzle.party.domain.Group;
import com.processpuzzle.party.domain.Organization;
import com.processpuzzle.party.domain.OrganizationName;
import com.processpuzzle.party.domain.OrganizationUnit;
import com.processpuzzle.party.domain.Party;
import com.processpuzzle.party.domain.PartyName;
import com.processpuzzle.party.domain.PartyRelationship;
import com.processpuzzle.party.domain.PartyRole;
import com.processpuzzle.party.domain.PartySummary;
import com.processpuzzle.party.domain.Person;
import com.processpuzzle.party.domain.PersonGroup;
import com.processpuzzle.party.domain.PersonName;
import com.processpuzzle.party.domain.Project;
import com.processpuzzle.party.domain.RuleSet;
import com.processpuzzle.party.domain.TelecomAddress;
import com.processpuzzle.party.domain.WebPageAddress;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRelationshipConstraint;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRelationshipType;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRoleConstraint;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRoleType;
import com.processpuzzle.party.partytype.domain.PartyType;
import com.processpuzzle.persistence.domain.PersistentClassList;
import com.processpuzzle.persistence.query.domain.DefaultQuery;
import com.processpuzzle.util.domain.AutoIdentifier;
import com.processpuzzle.util.domain.HtmlAttributeFormat;
import com.processpuzzle.workflow.activity.artifact.ActionDataSheet;
import com.processpuzzle.workflow.activity.artifact.ActionList;
import com.processpuzzle.workflow.activity.artifact.PersonalTodoList;
import com.processpuzzle.workflow.activity.domain.AbandonedAction;
import com.processpuzzle.workflow.activity.domain.Action;
import com.processpuzzle.workflow.activity.domain.ActionDependency;
import com.processpuzzle.workflow.activity.domain.ActionEvent;
import com.processpuzzle.workflow.activity.domain.ActionReference;
import com.processpuzzle.workflow.activity.domain.ActionStatus;
import com.processpuzzle.workflow.activity.domain.Activity;
import com.processpuzzle.workflow.activity.domain.CompletedAction;
import com.processpuzzle.workflow.activity.domain.GeneralResourceAllocation;
import com.processpuzzle.workflow.activity.domain.GenericAction;
import com.processpuzzle.workflow.activity.domain.ImplementedAction;
import com.processpuzzle.workflow.activity.domain.Plan;
import com.processpuzzle.workflow.activity.domain.ProcessPlan;
import com.processpuzzle.workflow.activity.domain.ProposedAction;
import com.processpuzzle.workflow.activity.domain.ResourceAllocation;
import com.processpuzzle.workflow.activity.domain.SimpleActionDependency;
import com.processpuzzle.workflow.activity.domain.Suspension;
import com.processpuzzle.workflow.activity.domain.TemporalResourceAllocation;
import com.processpuzzle.workflow.protocol.domain.ActivityProtocol;
import com.processpuzzle.workflow.protocol.domain.CompositeProtocol;
import com.processpuzzle.workflow.protocol.domain.Discipline;
import com.processpuzzle.workflow.protocol.domain.LifecyclePhaseProtocol;
import com.processpuzzle.workflow.protocol.domain.LifecycleProtocol;
import com.processpuzzle.workflow.protocol.domain.Protocol;
import com.processpuzzle.workflow.protocol.domain.ProtocolCallAction;
import com.processpuzzle.workflow.protocol.domain.ProtocolDependency;
import com.processpuzzle.workflow.protocol.domain.SimpleProtocolDependency;
import com.processpuzzle.workflow.protocol.domain.WorkflowDetailProtocol;

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
      //aggregateRoots.add( SettlementDataSheet.class );
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
//      aggregateRoots.add( Party.class );
//      aggregateRoots.add( PartyRelationshipType.class );
//      aggregateRoots.add( PartyType.class );
//      aggregateRoots.add( Person.class );
//      aggregateRoots.add( Plan.class );
//      aggregateRoots.add( Protocol.class );
//      aggregateRoots.add( Project.class );
//      aggregateRoots.add( RuleSet.class );
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
//      entities.add( Address.class );
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
//      entities.add( PartyName.class );
//      entities.add( PartyRelationship.class );
//      entities.add( PartyRelationshipConstraint.class );
//      entities.add( PartyRoleType.class );
//      entities.add( PartySummary.class );
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
//      valueObjects.add( PartyRole.class );
//      valueObjects.add( PartyRoleConstraint.class );
      valueObjects.add( DefaultQuery.class );
   }
}
