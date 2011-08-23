/*
 * Created on Jul 15, 2006
 */
package com.processpuzzle.artifact.domain;

import com.processpuzzle.artifact_type.domain.ArtifactViewType;

/**
 * @author zsolt.zsuffa
 */
public class ArtifactCommentsView extends EditableTextView<Artifact<?>> {

   public ArtifactCommentsView( Artifact<?> artifact, String name, ArtifactViewType type) {
      super( artifact, name, type );
   }

   public void initializeView() {
      // TODO Auto-generated method stub      
   }

   protected String retrieveObjectsAsXml() {
      return null;     
   }

   protected void updateObjectsFromXml() {
      // TODO Auto-generated method stub
   }

   protected void updateObjectsFromXml(String data) {
      // TODO Auto-generated method stub
      
   }
}
