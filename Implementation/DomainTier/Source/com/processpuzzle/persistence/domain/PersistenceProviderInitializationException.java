/*
Name: 
    - PersistenceProviderInitializationException 

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

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

public class PersistenceProviderInitializationException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = -7120926168654173563L;
   private static final String defaultMessagePattern = "Initialization of ''{0}'' strategy failed.";
   private String strategyName = null;

   public PersistenceProviderInitializationException( Object[] arguments ) {
//      super( ExceptionHelper.defineMessage(
//            PersistenceProviderInitializationException.class,
//            new Object[] {arguments},
//            defaultMessagePattern));
      this(arguments, null);
   }

   public PersistenceProviderInitializationException(Object[] arguments, Throwable cause) {
      super( ExceptionHelper.defineMessage(
            PersistenceProviderInitializationException.class,
            new Object[] {arguments},
            defaultMessagePattern),
            cause);
   }

   public PersistenceProviderInitializationException( Class<?> strategy, Throwable cause ) {
      super( ExceptionHelper.defineMessage(
            PersistenceProviderInitializationException.class,
            new Object[] {strategy},
            defaultMessagePattern),
            cause);
      this.strategyName = strategy.getName();
   }
   
   public String getStrategyName() { return strategyName; }
   
}
