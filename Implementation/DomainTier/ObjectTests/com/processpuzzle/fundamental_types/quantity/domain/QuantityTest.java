/**
 * 
 */
package com.processpuzzle.fundamental_types.quantity.domain;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.MockitoAnnotations.Mock;

import com.processpuzzle.application.configuration.domain.MeasurementContext;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.domain.Application;
import com.processpuzzle.fundamental_types.domain.AssertionException;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;
import com.processpuzzle.fundamental_types.domain.ZeroDenominatorException;
import com.processpuzzle.fundamental_types.textformat.domain.QuantityFormatSpecifier;
import com.processpuzzle.internalization.domain.ProcessPuzzleLocale;
import com.processpuzzle.preferences.domain.Preferences;
import com.processpuzzle.preferences.domain.UserPreferences;
import com.processpuzzle.sharedfixtures.domaintier.ProcessPuzzleContextFixture;

public class QuantityTest {
   @Mock private static Application application;
   private static Unit kgUnit = null;
   private static Unit dkgUnit = null;
   private static Unit grammUnit = null;
   private static ProcessPuzzleLocale englishLocale = new ProcessPuzzleLocale("en");
   private static ProcessPuzzleLocale hungarianLocale = new ProcessPuzzleLocale("hu");
   private static MeasurementContext measurementContext = null;
   private Preferences testPreferences = null;
   private QuantityFormatSpecifier quantityNumberFormat = null;      

   private Quantity oneKilogramm = quantityCreator( 1, Units.KILOGRAMM );
   private Quantity fifteenKilogram = quantityCreator( 15, Units.KILOGRAMM );
   private Quantity oneThousandFivehundredOneKilogramm = quantityCreator( 1501, Units.KILOGRAMM );
   private Quantity onehundredDekagramm = quantityCreator( 100, Units.DEKAGRAMM );
   private Quantity tenThousandGramm = quantityCreator( 10000, Units.GRAMM );
   private Quantity sixPointFiveTon = quantityCreator( 6.5, Units.TON );
   private Quantity tenKilometre = quantityCreator( 10, Units.KILOMETRE );
   private Quantity fivehundredMetre = quantityCreator( 500, Units.METRE );

   private static ProcessPuzzleContextFixture contextFixture = null;
      
   @BeforeClass
   public static void setUp() throws Exception {
      contextFixture = ProcessPuzzleContextFixture.getInstance();
      contextFixture.setUp();

/*      MockitoAnnotations.initMocks( QuantityTest.class );
      application = mock(Application.class);
      ProcessPuzzleContext config = ApplicationContextFactory.create( application, DomainTierTestConfiguration.CONFIGURATION_DESCRIPTOR_PATH );
      config.setUp( Application.Action.start );
 */     
      ProcessPuzzleContext config = contextFixture.getApplicationContext();
      measurementContext = config.getMeasurementContext();
      
      kgUnit = measurementContext.findUnitBySymbol( Units.KILOGRAMM );
      dkgUnit = measurementContext.findUnitBySymbol( Units.DEKAGRAMM );
      grammUnit = measurementContext.findUnitBySymbol( Units.GRAMM );
   }
   
   @Before
   public void setUpBeforeTest() {
      testPreferences = new UserPreferences( englishLocale );
      quantityNumberFormat = new QuantityFormatSpecifier( englishLocale );      
   }

   @After 
   public void tearDown() throws Exception {}

   @Test(expected = AssertionException.class) 
   public final void testConstructor_WithNullParameters() {
      new Quantity((Double) null, kgUnit );
      new Quantity(12.0, null );
   }

   @Test
   public final void testAsText_ForEnglishLocale_WithDefaults() {
      //Note: This is the default formmating.
      assertEquals("In english 'six point five ton' quantity is: ", "6.5 t", sixPointFiveTon.asText( englishLocale ) );
   }

   @Test
   public final void testAsText_ForEnglishLocale_WithPreferences() {
      quantityNumberFormat.setDecimalSeparator( ',');
      testPreferences.prefereQuantityFormat( quantityNumberFormat );
      assertEquals("We have overridden the default '.' separator to ',' ", "6,5 t", sixPointFiveTon.asText( testPreferences ) );      
   }

   @Test
   public final void testAsText_ForHungarianLocale_WithDefaults() {
      //Note: This is the default formmating.
      assertEquals("In hungarian 'six point five ton' quantity is: ", "6,5 t", sixPointFiveTon.asText( hungarianLocale ) );
   }

   @Test
   public final void testCompareTo() {
      assertEquals("If the base quantity is less than the argument than we expect: ", -1, fifteenKilogram.compareTo(oneThousandFivehundredOneKilogramm));
      assertEquals("If the base quantity is greater than the argument than we expect: ", +1, oneThousandFivehundredOneKilogramm.compareTo(fifteenKilogram));
      assertEquals("If boths quantities equals then we expect: ", 0, oneKilogramm.compareTo( onehundredDekagramm ));
      
      assertEquals("Event with different unit, the rules are same.", -1, fivehundredMetre.compareTo( tenKilometre ));
   }

