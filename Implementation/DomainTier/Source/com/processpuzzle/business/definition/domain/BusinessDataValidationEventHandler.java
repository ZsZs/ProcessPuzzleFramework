/*
Name: 
    - BusinessDataValidationEventHandler

Description: 
    -  

Requires:
    - 
    
Provides:
    - 

Part of: ProcessPuzzle Framework, Domain and Business Model Ready Architecture. Provides content, workflow and social networking functionality. 
http://www.processpuzzle.com

ProcessPuzzle - Content and Workflow Management Integration Business Platform

Author(s): 
    - Zsolt Zsuffa

Copyright: (C) 2011 This program is free software: you can redistribute it and/or modify it under the terms of the 
GNU General Public License as published by the Free Software Foundation, either version 3 of the License, 
or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

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
