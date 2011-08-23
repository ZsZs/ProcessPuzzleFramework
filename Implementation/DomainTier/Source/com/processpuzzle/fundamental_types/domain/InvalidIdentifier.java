package com.processpuzzle.fundamental_types.domain;

public class InvalidIdentifier extends ProcessPuzzleException {
   private static final long serialVersionUID = -5819096818529663038L;
   private static String defaultMessagePattern = "Identifier: ''{0}'' is invalid.";
   
   public InvalidIdentifier( String identifier ) {
      super( ExceptionHelper.defineMessage( 
            InvalidIdentifier.class, 
            new Object[] { identifier},
            defaultMessagePattern ) );
   }
}
