package com.processpuzzle.persistence.domain;

public class PersistentLong extends GenericPersistentSimpleType<Long> implements PersistentSimpleType<Long>{
   
   public PersistentLong( Long value ) {
      super( value );
   }
}
