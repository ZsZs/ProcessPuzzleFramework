package com.processpuzzle.artifact.artifact;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.artifact.domain.ArtifactListFactory;
import com.processpuzzle.litest.template.ArtifactTestFixture;
import com.processpuzzle.litest.template.ArtifactTestTemplate;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public abstract class ArtifactListTest<A extends Artifact<A>, F extends ArtifactTestFixture<A>> extends ArtifactTestTemplate<A, F>{
   protected ArtifactListFactory<ArtifactList<?>> artifactListFactory;

   //Constructors
   public ArtifactListTest() {
      super( DomainTierTestConfiguration.FIXTURE_CONTAINER_DEFINITION_PATH );
   }

   //Test methods
   @Test public void testAvailableView() {
      assertTrue("An artifactList has at least one view.", sut.getAvailableViews().size() >= 1);
      assertNotNull("A PropertyView is required.", sut.getPropertyView());
   }

   @Test public void testPropertyView() {
      assertNotNull("A PropertyView is required.", sut.getPropertyView());
   }
}