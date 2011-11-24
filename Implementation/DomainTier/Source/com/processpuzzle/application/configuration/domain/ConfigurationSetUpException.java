/*
Name: 
    - ConfigurationSetUpException

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


/*
 * Created on Jul 12, 2006
 */
package com.processpuzzle.application.configuration.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

/**
 * @author zsolt.zsuffa
 */
public class ConfigurationSetUpException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = 5567878432430133490L;
   private static String defaultMessagePattern = "Setting up configuration: ''{0}'' caused an error."
      + " Look at your configuration file. Probably it is malformed."
      + " Note that this file''s root element should be <configuration>.";
   private String configurationName = "";

   ConfigurationSetUpException(String configurationName, Exception cause) {
      super( ExceptionHelper.defineMessage( 
               ConfigurationSetUpException.class, 
               new Object[] {configurationName}, 
               defaultMessagePattern ), 
               cause );
      this.configurationName = configurationName;
   }

//Properties
   public String getConfigurationName() { return configurationName; }

//Private helper methods
}