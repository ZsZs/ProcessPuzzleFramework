package com.processpuzzle.artifact_type.domain;

import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

public class ArtifactViewInstantiationException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = 5410648197417169715L;
   private static String messageTemplate = "Failed to instantiate view:''{0}'' of artifact:''{1}''";
   private Artifact<?> artifact;
   private String viewClassName;

   public ArtifactViewInstantiationException( Artifact<?> artifact, String viewClassName, Throwable cause ) {
      super( ExceptionHelper.defineMessage(
            ArtifactViewInstantiationException.class, new Object[] { viewClassName, artifact.getClass().getName() }, messageTemplate ), cause );
      this.artifact = artifact;
      this.viewClassName = viewClassName;
   }

   public Artifact<?> getArtifact() { return artifact;}
   public String getViewClassName() { return viewClassName; }
}
