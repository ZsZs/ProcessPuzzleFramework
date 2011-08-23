/*
 * Created on Jul 15, 2006
 */
package com.processpuzzle.artifact.domain;

import com.processpuzzle.artifact_type.domain.ArtifactViewType;

/**
 * @author zsolt.zsuffa
 */
public abstract class EditableTextView<A extends Artifact<?>> extends ArtifactView<A> {
   
   public EditableTextView( A artifact, String name, ArtifactViewType type) {
      super( artifact, name, type );
   }
   
   protected abstract String retrieveObjectsAsXml();
   
   protected abstract void updateObjectsFromXml(String data);
}
