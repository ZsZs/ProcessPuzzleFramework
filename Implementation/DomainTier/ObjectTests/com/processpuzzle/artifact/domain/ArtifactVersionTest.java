package com.processpuzzle.artifact.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.artifact.domain.Modification;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.application.security.domain.UserFactory;
import com.processpuzzle.application.security.domain.UserRepository;
import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.artifact.domain.ArtifactFactory;
import com.processpuzzle.artifact.domain.ArtifactVersion;
import com.processpuzzle.artifact.domain.CommentList;
import com.processpuzzle.artifact.domain.CommentListFactory;
import com.processpuzzle.artifact.domain.DefaultArtifactRepository;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.artifact_type.domain.ArtifactTypeFactory;
import com.processpuzzle.artifact_type.domain.ArtifactTypeTestFixture;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;
import com.processpuzzle.sharedfixtures.domaintier.ProcessPuzzleContextFixture;
import com.processpuzzle.user_session.domain.UserRequestManager;

public class ArtifactVersionTest {
   private ProcessPuzzleContextFixture applicationContextFixture;
   private ArtifactTypeTestFixture typeFixture = null;
   private static User person;
   private static Date creation;
   private ArtifactVersion version;
   private static DefaultArtifactRepository repository = null;
   private static UserRepository userRepository = null;
   private ProcessPuzzleContext applicationContext;
   private UserFactory userFactory;
   private ArtifactTypeFactory artifactTypeFactory;
   private CommentListFactory commentListFactory;
   private User currentUser;

   @Before
   public void setUp() throws Exception {
      applicationContextFixture = ProcessPuzzleContextFixture.getInstance( DomainTierTestConfiguration.APPLICATION_CONFIGURATION_DESCRIPTOR_PATH );
      applicationContextFixture.setUp();
      
      applicationContext = applicationContextFixture.getApplicationContext();
      
      typeFixture = ArtifactTypeTestFixture.getInstance( applicationContext );
      typeFixture.setUp();

      userFactory = applicationContext.getEntityFactory( UserFactory.class );
      artifactTypeFactory = applicationContext.getEntityFactory( ArtifactTypeFactory.class );
      commentListFactory = applicationContext.getEntityFactory( CommentListFactory.class );
      
      currentUser = UserRequestManager.getInstance().currentUser();
      
      repository = (DefaultArtifactRepository) ProcessPuzzleContext.getInstance().getRepository( DefaultArtifactRepository.class );
      userRepository = (UserRepository) ProcessPuzzleContext.getInstance().getRepository( UserRepository.class );
      creation = new Date( System.currentTimeMillis() );
      person = userFactory.createUser( "Kis Nagy", "psw" );
   }

   @After
   public void tearDown() throws Exception {
      typeFixture.tearDown();
   }

   @Ignore
   @Test
   public final void testCreateArtifatVersion_withEmptyConstructor() {
      ArtifactVersion version = new ArtifactVersion();
      assertNotNull( "The artifact version object is exist.", version );
      assertNotNull( "The artifact version's date of creation is not null.", version.getCreation() );
      assertNotNull( "The artifact version's name is not null.", version.getName() );
      assertFalse( "The default value of the isVersionControlled field is the false.", version.isVersionControlled() );
      assertNull( "The default value of responsible field is null.", version.getCreator() );
      assertNull( "The default value of commentListName field is null.", version.getCommentList() );
      assertNull( "The default value of next field is null.", version.getNextModification() );
      assertNull( "The default value of previos field is null.", version.getPreviousModification() );
   }

   @Ignore
   @Test
   public final void testCreateArtifactVersion_withNameAndResponsible() {
      assertNotNull( "The person object is exist.", person );
      ArtifactVersion version = new ArtifactVersion( "AVersion", person );
      assertNotNull( "The artifact version object is exist.", version );
      assertNotNull( "The artifact version's date of creation is not null.", version.getCreation() );
      assertNotNull( "The artifact version's name is not null.", version.getName() );
      assertEquals( "The name of artifact version is same 'AVersion'.", version.getName(), "AVersion" );
      assertNotNull( "The responsible of artifact version is not null.", version.getCreator() );
      assertEquals( "The responsible of artifact version is 'Kis Nagy'.", version.getCreator(), person );
   }

