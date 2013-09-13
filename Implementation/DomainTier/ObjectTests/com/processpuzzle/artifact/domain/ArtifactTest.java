package com.processpuzzle.artifact.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.application.security.domain.UserFactory;
import com.processpuzzle.application.security.domain.UserRepository;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.artifact.domain.ArtifactVersion;
import com.processpuzzle.artifact.domain.Comment;
import com.processpuzzle.artifact.domain.CommentList;
import com.processpuzzle.artifact.domain.CommentListFactory;
import com.processpuzzle.artifact.domain.DefaultArtifactRepository;
import com.processpuzzle.artifact.domain.VersionControlException;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.artifact_type.domain.ArtifactTypeFactory;
import com.processpuzzle.artifact_type.domain.ArtifactTypeTestFixture;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;
import com.processpuzzle.sharedfixtures.domaintier.ProcessPuzzleContextFixture;

public class ArtifactTest {
   private ProcessPuzzleContextFixture applicationContextFixture;
   private ProcessPuzzleContext applicationContext;
   private UserFactory userFactory;
   private ArtifactTypeTestFixture typeFixture = null;
   private Artifact<?> artifact;
   private static DefaultArtifactRepository arepository = null;
   private static UserRepository userRepository = null;
   private User user;
   private User anotherUser;
   private static ArtifactTypeFactory artifactTypeFactory;
   private static CommentListFactory commentListFactory;

   @Before
   public void setUp() throws Exception {
      DefaultUnitOfWork work = new DefaultUnitOfWork(true);
      applicationContextFixture = ProcessPuzzleContextFixture.getInstance( DomainTierTestConfiguration.APPLICATION_CONFIGURATION_DESCRIPTOR_PATH );
      applicationContextFixture.setUp();
      
      applicationContext = applicationContextFixture.getApplicationContext();

      typeFixture = ArtifactTypeTestFixture.getInstance( applicationContext );
      typeFixture.setUp();

      userFactory = applicationContext.getEntityFactory( UserFactory.class );
      artifactTypeFactory = applicationContext.getEntityFactory( ArtifactTypeFactory.class );
      commentListFactory = applicationContext.getEntityFactory( CommentListFactory.class );
     
      arepository = (DefaultArtifactRepository) ProcessPuzzleContext.getInstance().getRepository(DefaultArtifactRepository.class);
      userRepository = (UserRepository) ProcessPuzzleContext.getInstance().getRepository( UserRepository.class);

      user = userFactory.createUser( "A", "password");
      userRepository.addUser(work, user);
      artifact = new ArtifactSubClass("ArtifactForTest", typeFixture.getArtifactSubClassType(), user);
      arepository.add(work, artifact);
      work.finish();
   }

   @After
   public void tearDown() throws Exception {
      DefaultUnitOfWork work = new DefaultUnitOfWork(true);
      arepository.update(work, artifact);
      arepository.delete(work, artifact);
      userRepository.deleteUser(work, user);
      typeFixture.tearDown();

      artifact = null;
      user = null;
      if (anotherUser != null) {
         userRepository.deleteUser( work, anotherUser );
         anotherUser = null;
      }
      work.finish();
   }

   @Ignore
   @Test
   public void testCreatAnArtifact_withEmptyConstructor() {
      Artifact<?> artifact2 = new ArtifactSubClass();
      assertNotNull("The artifact is exist.", artifact2);
      assertNotNull("The artifact have a version.", artifact2.latest());
   }

   @Ignore
   @Test
   public void testCreatAnArtifact_withNameAndResponsible() {
      Artifact<?> artifact2 = new ArtifactSubClass("artifact", typeFixture.getArtifactSubClassType(), user);
      assertNotNull("The artifact is exist.", artifact2);
      assertNotNull("The artifact have a version.", artifact2.latest());
      assertEquals("The artifact name is 'artifact'", artifact2.getName(), "artifact");
      assertEquals("The responsible of artifact is 'A B'.", artifact2.getResponsible(), user);
   }

