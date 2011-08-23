package com.processpuzzle.application.configuration.control;

import java.util.HashMap;
import java.util.Map;

public abstract class CommandMapping {
   protected Map<Object, Object> commandMappings = new HashMap<Object, Object>();

   protected void appendMappings(Object[] classMappings) {
      for (int i = 0; i < classMappings.length; i += 2) {
         // Note: the commandMappings array is a 2xn dimensional array.
         // The first column contains the key string, the second contains the
         // command class object.
         commandMappings.put(classMappings[i], classMappings[i + 1]);
      }
   }

   public Map<Object, Object> getDomainClassRepositoryMappings() {
      return commandMappings;
   }
}