   @Ignore
   @Test
   public final void testCreateArtifactVersion_withNameAndResponsibleAndCreation() {
      assertNotNull( "The person object is exist.", person );
      assertNotNull( "Creation date object is not null.", creation );
      ArtifactVersion version = new ArtifactVersion( "AVersion", person, creation );
      assertNotNull( "The artifact version object is exist.", version );
      assertNotNull( "The artifact version's date of creation is not null.", version.getCreation() );
      assertEquals( "The date of creration of artifact version is " + creation.getTime(), version.getCreation().getTime(), creation
            .getTime() );
      assertNotNull( "The artifact version's name is not null.", version.getName() );
      assertEquals( "The name of artifact version is same 'AVersion'.", version.getName(), "AVersion" );
      assertNotNull( "The responsible of artifact version is not null.", version.getCreator() );
      assertEquals( "The responsible of artifact version is 'Kis Nagy'.", version.getCreator(), person );
   }
   
   @Ignore
   @Test
   public final void testCreateArtifactVersion_withNameAndResponsibleAndType() {
      ArtifactType type = artifactTypeFactory.createArtifactType( "testType", "ArtifactGroup" );
      ArtifactVersion version = new ArtifactVersion( "AVersion", person );
      assertNotNull( "The artifact version object is exist.", version );
      assertNotNull( "The artifact version's date of creation is not null.", version.getCreation() );
      assertNotNull( "The artifact version's name is not null.", version.getName() );
      assertEquals( "The name of artifact version is same 'AVersion'.", version.getName(), "AVersion" );
      assertNotNull( "The responsible of artifact version is not null.", version.getCreator() );
      assertEquals( "The responsible of artifact version is 'Kis Nagy'.", version.getCreator(), person );
   }

   @Ignore
   @Test
   public final void testGettersAndSettersOfArtifactVersion() {
      ArtifactVersion version = new ArtifactVersion();
      assertNotNull( "The artifact version object is exist.", version );

      assertNotNull( "The person object is exist.", person );
      version.setResponsible( person );
      assertNotNull( "The responsible of artifact version is not null.", version.getCreator() );
      assertEquals( "The responsible of artifact version is 'Kis Nagy'.", version.getCreator(), person );

      assertNotNull( "Creation date object is not null.", creation );
      version.setCreation( creation );
      assertNotNull( "The artifact version's date of creation is not null.", version.getCreation() );
      assertEquals( "The date of creration of artifact version is " + creation.getTime(), version.getCreation().getTime(), creation
            .getTime() );

      Date modification = new Date( System.currentTimeMillis() );
      assertNotNull( "Modification date object is not null.", modification );
      version.setModification( modification );
      assertNotNull( "The artifact version's date of modification is not null.", version.getModification() );
      assertEquals( "The date of modification of artifact version is " + modification.getTime(), version.getModification().getTime(),
            modification.getTime() );

      version.setVersionControlled( true );
      assertTrue( "The artifact version is is version controlled.", version.isVersionControlled() );

      ArtifactType artifactType = artifactTypeFactory.createArtifactType( "anArtifactType", "ArtifactGroup" );
      assertNotNull( "The artifact type is not null.", artifactType );

      CommentList commentList = commentListFactory.create( "aCommentList" );
      assertNotNull( "The comment list is not null.", commentList );
      version.setCommentList( commentList );
      assertNotNull( "The comment list of artifact version is not null.", version.getCommentList() );
      assertEquals( "The name of comment list of artifact version is same 'aCommentList'.", version.getCommentList().getName(), commentList
            .getName() );

      Set<Artifact<?>> relatedArtifacts = new HashSet<Artifact<?>>();
      assertNotNull( "The relatedArtifacts is not null.", relatedArtifacts );
      addRelatedArtifacts( version, relatedArtifacts );
      assertNotNull( "The relatedArtifacts of artifact version is not null.", version.getRelatedArtifacts() );
      assertEquals( "The relatedArtifacts of artifact version is equals with relatedArtifacts.", version.getRelatedArtifacts(),
            relatedArtifacts );

      Modification next = new Modification( version, person, "aComment" );
      assertNotNull( "The next is not null.", next );
      version.setNextModification( next );
      assertNotNull( "The next of artifact version is not null.", version.getNextModification() );
      assertEquals( "The next of artifact version is ", version.getNextModification(), next );

      Modification previous = new Modification( version, person, "aComment" );
      assertNotNull( "The previous is not null.", previous );
      version.setPreviousModification( previous );
      assertNotNull( "The previous of artifact version is not null.", version.getPreviousModification() );
      assertEquals( "The previous of artifact version is ", version.getPreviousModification(), previous );
   }

