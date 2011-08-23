package com.processpuzzle.persistence.typemapping.domain;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeThat;
import hu.itkodex.litest.template.RepositoryTestTemplate;

import java.util.List;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.fundamental_types.possiblevalue.domain.PossibleValueDefinition;
import com.processpuzzle.fundamental_types.possiblevalue.domain.QuantityEnumeration;
import com.processpuzzle.fundamental_types.possiblevalue.domain.QuantityRange;
import com.processpuzzle.fundamental_types.possiblevalue.domain.StringEnumeration;
import com.processpuzzle.fundamental_types.quantity.domain.Quantity;
import com.processpuzzle.fundamental_types.quantity.domain.Units;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.domain.TestEntity;
import com.processpuzzle.persistence.domain.TestEntityRepository;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class PossibleValueDefinitionTypeMappingTest extends
      RepositoryTestTemplate<TestEntityRepository, PossibleValueDefinitionTypeMappingTestFixture, TestEntity> {

   public PossibleValueDefinitionTypeMappingTest() {
      super( DomainTierTestConfiguration.FIXTURE_CONTAINER_DEFINITION_PATH );
   }

   @Ignore
   @Test
   public void testValueForRange() {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      TestEntity retrievedTestEntity = repository.findById( work, fixture.getTestEntity().getId() );
      work.finish();

      PossibleValueDefinition expectedValeDefinition = fixture.getTestEntity().getPossibleValues();
      assumeThat( expectedValeDefinition, instanceOf( QuantityRange.class ) );

      PossibleValueDefinition retrievedValueDefinition = retrievedTestEntity.getPossibleValues();
      assertThat( retrievedValueDefinition, instanceOf( QuantityRange.class ) );

      Quantity expectedMinValue = ((QuantityRange) expectedValeDefinition).getMinValue();
      Quantity retrievedMinValue = ((QuantityRange) retrievedValueDefinition).getMinValue();
      assertThat( expectedMinValue.getAmount(), equalTo( retrievedMinValue.getAmount() ) );
      assertThat( expectedMinValue.getUnit().getSymbol(), equalTo( retrievedMinValue.getUnit().getSymbol() ) );

      Quantity expectedMaxValue = ((QuantityRange) expectedValeDefinition).getMaxValue();
      Quantity retrievedMaxValue = ((QuantityRange) retrievedValueDefinition).getMaxValue();
      assertThat( expectedMaxValue.getAmount(), equalTo( retrievedMaxValue.getAmount() ) );
      assertThat( expectedMaxValue.getUnit().getSymbol(), equalTo( retrievedMaxValue.getUnit().getSymbol() ) );

      assertEquals( databaseSpy.retrieveColumnFromRow( "T_TEST_ENTITY", root.getId(), String.class, "valueDefinitionClassDiscriminator" ),
            "com.processpuzzle.framework.fundamental_types.domain.possible_value.QuantityRange" );
      assertThat( fixture.getMinValue().getAmount(), equalTo( databaseSpy.retrieveColumnFromRow( "T_TEST_ENTITY", root.getId(), Double.class, "minAmount" ) ) );
      assertThat( fixture.getMinValue().getUnit().getSymbol(), equalTo( databaseSpy.retrieveColumnFromRow( "T_TEST_ENTITY", root.getId(), String.class,
            "minUnit" ) ) );
      assertThat( fixture.getMaxValue().getAmount(), equalTo( databaseSpy.retrieveColumnFromRow( "T_TEST_ENTITY", root.getId(), Double.class, "maxAmount" ) ) );
      assertThat( fixture.getMinValue().getUnit().getSymbol(), equalTo( databaseSpy.retrieveColumnFromRow( "T_TEST_ENTITY", root.getId(), String.class,
            "maxUnit" ) ) );
      assertThat( databaseSpy.retrieveColumnFromRow( "T_TEST_ENTITY", root.getId(), String.class, "possibleValues" ), nullValue() );
   }

   @Ignore
   @Test
   public void testValueForQuantityEnumeration() {
      // SETUP
      Quantity enumValue1 = new Quantity( new Double( 5 ), fixture.getMeasurementContext().findUnitBySymbol( Units.PIECE ) ); // 5 db
      Quantity enumValue2 = new Quantity( new Double( 10 ), fixture.getMeasurementContext().findUnitBySymbol( Units.PIECE ) ); // 10 db
      Quantity enumValue3 = new Quantity( new Double( 12 ), fixture.getMeasurementContext().findUnitBySymbol( Units.PIECE ) ); // 12 db
      Quantity enumValue4 = new Quantity( new Double( 15 ), fixture.getMeasurementContext().findUnitBySymbol( Units.PIECE ) ); // 15 db

      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      TestEntity testEntityForEnumeration = new TestEntity( "Test Entity with enumeration possible value definition" );
      PossibleValueDefinition valueEnumeration = new QuantityEnumeration();
      valueEnumeration.addPossibleValue( enumValue1 );
      valueEnumeration.addPossibleValue( enumValue2 );
      valueEnumeration.addPossibleValue( enumValue3 );
      valueEnumeration.addPossibleValue( enumValue4 );
      testEntityForEnumeration.setPossibleValues( valueEnumeration );

      repository.add( work, testEntityForEnumeration );
      work.finish();

      // VERIFY
      work = new DefaultUnitOfWork( true );
      TestEntity retrievedTestEntity = repository.findById( work, testEntityForEnumeration.getId() );
      work.finish();

      PossibleValueDefinition expectedValeDefinition = testEntityForEnumeration.getPossibleValues();
      assumeThat( expectedValeDefinition, instanceOf( QuantityEnumeration.class ) );

      PossibleValueDefinition retrievedValueDefinition = retrievedTestEntity.getPossibleValues();
      assertThat( retrievedValueDefinition, instanceOf( QuantityEnumeration.class ) );

      Set<Quantity> retrievedValues = ((QuantityEnumeration) retrievedValueDefinition).getValues();
      assertThat( retrievedValues.size(), is( 4 ) );

      assertThat( retrievedValues.contains( enumValue1 ), is( true ) );
      assertThat( retrievedValues.contains( enumValue2 ), is( true ) );
      assertThat( retrievedValues.contains( enumValue3 ), is( true ) );
      assertThat( retrievedValues.contains( enumValue4 ), is( true ) );

      assertThat(
            (String) databaseSpy.retrieveColumnFromRow( "T_TEST_ENTITY", retrievedTestEntity.getId(), String.class, "valueDefinitionClassDiscriminator" ),
            equalTo( "com.processpuzzle.framework.fundamental_types.domain.possible_value.QuantityEnumeration" ) );
      assertThat( databaseSpy.retrieveColumnFromRow( "T_TEST_ENTITY", retrievedTestEntity.getId(), Double.class, "minAmount" ), nullValue() );
      assertThat( databaseSpy.retrieveColumnFromRow( "T_TEST_ENTITY", retrievedTestEntity.getId(), String.class, "minUnit" ), nullValue() );
      assertThat( databaseSpy.retrieveColumnFromRow( "T_TEST_ENTITY", retrievedTestEntity.getId(), Double.class, "maxAmount" ), nullValue() );
      assertThat( databaseSpy.retrieveColumnFromRow( "T_TEST_ENTITY", retrievedTestEntity.getId(), String.class, "maxUnit" ), nullValue() );
      assertThat( (String) databaseSpy.retrieveColumnFromRow( "T_TEST_ENTITY", retrievedTestEntity.getId(), String.class, "possibleValues" ),
            equalTo( "5.0 pc;10.0 pc;12.0 pc;15.0 pc" ) );

   }

   @Ignore
   @Test
   public void testValueForStringEnumeration() {
      // SETUP
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      TestEntity testEntityForEnumeration = new TestEntity( "Test Entity with string enumeration possible value definition" );
      PossibleValueDefinition valueEnumeration = new StringEnumeration();
      valueEnumeration.addPossibleValue( "körte" );
      valueEnumeration.addPossibleValue( "alma" );
      valueEnumeration.addPossibleValue( "szilva" );
      testEntityForEnumeration.setPossibleValues( valueEnumeration );

      repository.add( work, testEntityForEnumeration );
      work.finish();

      // VERIFY
      work = new DefaultUnitOfWork( true );
      TestEntity retrievedTestEntity = repository.findById( work, testEntityForEnumeration.getId() );
      work.finish();

      PossibleValueDefinition expectedValeDefinition = testEntityForEnumeration.getPossibleValues();
      assumeThat( expectedValeDefinition, instanceOf( StringEnumeration.class ) );

      PossibleValueDefinition retrievedValueDefinition = retrievedTestEntity.getPossibleValues();
      assertThat( retrievedValueDefinition, instanceOf( StringEnumeration.class ) );

      List<String> retrievedValues = ((StringEnumeration) retrievedValueDefinition).getValues();
      assertThat( retrievedValues.size(), is( 3 ) );

      assertThat( retrievedValues.contains( "körte" ), is( true ) );
      assertThat( retrievedValues.contains( "alma" ), is( true ) );
      assertThat( retrievedValues.contains( "szilva" ), is( true ) );

      assertThat(
            (String) databaseSpy.retrieveColumnFromRow( "T_TEST_ENTITY", retrievedTestEntity.getId(), String.class, "valueDefinitionClassDiscriminator" ),
            equalTo( "com.processpuzzle.framework.fundamental_types.domain.possible_value.StringEnumeration" ) );
      assertThat( databaseSpy.retrieveColumnFromRow( "T_TEST_ENTITY", retrievedTestEntity.getId(), Double.class, "minAmount" ), nullValue() );
      assertThat( databaseSpy.retrieveColumnFromRow( "T_TEST_ENTITY", retrievedTestEntity.getId(), String.class, "minUnit" ), nullValue() );
      assertThat( databaseSpy.retrieveColumnFromRow( "T_TEST_ENTITY", retrievedTestEntity.getId(), Double.class, "maxAmount" ), nullValue() );
      assertThat( databaseSpy.retrieveColumnFromRow( "T_TEST_ENTITY", retrievedTestEntity.getId(), String.class, "maxUnit" ), nullValue() );
      assertThat( (String) databaseSpy.retrieveColumnFromRow( "T_TEST_ENTITY", retrievedTestEntity.getId(), String.class, "possibleValues" ),
            equalTo( "körte;alma;szilva" ) );

   }

   @Override
   public void testAdd_ForOwnedAttributesAndComponents() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testAdd_ForReferencedAggregateRoots() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testDelete_ForOwnedAttributesAndComponents() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testFindAll_ForResultCount() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testFindById() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testFindById_ForEagerLoadedComponents() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testFindById_ForLazyLoadedComponents() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testFindByQuery_ForComponentAttributes() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testFindByQuery_ForDirectAttributes() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testUpdate_ForOwnedAttributesAndComponents() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testUpdate_ForReferencedAggregateRoots() {
   // TODO Auto-generated method stub

   }
}
