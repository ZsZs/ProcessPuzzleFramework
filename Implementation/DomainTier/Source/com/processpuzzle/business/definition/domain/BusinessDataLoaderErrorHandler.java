package com.processpuzzle.business.definition.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class BusinessDataLoaderErrorHandler implements ErrorHandler {
   private static Logger logger = LoggerFactory.getLogger( BusinessDataLoader.class );
   private String schemaPath;
   
   BusinessDataLoaderErrorHandler( String schemaPath ) {
      this.schemaPath = schemaPath;
   }
   
   public void error( SAXParseException exception ) throws SAXException {
      logger.debug( "Schema: " + schemaPath + " caused error:" + exception.getMessage() );
      throw exception;
   }

   public void fatalError( SAXParseException exception ) throws SAXException {
      logger.error( "Schema: " + schemaPath + " caused fatal error:" + exception.getMessage() );
      throw exception;
   }

   public void warning( SAXParseException exception ) throws SAXException {
      logger.warn( "Schema: " + schemaPath + " caused some problems:" + exception.getMessage() );
   }
}
