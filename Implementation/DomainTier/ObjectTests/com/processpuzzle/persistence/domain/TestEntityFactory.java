/*
 * Created on Jul 12, 2006
 */
package com.processpuzzle.persistence.domain;

/**
 * @author zsolt.zsuffa
 */
public class TestEntityFactory extends GenericFactory<TestEntity> {
   
   public TestEntityFactory() {
      super();
   }
   
   public static TestEntity createTestEntity (String name) {
      return new TestEntity(name);
   }
}
