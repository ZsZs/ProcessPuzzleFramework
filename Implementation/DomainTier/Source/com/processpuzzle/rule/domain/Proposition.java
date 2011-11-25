/*
Name: 
    - Proposition 

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

public class Proposition extends ValueHolderRuleElement {

   public Proposition(String name, boolean value) {
      super(name, Boolean.valueOf(value).toString());
   }
   
   public Proposition(String name) {
      super(name);
   }

   public Proposition and(Proposition proposition) {
      return new Proposition("(" + name + " AND " + proposition.name + ")", Boolean.valueOf(value).booleanValue() && Boolean.valueOf(proposition.value).booleanValue());
   }

   public Proposition or(Proposition proposition) {
      return new Proposition("(" + name + " OR " + proposition.name + ")", Boolean.valueOf(value).booleanValue() || Boolean.valueOf(proposition.value).booleanValue());
   }

   public Proposition not() {
      return new Proposition("(NOT " + name+")", !Boolean.valueOf(value).booleanValue());
   }
   
   public Proposition is() {
      return new Proposition("(IS " + name+")", Boolean.valueOf(value).booleanValue());
   }

   public Proposition xor(Proposition proposition) {
      if (value == proposition.value)
         return new Proposition("(" + name + " XOR " + proposition.name + ")", false);
      else
         return new Proposition("(" + name + " XOR " + proposition.name + ")", true);
   }

   public String toString() {
      return "Proposition statement = " + name + ", value = " + value;
   }

   public String getType() {
      return "Proposition";
   }

}
