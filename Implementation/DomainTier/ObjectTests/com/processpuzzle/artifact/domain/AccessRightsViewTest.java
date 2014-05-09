/*
 * Created on May 18, 2006
 */
package com.processpuzzle.artifact.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.application.security.domain.AccessRight;
import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.application.security.domain.UserFactory;
import com.processpuzzle.application.security.domain.UserRepository;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.domain.Application;
import com.processpuzzle.artifact.domain.AccessRightsView;
import com.processpuzzle.artifact_type.domain.ArtifactTypeTestFixture;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;
import com.processpuzzle.sharedfixtures.domaintier.ProcessPuzzleContextFixture;
import com.processpuzzle.user_session.domain.UserRequestManager;

public class AccessRightsViewTest {
   private ProcessPuzzleContextFixture applicationContextFixture;
   private ArtifactTypeTestFixture typeFixture;
   private ArtifactSubClass anArtifact;
   private static ProcessPuzzleContext applicationContext;
   private static UserFactory userFactory;
   private static UserRepository userRepository;
   private static ArtifactSubClassRepository artifactSubClassRepository;
   private User currentUser;
   private User john;
   private User betty;

   @Before
   public void setUp() throws Exception {
      applicationContextFixture = ProcessPuzzleContextFixture.getInstance( DomainTierTestConfiguration.APPLICATION_CONFIGURATION_DESCRIPTOR_PATH );
      applicationContextFixture.setUp();
      
      applicationContext = applicationContextFixture.getApplicationContext();
      
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      typeFixture = new ArtifactTypeTestFixture( null );
      typeFixture.setUp();

      applicationContext.setUp( Application.Action.start );
      userFactory = applicationContext.getEntityFactory( UserFactory.class );
      userRepository = applicationContext.getRepository( UserRepository.class );
      artifactSubClassRepository = applicationContext.getRepository( ArtifactSubClassRepository.class );

      currentUser = UserRequestManager.getInstance().currentUser();
      anArtifact = new ArtifactSubClass( "AnArtifact", typeFixture.getArtifactSubClassType(), currentUser );
      artifactSubClassRepository.addArtifactSubClass( anArtifact );

      john = userFactory.createUser( "Jon.Smith", "psw" );
      AccessRight jonsRights = john.addRightFor( anArtifact );
      jonsRights.setCanCreate( true );
      jonsRights.setCanDelete( true );
      jonsRights.setCanRead( true );
      jonsRights.setCanModify( true );
      userRepository.addUser( work, john );

      betty = userFactory.createUser( "Betty.Smith", "psw" );
      AccessRight bettysRights = betty.addRightFor( anArtifact );
      bettysRights.setCanCreate( false );
      bettysRights.setCanDelete( false );
      bettysRights.setCanRead( true );
      bettysRights.setCanModify( true );
      userRepository.addUser( work, betty );
      work.finish();
   }

   @After
   public void tearDown() throws Exception {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      artifactSubClassRepository.deleteArtifactSubClass( anArtifact );
      userRepository.deleteUser( work, john );
      userRepository.deleteUser( work, betty );
      work.finish();
      typeFixture.tearDown();
   }

   @Ignore
   @Test
   public final void getAccessRights() {
      AccessRightsView view = (AccessRightsView) anArtifact.getAvailableViews().get( "AccessRightsView" );
      view.getRightsForArtifact();
      assertNotNull( "An artifact has an 'AccessRightsView' view.", view );
      assertNotNull( "The AccessRigthtsView provides rights.", view.getRightsForArtifact() );
      assertEquals( "The rights collection has 2 AccessRight object.", 2, view.getRightsForArtifact().size() );
   }
}
