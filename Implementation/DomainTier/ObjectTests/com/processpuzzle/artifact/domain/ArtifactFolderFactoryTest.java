package com.processpuzzle.artifact.domain;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.persistence.domain.EntityIdentityCollitionException;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class ArtifactFolderFactoryTest extends ArtifactFactoryTest<ArtifactFolderFactory, ArtifactFolderFactoryTestFixture, ArtifactFolder> {
   private static final String CHILD_FOLDER_NAME = "Test folder";
   private ArtifactFolder rootFolder;
   private ArtifactFolder childFolder;
   private RootArtifactFolderFactory rootFolderFactory;
   private ArtifactFolderFactory artifactFolderFactory;
   private ArtifactFolderRepository artifactFolderRepository;

   public ArtifactFolderFactoryTest() {
      super( DomainTierTestConfiguration.FIXTURE_CONTAINER_DEFINITION_PATH );
   }

   @Ignore
   @Override
   @Test
   public void create_ForSuccess() {
      // EXCERCISE:
      rootFolder = rootFolderFactory.create();
      childFolder = artifactFolderFactory.create( rootFolder, CHILD_FOLDER_NAME );

      // VERIFY:
      assertThat( rootFolder.getName(), equalTo( RootArtifactFolder.ROOT_ARTIFACT_FOLDER_NAME ) );
      assertThat( childFolder.getContainingFolder(), equalTo( rootFolder ) );
      assertThat( childFolder.getPath(), equalTo( rootFolder.getName() + ArtifactFolder.PATH_SEPARATOR + childFolder.getName() ) );
   }

   @Ignore
   @Override
   @Test( expected = EntityIdentityCollitionException.class )
   public void create_ForCollision() {
      // SETUP:
      rootFolder = rootFolderFactory.create();
      artifactFolderRepository.add( rootFolder );

      // EXCERCISE:
      rootFolderFactory.create();
   }

   @Ignore
   @Test( expected = EntityIdentityCollitionException.class )
   public void create_ShouldThowExceptionWhenFolderNameAlreadyExistInParentFolder() {
      // SETUP:
      rootFolder = rootFolderFactory.create();
      childFolder = artifactFolderFactory.create( rootFolder, CHILD_FOLDER_NAME );
      artifactFolderRepository.add( rootFolder );

      // EXCERCISE:
      artifactFolderFactory.create( rootFolder, CHILD_FOLDER_NAME );
   }

   @Ignore
   @Test
   public final void createArtifactFolder_ForChildFolder() {
      ArtifactFolder childFolder = artifactFolderFactory.create( rootFolder, "aFolder" );
      assertNotNull( childFolder );
      assertEquals( "The 'childFolder's containing folder is:", rootFolder, childFolder.getContainingFolder() );
      assertTrue( "The 'rootFolder's contains the new 'childFolder':", rootFolder.getChildArtifacts().contains( childFolder ) );
   }

   @Ignore
   @Test
   public final void createArtifactVersion() {
      User user = userFactory.createUser( "Nagy Kis", "password" );
      Date creation = new Date( System.currentTimeMillis() );
      ArtifactVersion version = ArtifactFactory.createArifactVersion( "AVersion", user, creation );
      assertNotNull( "The artifact version object is exist.", version );
      assertNotNull( "The artifact version's date of creation is not null.", version.getCreation() );
      assertEquals( "The date of creration of artifact version is " + creation.getTime(), version.getCreation().getTime(), creation.getTime() );
      assertNotNull( "The artifact version's name is not null.", version.getName() );
      assertEquals( "The name of artifact version is same 'AVersion'.", version.getName(), "AVersion" );
      assertNotNull( "The responsible of artifact version is not null.", version.getCreator() );
      assertEquals( "The responsible of artifact version is 'Kis Nagy'.", version.getCreator(), user );
   }
}