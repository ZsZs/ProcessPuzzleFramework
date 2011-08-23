package com.processpuzzle.artifact_type.domain;

import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.artifact_type.domain.ArtifactTypeFactory;
import com.processpuzzle.artifact_type.domain.ArtifactTypeRepository;

public class ArtifactTypeFixture {
   private static final String IMAGEFILE_TYPE_NAME = "ImageFileType";
   private static final String IMAGEFILE_GROUP = "SystemAdministration";
   private static final String IMAGEFILE_CLASSNAME = "com.processpuzzle.artifact.domain.ImageFile";

   private static final String DOCUMENT_TYPE_NAME = "DocumentType";
   private static final String DOCUMENT_GROUP = "SystemAdministration";
   private static final String DOCUMENT_CLASSNAME = "com.processpuzzle.artifact.domain.Document";

   private static ArtifactTypeFixture soleInstance;
   //private static boolean makePersistent = false;
   
   private ArtifactTypeRepository artifactTypeRepository;
   private ArtifactTypeFactory artifactTypeFactory;

   public static ArtifactTypeFixture getIstance( ProcessPuzzleContext processPuzzleContext ) {
      if( soleInstance == null ){
         soleInstance = new ArtifactTypeFixture( processPuzzleContext );
      }
      return soleInstance;
   }

   public void setUpImageFileArtifactType() {
      setUpArtifactType( IMAGEFILE_TYPE_NAME, IMAGEFILE_GROUP, IMAGEFILE_CLASSNAME );
   }

   public void setUpDocumentArtifactType() {
      setUpArtifactType( DOCUMENT_TYPE_NAME, DOCUMENT_GROUP, DOCUMENT_CLASSNAME );
   }

   public void tearDownImageFileArtifactType() {
      tearDownArtifactType( IMAGEFILE_TYPE_NAME );
   }

   public void tearDownDocumentArtifactType() {
      tearDownArtifactType( DOCUMENT_TYPE_NAME );
   }

   private ArtifactTypeFixture( ProcessPuzzleContext processPuzzleContext ) {
      artifactTypeFactory = processPuzzleContext.getEntityFactory( ArtifactTypeFactory.class );
      artifactTypeRepository = processPuzzleContext.getRepository( ArtifactTypeRepository.class );
   }

   private void setUpArtifactType( String artifactTypeName, String artifactGroupName, String artifactClassName ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      ArtifactType artifactType = artifactTypeRepository.findArtifactTypeByName( work, artifactTypeName );

      if( artifactType == null ){
         artifactType = artifactTypeFactory.createArtifactType( artifactTypeName, artifactGroupName );
         artifactType.setArtifactClassName( artifactClassName );
         artifactTypeRepository.addArtifactType( work, artifactType );
      }
      work.finish();
   }

   private void tearDownArtifactType( String artifactTypeName ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      ArtifactType artifactType = artifactTypeRepository.findArtifactTypeByName( work, artifactTypeName );

      if( artifactType != null ){
         artifactTypeRepository.deleteArtifactType( work, artifactType );
      }
      work.finish();
   }
}
