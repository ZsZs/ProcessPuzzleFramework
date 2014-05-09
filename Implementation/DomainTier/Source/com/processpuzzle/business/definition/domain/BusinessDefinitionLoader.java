/*
Name: 
    - BusinessDefinitionLoader

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

package com.processpuzzle.business.definition.domain;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import com.processpuzzle.application.configuration.domain.PropertyKeys;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.artifact_type.domain.ArtifactTypeRepository;
import com.processpuzzle.artifact_type_group.domain.ArtifactTypeGroup;
import com.processpuzzle.artifact_type_group.domain.ArtifactTypeGroupRepository;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRelationshipType;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRelationshipTypeRepository;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRoleType;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRoleTypeRepository;
import com.processpuzzle.party.partytype.domain.PartyType;
import com.processpuzzle.party.partytype.domain.PartyTypeRepository;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.workflow.protocol.domain.LifecycleProtocol;
import com.processpuzzle.workflow.protocol.domain.ProtocolRepository;

public class BusinessDefinitionLoader extends BusinessDataLoader<BusinessDefinition> {
   public static final String WORKLOWS_FOLDER_NAME = "Workflows";
   public static final String ROLES_FOLDER_NAME = "Roles";
   public static final String WORKPRODUCTS_FOLDER_NAME = "WorkProducts";
   public static final String CONTENTS_FOLDER_NAME = "Content"; 

   public BusinessDefinitionLoader( String businessDefinition ) {
      this( businessDefinition, new DefaultResourceLoader() );
   }

   public BusinessDefinitionLoader( String businessDefinition, ResourceLoader resourceLoader ) {
      super( resourceLoader, businessDefinition );
      mappingKey = PropertyKeys.BUSINESS_DEFINITION_MAPPING.getDefaultKey();
      schemaKey = PropertyKeys.BUSINESS_DEFINITION_SCHEMA.getDefaultKey();
   }

   // Protected, private methods
   @Override protected void saveObjects( DefaultUnitOfWork work ) {
      savePartyTypes( work, unmarshalledData );      
      savePartyRoleTypes( work, unmarshalledData );
      savePartyRelationshipTypes( work, unmarshalledData );
      saveArtifactTypeGroups( work, unmarshalledData );
      saveArtifactTypes( work, unmarshalledData );
      saveProtocols( work, unmarshalledData );
   }

   private void saveArtifactTypeGroups( DefaultUnitOfWork work, BusinessDefinition businessDefinition ) {
      ArtifactTypeGroupRepository groupRepository = applicationContext.getRepository( ArtifactTypeGroupRepository.class );
      if( businessDefinition.artifactTypeGroups != null ){
         for( ArtifactTypeGroup anArtifactTypeGroup : businessDefinition.artifactTypeGroups ){
            groupRepository.addArtifactTypeGroup( work, anArtifactTypeGroup );
            log.trace( "Artifact type group: '" + anArtifactTypeGroup.getName() + "' was created and saved." );
         }
      }
   }

   private void saveArtifactTypes( DefaultUnitOfWork work, BusinessDefinition businessDefinition ) {
      ArtifactTypeRepository artifactTypeRepository = applicationContext.getRepository( ArtifactTypeRepository.class );
      if( businessDefinition.artifactTypeGroups != null ){
         for( ArtifactTypeGroup anArtifactTypeGroup : businessDefinition.artifactTypeGroups ){
            for( ArtifactType anArtifactType : anArtifactTypeGroup.getArtifactTypes() ) {
               artifactTypeRepository.add( work, anArtifactType );
               log.trace( "Artifact type: '" + anArtifactType.getName() + "' was created and saved." );
            }
         }
      }
   }

   private void savePartyRelationshipTypes( DefaultUnitOfWork work, BusinessDefinition businessDefinition ) {
      PartyRelationshipTypeRepository partyRelationshipTypeRepository = applicationContext.getRepository( PartyRelationshipTypeRepository.class );
      if( businessDefinition.partyRelationshipTypes != null ){
         for( PartyRelationshipType relationshipType : businessDefinition.partyRelationshipTypes ){
            partyRelationshipTypeRepository.add( work, relationshipType );
            log.trace( "Party relationship type: '" + relationshipType.getName() + "' was created and saved." );
         }
      }
   }

   private void savePartyRoleTypes( DefaultUnitOfWork work, BusinessDefinition businessDefinition ) {
      PartyRoleTypeRepository partyRoleTypeRepository = applicationContext.getRepository( PartyRoleTypeRepository.class );
      if( businessDefinition.partyRoleTypes != null ){
         for( PartyRoleType partyRoleType : businessDefinition.partyRoleTypes ){
            partyRoleTypeRepository.addPartyRoleType( work, partyRoleType );
            log.trace( "Party role type: '" + partyRoleType.getName() + "' was created and saved." );
         }
      }
   }

   private void savePartyTypes( DefaultUnitOfWork work, BusinessDefinition businessDefinition ) {
      PartyTypeRepository partyTypeRepository = applicationContext.getRepository( PartyTypeRepository.class );
      if( businessDefinition.partyTypes.size() > 0 ) {
         for( PartyType partyType : businessDefinition.partyTypes ) {
            partyTypeRepository.addPartyType( work, partyType );
            log.trace( "Party type: '" + partyType.getName() + "' was created and saved." );
         }
      }
   }

   private void saveProtocols( DefaultUnitOfWork work, BusinessDefinition businessDefinition ) {
      ProtocolRepository protocolRepository = applicationContext.getRepository( ProtocolRepository.class );
      if( businessDefinition.protocols != null ){
         for( LifecycleProtocol lifeCycleProtocol : businessDefinition.protocols.lifecycles ){
            protocolRepository.addLifecycle( work, lifeCycleProtocol );
            log.trace( "Protocol: '" + lifeCycleProtocol.getName() + "' was created and saved." );
         }
      }
   }
}
