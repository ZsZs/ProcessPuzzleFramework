package com.processpuzzle.internalization.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;

public class ResourceBundleIOException extends InternalizationException {
   private static final long serialVersionUID = -2723446459618618112L;
   private static String defaultMessagePattern = "Can't load resource bundle: '{0}'";

   public ResourceBundleIOException( String resourceBundlePath, Throwable cause ) {
      super( ExceptionHelper.defineMessage(
            ResourceBundleIOException.class,
            new Object[] {resourceBundlePath},
            defaultMessagePattern),
            cause);
   }

}
