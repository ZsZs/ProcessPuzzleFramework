/*
Name: 
    - UndefinedBeanException

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

public class UndefinedBeanException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = -3463810171263032258L;
   private static String defaultMessagePattern = "Bean: ''{0}'' in container: ''{1}'' not found.";
   private final String beanName;
   private final String configurationName;

   UndefinedBeanException( String beanName, String configurationName ) {
      super( ExceptionHelper.defineMessage(
             UndefinedBeanException.class, new Object[] { beanName, configurationName}, defaultMessagePattern ));
      this.beanName = beanName;
      this.configurationName = configurationName;
   }

   public String getBeanName() {
      return beanName;
   }

   public String getConfigurationName() {
      return configurationName;
   }
}
