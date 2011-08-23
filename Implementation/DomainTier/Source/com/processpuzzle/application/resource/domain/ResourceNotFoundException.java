package com.processpuzzle.application.resource.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;

public class ResourceNotFoundException extends DataLoaderException {
   private static final long serialVersionUID = 1L;
   private static final String defaultMessagePattern = "Resource: ''{0}'' not found.";
   private String resourceUrl = null;

   public ResourceNotFoundException(DataLoader loader, String problem, Throwable cause) {
      super(loader, problem, cause);
   }

   public ResourceNotFoundException(String resourceUrl ) {
      this( resourceUrl, null );
   }

   public ResourceNotFoundException(String resourceUrl, Throwable cause) {
      super( ExceptionHelper.defineMessage( ResourceNotFoundException.class, new Object[] { resourceUrl }, defaultMessagePattern), cause);
      this.resourceUrl = resourceUrl;
   }

   public String getReourceUrl() {
      return resourceUrl;
   }

}
