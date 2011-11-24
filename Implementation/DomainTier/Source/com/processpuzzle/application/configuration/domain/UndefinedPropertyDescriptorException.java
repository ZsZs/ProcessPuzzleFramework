/*
Name: 
    - UndefinedPropertyDescriptionException

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

package com.processpuzzle.application.configuration.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

public class UndefinedPropertyDescriptorException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = 7733136640533283357L;
   private static String defaultMessagePattern = "Property descriptor ''{0}'' is undefined.";
   private String descriptorPath = null;

   public UndefinedPropertyDescriptorException( String descriptorPath, Throwable cause ) {
      super( ExceptionHelper.defineMessage( 
                  UndefinedPropertyDescriptorException.class, 
                  new Object[] {descriptorPath}, 
                  defaultMessagePattern ), 
                  cause );
      this.descriptorPath = descriptorPath;
   }

//Properties
   public String getDescriptorPath() { return descriptorPath; }
}
