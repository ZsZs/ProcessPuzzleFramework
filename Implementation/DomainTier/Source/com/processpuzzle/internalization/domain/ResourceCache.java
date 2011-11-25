/*
Name: 
    - ResourceCache

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
 * ResourceCache.java
 * 
 * Created by Claude Duguay Copyright (c) 2002
 * 
 * =====================================================================
 */
package com.processpuzzle.internalization.domain;

import java.util.*;


public class ResourceCache extends HashMap<ResourceKey, String> {
	private static final long serialVersionUID = 5761939606342586120L;

	public String getResource(String name, String type) throws NoneExistingResourceKeyException {
      ResourceKey key = new ResourceKey(name, type);
      if (!containsKey(key))
    	  throw new NoneExistingResourceKeyException( name );
      else
    	  return (String) get(key);
   }
   
   public String put( ResourceKey key, String value ) {
      return (String) super.put( key, value );
   }
}