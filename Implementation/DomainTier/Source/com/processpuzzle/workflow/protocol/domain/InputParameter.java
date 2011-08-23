/*
 * Created on Nov 30, 2006
 */
package com.processpuzzle.workflow.protocol.domain;

import com.processpuzzle.artifact_type.domain.ArtifactType;

public class InputParameter extends ProtocolParameter {

   protected InputParameter() { super(); }

   InputParameter( String name, Protocol parentProtocol, ArtifactType type ) { 
      super( name, parentProtocol, type ); 
   }
}
