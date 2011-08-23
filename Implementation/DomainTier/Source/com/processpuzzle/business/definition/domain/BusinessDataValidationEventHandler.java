package com.processpuzzle.business.definition.domain;

import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.ValidationEventLocator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BusinessDataValidationEventHandler implements ValidationEventHandler {
   private static Logger log = LoggerFactory.getLogger( BusinessDataLoader.class );
   private String sourcePath;
   private String schemaPath;

   BusinessDataValidationEventHandler( String sourcePath, String schemaPath ) {
      this.sourcePath = sourcePath;
      this.schemaPath = schemaPath;
   }

   public boolean handleEvent( ValidationEvent validationEvent ) {
      
      if( validationEvent.getSeverity() == ValidationEvent.FATAL_ERROR || validationEvent.getSeverity() == ValidationEvent.ERROR ){
         ValidationEventLocator locator = validationEvent.getLocator();

         // Print message from validation event
         log.error( "Validating document: " + sourcePath + " against: " + schemaPath + " schema caused error. It's url: " + locator.getURL() );
         log.error( "Caused error: " + validationEvent.getMessage() );

         // Output line and column number
         log.error( "At column " + locator.getColumnNumber() + ", line " + locator.getLineNumber() );
         if( validationEvent.getLinkedException() != null )
            log.error( "The route case is: " + validationEvent.getLinkedException().getMessage() );
      }else {
         log.warn( validationEvent.getMessage() );
      }
      return true;
   }

   public String getSourcePath() {
      return sourcePath;
   }

   public String getSchemaPath() {
      return schemaPath;
   }
}
