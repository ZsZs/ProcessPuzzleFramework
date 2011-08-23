/*
 * Created on Apr 12, 2006
 */
package com.processpuzzle.artifact.domain;

import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.artifact.domain.PropertyView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;

/**
 * @author zsolt.zsuffa
 */
public class PropertyViewSubClass extends PropertyView {

   public PropertyViewSubClass( Artifact<?> artifact ) {
      super( artifact, "PropertyViewSubClass", null );
   }

   public PropertyViewSubClass( Artifact artifact, ArtifactViewType viewType ) {
      super( artifact, "PropertyViewSubClass", viewType );
   }

   protected void initializeProperties() {
      properties.put( "testPropertyKey_1", "testPropertyValue_1" );
      properties.put( "testPropertyKey_2", "testPropertyValue_2" );
   }
}
