package com.processpuzzle.fundamental_types.domain;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.MockitoAnnotations.Mock;

import com.processpuzzle.application.configuration.domain.ApplicationContextFactory;
import com.processpuzzle.application.configuration.domain.MeasurementContext;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.domain.Application;
import com.processpuzzle.fundamental_types.quantity.domain.Unit;
import com.processpuzzle.fundamental_types.quantity.domain.Units;
import com.processpuzzle.fundamental_types.quantity.money.domain.Currency;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class UnitRepositoryTest {
   @Mock private Application application;
   private ProcessPuzzleContext config;
   private MeasurementContext measurementContext = null;
   private MeasurementContext repository = null;
   private Unit metre;
   private Unit kilometre;
   private Unit gramm;
   private Unit dekagramm;
   private Unit kilogramm;
   private Unit ton;
   private Unit second;
   private Unit minute;
   private Unit hour;
   private Unit day;

   @Before
   public void setUp() throws Exception {
      MockitoAnnotations.initMocks( UnitRepositoryTest.class );
      config = ApplicationContextFactory.create( application, DomainTierTestConfiguration.CONFIGURATION_PROPERTY_FILE );
      config.setUp( Application.Action.start );
      measurementContext = config.getMeasurementContext();

      metre = measurementContext.findUnitBySymbol( Units.METRE );
      kilometre = measurementContext.findUnitBySymbol( Units.KILOMETRE );
      gramm = measurementContext.findUnitBySymbol( Units.GRAMM );
      dekagramm = measurementContext.findUnitBySymbol( Units.DEKAGRAMM );
      kilogramm = measurementContext.findUnitBySymbol( Units.KILOGRAMM );
      ton = measurementContext.findUnitBySymbol( Units.TON );
      second = measurementContext.findUnitBySymbol( Units.SECOND );
      minute = measurementContext.findUnitBySymbol( Units.MINUTE );
      hour = measurementContext.findUnitBySymbol( Units.HOUR );
      day = measurementContext.findUnitBySymbol( Units.DAY );
   }

   @After
   public void tearDown() throws Exception {}

   @Ignore
   @Test
   public final void testUnitRepository_ForSymbol() {
      assertTrue( "We can find a given unit by symbol: ", measurementContext.findUnitBySymbol( "m" ) instanceof Unit );
      // assertTrue("Unit's symbol also uniquely identifies a unit: ", repository.findByIdentityExpression("m") instanceof Unit);
      assertTrue( "We can find a given currency by symbol: ", repository.findUnitBySymbol( "EUR" ) instanceof Currency );
   }
   
   @Ignore
   @Test
   public final void testPredefinedConversationRatio() {
      // Conversation ratios are expexted to setUp by UnitRepository
      assertThat( 0.001d, equalTo( metre.findConversionRatio( kilometre )));
      assertThat( 0.1d, equalTo( gramm.findConversionRatio( dekagramm )));
      assertThat( 0.001d, equalTo( gramm.findConversionRatio( kilogramm )));
      assertThat( 0.000001d, equalTo( gramm.findConversionRatio( ton )));
      assertThat( 0.01d, equalTo( dekagramm.findConversionRatio( kilogramm )));
      assertThat( 0.00001d, equalTo( dekagramm.findConversionRatio( ton )));
      assertThat( 0.001d, equalTo( kilogramm.findConversionRatio( ton )));

      assertThat( 0.016666666666666666d, equalTo( second.findConversionRatio( minute )));
      assertThat( 2.7777777777777777777777777777778e-4d, equalTo( second.findConversionRatio( hour )));
      assertThat( 1.1574074074074074074074074074074e-5d, equalTo( second.findConversionRatio( day )));
      assertThat( 0.016666666666666666666666666666667d, equalTo( minute.findConversionRatio( hour )));
      assertThat( 6.9444444444444444444444444444444e-4d, equalTo( minute.findConversionRatio( day )));
      assertThat( 0.041666666666666666666666666666667d, equalTo( hour.findConversionRatio( day )));
   }

}
