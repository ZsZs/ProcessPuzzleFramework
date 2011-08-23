package com.processpuzzle.persistence.typemapping.domain;

import hu.itkodex.litest.template.RepositoryTestEnvironment;
import hu.itkodex.litest.template.RepositoryTestFixture;

import com.processpuzzle.application.configuration.domain.MeasurementContext;
import com.processpuzzle.fundamental_types.possiblevalue.domain.PossibleValueDefinition;
import com.processpuzzle.fundamental_types.possiblevalue.domain.QuantityRange;
import com.processpuzzle.fundamental_types.quantity.domain.Quantity;
import com.processpuzzle.fundamental_types.quantity.domain.Units;
import com.processpuzzle.persistence.domain.TestEntity;
import com.processpuzzle.persistence.domain.TestEntityRepository;

public class PossibleValueDefinitionTypeMappingTestFixture extends RepositoryTestFixture<TestEntityRepository, TestEntity> {
   private TestEntity testEntity;
   private Quantity minValue;
   private Quantity maxValue;
   private MeasurementContext measurementContext = null;

   protected PossibleValueDefinitionTypeMappingTestFixture( RepositoryTestEnvironment<TestEntityRepository, RepositoryTestFixture<TestEntityRepository, TestEntity>> testEnvironment ) {
      super( testEnvironment );
   }

   public MeasurementContext getMeasurementContext() {
      return measurementContext;
   }

   public Quantity getMinValue() {
      return minValue;
   }

   public Quantity getMaxValue() {
      return maxValue;
   }

   public TestEntity getTestEntity() {
      return testEntity;
   }

   @Override
   protected void configureBeforeSutInstantiation() {
      measurementContext = applicationContext.getMeasurementContext();
      minValue = new Quantity (new Double(20), measurementContext.findUnitBySymbol(Units.CENTIMETRE)); // 20 cm
      maxValue = new Quantity (new Double(40), measurementContext.findUnitBySymbol(Units.CENTIMETRE)); // 40 cm
   }

   @Override
   protected TestEntity createNewAggregate() throws Exception {
      testEntity = new TestEntity("Test Entity with range possible value definition");
      
      PossibleValueDefinition valueRange = new QuantityRange(minValue, maxValue);
      testEntity.setPossibleValues(valueRange);

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
