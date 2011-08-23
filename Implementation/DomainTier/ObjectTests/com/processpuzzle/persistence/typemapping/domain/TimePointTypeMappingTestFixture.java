package com.processpuzzle.persistence.typemapping.domain;

import hu.itkodex.litest.template.RepositoryTestEnvironment;
import hu.itkodex.litest.template.RepositoryTestFixture;

import java.util.Date;

import com.processpuzzle.fundamental_types.domain.TimePoint;
import com.processpuzzle.persistence.domain.TestEntity;
import com.processpuzzle.persistence.domain.TestEntityRepository;

public class TimePointTypeMappingTestFixture extends RepositoryTestFixture<TestEntityRepository, TestEntity> {
   private TestEntity testEntity;

   protected TimePointTypeMappingTestFixture( RepositoryTestEnvironment<TestEntityRepository, RepositoryTestFixture<TestEntityRepository, TestEntity>> testEnvironment ) {
      super( testEnvironment );
   }

   public TestEntity getTestEntity() {
      return testEntity;
   }

   @Override
   protected TestEntity createNewAggregate() throws Exception {
      testEntity = new TestEntity("Test Entity");
      testEntity.setTimePoint( new TimePoint( new Date( System.currentTimeMillis() )));
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
