/*
Name: 
    - UniqueIdentifierInstantiationException

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

package com.processpuzzle.fundamental_types.uniqueidentifier.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;



public class UniqueIdentifierInstantiationException extends ProcessPuzzleProgrammingException {

   /**
    * 
    */
   private static final long serialVersionUID = -5207827292985847604L;
   private static String defaultMessagePattern = "Serious error occured during instantiation of unique identifer ''{1}'' of type ''{0}''.";

   UniqueIdentifierInstantiationException(String identiferTypeName, String identifier) {
      super(ExceptionHelper.defineMessage(UniqueIdentifierInstantiationException.class, new Object[] { identiferTypeName, identifier },
            defaultMessagePattern));
   }
}