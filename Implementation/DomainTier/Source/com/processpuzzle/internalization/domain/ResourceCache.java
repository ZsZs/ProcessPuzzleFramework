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