   @Ignore
   @Test
   public void testCreatAnArtifact_withNameAndResponsibleAndArtifactType() {
      ArtifactType artifactType = artifactTypeFactory.createArtifactType( "type", "ArtifactGroup" );
      Artifact<?> artifact2 = new ArtifactSubClass("artifact", artifactType, user);
      assertNotNull("The artifact is exist.", artifact2);
      assertNotNull("The artifact have a version.", artifact2.latest());
      assertEquals("The artifact name is 'artifact'", artifact2.getName(), "artifact");
      assertEquals("The responsible of artifact is 'A B'.", artifact2.getResponsible(), user);
      assertEquals("The artifact type of artifact is 'type'.", artifact2.getType(), artifactType);
   }

   @Ignore
   @Test
   public void testGettersAndSettersOfArtifact() {
      Artifact<?> artifact2 = new ArtifactSubClass();
      artifact2.setResponsible(user);
      assertEquals("The responsible of artifact is 'A B'.", artifact2.getResponsible(), user);
      Date creation = new Date(System.currentTimeMillis());
      artifact2.setCreation(creation);
      assertEquals("The creation date of artifact is " + creation.getTime(), artifact2.getCreation().getTime(), creation.getTime());
      artifact2.setVersionControlled(true);
      assertTrue("The artifact is version controlled.", artifact2.isVersionControlled());
      artifact2.setVersionControlled(false);
      assertFalse("The artifact is not version controlled.", artifact2.isVersionControlled());
      CommentList commentList = commentListFactory.create( "aCommentList" );
      addCommentsToArtifact( artifact2, commentList );
      assertNotNull("The comment list of artifact is not null.", artifact2.getCommentList());
      assertEquals("The name of comment list of artifact is same 'aCommentList'.", artifact2.getCommentList().getName(), commentList
            .getName());
      artifact2.setResponsible(null);
   }

   @Ignore
   @Test
   public void testDelete() {
      DefaultUnitOfWork work = new DefaultUnitOfWork(true);
      ArtifactType artifactType = artifactTypeFactory.createArtifactType( "type", "ArtifactGroup" );
      Artifact<?> artifact2 = new ArtifactSubClass("artifactDelete", artifactType, user);
      arepository.add(work, artifact2);
      assertNotNull("The artifact updated.", arepository.findByName(work, artifact2.getName()));
      arepository.delete(work, artifact2);
      assertNull("The artifact deleted.", arepository.findByName(work, artifact2.getName()));
      work.finish();
   }

   @Ignore
   @Test
   public void testAvailableView() {
      assertTrue("An artifact has at least one view.", artifact.getAvailableViews().size() >= 1);
      assertNotNull("A subclass of PropertyView is required.", artifact.getPropertyView());
   }

   @Ignore
   @Test
   public void testGetPropertyView() {
      assertNotNull("A subclass of PropertyView is required for each .", artifact.getPropertyView());
   }

   @Ignore
   @Test
   public void testFindView() {
      assertNotNull("We can find an artifact's view by name.", artifact.findViewByName("SpecializedRelatedArtifactListView"));
   }

   @Ignore
   @Test
   public void r__testAddRelatedArtifact() {
      addRelatedArtifacts();
      assertEquals("Artifact.relatedArtifacts() collection contains 2 elements.", 2, artifact.getRelatedArtifacts().size());
      assertNotNull("We can retrieve the related artifact by name.", artifact.findRelatedArtifact("AnotherArtifactSubClass"));
      assertNotNull("We can retrieve the related artifact by name.", artifact.findRelatedArtifact("YetAnotherArtifactSubClass"));
      removeRelatedArtifacts();
   }

   @Ignore
   @Test
   public void r__testFindRelatedArtifact_ForUnsuccess() {
      addRelatedArtifacts();
      assertNull("If the given artifact name is unknown the findRelatedArtifact() returns null.", artifact
            .findRelatedArtifact("Unknown artifact"));
      removeRelatedArtifacts();
   }

   @Ignore
   @Test
   public void testAddArtifactVersion() {
      artifact.setVersionControlled(true);
      try {
         artifact.reserve(user, "comment");
         artifact.release(user);
      } catch (VersionControlException e) {
         fail();
      }
      assertNotNull("The version have modification.", artifact.latest().getPreviousModification());
   }

