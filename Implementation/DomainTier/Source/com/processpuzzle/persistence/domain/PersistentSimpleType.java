package com.processpuzzle.persistence.domain;

import hu.itkodex.commons.persistence.PersistentObject;

public interface PersistentSimpleType<T> extends PersistentObject {
   public T getValue();
}
