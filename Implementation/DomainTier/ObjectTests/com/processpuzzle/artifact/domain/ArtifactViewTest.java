package com.processpuzzle.artifact.domain;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.application.security.domain.UserFactory;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.artifact.domain.ArtifactView;
import com.processpuzzle.artifact_type.domain.ArtifactTypeTestFixture;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;
import com.processpuzzle.sharedfixtures.domaintier.ProcessPuzzleContextFixture;

public class ArtifactViewTest {
   private ProcessPuzzleContextFixture applicationContextFixture;
   private ArtifactTypeTestFixture typeFixture = null;
   private Artifact<?> artifact;
   private ArtifactView<?> artifactView;
   private User user;
   private ProcessPuzzleContext applicationContext;
   private UserFactory userFactory;

   @Before
   public void setUp() throws Exception {
      applicationContextFixture = ProcessPuzzleContextFixture.getInstance( DomainTierTestConfiguration.APPLICATION_CONFIGURATION_DESCRIPTOR_PATH );
      applicationContextFixture.setUp();
      
      applicationContext = applicationContextFixture.getApplicationContext();

      typeFixture = ArtifactTypeTestFixture.getInstance( applicationContext );
      typeFixture.setUp();

      userFactory = applicationContext.getEntityFactory( UserFactory.class );

      user = userFactory.createUser( "P", "psw" );
      artifact = new ArtifactSubClass( "ArtifactForTest", typeFixture.getArtifactSubClassType(), user );
      artifactView = artifact.getPropertyView();
   }

   @After
   public void tearDown() throws Exception {
      typeFixture.tearDown();
      artifactView = null;
   }

   @Ignore
   @Test
   public void testgetName() {
      assertEquals( "ArtifactView's name: ", "PropertyViewSubClass", artifactView.getName() );
   }

   @Ignore
   @Test
   public void testGetType() {
      assertEquals( "ArtifactView's type name: ", "PropertyViewType", artifactView.getType().getName() );
   }
}