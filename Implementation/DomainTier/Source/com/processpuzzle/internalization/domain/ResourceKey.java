/*
 * =====================================================================
 * 
 * ResourceKey.java
 * 
 * Created by Claude Duguay Copyright (c) 2002
 * 
 * =====================================================================
 */

package com.processpuzzle.internalization.domain;

public class ResourceKey implements Comparable<Object> {
   protected String key, type;

   public ResourceKey(String key, String type) {
      this.key = key;
      this.type = type;
   }

   public String toString() {
      return key + '/' + type;
   }

   public int hashCode() {
      return toString().hashCode();
   }

   public boolean equals(Object obj) {
      return compareTo(obj) == 0;
   }

   public int compareTo(Object obj) {
      if (obj instanceof ResourceKey) {
         ResourceKey other = (ResourceKey) obj;
         int response = key.compareTo(other.key);
         if (response == 0) {
            response = type.compareTo(other.type);
         }
         return response;
      }
      return 1;
   }
}
