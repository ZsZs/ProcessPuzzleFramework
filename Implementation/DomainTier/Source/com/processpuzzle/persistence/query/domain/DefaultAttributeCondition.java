/*
Name: 
    - DefaultAttributeCondition 

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

package com.processpuzzle.persistence.query.domain;


import com.processpuzzle.commons.persistence.query.AttributeCondition;
import com.processpuzzle.commons.persistence.query.DefaultConditionElement;
import com.processpuzzle.fundamental_types.domain.OpAssertion;

public abstract class DefaultAttributeCondition extends DefaultConditionElement implements AttributeCondition {
   protected String attributeName = null;
   protected Object value = null;
   protected ComparisonOperators operator = null;
   
   public DefaultAttributeCondition(String attributeName, Object value, ComparisonOperators operator) {
      super();
      this.attributeName = attributeName;
      this.value = value;
      this.operator = operator;
   }
   
   public DefaultAttributeCondition(String attributeName, ComparisonOperators operator ) {
      this( attributeName, null, operator );
      OpAssertion.ppAssert(((operator == ComparisonOperators.IS_NULL) || (operator == ComparisonOperators.IS_NOT_NULL )), 
      "AttributeCondition without attribute value can use only: 'is null, is not null' operators.");      
   }

//Public accessors
   public abstract String toString();
   
//Properties
   public String getAttributeName() { return attributeName; }
   public Object getValue() { return value; }
   public ComparisonOperators getOperator() { return operator; }
}
