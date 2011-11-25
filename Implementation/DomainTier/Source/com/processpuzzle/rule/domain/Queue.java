/*
Name: 
    - Queue 

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

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

public class Queue {

   private Vector<Object> queue;

   Queue() {
      queue = new Vector<Object>();
   }

   public void enQueue(Object item) {
      queue.addElement(item);
   }

   public Object front() {
      return queue.firstElement();
   }

   public Object deQueue() {
      Object obj = null;

      if (!queue.isEmpty()) {
         obj = front();
         queue.removeElement(obj);
      }
      return obj;
   }
   
   public Iterator<Object> iterator() {
      return queue.iterator();
   }
   
   public RuleElement findElement(String name) {
      for (Iterator<Object> iter = queue.iterator(); iter.hasNext();) {
         RuleElement element = (RuleElement) iter.next();
         if(element.getName().equals(name))
            return element;
      }
      return null;
   }

   public boolean isEmpty() {
      return queue.isEmpty();
   }

   public int size() {
      return queue.size();
   }

   public int availableRoom() {
      return (queue.capacity() - queue.size());
   }

   public String deQueueString() {
      Object obj = deQueue();
      if (obj != null) {
         return obj.toString();
      } else {
         return " ";
      }
   }

   public void printQueue() {

      Enumeration<Object> e = queue.elements();
      String str;
      while (e.hasMoreElements()) {
         str = e.nextElement().toString();
         System.out.print(str + " ");
      }
      System.out.println();
   }
}
