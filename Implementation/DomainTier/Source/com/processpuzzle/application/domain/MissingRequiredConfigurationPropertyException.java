/*
Name: 
    - MissingRequiredConfigurationPropertyException

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

package com.processpuzzle.application.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

public class MissingRequiredConfigurationPropertyException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = 6999302450993927981L;
   private static String defaultMessagePattern = "Required property: ''{0}'' is missing from configuration: ''{1}''.";
   private final String propertyName;
   private final String configurationFile;
   
   MissingRequiredConfigurationPropertyException( String propertyName, String configurationFile ) {
      super( ExceptionHelper.defineMessage( 
            MissingRequiredConfigurationPropertyException.class, 
            new Object[] { propertyName, configurationFile }, 
            defaultMessagePattern ));
      this.propertyName = propertyName;
      this.configurationFile = configurationFile;
   }
   
   public String getPropertyName() {
      return propertyName;
   }
   
   public String getConfigurationFile() {
      return configurationFile;
   }
}
