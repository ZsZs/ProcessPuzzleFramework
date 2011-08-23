package com.processpuzzle.artifact_management.control;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

public class ArtifactCommandException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = -8556626625480894399L;
   private static String defaultMessagePattern = "The artifact command caused error: ''{0}''. See the log for more details.";
   private String message;

   public ArtifactCommandException( String message, Throwable cause ) {
      super( ExceptionHelper.defineMessage( ArtifactCommandException.class, new Object[] {message}, defaultMessagePattern ), cause );
      this.message = message;
   }

   protected ArtifactCommandException( ExceptionHelper helper, Throwable cause ) {
      super( helper, cause );
   }
   
   public String getMessage() { return message; }
}
