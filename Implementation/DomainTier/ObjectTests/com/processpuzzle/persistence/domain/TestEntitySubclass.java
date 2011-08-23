package com.processpuzzle.persistence.domain;

public class TestEntitySubclass extends TestEntity {
   public String childProperty;
   
   protected TestEntitySubclass(){
      super();
   }
   
   public TestEntitySubclass(String name) {
      super(name);
   }

}
