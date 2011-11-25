/*
Name: 
    - ResourceTypeRepository 

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

package com.processpuzzle.resource.resourcetype.domain;

import hu.itkodex.commons.persistence.PersistenceStrategy;
import hu.itkodex.commons.persistence.RepositoryResultSet;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.artifact_type.domain.DocumentType;
import com.processpuzzle.inventory.domain.HoldingType;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.domain.GenericRepository;

public class ResourceTypeRepository extends GenericRepository<ResourceType> {

   public ResourceTypeRepository( PersistenceStrategy strategy, ProcessPuzzleContext applicationContext ) {
      super( strategy, applicationContext );
   }

   // ResourceType
   public void addResourceType( DefaultUnitOfWork work, ResourceType theResourceType ) {
      add( work, theResourceType );
   }

   public void deleteResourceType( DefaultUnitOfWork work, ResourceType theResourceType ) {
      delete( work, theResourceType );
   }

   public void updateResourceType( DefaultUnitOfWork work, ResourceType theResourceType ) {
      update( work, theResourceType );
   }

   public ResourceType findResourceTypeById( DefaultUnitOfWork work, String id ) {
      return (ResourceType) findById( work, new Integer( id ) );
   }

   public RepositoryResultSet<ResourceType> findAllResourceType( DefaultUnitOfWork work ) {
      return findAll( work );
   }

   // ConsumableResourceType
   public void addConsumableResourceType( DefaultUnitOfWork work, ConsumableResourceType theConsumableResourceType ) {
      add( work, ConsumableResourceType.class, theConsumableResourceType );
   }

   public void deleteConsumableResourceType( DefaultUnitOfWork work, ConsumableResourceType theConsumableResourceType ) {
      delete( work, ConsumableResourceType.class, theConsumableResourceType );
   }

   public void updateConsumableResourceType( DefaultUnitOfWork work, ConsumableResourceType theConsumableResourceType ) {
      update( work, ConsumableResourceType.class, theConsumableResourceType );
   }

   public ConsumableResourceType findConsumableResourceTypeById( DefaultUnitOfWork work, String id ) {
      return (ConsumableResourceType) findById( work, ConsumableResourceType.class, new Integer( id ) );
   }

   public RepositoryResultSet<ResourceType> findAllConsumableResourceType( DefaultUnitOfWork work ) {
      return findAll( work, ConsumableResourceType.class );
   }

   // HoldingType
   public void addHoldingType( DefaultUnitOfWork work, HoldingType theHoldingType ) {
      super.add( work, HoldingType.class, theHoldingType );
   }

   public void deleteHoldingType( DefaultUnitOfWork work, HoldingType theHoldingType ) {
      delete( work, HoldingType.class, theHoldingType );
   }

   public void updateHoldingType( DefaultUnitOfWork work, HoldingType theHoldingType ) {
      update( work, HoldingType.class, theHoldingType );
   }

   public HoldingType findHoldingTypeById( DefaultUnitOfWork work, String id ) {
      return (HoldingType) findById( work, HoldingType.class, new Integer( id ) );
   }

   public RepositoryResultSet<ResourceType> findAllHoldingType( DefaultUnitOfWork work ) {
      return findAll( work, HoldingType.class );
   }

   // AssetType
   public void addAssetType( DefaultUnitOfWork work, AssetType theAssetType ) {
      add( work, AssetType.class, theAssetType );
   }

   public void deleteAssetType( DefaultUnitOfWork work, AssetType theAssetType ) {
      delete( work, AssetType.class, theAssetType );
   }

   public void updateAssetType( DefaultUnitOfWork work, AssetType theAssetType ) {
      update( work, AssetType.class, theAssetType );
   }

   public AssetType findAssetTypeById( DefaultUnitOfWork work, String id ) {
      return (AssetType) findById( work, AssetType.class, new Integer( id ) );
   }

   public RepositoryResultSet<ResourceType> findAllAssetType( DefaultUnitOfWork work ) {
      return findAll( work, AssetType.class );
   }

   // ArtifactType
   public void addArtifactType( DefaultUnitOfWork work, ArtifactType theArtifactType ) {
      add( work, ArtifactType.class, theArtifactType );
   }

   public void deleteArtifactType( DefaultUnitOfWork work, ArtifactType theArtifactType ) {
      delete( work, ArtifactType.class, theArtifactType );
   }

   public void updateArtifactType( DefaultUnitOfWork work, ArtifactType theArtifactType ) {
      update( work, ArtifactType.class, theArtifactType );
   }

   public ArtifactType findArtifactTypeById( DefaultUnitOfWork work, String id ) {
      return (ArtifactType) findById( work, ArtifactType.class, new Integer( id ) );
   }

   public RepositoryResultSet<ResourceType> findAllArtifactType( DefaultUnitOfWork work ) {
      return findAll( work, ArtifactType.class );
   }

   // DocumentType
   public void addDocumentType( DefaultUnitOfWork work, DocumentType theDocumentType ) {
      add( work, DocumentType.class, theDocumentType );
   }

   public void deleteDocumentType( DefaultUnitOfWork work, DocumentType theDocumentType ) {
      delete( work, DocumentType.class, theDocumentType );
   }

   public void updateDocumentType( DefaultUnitOfWork work, DocumentType theDocumentType ) {
      update( work, DocumentType.class, theDocumentType );
   }

   public DocumentType findDocumentTypeById( DefaultUnitOfWork work, String id ) {
      return (DocumentType) findById( work, DocumentType.class, new Integer( id ) );
   }

   public RepositoryResultSet<ResourceType> findAllDocumentType( DefaultUnitOfWork work ) {
      return findAll( work, DocumentType.class );
   }

}