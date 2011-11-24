/*
Name: 
    - BusinessDataLoaderErrorHandler

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
