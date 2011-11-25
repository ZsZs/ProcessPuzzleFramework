/*
Name: 
    - ResourceKey

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
