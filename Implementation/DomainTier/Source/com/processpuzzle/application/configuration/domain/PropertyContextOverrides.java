package com.processpuzzle.application.configuration.domain;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.processpuzzle.fundamental_types.domain.ProcessPuzzleEnumeration;

public class PropertyContextOverrides {
   private Map<PropertyKeys, ProcessPuzzleEnumeration> overrides = new HashMap<PropertyKeys, ProcessPuzzleEnumeration>();
   
   public PropertyContextOverrides() {}
   
   public void addProperty( PropertyKeys key, ProcessPuzzleEnumeration value ) {
      overrides.put(key, value);
   }
   
//Collection facade
   public Iterator<Map.Entry<PropertyKeys, ProcessPuzzleEnumeration>> overridesEntrySetIterator() {
      return overrides.entrySet().iterator();
   }
}
