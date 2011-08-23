package com.processpuzzle.internalization.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleException;

public class InvalidResourcePathException extends ProcessPuzzleException {
   private static final long serialVersionUID = 4990821965292297031L;
   private static String defaultMessagePattern = "Path: ''{0}'' to the resource bundle files is invalid.";

   public InvalidResourcePathException( String resourcePath, Throwable cause ) {
      super( ExceptionHelper.defineMessage(
            InvalidResourcePathException.class,
            new Object[] {resourcePath},
            defaultMessagePattern ),
            cause);
   }
   
   public InvalidResourcePathException( String resourcePath ) {
      this( resourcePath, null );
   }

   protected static Object[] defineMessage( String source ) {
      formatPattern = "Path: ''{0}'' to the resource bundle files is invalid.";
      Object[] arguments = {source};
      return arguments;
   }
}
