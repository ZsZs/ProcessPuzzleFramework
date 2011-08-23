package com.processpuzzle.fundamental_types.possiblevalue.domain;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.processpuzzle.fundamental_types.quantity.domain.Quantity;
import com.processpuzzle.fundamental_types.quantity.domain.Unit;
import com.processpuzzle.fundamental_types.quantity.domain.Units;
import com.processpuzzle.internalization.domain.ProcessPuzzleLocale;
import com.processpuzzle.sharedfixtures.domaintier.ProcessPuzzleContextFixture;

public class QuantityEnumerationTest {
   private Unit decimetre;
   private QuantityEnumeration sizes;
   private static ProcessPuzzleLocale hungarianLocale = new ProcessPuzzleLocale("hu");
   private static ProcessPuzzleContextFixture context = null;
   
   @BeforeClass
   public static void beforeAllTests() {
      context = ProcessPuzzleContextFixture.getInstance();
      context.setUp();
   }
   

   @Before public void beforeEachTest() {

      decimetre = new Unit("decimetre", Units.DECIMETRE);
      sizes = new QuantityEnumeration();
      sizes.addPossibleValue(new Quantity(5, decimetre));
      sizes.addPossibleValue(new Quantity(10, decimetre));
      sizes.addPossibleValue(new Quantity(15, decimetre));
   }
   
   @Test public void checkValue_ShouldExamineValueEquality() {
      //Setup
      
      //Verify
      assertThat(sizes.checkValue( new Quantity(5, decimetre)), is(true));
   }
   
   @Test public void asText() {
      assertThat(sizes.asText(hungarianLocale), is("( 5 dm, 10 dm, 15 dm )"));
   }
   
   @Test public void checkValue_ForNegativeOutcome() {
      assertThat(sizes.checkValue( new Quantity(6, decimetre)), is(false));
   }
   
   @Test public void stringValue() {
      assertThat(sizes.stringValue(), is("5.0 dm;10.0 dm;15.0 dm"));
   }
   
   @Test public void parseFromString() {
      QuantityEnumeration newEnumeration = new QuantityEnumeration();
      String possibleValuesDef = "7.25 dm;11.01 dm;50.0 dm";
      newEnumeration.parseFromString( newEnumeration, possibleValuesDef );
      assertThat(newEnumeration.getValues().size(), is(3));
      assertThat(newEnumeration.getValues().contains( new Quantity(Double.valueOf("7.25").doubleValue(), decimetre ) ), is(true));
      assertThat(newEnumeration.getValues().contains( new Quantity(Double.valueOf("11.01").doubleValue(), decimetre ) ), is(true));
      assertThat(newEnumeration.getValues().contains( new Quantity(Double.valueOf("50.0").doubleValue(), decimetre ) ), is(true));
   }

   @AfterClass
   public static void afterAllTests() {
      context.tearDown();
   }
}
