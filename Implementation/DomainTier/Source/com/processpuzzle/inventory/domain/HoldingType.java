/*
Name: 
    - HoldingType

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
 * Created on 2005.08.08.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.inventory.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;

import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;
import com.processpuzzle.resource.resourcetype.domain.ConsumableResourceType;
import com.processpuzzle.resource.resourcetype.domain.ResourceType;

public class HoldingType extends GenericEntity<HoldingType> implements ConsumableResourceType {
   protected @XmlAttribute @XmlID String name;
   protected @XmlAttribute String description;
//	private Holding holding;
//	private TransferRule rule;
    
    public HoldingType( String name, String description) {
		this.name = name ;
		this.description = description;
	}

   public @Override int compareTo( ResourceType o ) {
      // TODO Auto-generated method stub
      return 0;
   }

   public @Override <I extends DefaultIdentityExpression<HoldingType>> I getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }

   public @Override String getDescription() { return description; }
   public @Override String getName() { return name; }

   //Public mutators
   public @Override void setDescription( String description ) { this.description = description; }

   protected HoldingType() {
    	super();
    }

   @Override
   protected void defineIdentityExpressions() {
      // TODO Auto-generated method stub
   }
}