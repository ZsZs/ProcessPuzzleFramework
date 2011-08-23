/*
 * Created on Apr 28, 2006
 */
package com.processpuzzle.artifact.domain;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.application.security.domain.UserFactory;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.artifact.domain.PropertyView;
import com.processpuzzle.artifact.domain.RelatedArtifactsListView;
import com.processpuzzle.artifact_type.domain.ArtifactTypeTestFixture;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;
import com.processpuzzle.sharedfixtures.domaintier.ProcessPuzzleContextFixture;

/**
 * @author zsolt.zsuffa
 */
public class RelatedArtifactListViewTest {
   private ProcessPuzzleContextFixture applicationContextFixture;
   private ArtifactTypeTestFixture typeFixture = null;
   private Artifact<?> anArtifact;
   private Artifact<?> aRelatedArtifact;
   private Artifact<?> anotherRelatedArtifact;
   private RelatedArtifactsListView listView;
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
      
      user = userFactory.createUser( "P", "B");
      anArtifact = new ArtifactSubClass("anArtifact", typeFixture.getArtifactSubClassType(), user);
      aRelatedArtifact = new ArtifactSubClass("aRelatedArtifact", typeFixture.getArtifactSubClassType(), user);
      anotherRelatedArtifact = new ArtifactSubClass("anotherRelatedArtifact", typeFixture.getArtifactSubClassType(), user);
      anArtifact.addRelatedArtifact(aRelatedArtifact);
      anArtifact.addRelatedArtifact(anotherRelatedArtifact);

      listView = (RelatedArtifactsListView) anArtifact.findViewByName("SpecializedRelatedArtifactListView");
   }

   @After
   public void tearDown() throws Exception {
      typeFixture.tearDown();
      anArtifact = null;
   }

   @Ignore @Test
   public final void testGetPropertyViews() {
      assertEquals("The artifact has 2 related artifact, therefore it's RelatedArtifactListView has 2 PropertyViews", 2, listView
            .getRelatedArtifactsPropertyViews().size());
   }

   @Ignore @Test
   public final void testRelatedArtifactProperties() {
      int numOfFoundArtifact = 0;

      for (Iterator<?> iterator = listView.getRelatedArtifactsPropertyViews().iterator(); iterator.hasNext();) {
         PropertyView<?> propertyView = (PropertyView<?>) iterator.next();
         if (propertyView.getArtifactName().equals("aRelatedArtifact"))
            numOfFoundArtifact++;
         if (propertyView.getArtifactName().equals("anotherRelatedArtifact"))
            numOfFoundArtifact++;
      }

      assertEquals("We could find both related artifact's property view in the 'listView.getPropertyViews' collection.", 2,
            numOfFoundArtifact);
   }
}