   @Test
   public final void testConvertTo() throws UnitMismatchException {
      Quantity convertedQuantity = oneKilogramm.convertTo( grammUnit );
      assertThat( "The amount of converted quantity is:", 1000.0, equalTo( convertedQuantity.getAmount() ));
      assertThat( "The unit of converted quantity changed to: ", grammUnit, equalTo( convertedQuantity.getUnit() ));
   }

   @Test
   public final void testEquals() {
      Quantity anotherTenThousandGramm = quantityCreator( 10000, Units.GRAMM );
      assertTrue( tenThousandGramm.equals( anotherTenThousandGramm ));
   }

   /**
    * Test method for
    * {@link com.processpuzzle.fundamental_types.quantity.domain.Quantity#add(com.processpuzzle.fundamental_types.quantity.domain.Quantity)}.
    * 
    * @throws UnitMismatchException
    */
   @Test
   public final void testAdd() {
      Quantity qLower = new Quantity(Double.valueOf(10), kgUnit );
      Quantity qHigher = new Quantity(Double.valueOf(5), dkgUnit );
      assertThat( "Adding 5 dkg to 10 kg should result: ", 10.05, equalTo( qLower.add(qHigher).getAmount() ));
   }

   @Test
   public final void testAdd_WithSameUnits() {
      Quantity tenKg = new Quantity(Double.valueOf(10), kgUnit );
      Quantity fiveKg = new Quantity(Double.valueOf(5), kgUnit );
      assertThat( "Adding 5 kg to 10 kg should be: ", 15.0, equalTo( tenKg.add(fiveKg).getAmount() ));
   }

   @Test
   public final void add_WithDifferentUnits() {
      Quantity twoPointThreeKg = quantityCreator(2.3, Units.KILOGRAMM);
      Quantity fiveDkg = quantityCreator(5, Units.DEKAGRAMM);
      assertThat( "Adding 2.3 kgs and 5 dkgs should be: ", 2.35f, equalTo( twoPointThreeKg.add(fiveDkg).getAmount().floatValue() ));
   }

   /**
    * Test method for
    * {@link com.processpuzzle.fundamental_types.quantity.domain.Quantity#subtract(com.processpuzzle.fundamental_types.quantity.domain.Quantity)}.
    * 
    * @throws UnitMismatchException
    * @throws AssertionException
    */
   @Test
   public final void testSustract() {
      Quantity qLower = new Quantity(Double.valueOf("0.5"), kgUnit );
      Quantity qHigher = new Quantity(Double.valueOf("50"), dkgUnit );
      assertThat( "", 0.0, equalTo( qLower.subtract(qHigher).getAmount() ));
   }

   /**
    * Test method for
    * {@link com.processpuzzle.fundamental_types.quantity.domain.Quantity#multiply(com.processpuzzle.fundamental_types.quantity.domain.Quantity)}.
    * 
    * @throws UnitMismatchException
    * @throws AssertionException
    */
   @Test
   public final void testMultiply() throws AssertionException, UnitMismatchException {
      Quantity qLower = new Quantity(Double.valueOf(2), kgUnit );
      Quantity qHigher = new Quantity(Double.valueOf(200), dkgUnit );
      assertThat( 4.0, equalTo( qLower.multiply(qHigher).getAmount() ));
   }

   /**
    * Test method for
    * {@link com.processpuzzle.fundamental_types.quantity.domain.Quantity#divide(com.processpuzzle.fundamental_types.quantity.domain.Quantity)}.
    * 
    * @throws UnitMismatchException
    * @throws AssertionException
    */
   @Test(expected = ZeroDenominatorException.class)
   public final void testDivide() throws AssertionException, UnitMismatchException {
      Quantity qLower = new Quantity(Double.valueOf("5"), kgUnit );
      Quantity qHigher = new Quantity(Double.valueOf("500"), dkgUnit );
      assertThat( 1.0, equalTo( qLower.divide(qHigher).getAmount() ));
      
      qLower.divide(quantityCreator( 0, Units.KILOGRAMM )); //throws exception
   }   
   
   @Test
   public final void testParse() throws ProcessPuzzleProgrammingException,InvalidUnitException {
	   Quantity q = Quantity.parse( "32.4 kg", testPreferences );
	   assertNotNull( q );
      assertThat( 32.4, equalTo( q.getAmount() ));
   }
   
   private Quantity quantityCreator(double amount, String symbol) {
      return new Quantity(Double.valueOf(amount), measurementContext.findUnitBySymbol(symbol));
   }
   @AfterClass
   public static void afterAllTests() {
      contextFixture.tearDown();
   }

}
