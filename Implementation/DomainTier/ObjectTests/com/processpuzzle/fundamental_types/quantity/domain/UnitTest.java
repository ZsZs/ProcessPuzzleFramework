package com.processpuzzle.fundamental_types.quantity.domain;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.processpuzzle.fundamental_types.quantity.domain.Unit;
import com.processpuzzle.sharedfixtures.domaintier.ProcessPuzzleContextFixture;

public class UnitTest {

   private static ProcessPuzzleContextFixture context = null;
   
   @BeforeClass
   public static void beforeAllTests() {
      context = ProcessPuzzleContextFixture.getInstance();
      context.setUp();
   }

   @Test
   public final void testAddConversionRatio() {
      Unit metre = new Unit("metre", "m");
      Unit mm = new Unit("millimetre", "mm");
      metre.addConversionRatio(0.001, mm);
      assertThat( "Conversation ratio from meter to mm should be: ", 0.001d, equalTo( metre.findConversionRatio(mm) ));
      assertThat( "Conversation ratio from mm to meter should be: ", 1000d, equalTo( mm.findConversionRatio(metre) ));
   }
   
   @Test 
   public void equals() {
      Unit unit1 = new Unit("metre", "m");
      Unit unit2 = new Unit("meter", "m");
      assertThat("A unit  to another one if their symbols equal", unit1.equals(unit2), is(true));

      Unit unit3 = new Unit("centimetre", "cm");
      assertThat("A unit  to another one if their symbols equal", unit1.equals(unit3), is(false));

   }

   @AfterClass
   public static void afterAllTests() {
      context.tearDown();
   }
}
