package com.processpuzzle.application.resource.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;

public class XmlSchemaNotFoundException extends XmlDataLoaderException {
   private static final long serialVersionUID = 1L;
   private static String defaultMessagePattern = "XML schema resource: ''{0}'' not found.";
   private String schemaUrl = null;

   public XmlSchemaNotFoundException ( String schemaUrl, Throwable cause) {
      super(ExceptionHelper.defineMessage(XmlSchemaNotFoundException.class, new Object[] { schemaUrl }, defaultMessagePattern), cause);
      this.schemaUrl = schemaUrl;
   }

   public String getSchemaUrl() { return schemaUrl; }

}
