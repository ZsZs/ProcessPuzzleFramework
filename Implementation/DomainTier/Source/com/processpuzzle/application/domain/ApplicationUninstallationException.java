/*
Name: 
    - ApplicationUninstallationException

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
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleException;

public class ApplicationUninstallationException extends ProcessPuzzleException {
   private static final long serialVersionUID = 484929754260458232L;
   private static final String defaultMessagePattern = "Unistalling application: ''{%0}'' failed.";
   private Application application;
   
   ApplicationUninstallationException( Application application, Throwable cause ) {
      super( ExceptionHelper.defineMessage( 
             ApplicationUninstallationException.class, new Object[] {application.getApplicationName()}, defaultMessagePattern ), cause );
      this.application = application;
   }
   
   public Application getApplication() { return application; }
}
