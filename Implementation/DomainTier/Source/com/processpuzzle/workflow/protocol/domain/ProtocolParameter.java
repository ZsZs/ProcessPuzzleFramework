/*
 * Created on Nov 30, 2006
 */
package com.processpuzzle.workflow.protocol.domain;

import com.processpuzzle.artifact_type.domain.ArtifactType;

public abstract class ProtocolParameter {
   private Integer id = null;
   private String name = null;
   private Protocol parentProtocol = null;
   private ArtifactType type = null;
   
   //Constructors
   ProtocolParameter( String name, Protocol parentProtocol, ArtifactType type ) {
      this.name = name;
      this.parentProtocol = parentProtocol;
      this.type = type;
   }

   protected ProtocolParameter() {}
   
   //Getters and Setters
   public Integer getId() { return id; }
   public String getName() { return name; }
   public Protocol getParentProtocol() { return parentProtocol; }
   public ArtifactType getType() { return type; }
}
