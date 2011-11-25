/*
Name: 
    - EntityInstantiationException 

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

import hu.itkodex.commons.persistence.Entity;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

public class EntityInstantiationException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = 8988275764874004878L;
   private static final String defaultMessagePattern = "Creating entity: ''{0}'' with parameter: ''{1}'' = ''{2}'' caused error.";
   private Class<? extends Entity> entityClass;
   private String parameterName, parameterValue;
   
   public EntityInstantiationException( Class<? extends Entity> entityClass, String parameterName, String parameterValue, Throwable cause ) {
      super( ExceptionHelper.defineMessage(
            EntityInstantiationException.class,
            new Object[] { entityClass.getName(), parameterName, parameterValue },
            defaultMessagePattern ), 
            cause );
      
   }

   public Class<? extends Entity> getEntityClass() {
      return entityClass;
   }

   public String getParameterName() {
      return parameterName;
   }

   public String getParameterValue() {
      return parameterValue;
   }
}
