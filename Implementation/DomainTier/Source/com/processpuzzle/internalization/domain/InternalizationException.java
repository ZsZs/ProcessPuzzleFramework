package com.processpuzzle.internalization.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleException;

public class InternalizationException extends ProcessPuzzleException {
   private static final long serialVersionUID = 1L;
   public static String defaultMessagePattern = "Error occured in internalization context. ''{0}''.";

   protected InternalizationException( String resourceBundlePath, Throwable cause ) {
      super( ExceptionHelper.defineMessage(
            InternalizationException.class, 
            new Object[] {resourceBundlePath},
            defaultMessagePattern),
            cause);
   }
   
   protected InternalizationException( Object[] arguments, Throwable cause ) {
      super( arguments, cause );
   }

   protected InternalizationException( Object[] arguments ) {
      this( arguments, null );
   }

   public InternalizationException(ExceptionHelper helper, Throwable cause) {
      super(helper, cause);
   }

   public InternalizationException(ExceptionHelper helper) {
      super(helper, null);
   }

}
