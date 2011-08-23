/*
 * Created on Jun 30, 2006
 */
package com.processpuzzle.artifact.domain;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.security.domain.AccessRight;
import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.application.security.domain.UserFactory;
import com.processpuzzle.artifact_type.domain.ArtifactTypeTestFixture;
import com.processpuzzle.artifact_type_group.domain.ArtifactTypeGroup;
import com.processpuzzle.generictests.ApplicationContextAwareTest;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.sharedfixtures.domaintier.ProcessPuzzleContextFixture;
import com.processpuzzle.user_session.domain.UserRequestManager;

public class ArtifactFolderTest extends ApplicationContextAwareTest {
   private ProcessPuzzleContextFixture applicationContextFixture;
   private ArtifactTypeTestFixture typeFixture = null;
   private ProcessPuzzleContext config = null;
   private ArtifactTypeGroup typeGroup = null;
   private ArtifactFolder rootFolder = null;
   private ArtifactFolder firstChildFolder = null;
   private ArtifactFolder secondChildFolder = null;
   private Artifact<?> oneArtifact = null;
   private Artifact<?> anotherArtifact = null;
   private User johnUser = null;
   private ArtifactFolderRepository artifactFolderRepository;
   private ProcessPuzzleContext applicationContext;
   private ArtifactFolderFactory artifactFolderFactory;
   private User currentUser;
   private UserFactory userFactory;

   @Before
   public void setUp() throws Exception {
      
      typeFixture = ArtifactTypeTestFixture.getInstance( applicationContext );
      typeFixture.setUp();

      userFactory = applicationContext.getEntityFactory( UserFactory.class );
      artifactFolderFactory = applicationContext.getEntityFactory( ArtifactFolderFactory.class );

      artifactFolderRepository = applicationContext.getRepository( ArtifactFolderRepository.class );

      currentUser = UserRequestManager.getInstance().currentUser();

      oneArtifact = new ArtifactSubClass( "oneArtifact", typeFixture.getArtifactSubClassType(), currentUser );
      anotherArtifact = new ArtifactSubClass( "anotherArtifact", typeFixture.getArtifactSubClassType(), currentUser );

      rootFolder = artifactFolderFactory.create( null, "rootFolder" );
      rootFolder.addChildArtifact( (Artifact<?>) oneArtifact );

      firstChildFolder = artifactFolderFactory.create( rootFolder, "firstChildFolder" );
      firstChildFolder.addChildArtifact( anotherArtifact );

      secondChildFolder = artifactFolderFactory.create( rootFolder, "secondChildFolder" );

      johnUser = userFactory.createUser( "john.smith", "password" );
      AccessRight johnsRight = johnUser.addRightFor( oneArtifact );
      johnsRight.setCanCreate( true );
      johnsRight.setCanDelete( true );
      johnsRight.setCanRead( true );
      johnsRight.setCanModify( true );
   }

   @After
   public void tearDown() throws Exception {
      typeFixture.tearDown();
      oneArtifact = null;
      anotherArtifact = null;
      rootFolder = null;
      firstChildFolder = null;
      secondChildFolder = null;
   }

   // public final void testChildArtifacts_ForRelationships () {
   // assertEquals("The number of 'parentFolder's childfolder is:", 3,
   // rootFolder.getChildArtifacts().size());
   // assertTrue("'firstChildFolder' is a child artifact of 'parentFolder'",
   // rootFolder.getChildArtifacts().contains(firstChildFolder));
   // assertTrue("'oneArtifact' is a child artifact of 'parentFolder'",
   // rootFolder.getChildArtifacts().contains(oneArtifact));
   // assertEquals("'firstChildFolder's containing folder is:", rootFolder,
   // firstChildFolder.getContainingFolder());
   // assertEquals("'oneArtifact's containing folder is:", rootFolder,
   // oneArtifact.getContainingFolder());
   // }
   //   
   @Test
   public final void relationships_ForPersistence() {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      artifactFolderRepository.add( work, rootFolder );
      assertNotNull( rootFolder.getId() );
      assertNotNull( artifactFolderRepository.findByPath( work, rootFolder.getName() ) );
      ArtifactFolder a = artifactFolderRepository.findByPath( work, rootFolder.getName() + "." + "firstChildFolder" );
      assertNotNull( a );
      firstChildFolder = artifactFolderFactory.create( firstChildFolder, "subFirstChildFolder" );
      artifactFolderRepository.update( work, rootFolder );
      a = artifactFolderRepository.findByPath( work, "rootFolder.firstChildFolder." );
      // assertNotNull(a);

      ArtifactFolder folder3 = artifactFolderFactory.create( firstChildFolder, "childFolder3" );
      ArtifactFolder folder4 = artifactFolderFactory.create( folder3, "childFolder4" );
      ArtifactFolder folder5 = artifactFolderFactory.create( folder4, "childFolder5" );
      ArtifactFolder folder6 = artifactFolderFactory.create( folder5, "childFolder6" );
      artifactFolderFactory.create( folder6, "childFolder7" );
      artifactFolderRepository.update( work, rootFolder );
      a = artifactFolderRepository.findByPath( work,
            "rootFolder.firstChildFolder.subFirstChildFolder.childFolder3.childFolder4.childFolder5.childFolder6" );
      assertNotNull( a );

      //      
      // ArtifactFolder anotherInstanceOfParentFolder = (ArtifactFolder)
      // artifactRepository.findArtifactById(parentFolder.getId());
      // assertEquals("The number of 'parentFolder's childfolder is:", 3,
      // anotherInstanceOfParentFolder.getChildArtifacts().size());
      //      
      // artifactRepository.deleteArtifact(parentFolder);
      artifactFolderRepository.delete( work, rootFolder );
      work.finish();

   }

   @Test
   public void addSameSubFolder() {
      ArtifactFolder firstChildFolder = artifactFolderFactory.create( rootFolder, "firstChildFolder" );
   }
}
