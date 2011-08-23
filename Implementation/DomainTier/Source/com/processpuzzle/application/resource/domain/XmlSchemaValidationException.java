package com.processpuzzle.application.resource.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;

public class XmlSchemaValidationException extends XmlDataLoaderException {
   private static final long serialVersionUID = 1L;
   private static String defaultMessagePattern = "XML resource: ''{0}'' doesn't match with schema: '''{1}'''.";
   private String resourceUrl = null;
   private String schemaUrl = null;

   public XmlSchemaValidationException( String xmlPath, String schemaPath, Throwable cause) {
      super(ExceptionHelper.defineMessage(XmlSchemaValidationException.class, new Object[] { xmlPath, schemaPath }, defaultMessagePattern), cause);
      this.resourceUrl = xmlPath;
      this.schemaUrl = schemaPath;
   }

   public XmlSchemaValidationException( String xmlPath, String schemaPath ) {
      this( xmlPath, schemaPath, null );
   }

   public String getResourceUrl() { return resourceUrl; }
   public String getSchemaUrl() { return schemaUrl; }
}
