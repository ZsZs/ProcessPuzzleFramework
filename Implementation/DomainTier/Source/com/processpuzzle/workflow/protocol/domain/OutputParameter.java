/*
 * Created on Nov 30, 2006
 */
package com.processpuzzle.workflow.protocol.domain;

import com.processpuzzle.artifact_type.domain.ArtifactType;

public class OutputParameter extends ProtocolParameter {

   protected OutputParameter() { super(); }

   OutputParameter( String parameterName, Protocol parentProtocol, ArtifactType type) { 
      super( parameterName, parentProtocol, type ); 
   }

}