   @Ignore
   @Test
   public void testReserveVersionedArtifact() {
      artifact.setVersionControlled(true);
      ArtifactVersion version = artifact.latest();
      assertNull("latest() hasn't next modification.", artifact.latest().getNextModification());
      try {
         artifact.reserve(user, "aComment");
      } catch (VersionControlException e) {
         fail();
      }
      assertNotNull("The next modification of latest() is exist.", artifact.latest().getNextModification());
      assertEquals("latest() not changed.", artifact.latest(), version);
      assertNull("latest() has got next modification.", artifact.latest().getNextModification().getModificationPeriod().getEnd());
      assertEquals("Artifact have 2 version.", artifact.getVersions().size(), 2);
   }

   @Ignore
   @Test
   public void testReserveVersionedArtifact_twoTime() {
      artifact.setVersionControlled(true);
      ArtifactVersion version = artifact.latest();
      assertNull("latest() hasn't next modification.", artifact.latest().getNextModification());
      try {
         artifact.reserve(user, "aComment");
      } catch (VersionControlException e) {
         fail();
      }
      boolean wasException = false;
      try {
         artifact.reserve(user, "aComment");
      } catch (VersionControlException e) {
         wasException = true;
      }
      assertTrue("There must be a VersionControlException.", wasException);
      assertNotNull("The next modification of latest() is exist.", artifact.latest().getNextModification());
      assertEquals("latest() not changed.", artifact.latest(), version);
      assertNull("latest() has got next modification.", artifact.latest().getNextModification().getModificationPeriod().getEnd());
      assertEquals("Artifact have 2 version.", artifact.getVersions().size(), 2);
   }

   @Ignore
   @Test
   public void testReleaseVersionedArtifact_notReserved() {
      artifact.setVersionControlled(true);
      assertNull("latest() hasn't next modification.", artifact.latest().getNextModification());

      boolean wasException = false;
      try {
         artifact.release(user);
      } catch (VersionControlException e) {
         wasException = true;
      }
      assertTrue("There must be a VersionControlException.", wasException);
      assertNull("latest() hasn't next modification.", artifact.latest().getNextModification());
   }

   @Ignore
   @Test
   public void testReleaseVersionedArtifact_reserved() {
      artifact.setVersionControlled(true);
      ArtifactVersion version = artifact.latest();
      try {
         artifact.reserve(user, "aComment");
      } catch (VersionControlException e) {
         fail();
      }
      try {
         artifact.release(user);
      } catch (VersionControlException e) {
         fail();
      }
      assertNull("The next modification of latest() is exist.", artifact.latest().getNextModification());
      assertNotSame("latest() not changed.", artifact.latest(), version);
      assertNotNull("latest() has got next modification.", artifact.latest().getPreviousModification().getModificationPeriod().getEnd());
      assertEquals("Artifact have 2 version.", artifact.getVersions().size(), 2);
   }

   @Ignore
   @Test
   public void testReleaseVersionedArtifact_reservedWithWrongModifier() {
      DefaultUnitOfWork work = new DefaultUnitOfWork(true);
      artifact.setVersionControlled(true);
      ArtifactVersion version = artifact.latest();
      try {
         artifact.reserve(user, "aComment");
      } catch (VersionControlException e) {
         fail();
      }

      anotherUser = userFactory.createUser("C", "psw");
      userRepository.addUser(work, anotherUser);
      boolean wasException = false;
      try {
         artifact.release(anotherUser);
      } catch (VersionControlException e) {
         wasException = true;
      }
      assertTrue("There must be a VersionControlException.", wasException);
      assertNotNull("The next modification of latest() is exist.", artifact.latest().getNextModification());
      assertEquals("latest() not changed.", artifact.latest(), version);
      assertNull("latest() has got next modification.", artifact.latest().getNextModification().getModificationPeriod().getEnd());
      assertEquals("Artifact have 2 version.", artifact.getVersions().size(), 2);
      work.finish();
   }

