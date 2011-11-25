/*
Name: 
    - InvalidResourcePathException

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

package com.processpuzzle.internalization.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleException;

public class InvalidResourcePathException extends ProcessPuzzleException {
   private static final long serialVersionUID = 4990821965292297031L;
   private static String defaultMessagePattern = "Path: ''{0}'' to the resource bundle files is invalid.";

   public InvalidResourcePathException( String resourcePath, Throwable cause ) {
      super( ExceptionHelper.defineMessage(
            InvalidResourcePathException.class,
            new Object[] {resourcePath},
            defaultMessagePattern ),
            cause);
   }
   
   public InvalidResourcePathException( String resourcePath ) {
      this( resourcePath, null );
   }

   protected static Object[] defineMessage( String source ) {
      formatPattern = "Path: ''{0}'' to the resource bundle files is invalid.";
      Object[] arguments = {source};
      return arguments;
   }
}
