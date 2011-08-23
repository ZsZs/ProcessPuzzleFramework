/*
 * Created on Apr 12, 2006
 */
package com.processpuzzle.artifact.domain;

import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.artifact.domain.ArtifactView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;

/**
 * @author zsolt.zsuffa
 */
public class ArtifactViewSubClass extends ArtifactView {

   public ArtifactViewSubClass(Artifact<?> artifact, String name, ArtifactViewType type) {
      super(artifact, name, type);
   }

   public void initializeView() {
      // TODO Auto-generated method stub
      
   }
}