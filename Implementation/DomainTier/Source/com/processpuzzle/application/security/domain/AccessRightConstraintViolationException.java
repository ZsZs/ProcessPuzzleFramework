/*
Name: 
    - AccessRightConstraintViolationException

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
 * Created on Jul 2, 2006
 */
package com.processpuzzle.application.security.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

/**
 * @author zsolt.zsuffa
 */
public class AccessRightConstraintViolationException extends ProcessPuzzleProgrammingException {
   /**
	 * 
	 */
	private static final long serialVersionUID = -2221045569851306096L;
private static String [] descriptions = {
         "A user can't have more than one right for the same AccessControlledObject.",
         "An AccessControlledObject can't have more than one right for the same user."};
   private static String defaultMessagePattern = "AccessRight Constraint: ''{0}'' caused an error";
   private String userName = "";
   private Integer objectId = null;
   private int descriptionIndex;

   public AccessRightConstraintViolationException(int descriptionIndex, String userName, Integer objectId) {
      super( ExceptionHelper.defineMessage( 
            AccessRightConstraintViolationException.class,
            new Object[] {descriptionIndex, userName, objectId},
            defaultMessagePattern ));
      
      this.descriptionIndex = descriptionIndex;
      this.userName = userName;
      this.objectId = objectId;
   }
   
   public Integer getObjectId() {
      return objectId;
   }

   public String getUsername() {
      return userName;
   }

   public String getDescription () {
      return descriptions[descriptionIndex];
   }
}
