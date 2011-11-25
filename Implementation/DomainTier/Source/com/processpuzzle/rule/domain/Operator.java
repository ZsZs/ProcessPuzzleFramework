/*
Name: 
    - Operator 

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

package com.processpuzzle.rule.domain;

public class Operator extends RuleElement {   
   private String[] operators = {"AND", "OR", "NOT", "IS", "EQUALTO", "NOTEQUALTO", "LESSTHAN", "GREATERTHAN", "LESSTHANOREQUALTO", "GREATERTHANOREQUALTO"};

   public Operator(String operator) {
      super(operator);
      boolean isValid = false;
      for (int i = 0; i < operators.length; i++) {
         if(operators[i].equals(operator))
            isValid = true;
      }
      if(!isValid) throw new RuntimeException(operator+" is not a valid operator");
   }

   public String getType() {
      return "Operator";
   }

   public String getName() {
      return name;
   }
   
   

}
