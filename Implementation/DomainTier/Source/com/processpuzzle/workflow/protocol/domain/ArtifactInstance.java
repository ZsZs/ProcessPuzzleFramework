/*
 * Created on Nov 28, 2006
 */
package com.processpuzzle.workflow.protocol.domain;

import com.processpuzzle.artifact_type.domain.ArtifactType;

public class ArtifactInstance {
   private Integer Id = null;
   private String name = null;
   private ArtifactType type = null;

   private ArtifactInstance() {}
 
   public ArtifactInstance( ArtifactType type, String name ) {
      this.type = type;
      this.name = name;
   }  
   
   public Integer getId() { return Id; }
   public String getName() { return name; }
   public ArtifactType getType() { return type; }
}
