package com.processpuzzle.artifact.domain;


import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.application.security.domain.UserFactory;
import com.processpuzzle.application.security.domain.UserRepository;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.artifact_type.domain.ArtifactTypeFactory;
import com.processpuzzle.artifact_type.domain.ArtifactTypeRepository;
import com.processpuzzle.litest.template.RepositoryTestEnvironment;
import com.processpuzzle.litest.template.RepositoryTestFixture;
import com.processpuzzle.party.artifact.PersonList;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;

public class ArtifactFolderRepositoryTestFixture extends RepositoryTestFixture<ArtifactFolderRepository, ArtifactFolder> {
   private static final String ARTIFACT_FOLDER_NAME = "ArtifactFolder";
   private static final String ARTIFACT_ONE_NAME = "ArtifactOne";
   private static final String ARTIFACT_TWO_NAME = "ArtifactTwo";
   private static final String ARTIFACT_LIST_NAME = "ArtifactList";
   private ArtifactFolderFactory artifactFolderFactory;
   private ArtifactTypeRepository artifactTypeRepository;
   private DefaultArtifactRepository artifactRepository;
   private ArtifactTypeFactory artifactTypeFactory;
   private UserFactory userFactory;
   private UserRepository userRepository;
   private ArtifactType folderType;
   private ArtifactType subClassType;
   private ArtifactType personListType;
   private User creator;
   private Artifact<?> artifactOne;
   private Artifact<?> artifactTwo;
   private ArtifactFolder artifactFolder;
   private PersonList personList;

   public ArtifactFolderRepositoryTestFixture( RepositoryTestEnvironment<ArtifactFolderRepository, RepositoryTestFixture<ArtifactFolderRepository,ArtifactFolder>> testEnvironment ) {
      super( testEnvironment );
   }
   
   public ArtifactFolder getArtifactFolder() { return artifactFolder; }
   public Artifact<?> getArtifactOne() { return artifactOne; }
   public Artifact<?> getArtifactTwo() { return artifactTwo; }
   public DefaultArtifactRepository getArtifactRepository() { return artifactRepository; }
   public ArtifactType getFolderType(){ return folderType; }

   @Override
   protected void configureBeforeSutInstantiation() {
      super.configureBeforeSutInstantiation();
      artifactFolderFactory = applicationContext.getEntityFactory( ArtifactFolderFactory.class );
      artifactTypeFactory = applicationContext.getEntityFactory( ArtifactTypeFactory.class );
      artifactTypeRepository = applicationContext.getRepository( ArtifactTypeRepository.class );
      artifactRepository = applicationContext.getRepository( DefaultArtifactRepository.class );
      userFactory = applicationContext.getEntityFactory( UserFactory.class );
      userRepository = applicationContext.getRepository( UserRepository.class );
      
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      folderType = artifactTypeRepository.findArtifactTypeByName( work, "ArtifactFolder" );
      
      subClassType = artifactTypeFactory.create( "ArtifactSubClass", "SystemAdministration" );
      subClassType.setArtifactClassName( ArtifactSubClass.class.getName() );
      subClassType.setSystem( true );
      artifactTypeRepository.addArtifactType( work, subClassType );
      
      personListType = artifactTypeRepository.findArtifactTypeByName( work, "PersonList" );
      
      creator = userFactory.createUser( "testPerson", "password" );
      userRepository.add( work, creator );
      work.finish();
   }

   @Override
   protected ArtifactFolder createNewAggregate() throws Exception {
      artifactFolder = artifactFolderFactory.create( ARTIFACT_FOLDER_NAME );
      artifactOne = new ArtifactSubClass( ARTIFACT_ONE_NAME, subClassType, creator );
      artifactFolder.addChildArtifact( artifactOne );
      
      artifactTwo = new ArtifactSubClass( ARTIFACT_TWO_NAME, subClassType, creator );
      artifactFolder.addChildArtifact( artifactTwo );

      personList = new PersonList( ARTIFACT_LIST_NAME, personListType, creator );
      artifactFolder.addChildArtifact( personList );
      
      return artifactFolder;
   }

   @Override
   protected void afterAggregateCreation() {
   }

   @Override
   protected void afterAggregateDeletion() {
   }

   @Override
   protected void beforeAggregateCreation() {
   }

}
