package com.processpuzzle.application.configuration.domain;

import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.persistence.domain.PersistentClassList;
import com.processpuzzle.persistence.domain.TestEntity;
import com.processpuzzle.persistence.domain.TestEntityComponent;

public class TestClassList extends PersistentClassList {
   public TestClassList() {
      super();
   }

   @Override
   protected void defineAggregateRoots() {
      aggregateRoots.add( TestEntity.class );
   }

   @Override
   protected void defineEntities() {
      entities.add( GenericEntity.class );
      entities.add( TestEntityComponent.class );
   }

   @Override
   protected void defineValueObjects() {
      // TODO Auto-generated method stub
   }
}
