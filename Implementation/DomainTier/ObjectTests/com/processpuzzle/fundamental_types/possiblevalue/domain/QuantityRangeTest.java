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

public class QuantityRangeTest {
   private Unit decimetre;
   private QuantityRange range;
   private static ProcessPuzzleLocale hungarianLocale = new ProcessPuzzleLocale("hu");
   private static ProcessPuzzleContextFixture context = null;
   
   @BeforeClass
   public static void beforeAllTests() {
      context = ProcessPuzzleContextFixture.getInstance();
      context.setUp();
   }
   
   @Before public void beforeEachTest() {
      
      decimetre = new Unit("decimetre", Units.DECIMETRE);
      Quantity minValue = new Quantity(5, decimetre);
      Quantity maxValue = new Quantity(8, decimetre);
      range = new QuantityRange(minValue, maxValue);
   }
   
   @Test public void checkValue_ShouldExamineValueEquality() {
      //Setup
      
      //Verify
      assertThat(range.checkValue( new Quantity(6, decimetre)), is(true));
      assertThat(range.checkValue( new Quantity(8, decimetre)), is(true));
      assertThat(range.checkValue( new Quantity(9, decimetre)), is(false));
   }
   
   @Test public void asText() {
      assertThat(range.asText(hungarianLocale), is("( 5 dm - 8 dm )"));
   }
   
   @Test (expected = InvalidValueRangeException.class)
   public void constructor_failed() {
      new QuantityRange(new Quantity(8, decimetre), new Quantity(2, decimetre));
   }
   
   @AfterClass
   public static void afterAllTests() {
      context.tearDown();
   }

}
