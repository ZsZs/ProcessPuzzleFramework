package com.processpuzzle.artifact_management.control;

import java.security.InvalidParameterException;

import com.processpuzzle.application.control.control.CommandDispatcher;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;

public class ShowArtifactViewCommand extends ArtifactViewCommand {

   public void init( CommandDispatcher dispatcher ) {
      super.init( dispatcher );
   }

   protected void retrieveResponseDocument() {
      ArtifactViewType viewType = subjectArtifactView.getType();
      if( viewType != null )
         responseDocument = viewType.getPresentationUri();
      else
         throw new InvalidParameterException( "Missing view type!" );
   }

   public String getName() {
      return "ShowView";
   }
}