package com.processpuzzle.fundamental_types.uniqueidentifier.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleException;


public class InvalidUniqueIdentifier extends ProcessPuzzleException {
   private static final long serialVersionUID = -3687808736297388780L;
   private static String defaultMessagePattern = "Unique identifier: ''{0}'' is invalid.";
   
   public InvalidUniqueIdentifier( String identifier ) {
      super( ExceptionHelper.defineMessage( 
            InvalidUniqueIdentifier.class, 
            new Object[] { identifier},
            defaultMessagePattern ) );
   }
}
