package com.processpuzzle.internalization.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;

public class InvalidResourceBundleException extends InternalizationException {
   private static final long serialVersionUID = 2643915158089248234L;
   private static String defaultMessagePattern = "\nResource bundle: ''{0}'' is invalid.";

   public InvalidResourceBundleException(  String resourceBundlePath, Throwable cause ) {
      super( ExceptionHelper.defineMessage(
            InvalidResourceBundleException.class,
            new Object[] {resourceBundlePath},
            defaultMessagePattern ),
            cause);
   }

}
