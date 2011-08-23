package com.processpuzzle.business.definition.domain;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.exolab.castor.mapping.MappingException;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.ValidationException;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
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

   @SuppressWarnings("unused")
   private BusinessDefinition unmarshalBusinessDefinition_Castor( String businessDefinitionPath, String mappingPath ) {
      BusinessDefinition businessDefinition = null;
      
      Resource mappingResource = resourceLoader.getResource( mappingPath );
      org.exolab.castor.mapping.Mapping mapping = new org.exolab.castor.mapping.Mapping();
      org.exolab.castor.xml.Unmarshaller unMarshaller = new org.exolab.castor.xml.Unmarshaller( BusinessDefinition.class );

      Resource businessDefinitionResource = resourceLoader.getResource( businessDefinitionPath );
      try{
         mapping.loadMapping( mappingResource.getURL() );
         unMarshaller.setMapping( mapping );
         FileReader fileReader = new FileReader( businessDefinitionResource.getFile() );
         businessDefinition = (BusinessDefinition) unMarshaller.unmarshal( fileReader );
      }catch( FileNotFoundException e ){
         throw new BusinessDataLoaderException( businessDefinitionPath, e );
      }catch( IOException e ){
         throw new BusinessDataLoaderException( businessDefinitionPath, e );
      }catch( MarshalException e ){
         throw new BusinessDataLoaderException( businessDefinitionPath, e );
      }catch( ValidationException e ){
         throw new BusinessDataLoaderException( businessDefinitionPath, e );
      }catch( MappingException e ){
         throw new BusinessDataLoaderException( businessDefinitionPath, e );
      }
      return businessDefinition;
   }
}
