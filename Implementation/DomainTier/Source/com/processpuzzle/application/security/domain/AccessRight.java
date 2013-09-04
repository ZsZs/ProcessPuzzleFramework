/*
Name: 
    - AccessRight

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
 * Created on May 18, 2006
 */
package com.processpuzzle.application.security.domain;

import com.processpuzzle.commons.persistence.PersistentObject;

/**
 * @author zsolt.zsuffa
 */
public class AccessRight extends DefaultAccessRight implements PersistentObject {
//   private User targetUser = null;
   // must be unique
   private Integer controlledObjectId = null;
   private String controlledObjectClass = null;

   public AccessRight( AccessControlledObject controlledObject, boolean canRead, boolean canCreate, boolean canWrite, boolean canDelete) {
      super(canRead, canCreate, canWrite, canDelete);
//      this.targetUser = user;
      this.controlledObjectId = controlledObject.getId();
      this.controlledObjectClass = controlledObject.getClass().getName();
   }

   AccessRight( AccessControlledObject object) {
      this( object, false, false, false, false);
   }

   protected AccessRight() {}

//   public User getTargetUser() {
//      return targetUser;
//   }
//
//   public void setTargetUser(User targetUser) {
//      this.targetUser = targetUser;
//   }
//
   public Integer getControlledObjectId() { return controlledObjectId; }
   public String getControlledObjectClass() { return controlledObjectClass; }

//   public void setControlledObjectName(String controlledObjectName) {
//      this.controlledObjectId = controlledObjectName;
//   }
}
