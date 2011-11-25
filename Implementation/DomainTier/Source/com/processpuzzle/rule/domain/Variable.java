/*
Name: 
    - Variable 

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


public class Variable extends ValueHolderRuleElement {

   public Variable(String name, double value) {
      super(name, Double.valueOf(value).toString());
   }
   
   public Variable(String name) {
      super(name);
   }

   public Proposition equalTo(Variable variable) {
      return new Proposition("(" + name + " == " + variable.name + ")", value.equals(variable.value));
   }

   public Proposition notEqualTo(Variable variable) {
      return new Proposition("(" + name + " != " + variable.name + ")", !value.equals(variable.value));
   }

   public Proposition lessThan(Variable variable) {
      return new Proposition("(" + name + " < " + variable.name + ")", Double.valueOf(value).doubleValue() < Double.valueOf(variable.value).doubleValue());
   }

   public Proposition greaterThan(Variable variable) {
      return new Proposition("(" + name + " > " + variable.name + ")", Double.valueOf(value).doubleValue() > Double.valueOf(variable.value).doubleValue());
   }

   public Proposition greaterThanOrEqualsTo(Variable variable) {
      return new Proposition("(" + name + " >= " + variable.name + ")", Double.valueOf(value).doubleValue() >= Double.valueOf(variable.value).doubleValue());
   }

   public Proposition lessThanOrEqualsTo(Variable variable) {
      return new Proposition("(" + name + " <= " + variable.name + ")", Double.valueOf(value).doubleValue() <= Double.valueOf(variable.value).doubleValue());
   }

   public String toString() {
      return "Variable name = " + name + ", value = " + value;
   }

   public String getType() {
      return "Variable";
   }

}
