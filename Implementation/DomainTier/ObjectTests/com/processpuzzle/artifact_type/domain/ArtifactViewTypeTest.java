/*
 * Created on Apr 17, 2006
 */
package com.processpuzzle.artifact_type.domain;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.processpuzzle.artifact_type.domain.ArtifactViewType;

/**
 * @author zsolt.zsuffa
 */
public class ArtifactViewTypeTest {

   private ArtifactViewType artifactViewType;
   
   @Before
   public void setUp() throws Exception {}

   @After
   public void tearDown() throws Exception {}

   @Test
   public final void artfactViewType_ForConstructor () {
      artifactViewType = new ArtifactViewType("artfactViewType","presentationUri");
      assertEquals("Artifact's name: ","artfactViewType", artifactViewType.getName());
      assertEquals("Artifact's uri: ","presentationUri", artifactViewType.getPresentationUri());
   }
}