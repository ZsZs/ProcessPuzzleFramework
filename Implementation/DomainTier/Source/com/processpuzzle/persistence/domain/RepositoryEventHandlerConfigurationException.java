/*
Name: 
    - RepositoryEventHandlerConfigurationException 

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

package com.processpuzzle.persistence.domain;

import org.apache.commons.configuration.HierarchicalConfiguration;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;

public class RepositoryEventHandlerConfigurationException extends RepositoryException {
   private static final long serialVersionUID = 8777428972186019161L;
   protected static String defaultMessagePattern = "Unable to configure the repository event handler with settings: ''{0}''";

   public RepositoryEventHandlerConfigurationException( HierarchicalConfiguration configuration, Throwable cause ) {
      super(ExceptionHelper.defineMessage(
            RepositoryEventHandlerConfigurationException.class, 
            new Object[] {configuration.toString()}, 
            defaultMessagePattern ), cause );
   }
}
