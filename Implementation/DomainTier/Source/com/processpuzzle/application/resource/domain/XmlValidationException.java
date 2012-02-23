/*
Name: 
    - XmlValidationException

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