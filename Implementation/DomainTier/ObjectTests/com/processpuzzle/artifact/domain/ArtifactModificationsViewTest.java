/*
 * Created on May 18, 2006
 *
 */
package com.processpuzzle.artifact.domain;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.artifact.domain.Modification;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.application.security.domain.UserFactory;
import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.artifact.domain.ArtifactModificationsView;
import com.processpuzzle.artifact_type.domain.ArtifactTypeTestFixture;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;
import com.processpuzzle.sharedfixtures.domaintier.ProcessPuzzleContextFixture;
import com.processpuzzle.user_session.domain.UserRequestManager;

/**
 * @author zsolt.zsuffa
 */
public class ArtifactModificationsViewTest {
   private ProcessPuzzleContextFixture applicationContextFixture;
   private ProcessPuzzleContext applicationContext;
   private UserFactory userFactory;
   private ArtifactTypeTestFixture typeFixture = null;
   private Artifact<?> anArtifact = null;
   private User currentUser;
   private User firstModifier;
   private User secondModifier;
   private ArtifactModificationsView view = null;

   @Before
   public void setUp() throws Exception {
      applicationContextFixture = ProcessPuzzleContextFixture.getInstance( DomainTierTestConfiguration.APPLICATION_CONFIGURATION_DESCRIPTOR_PATH );
      applicationContextFixture.setUp();
      
      applicationContext = applicationContextFixture.getApplicationContext();
      
      typeFixture = ArtifactTypeTestFixture.getInstance( applicationContext );
      typeFixture.setUp();

      userFactory = applicationContext.getEntityFactory( UserFactory.class );

      firstModifier = userFactory.createUser( "Bárczi Benõ", "password" );
      secondModifier = userFactory.createUser( "Gipsz Jakab", "password" );

      currentUser = UserRequestManager.getInstance().currentUser();

      anArtifact = new ArtifactSubClass( "AnArtifact", typeFixture.getArtifactSubClassType(), currentUser );
      anArtifact.reserve( firstModifier, "First modification" );
      anArtifact.release( firstModifier );
      anArtifact.reserve( secondModifier, "Second modification" );
      anArtifact.release( secondModifier );

      view = (ArtifactModificationsView) anArtifact.getView( ArtifactTypeTestFixture.MODIFICATIONS_VIEW_TYPE_NAME );
   }

   @After
   public void tearDown() throws Exception {
      typeFixture.tearDown();
      anArtifact = null;
   }

   @Ignore
   @Test
   public final void testModifications() {
      // / assertEquals("The number of collected modifications:", 2, view.getModifications().size());
      assertEquals( "The first modification was made by:", firstModifier, ((Modification) view.getModifications().get( 0 )).getModifier() );
   }
}
