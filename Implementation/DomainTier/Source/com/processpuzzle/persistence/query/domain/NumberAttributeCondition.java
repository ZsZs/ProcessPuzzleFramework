/*
Name: 
    - NumberAttributeCondition 

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


public abstract class NumberAttributeCondition extends DefaultAttributeCondition {

   public NumberAttributeCondition(String attributeName, Double value, ComparisonOperators operator) {
      super(attributeName, value, operator);
   }

   public NumberAttributeCondition(String attributeName, Integer value, ComparisonOperators operator) {
      super(attributeName, value, operator);
   }

   public NumberAttributeCondition(String attributeName, Long value, ComparisonOperators operator) {
      super(attributeName, value, operator);
   }

   public NumberAttributeCondition(String attributeName, int value, ComparisonOperators operator) {
      this(attributeName, new Integer(value), operator);
   }

   public NumberAttributeCondition(String attributeName, QueryVariable variable, ComparisonOperators operator) {
      super( attributeName, variable, operator);
   }

   public NumberAttributeCondition( String attributeName, ComparisonOperators operator) {
      super( attributeName, operator);
   }


//   @Override
//   public String toString() {
//      if(value instanceof Double )
//         return attributeName + " " + operator.getSymbol() + " " + ((Double)value).toString();
//      else
//         return attributeName + " " + operator.getSymbol() + " " + ((Long)value).toString();
//   }
   
      
   public abstract String toString();
}