   @Ignore
   @Test
   public void testReleaseVersionedArtifact_reservedTwoTimes() {
      DefaultUnitOfWork work = new DefaultUnitOfWork(true);
      artifact.setVersionControlled(true);
      ArtifactVersion version = artifact.latest();
      try {
         artifact.reserve(user, "aComment");
         artifact.release(user);
      } catch (VersionControlException e) {
         fail();
      }

      boolean wasException = false;
      try {
         artifact.release(user);
      } catch (VersionControlException e) {
         wasException = true;
      }

      assertTrue("There must be VersionControlException at secend release.", wasException);
      arepository.update(work, artifact);
      assertNull("The next modification of latest() is exist.", artifact.latest().getNextModification());
      assertNotSame("latest() not changed.", artifact.latest(), version);
      assertNotNull("latest() has got next modification.", artifact.latest().getPreviousModification().getModificationPeriod().getEnd());
      assertEquals("Artifact have 2 version.", artifact.getVersions().size(), 2);
      work.finish();
   }

   @Ignore
   @Test
   public void testReleaseVersionedArtifact_reservedRelaesedTwoTimes() {
      DefaultUnitOfWork work = new DefaultUnitOfWork(true);
      artifact.setVersionControlled(true);
      ArtifactVersion version = artifact.latest();
      try {
         artifact.reserve(user, "aComment1");
         artifact.release(user);
         artifact.reserve(user, "aComment2");
         artifact.release(user);
      } catch (VersionControlException e) {
         fail();
      }

      arepository.update(work, artifact);
      assertNull("The next modification of latest() is exist.", artifact.latest().getNextModification());
      assertNotSame("latest() changed.", artifact.latest(), version);
      assertNotNull("latest() has got next modification.", artifact.latest().getPreviousModification().getModificationPeriod().getEnd());
      assertEquals("Artifact have 2 version.", artifact.getVersions().size(), 3);
      assertEquals("The artifact updated.", ((ArtifactSubClass) arepository.findById(work, artifact.getId())).getVersions().size(),
            3);
      work.finish();
   }

   @Ignore
   @Test
   public void testReserveNonVersionedArtifact() {
      artifact.setVersionControlled(false);
      ArtifactVersion version = artifact.latest();
      assertNull("latest() hasn't next modification.", artifact.latest().getNextModification());
      try {
         artifact.reserve(user, "aComment");
      } catch (VersionControlException e) {
         fail();
      }
      assertNotNull("The next modification of latest() is exist.", artifact.latest().getNextModification());
      assertEquals("latest() not changed.", artifact.latest(), version);
      assertNull("latest() has got next modification.", artifact.latest().getNextModification().getModificationPeriod().getEnd());
      assertEquals("Artifact have 2 version.", artifact.getVersions().size(), 2);
   }

   @Ignore
   @Test
   public void testReserveNonVersionedArtifact_twoTime() {
      artifact.setVersionControlled(false);
      ArtifactVersion version = artifact.latest();
      assertNull("latest() hasn't next modification.", artifact.latest().getNextModification());
      try {
         artifact.reserve(user, "aComment");
      } catch (VersionControlException e) {
         fail();
      }

      boolean wasException = false;
      try {
         artifact.reserve(user, "aComment");
      } catch (VersionControlException e) {
         wasException = true;
      }
      assertTrue("There must be a VersionControlException.", wasException);
      assertNotNull("The next modification of latest() is exist.", artifact.latest().getNextModification());
      assertEquals("latest() not changed.", artifact.latest(), version);
      assertNull("latest() has got next modification.", artifact.latest().getNextModification().getModificationPeriod().getEnd());
      assertEquals("Artifact have 2 version.", artifact.getVersions().size(), 2);
   }

   @Ignore
   @Test
   public void testReleaseNonVersionedArtifact_notReserved() {
      artifact.setVersionControlled(false);
      assertNull("latest() hasn't next modification.", artifact.latest().getNextModification());
      boolean wasException = false;
      try {
         artifact.release(user);
      } catch (VersionControlException e) {
         wasException = true;
      }
      assertTrue("There must be a VersionControlException.", wasException);
      assertNull("latest() hasn't next modification.", artifact.latest().getNextModification());
   }

   @Ignore
   @Test
   public void testReleaseNonVersionedArtifact_reserved() {
      artifact.setVersionControlled(false);
      // ArtifactVersion version = artifact.latest();
      try {
         artifact.reserve(user, "aComment");
         artifact.release(user);
      } catch (VersionControlException e) {
         fail();
      }
      assertNotNull("The next modification of latest() is exist.", artifact.latest().getNextModification());
      // assertNotSame("latest() not changed.", artifact.latest(), version);
      assertNull("latest() has got next modification.", artifact.latest().getPreviousModification());
      assertEquals("Artifact have 1 version.", artifact.getVersions().size(), 1);
   }

