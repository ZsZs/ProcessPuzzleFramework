package com.processpuzzle.fundamental_types.possiblevalue.domain;

public interface StringParseable {
   
   public abstract String stringValue();

   public abstract void parseFromString(Object object, String objectStr);
}
