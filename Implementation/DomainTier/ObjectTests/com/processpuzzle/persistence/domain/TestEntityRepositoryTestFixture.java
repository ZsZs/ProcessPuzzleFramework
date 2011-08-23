package com.processpuzzle.persistence.domain;

import hu.itkodex.litest.template.RepositoryTestEnvironment;
import hu.itkodex.litest.template.RepositoryTestFixture;

import java.util.GregorianCalendar;

import com.processpuzzle.fundamental_types.domain.TimePeriod;
import com.processpuzzle.fundamental_types.domain.TimePoint;
import com.processpuzzle.fundamental_types.quantity.domain.Quantity;
import com.processpuzzle.fundamental_types.quantity.domain.Unit;

public class TestEntityRepositoryTestFixture extends RepositoryTestFixture<TestEntityRepository, TestEntity> {
   private DefaultUnitOfWork unstartedWork = null;

   protected TestEntityRepositoryTestFixture( RepositoryTestEnvironment<TestEntityRepository, RepositoryTestFixture<TestEntityRepository, TestEntity>> testEnvironment ) {
      super( testEnvironment );
   }

   public DefaultUnitOfWork getUnstartedWork() {
      return unstartedWork;
   }

   @Override
   protected void configureBeforeSutInstantiation() {
      unstartedWork = new DefaultUnitOfWork(false);
   }

   @Override
   protected TestEntity createNewAggregate() throws Exception {
      // Set up attributes
      TestEntity testEntity = new TestEntity("TestEntity");
      testEntity.setTextAttribute("textAttributeValue");
      testEntity.setNumberAttribute(2007);
      testEntity.setDateAttribute(new GregorianCalendar(1960, 12, 9).getTime());
      testEntity.setTimePoint(new TimePoint(1960, 12, 9));
      testEntity.setTimePeriod(new TimePeriod(new TimePoint(1960, 12, 9), new TimePoint(2007, 9, 23)));
      testEntity.setQuantity( new Quantity( 10.25, new Unit( "kilometre", "km") ));

      // Set up associations
      testEntity.setEnitiyComponentWithCascade(new TestEntityComponent("TestEntity_1_Component_1"));
      testEntity.addComponent(new TestEntityComponent("bulk_1"));
      testEntity.addComponent(new TestEntityComponent("bulk_2"));

      return testEntity;
   }

   @Override
   protected void afterAggregateCreation() {
   }

   @Override
   protected void afterAggregateDeletion() {
   }

   @Override
   protected void beforeAggregateCreation() {
   }
}
