/*
Name: 
    - PersistenceContextSetUpException 

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

public class PersistenceContextSetUpException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = 9051956818305544583L;
   private static String defaultMessagePattern = "Setting up PersistenceContext with configuration descriptor: ''{0}'' caused an error.";
   private String descriptorUrl = null;
   private ExceptionHelper nestedHelper = null;

   PersistenceContextSetUpException( PropertyContext context, Throwable cause ){
      super( ExceptionHelper.defineMessage( 
            PersistenceContextSetUpException.class, 
            new Object[] { context.getConfigurationDescriptorUrl() },
            defaultMessagePattern ),
            cause );
      this.descriptorUrl = context.getConfigurationDescriptorUrl();
   }
   
   PersistenceContextSetUpException( ExceptionHelper nestedHelper, PropertyContext context, Throwable cause ) {
      this( context, cause );
      this.nestedHelper = nestedHelper;
   }

   // Properties
   public String getDescriptorUrl() { return descriptorUrl; }
   public ExceptionHelper getNestedHelper() { return nestedHelper; }
}
