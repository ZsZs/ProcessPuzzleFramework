/*
Name: 
    - UndefinedBeanContainerPathException

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

public class UndefinedBeanContainerPathException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = 7030311720641321562L;
   private static String defaultMessagePattern = "Retrieving Bean Container's configuration path from: '''{0}''' configuration failed.";
   private String configurationName = "";

   public UndefinedBeanContainerPathException( String configurationName ) {
      super( ExceptionHelper.defineMessage( UndefinedBeanContainerPathException.class, new Object[] {configurationName}, defaultMessagePattern ));
      this.configurationName = configurationName;
   }
   
   public String getConfigurationName() { return configurationName; }
}
