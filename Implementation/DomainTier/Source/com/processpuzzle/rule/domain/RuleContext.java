/*
Name: 
    - RuleContext 

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

import java.util.Iterator;

public class RuleContext {

   private String name;
   private Queue elements = new Queue();

   public RuleContext(String name) {
      this.name = name;
   }

   public void addProposition(String statement, boolean value) {
      elements.enQueue(new Proposition(statement, value));
   }

   public void addVariable(String name, double value) {
      elements.enQueue(new Variable(name, value));
   }

   public ValueHolderRuleElement findElement(String name) {
      return (ValueHolderRuleElement) elements.findElement(name);
   }

   public String getName() {
      return name;
   }

   public String toString() {
      String resultString = "";
      for( Iterator<?> iter = elements.iterator(); iter.hasNext();) {
         RuleElement e = (RuleElement) iter.next();
         resultString += ((ValueHolderRuleElement) e).toString();
      }
      return resultString;
   }

}
