package com.processpuzzle.application.resource.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;

public class XmlResourceNotFoundException extends XmlDataLoaderException {
   private static final long serialVersionUID = 1L;
   private static String defaultMessagePattern = "XML resource: ''{0}'' not found.";
   private String resourceUrl = null;

   public XmlResourceNotFoundException( XmlDataLoader loader, String problem, Throwable cause) {
      super( loader, problem, cause);
   }

   public XmlResourceNotFoundException( String resourceUrl, Throwable cause) {
      super(ExceptionHelper.defineMessage(XmlResourceNotFoundException.class, new Object[] { resourceUrl }, defaultMessagePattern), cause);
      this.resourceUrl = resourceUrl;
   }

   public String getReourceUrl() { return resourceUrl; }
}
