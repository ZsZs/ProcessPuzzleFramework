package com.processpuzzle.fitnesse.persistence;

import hu.itkodex.commons.persistence.PersistentObject;

public interface PersistentObjectWrapper<P extends PersistentObject> {
   public void wrapPersistentObject( P persistentObject );
}
