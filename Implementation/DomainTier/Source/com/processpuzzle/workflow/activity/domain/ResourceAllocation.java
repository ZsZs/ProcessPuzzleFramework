/*
Name: 
    - ResourceAllocation 

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
 * Created on 2005.07.19.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.workflow.activity.domain;


import com.processpuzzle.commons.persistence.Entity;
import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.fundamental_types.quantity.domain.Quantity;
import com.processpuzzle.resource.resourcetype.domain.ResourceType;

public abstract class ResourceAllocation extends GenericEntity<ResourceAllocation> implements Entity {
   private String name;
   private ResourceType type;
   private Quantity quantity;

   public ResourceAllocation(ResourceType theType, Quantity theQuantity) {
      this.type = theType;
      this.quantity = theQuantity;
   }

   public ResourceAllocation() {
      super();
   }

   public ResourceType getType() {
      return type;
   }

   public void setType(ResourceType type) {
      this.type = type;
   }

   public Quantity getQuantity() {
      return quantity;
   }

   public void setQuantity(Quantity quantity) {
      this.quantity = quantity;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }
}
