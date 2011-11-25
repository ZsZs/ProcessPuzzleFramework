/*
Name: 
    - IdentityCollisionException 

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
 * Created on Nov 29, 2006
 */
package com.processpuzzle.persistence.domain;

import hu.itkodex.commons.persistence.Entity;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

public class IdentityCollisionException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = -3548255639565760641L;
   private static String defaultMessagePattern = "An object of entity: ''{0}'' already exists.";
   private Class<? extends Entity> entityClass;
   
   public IdentityCollisionException( Class<? extends Entity> entityClass ) {
      super(ExceptionHelper.defineMessage(IdentityCollisionException.class, new Object[] { entityClass.getName() }, defaultMessagePattern));
   }

   public Class<? extends Entity> getEntity() { return entityClass; }

   public Object getIdentityExpression() {
      // TODO Auto-generated method stub
      return null;
   }

   public Object getContext() {
      // TODO Auto-generated method stub
      return null;
   }

}
