/*
Name: 
    - EntityIdentityCollitionException 

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
 * Created on Jun 25, 2006
 */
package com.processpuzzle.persistence.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

/**
 * @author zsolt.zsuffa
 */
public class EntityIdentityCollitionException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = -325756507198654604L;
   private static String defaultMessagePattern = "The given entity's identity is in coallision whit one other entity.";
   private String entityClassName = "";
   private String identityExpression = "";
   private String context = "";

   public EntityIdentityCollitionException(String entityClassName, String identityExpression, String context) {
      super(ExceptionHelper.defineMessage(EntityIdentityCollitionException.class, new Object[] { entityClassName, identityExpression,
            context }, defaultMessagePattern));
      // super(messageBody + "\nEntity class name: " + entityClassName +
      // "\tidentitiyExpression: " + identityExpression + "\tcontext: " +
      // context);
      this.entityClassName = entityClassName;
      this.identityExpression = identityExpression;
      this.context = context;
   }

   public String getEntityClassName() {
      return entityClassName;
   }

   public String getIdentityExpression() {
      return identityExpression;
   }

   public String getContext() {
      return context;
   }
}
