package com.processpuzzle.artifact.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

public class NoParentFolderException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = 7790754372857010943L;
   private static String defaultMessagePattern = "Artifact ''{0}'' doesn't have any containing folder.";
   private Artifact<?> subjectArtifact;
   
   public NoParentFolderException( Artifact<?> subjectArtifact ) {
      super( ExceptionHelper.defineMessage( NoParentFolderException.class, new Object[] { subjectArtifact.getName()}, defaultMessagePattern ) );
      this.subjectArtifact = subjectArtifact;
   }

   public Artifact<?> getSubjectArtifact() { return subjectArtifact; }
}
