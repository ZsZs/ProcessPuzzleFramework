package com.processpuzzle.business.definition.domain;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.xerces.dom.DOMInputImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSResourceResolver;

public class SchemaResourceResolver implements LSResourceResolver {
   private static Logger logger = LoggerFactory.getLogger( SchemaResourceResolver.class );
   private ResourceLoader resourceLoader;
   private String schemaPath;
   private String schemaRoot;

   // Constructors
   public SchemaResourceResolver( final String schemaPath, final ResourceLoader resourceLoader ) {
      this.schemaPath = schemaPath;
      this.resourceLoader = resourceLoader;
      determineSchemaRoot();
   }

   // Public accesors and mutators
   @Override
   public LSInput resolveResource( String type, String namespaceURI, String publicId, String systemId, String baseURI ) {
      Resource resource = resourceLoader.getResource( schemaRoot + "/" + systemId );
      LSInput input = null;
      FileInputStream fileInputStream = null;
      
      try{
         fileInputStream = new FileInputStream( resource.getFile() );
         input = new DOMInputImpl();
         input.setByteStream( fileInputStream );
      }catch( IOException ex ){
         logger.error( "XML schema resource " + publicId + " not found.", ex );
      }
      
      return input;
   }

   // Protected, private helper methods
   private void determineSchemaRoot() {
      schemaRoot = StringUtils.substringBeforeLast( schemaPath, "/" );
   }
}
