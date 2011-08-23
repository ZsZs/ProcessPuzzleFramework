/*
 * Created on Jul 10, 2006
 */
package com.processpuzzle.artifact_type_group.domain;

import com.processpuzzle.persistence.domain.GenericFactory;

/**
 * @author zsolt.zsuffa
 */
public class ArtifactTypeGroupFactory extends GenericFactory<ArtifactTypeGroup> {

   public static ArtifactTypeGroup createArtifactTypeGroup(String typeGroupName) {
      ArtifactTypeGroup typeGroup = new ArtifactTypeGroup( typeGroupName );
      checkEntityIdentityCollition( typeGroup.getDefaultIdentity());
      return typeGroup;
   }
}
