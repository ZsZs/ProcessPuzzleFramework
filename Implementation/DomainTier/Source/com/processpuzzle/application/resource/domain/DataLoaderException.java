/*
Name: 
    - DataLoaderException

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
 * Created on Feb 14, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.application.resource.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

public class DataLoaderException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = 6631093747323781217L;
   private static String defaultMessagePattern = "DataLoader: ''{0}'' recognized an unexpected error. ''{1}''";

   public DataLoaderException( DataLoader loader, String problem ) {
      this( loader, problem , null );
   }

   public DataLoaderException( DataLoader loader, String problem, Throwable cause ) {
      super( ExceptionHelper.defineMessage(
            DataLoaderException.class, new Object[] {loader, problem},
            defaultMessagePattern ),
            cause);
   }

   public DataLoaderException(Object[] objects, Throwable cause) {
      super( objects, cause );
   }

   public DataLoaderException(ExceptionHelper helper, Throwable cause) {
      super( helper, cause );
   }

   protected static Object[] defineMessage( DataLoader loader,  String problem ) {
      formatPattern = "DataLoader: ''{0}'' recognized an unexpected error. ''{1}''";
      Object[] arguments = {loader.getClass().getSimpleName(), problem };
      return arguments;
   }
}