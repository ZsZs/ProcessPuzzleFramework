/*
Name: 
    - BooleanAttributeCondition

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

import com.processpuzzle.commons.persistence.query.ConditionElementType;

public class BooleanAttributeCondition extends DefaultAttributeCondition {

   public BooleanAttributeCondition(String attributeName, Object value, ComparisonOperators operator) {
      super(attributeName, value, operator);
      // TODO Auto-generated constructor stub
   }

   @Override
   public String toString() {
      String textualRepresentation = attributeName + " " + operator.getHqlVariant();
      if( value != null ) textualRepresentation += value.toString();
      return textualRepresentation;
   }

   @Override
   public ConditionElementType getType() {
      // TODO Auto-generated method stub
      return null;
   }

}
