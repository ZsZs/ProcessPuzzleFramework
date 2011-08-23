/*
 * Created on May 4, 2006
 */
package com.processpuzzle.artifact.domain;

import com.processpuzzle.artifact.artifact.ArtifactList;
import com.processpuzzle.artifact.domain.ArtifactListView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;

/**
 * @author zsolt.zsuffa
 */
public class ArtifactListViewSubClass extends ArtifactListView<ArtifactList<?>> {

   public ArtifactListViewSubClass( ArtifactList<?> artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );
   }

   public void initializeView() {
   // TODO Auto-generated method stub
   }
}