   @Ignore
   @Test
   public final void testCloneArtifactVersion() {
      version = new ArtifactVersion( "AVersion", person, creation );
      Date modification = new Date( System.currentTimeMillis() );
      assertNotNull( "Modification date object is not null.", modification );
      version.setModification( modification );
      version.setVersionControlled( true );

      ArtifactType artifactType = artifactTypeFactory.createArtifactType( "anArtifactType", "ArtifactGroup" );
      assertNotNull( "The artifact type is not null.", artifactType );

      CommentList commentList = commentListFactory.create( "aCommentList" );
      assertNotNull( "The comment list is not null.", commentList );
      version.setCommentList( commentList );

      Set<Artifact<?>> relatedArtifacts = new HashSet<Artifact<?>>();
      assertNotNull( "The relatedArtifacts is not null.", relatedArtifacts );
      addRelatedArtifacts( version, relatedArtifacts );

      Modification next = new Modification( version, person, "aComment" );
      assertNotNull( "The next is not null.", next );
      version.setNextModification( next );

      Modification previous = new Modification( version, person, "aComment" );
      assertNotNull( "The previous is not null.", previous );
      version.setPreviousModification( previous );

      ArtifactVersion version2 = (ArtifactVersion) version.clone();

      assertNotNull( "The name of the clone artifact version is not null.", version2.getName() );
      assertEquals( "The name of the clone and the original artifact versions are equals.", version.getName(), version2.getName() );

      assertNotNull( "The date of creation of the clone artifact version is not null.", version2.getCreation() );
      assertEquals( "The date of creation of the clone and the original artifact versions are equals.", version.getCreation().getTime(),
            version2.getCreation().getTime() );

      assertNotNull( "The responsible of the clone artifact version is not null.", version2.getCreator() );
      assertEquals( "The responsible of the clone and the original artifact version is 'Kis Nagy'.", version.getCreator(), version2
            .getCreator() );

      assertNotNull( "The date of modification of the clone artifact version is not null.", version2.getModification() );
      assertEquals( "The date of modification of the clone and the original artifact versions are equals.", version.getModification()
            .getTime(), version2.getModification().getTime() );

      assertTrue( "The artifact version is version controlled.", version2.isVersionControlled() );

      assertNotNull( "The comment list of the clone artifact version is not null.", version2.getCommentList() );
      assertEquals( "The name of comment list of the clone and the original artifact versions are equals.", version.getCommentList()
            .getName(), version2.getCommentList().getName() );

      assertNotNull( "The relatedArtifacts of the clone artifact version is not null.", version2.getRelatedArtifacts() );
      assertEquals( "The relatedArtifacts of the clone and the original artifact versions are equals.", version.getRelatedArtifacts()
            .size(), version2.getRelatedArtifacts().size() );

      // assertNotNull("The next of the clone artifact version is not null.", version2.getNextModification());
      // assertEquals("The next of the clone and the original artifact versions are equals.",
      // version.getNextModification(), version2.getNextModification());

      // assertNotNull("The previous of the clone artifact version is not null.", version2.getPreviousModification());
      // assertEquals("The previous of the clone and the original artifact versions are equals.",
      // version.getPreviousModification(), version2.getPreviousModification());
   }

   @Ignore
   @Test
   public final void testAddRelatedArtifact_withName() {
      version = ArtifactFactory.createArifactVersion( "AVersion", person, creation );
      Artifact<?> artifact = new ArtifactSubClass( "anArtifact", typeFixture.getArtifactSubClassType(), currentUser );
      version.addRelatedArtifact( artifact );
      assertNotNull( "Can't add artifact without name.", version.getRelatedArtifacts() );
      assertEquals( "The artifact version have a related artifact.", version.getRelatedArtifacts().size(), 1 );
      assertEquals( "The artifact version have a related artifact call anArtifact.", ((Artifact<?>) (version.getRelatedArtifacts().iterator()
            .next())).getName(), "anArtifact" );
   }

   @Ignore
   @Test
   public final void testAddRelatedArtifactName_withNullNameIfEmpty() {
      version = ArtifactFactory.createArifactVersion( "AVersion", person, creation );
      version.addRelatedArtifact( null );
      assertEquals( "Can't add artifact without name.", 0, version.getRelatedArtifacts().size() );
   }

   @Ignore
   @Test
   public final void testAddRelatedArtifactName_withName() {
      version = ArtifactFactory.createArifactVersion( "AVersion", person, creation );
      version.addRelatedArtifact( new ArtifactSubClass( "relatedArtifactName", typeFixture.getArtifactSubClassType(), currentUser ) );
      assertNotNull( "Can't add artifact without name.", version.getRelatedArtifacts() );
      assertEquals( "The artifact version have a related artifact.", version.getRelatedArtifacts().size(), 1 );
      assertEquals( "The artifact version have a related artifact call anArtifact.", ((Artifact<?>) (version.getRelatedArtifacts().iterator()
            .next())).getName(), "relatedArtifactName" );
   }

