package com.processpuzzle.artifact_type.domain;

import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.artifact.domain.Document;
import com.processpuzzle.artifact.domain.ImageFile;
import com.processpuzzle.artifact_type_group.domain.ArtifactTypeGroup;
import com.processpuzzle.artifact_type_group.domain.ArtifactTypeGroupFactory;
import com.processpuzzle.artifact_type_group.domain.ArtifactTypeGroupRepository;
import com.processpuzzle.litest.template.FactoryTestEnvironment;
import com.processpuzzle.litest.template.FactoryTestFixture;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;

public class ArtifactTypeFactoryTestFixture extends FactoryTestFixture<ArtifactTypeFactory, ArtifactType>{
   public static final String IMAGEFILE_TYPE_NAME = "ImageFileType";
   public static final String IMAGEFILE_GROUP = "TestImageFiles";
   public static final String IMAGEFILE_CLASSNAME = "com.processpuzzle.artifact.domain.ImageFile";
   public static final String DOCUMENT_TYPE_NAME = "DocumentType";
   public static final String DOCUMENT_GROUP = "SystemAdministration";
   public static final String DOCUMENT_CLASSNAME = "com.processpuzzle.artifact.domain.Document";
   private ArtifactType artifactType;
   private ArtifactTypeFactory artifactTypeFactory;
   private ArtifactTypeGroupFactory artifactTypeGroupFactory;
   private ArtifactTypeGroupRepository artifactTypeGroupRepository;
   private ArtifactTypeRepository artifactTypeRepository;

   //Constructors
   public ArtifactTypeFactoryTestFixture( FactoryTestEnvironment<ArtifactTypeFactory, ?> testEnvironment ) {
      super( testEnvironment );
   }

   //Public accessors and mutators
   public void setUpImageFileArtifactType() {
      setUpArtifactType( IMAGEFILE_TYPE_NAME, IMAGEFILE_GROUP, ImageFile.class );
   }

   public void setUpDocumentArtifactType() {
      setUpArtifactType( DOCUMENT_TYPE_NAME, DOCUMENT_GROUP, Document.class );
   }

   public void tearDownImageFileArtifactType() {
      tearDownArtifactType( IMAGEFILE_TYPE_NAME );
   }

   public void tearDownDocumentArtifactType() {
      tearDownArtifactType( DOCUMENT_TYPE_NAME );
   }
   
   //Properties
   public ArtifactTypeGroup getImageTypeGroup(){ return artifactTypeGroupRepository.findByName( IMAGEFILE_GROUP ); }

   //Protected, private helper methods
   @Override
   protected void configureAfterSutInstantiation() {
      artifactTypeFactory = applicationContext.getEntityFactory( ArtifactTypeFactory.class );
      artifactTypeGroupFactory = applicationContext.getEntityFactory( ArtifactTypeGroupFactory.class );
      artifactTypeGroupRepository = applicationContext.getRepository( ArtifactTypeGroupRepository.class );
      artifactTypeRepository = applicationContext.getRepository( ArtifactTypeRepository.class );
      
      setUpArtifactTypeGroup( IMAGEFILE_GROUP );
   }
   
   private void setUpArtifactType( String artifactTypeName, String artifactGroupName, Class<? extends Artifact<?>> artifactClass ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      
      artifactType = artifactTypeRepository.findArtifactTypeByName( work, artifactTypeName );

      if( artifactType == null ){
         artifactType = artifactTypeFactory.create( artifactTypeName, artifactGroupName, artifactClass );
         artifactTypeRepository.add( artifactType );
      }
      
      work.finish();
   }

   private ArtifactTypeGroup setUpArtifactTypeGroup( String typeGroupName ) {
      ArtifactTypeGroup artifactTypeGroup = artifactTypeGroupFactory.create( typeGroupName );
      artifactTypeGroupRepository.add( artifactTypeGroup );
      
      return artifactTypeGroup;
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
