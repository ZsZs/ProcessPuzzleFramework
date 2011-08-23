package com.processpuzzle.persistence.domain;

public abstract class GenericPersistentSimpleType<T> implements PersistentSimpleType<T> {
   protected T value;

   public GenericPersistentSimpleType( T value ) {
      this.value = value;
   }
   public T getValue() { return value; }
}
