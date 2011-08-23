package com.processpuzzle.persistence.domain;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class Enum implements Serializable {

   protected static Map<String, Enum> nameMap = new HashMap<String, Enum>(15);
   protected static Map<Integer, Enum> valueMap = new HashMap<Integer, Enum>(15);
   protected int value;
   protected transient String name;

   protected Enum(String name, int value) {
      this.value = value;
      this.name = name;
      add();
   }

   protected void add() {
      nameMap.put(this.name, this);
      valueMap.put(new Integer(this.value), this);
   }

   public static Enum get(String name) {
      return nameMap.get(name);
   }

   public static Enum get(int value) {
      return valueMap.get(new Integer(value));
   }

   public int getValue() {
      return value;
   }

   public String getName() {
      return name;
   }

   protected Object readResolve() throws ObjectStreamException {
      return get(this.value);
   }
}