package com.processpuzzle.application.resource.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;

public class XmlValidationException extends XmlDataLoaderException {
   private static final long serialVersionUID = 1L;
   private static String defaultMessagePattern = "XML resource: '''{0}''' is invalid.";
   private String resourceUrl = null;

   public XmlValidationException(String xmlPath, Throwable cause) {
      super(ExceptionHelper.defineMessage(XmlValidationException.class, new Object[] { xmlPath }, defaultMessagePattern), cause);
      this.resourceUrl = xmlPath;
   }

   public XmlValidationException(String xmlPath) {
      this(xmlPath, null);
   }

   public String getResourceUrl() {
      return resourceUrl;
   }
}
