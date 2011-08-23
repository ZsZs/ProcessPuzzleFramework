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
