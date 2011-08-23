package com.processpuzzle.util.domain;

import com.processpuzzle.fundamental_types.domain.TimePoint;

public abstract class TemporalCollection {
   abstract Object get(TimePoint when);

   abstract void put(TimePoint at, Object item);

   abstract Object get(int year, int month, int date);

   // get and put at today's date
   abstract Object get();

   abstract void put(Object item);

   private Integer id;

   public Integer getId() {
      return id;
   }
}
