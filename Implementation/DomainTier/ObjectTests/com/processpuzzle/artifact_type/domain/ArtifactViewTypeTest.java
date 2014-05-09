package com.processpuzzle.artifact_type.domain;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ArtifactViewTypeTest {
   private ArtifactViewType artifactViewType;

   @Test
   public final void instantiation_requiresNameAndPresentationUri() {
      
      artifactViewType = new ArtifactViewType( "artfactViewType", "presentationUri" );
      assertThat( artifactViewType.getName(), equalTo( "artfactViewType" ));
      assertThat( artifactViewType.getPresentationUri(), equalTo( "presentationUri" ));
   }
}