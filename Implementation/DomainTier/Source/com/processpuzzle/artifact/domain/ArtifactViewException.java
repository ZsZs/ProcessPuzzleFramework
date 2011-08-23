package com.processpuzzle.artifact.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

public class ArtifactViewException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = -3846192771409245727L;
   private static String defaultMessagePattern = "The artifact view caused error: '''{0}'''. See the log for more details.";
   private String message;

   public ArtifactViewException( String message, Throwable cause ) {
      super( ExceptionHelper.defineMessage( ArtifactViewException.class, new Object[] {message}, defaultMessagePattern ) );
      this.message = message;
   }

   public String getMessage() { return message; }
}
