package com.processpuzzle.application.configuration.domain;

import com.processpuzzle.fundamental_types.domain.ProcessPuzzleEnumeration;

public enum PersistentDataInitializationStrategies implements ProcessPuzzleEnumeration {
   dropAndCreate, create, update;

   public String asString() {
      return this.toString();
   }
}

