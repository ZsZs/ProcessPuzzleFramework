/*
 * Created on Apr 12, 2006
 */
package com.processpuzzle.artifact.domain;

import java.io.InputStream;

import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.artifact.domain.PrintView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;

/**
 * @author zsolt.zsuffa
 */
public class TestEntityDataSheet_PrintView extends PrintView {

   public TestEntityDataSheet_PrintView(Artifact<?> artifact, String name, ArtifactViewType type) {
      super(artifact, name, type);
   }

   public InputStream getPDF() {
      // TODO Auto-generated method stub
      return null;
   }

   public String buildXml() {
      return null;
      
   }

   public void initializeView() {
      // TODO Auto-generated method stub      
   }
}