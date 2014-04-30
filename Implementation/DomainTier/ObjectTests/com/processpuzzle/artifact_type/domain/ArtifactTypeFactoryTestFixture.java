package com.processpuzzle.artifact_type.domain;

import com.processpuzzle.litest.template.FactoryTestEnvironment;
import com.processpuzzle.litest.template.FactoryTestFixture;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;

public class ArtifactTypeFactoryTestFixture extends FactoryTestFixture<ArtifactTypeFactory, ArtifactType>{
   public static final String IMAGEFILE_TYPE_NAME = "ImageFileType";
   public static final String IMAGEFILE_GROUP = "SystemAdministration";
   public static final String IMAGEFILE_CLASSNAME = "com.processpuzzle.artifact.domain.ImageFile";
   public static final String DOCUMENT_TYPE_NAME = "DocumentType";
   public static final String DOCUMENT_GROUP = "SystemAdministration";
   public static final String DOCUMENT_CLASSNAME = "com.processpuzzle.artifact.domain.Document";
   private ArtifactType artifactType;
   private ArtifactTypeFactory artifactTypeFactory;
   private ArtifactTypeRepository artifactTypeRepository;

   //Constructors
   public ArtifactTypeFactoryTestFixture( FactoryTestEnvironment<ArtifactTypeFactory, ?> testEnvironment ) {
      super( testEnvironment );
   }

   //Public accessors and mutators
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

   //Protected, private helper methods
   @Override
   protected void configureAfterSutInstantiation() {
      artifactTypeFactory = applicationContext.getEntityFactory( ArtifactTypeFactory.class );
      artifactTypeRepository = applicationContext.getRepository( ArtifactTypeRepository.class );
   }
   
   private void setUpArtifactType( String artifactTypeName, String artifactGroupName, String artifactClassName ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      artifactType = artifactTypeRepository.findArtifactTypeByName( work, artifactTypeName );

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
