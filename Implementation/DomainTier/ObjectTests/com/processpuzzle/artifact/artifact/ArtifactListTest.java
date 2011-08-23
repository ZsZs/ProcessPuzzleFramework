package com.processpuzzle.artifact.artifact;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.artifact.domain.ArtifactListFactory;

public class ArtifactListTest {
   private ArtifactList<?> artifactList = null;
   private ProcessPuzzleContext applicationContext;
   private ArtifactListFactory<ArtifactList<?>> artifactListFactory;
   
   @Before
   public void setUp() throws Exception {
      artifactListFactory = applicationContext.getEntityFactory( ArtifactListFactory.class );
      artifactList = artifactListFactory.create(); //("AnArtifactList", ArtifactTypeFactory.createArtifactType( "anArtifactType", "ArtifactGroup" ));
   }

   @After
   public void tearDown() throws Exception {
      artifactList = null;
   }

   @Ignore @Test
   public void testAvailableView() {
      assertTrue("An artifactList has at least one view.", artifactList.getAvailableViews().size() >= 1);
      assertNotNull("A PropertyView is required.", artifactList.getPropertyView());
   }

   @Ignore @Test
   public void testPropertyView() {
      assertNotNull("A PropertyView is required.", artifactList.getPropertyView());
   }

   @Ignore @Test
   public void testListView() {
      assertNotNull("A ListView is required.", artifactList.getListView());
   }
}