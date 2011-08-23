package com.processpuzzle.artifact_management.control;

import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.fundamental_types.domain.ExceptionHelper;

public class ShowArttifactPrintViewCommentException extends ArtifactCommandException {
   private static final long serialVersionUID = 8694962995277908571L;
   private static String defaultMessagePattern = "Generating print view for artifact: '''{0}''' with style sheet: '''{1}''' causer error.";
   private Artifact<?> subjectArtifact;
   private String xslPath;
   
   public ShowArttifactPrintViewCommentException( Artifact<?> artifact, String xslPath, Throwable cause ) {
      super( ExceptionHelper.defineMessage( 
            ShowArttifactPrintViewCommentException.class, new Object[] {artifact.getName(), xslPath}, defaultMessagePattern ), cause );
      this.subjectArtifact = artifact;
      this.xslPath = xslPath;
   }

   public Artifact<?> getSubjectArtifact() { return subjectArtifact; }
   public String getXslPath() { return xslPath; }
}
