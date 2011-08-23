package com.processpuzzle.artifact_management.control;

import com.processpuzzle.application.control.control.CommandDispatcher;
import com.processpuzzle.artifact.domain.Artifact;

public class ShowArtifactCommand extends ArtifactCommand {

   public void init( CommandDispatcher dispatcher ) {
      super.init( dispatcher );
      dispatcher.getRequest().setAttribute( "subjectArtifact", subjectArtifact );
   }

   public Artifact<?> getArtifact() {
      return subjectArtifact;
   }

   public String getName() {
      return "Show";
   }

   public String execute( CommandDispatcher dispatcher ) throws Exception {
      // felul lehet/kell irni ezt a metodust
      return "/ProcessInstantiation/GenericArtifact.jsp";
   }

   protected void retrieveRequestParameters( CommandDispatcher dispatcher ) {
   // TODO Auto-generated method stub

   }

   protected void retrieveResponseDocument() {
   // TODO Auto-generated method stub

   }
}