   @Ignore
   @Test
   public final void testFindRelatedArtifactName_withNullParameterInNotNullSet() {
      version = ArtifactFactory.createArifactVersion( "AVersion", person, creation );
      version.addRelatedArtifact( new ArtifactSubClass( "relatedArtifactName", typeFixture.getArtifactSubClassType(), currentUser ) );
      assertNotNull( "The artifact version don't have related artifact name.", version.getRelatedArtifacts() );
   }

   @Ignore
   @Test
   public final void testFindRelatedArtifactName_withNotNullParameterInNullSet() {
      version = ArtifactFactory.createArifactVersion( "AVersion", person, creation );
      version.addRelatedArtifact( null );
      assertEquals( "The artifact version don't have related artifact name.", 0, version.getRelatedArtifacts().size() );
      assertNull( "Not find related artifact name.", version.findRelatedArtifact( "relatedArtifact" ) );
   }

   @Ignore
   @Test
   public final void testFindRelatedArtifactName_withNotNullParameterInNotNullSet() {
      version = ArtifactFactory.createArifactVersion( "AVersion", person, creation );
      version.addRelatedArtifact( new ArtifactSubClass( "relatedArtifactName", typeFixture.getArtifactSubClassType(), currentUser ) );
      assertNotNull( "The artifact version don't have related artifact name.", version.getRelatedArtifacts() );
      assertNotNull( "Found related artifact name.", version.findRelatedArtifact( "relatedArtifactName" ) );
      assertEquals( "The related artifact name what found is equals with 'relatedArtifactName'", version.findRelatedArtifact(
            "relatedArtifactName" ).getName(), "relatedArtifactName" );
   }

   @Ignore
   @Test
   public final void testFindRelatedArtifactName_withWrongParameterInNotSet() {
      version = ArtifactFactory.createArifactVersion( "AVersion", person, creation );
      version.addRelatedArtifact( new ArtifactSubClass( "relatedArtifactName", typeFixture.getArtifactSubClassType(), currentUser ) );
      assertNotNull( "The artifact version don't have related artifact name.", version.getRelatedArtifacts() );
      assertNull( "Not find related artifact name.", version.findRelatedArtifact( "relatedArtifactName1" ) );
   }

   @Ignore
   @Test
   public final void testRemoveRelatedArtifactName_withNotNullParameterFromNotNullSet() {
      version = ArtifactFactory.createArifactVersion( "AVersion", person, creation );
      version.addRelatedArtifact( new ArtifactSubClass( "relatedArtifactName", typeFixture.getArtifactSubClassType(), currentUser ) );
      version.removeRelatedArtifact( "relatedArtifactName" );
      assertEquals( "The related artifact call relatedArtifactName removed.", version.getRelatedArtifacts().size(), 0 );
   }

   @Ignore
   @Test
   public final void testRemoveRelatedArtifactName_withWrongParameterFromNotNullSet() {
      version = ArtifactFactory.createArifactVersion( "AVersion", person, creation );
      version.addRelatedArtifact( new ArtifactSubClass( "relatedArtifactName", typeFixture.getArtifactSubClassType(), currentUser ) );
      version.removeRelatedArtifact( "relatedArtifactName1" );
      assertEquals( "The related artifact call relatedArtifactName1 can't remove.", version.getRelatedArtifacts().size(), 1 );
   }

   @Ignore
   @Test
   public void testArtifactVersionWithModification() {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      userRepository.addUser( work, person );
      ArtifactVersion ver = ArtifactFactory.createArifactVersion( "AVersion", person, creation );
      // repository.addArtifactVersion(work, ver);
      // assertNull("", repository.findArtifactVersionsById(work, ver.getId()).getNextModification());
      new Modification( ver, person, "" );
      assertNotNull( "", ver.getNextModification() );
      assertNotNull( "", ver.getId() );
      // repository.updateArtifactVersion(work, ver);
      // assertNotNull("", repository.findArtifactVersionsById(work, ver.getId()).getNextModification());
      new Modification( ver.getNextModification().getTargetVersion(), person, "" );
      // repository.updateArtifactVersion(work, ver);
      assertNotNull( "", ver.getNextModification().getTargetVersion().getNextModification() );
      // assertNotNull("", repository.findArtifactVersionsById(work,
      // ver.getId()).getNextModification().getTargetVersion().getNextModification());
      // repository.deleteArtifactVersion(work, ver);
      userRepository.deleteUser( work, person );
      work.finish();
   }

   private void addRelatedArtifacts( ArtifactVersion version, Set<Artifact<?>> relatedArtifacts ) {
      for( Artifact<?> artifact : relatedArtifacts ) {
         version.addRelatedArtifact( artifact );
      }
   }
}