   @Ignore
   @Test
   public void testReleaseNonVersionedArtifact_reservedWithWrongModifier() {
      artifact.setVersionControlled(false);
      ArtifactVersion version = artifact.latest();
      try {
         artifact.reserve(user, "aComment");
      } catch (VersionControlException e) {
         fail();
      }

      anotherUser = userFactory.createUser("C", "pws");
      DefaultUnitOfWork work = new DefaultUnitOfWork(true);
      userRepository.addUser(work, anotherUser);
      boolean wasException = false;
      try {
         artifact.release(anotherUser);
      } catch (VersionControlException e) {
         wasException = true;
      }

      assertTrue("There must be a VersionControlException.", wasException);
      assertEquals("latest() not changed.", artifact.latest(), version);
      assertNull("latest() has got next modification.", artifact.latest().getNextModification().getModificationPeriod().getEnd());
      assertEquals("Artifact have 2 version.", artifact.getVersions().size(), 2);
      work.finish();
   }

   @Ignore
   @Test
   public void testReleaseNonVersionedArtifact_reservedTwoTimes() {
      artifact.setVersionControlled(false);
      // ArtifactVersion version = artifact.latest();
      try {
         artifact.reserve(user, "aComment");
         artifact.release(user);
         artifact.release(user);
      } catch (VersionControlException e) {
         fail();
      }

      assertNotNull("The next modification of latest() is exist.", artifact.latest().getNextModification());
      // assertNotSame("latest() changed.", artifact.latest(), version);
      assertNull("latest() has got next modification.", artifact.latest().getPreviousModification());
      assertEquals("Artifact have 1 version.", artifact.getVersions().size(), 1);
   }

   @Ignore
   @Test
   public void testReleaseNonVersionedArtifact_reservedRelaesedTwoTimes() {
      DefaultUnitOfWork work = new DefaultUnitOfWork(true);
      artifact.setVersionControlled(false);
      // ArtifactVersion version = artifact.latest();
      try {
         artifact.reserve(user, "aComment");
         artifact.release(user);
         artifact.reserve(user, "aComment");
         artifact.release(user);
      } catch (VersionControlException e) {
         fail();
      }

      assertNotNull("The next modification of latest() is exist.", artifact.latest().getNextModification());
      // assertNotSame("latest() changed.", artifact.latest(), version);
      assertNull("latest() has got next modification.", artifact.latest().getPreviousModification());
      assertEquals("Artifact have 1 version.", artifact.getVersions().size(), 1);
      arepository.update(work, artifact);
      assertEquals("The artifact updated.", ((ArtifactSubClass) arepository.findById(work, artifact.getId())).getVersions().size(),
            1);
      work.finish();
   }

   private void addCommentsToArtifact( Artifact<?> artifact, CommentList commentList ) {
      for( Comment comment : commentList.getComments() ) {
         artifact.addComment( comment );
      }  
   }

   private void addRelatedArtifacts() {
      DefaultUnitOfWork work = new DefaultUnitOfWork(true);
      Artifact<?> anotherArtifact = new ArtifactSubClass("AnotherArtifactSubClass", typeFixture.getArtifactSubClassType(), user);
      Artifact<?> yetAnotherArtifact = new ArtifactSubClass("YetAnotherArtifactSubClass", typeFixture.getArtifactSubClassType(), user);
      artifact.addRelatedArtifact(anotherArtifact);
      artifact.addRelatedArtifact(yetAnotherArtifact);
      arepository.update(work, artifact);
      work.finish();
   }

   private void removeRelatedArtifacts() {
      DefaultUnitOfWork work = new DefaultUnitOfWork(true);
      artifact.removeRelatedArtifact(arepository.findByName(work, "AnotherArtifactSubClass"));
      artifact.removeRelatedArtifact(arepository.findByName(work, "YetAnotherArtifactSubClass"));
      arepository.update(work, artifact);
      arepository.delete(work, arepository.findByName(work, "AnotherArtifactSubClass"));
      arepository.delete(work, arepository.findByName(work, "YetAnotherArtifactSubClass"));
      work.finish();
   }
}