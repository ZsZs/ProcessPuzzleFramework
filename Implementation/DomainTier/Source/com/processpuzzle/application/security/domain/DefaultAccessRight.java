/*
Name: 
    - DefaultAccessRight

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

package com.processpuzzle.application.security.domain;

import hu.itkodex.commons.persistence.Entity;

import javax.xml.bind.annotation.XmlAttribute;

import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

public class DefaultAccessRight extends GenericEntity<DefaultAccessRight> implements Entity {
   private @XmlAttribute boolean canRead = false;
   private @XmlAttribute boolean canCreate = false;
   private @XmlAttribute boolean canModify = false;
   private @XmlAttribute boolean canDelete = false;
   private @XmlAttribute(name="partyRoleType") String userRoleName = null;

   public DefaultAccessRight() {}
   
   public DefaultAccessRight(boolean r, boolean c, boolean m, boolean d) {
      this.canRead = r;
      this.canCreate = c;
      this.canModify = m;
      this.canDelete = d;
   }
   
//properties
   public boolean isDefault() { return !(this instanceof AccessRight); }

   public boolean canCreate() { return canCreate; }
   public boolean getCanCreate() { return canCreate; }
   public void setCanCreate(boolean canCreate) { this.canCreate = canCreate; }

   public boolean canDelete() { return canDelete; }
   public boolean getCanDelete() { return canDelete; }
   public void setCanDelete(boolean canDelete) { this.canDelete = canDelete; }

   public boolean canModify() { return canModify; }
   public boolean getCanModify() { return canModify; }
   public void setCanModify(boolean canModify) { this.canModify = canModify; }

   public boolean canRead() { return canRead; }
   public boolean getCanRead() { return canRead; }
   public void setCanRead(boolean canRead) { this.canRead = canRead; }

   public String getUserRoleName() { return userRoleName; }
   public void setUserRoleName(String userRoleName) { this.userRoleName = userRoleName; }

   @Override
   public <I extends DefaultIdentityExpression<DefaultAccessRight>> I getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   protected void defineIdentityExpressions() {
      // TODO Auto-generated method stub
      
   }

}
