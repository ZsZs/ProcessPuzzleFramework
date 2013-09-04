package com.processpuzzle.fitnesse.persistence;

import com.processpuzzle.commons.persistence.PersistentObject;

public interface PersistentObjectWrapper<P extends PersistentObject> {
   public void wrapPersistentObject( P persistentObject );
}
