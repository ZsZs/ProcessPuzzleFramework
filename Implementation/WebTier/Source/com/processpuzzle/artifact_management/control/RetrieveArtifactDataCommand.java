/*
 * Created on Oct 22, 2006
 */
package com.processpuzzle.artifact_management.control;

import com.processpuzzle.application.control.control.CommandDispatcher;

/**
 * @author zsolt.zsuffa
 */
public class RetrieveArtifactDataCommand extends ArtifactActionCommand {
   public static final Object COMMAND_NAME = "RetrieveArtifactData";

   public void init(CommandDispatcher dispatcher) {
      super.init( dispatcher );
   }
   
   protected void doAction() {
      String dataAsXml = subjectArtifact.getAsXml();
      if( dataAsXml != null ) {
         actionResponse.addStringToBody(dataAsXml);
         actionResponse.setOutcome(true);
      }
      else actionResponse.setOutcome(false);
   }

}
