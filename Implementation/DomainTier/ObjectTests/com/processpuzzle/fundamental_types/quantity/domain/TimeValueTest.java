/**
 * 
 */
package com.processpuzzle.fundamental_types.quantity.domain;

import static org.junit.Assert.*;
import static org.hamcrest.core.IsEqual.*;
import java.text.DateFormat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.processpuzzle.fundamental_types.domain.TimePoint;
import com.processpuzzle.fundamental_types.quantity.domain.TimeValue;
import com.processpuzzle.fundamental_types.quantity.domain.Unit;
import com.processpuzzle.generictests.PropertyContextAwareTest;

/**
 * @author Rendszergazda
 * 
 */
public class TimeValueTest extends PropertyContextAwareTest {

   private TimeValue tv;
   private Unit un;
   @Before
   public void beforeEachTests() {
   }

   @Test
   public void testTimeValue_forAddition() {
      //Setup
      un = new Unit("Perc", "yr");
      tv = new TimeValue(4, un); 
      TimePoint t = TimePoint.parse( "31/01/2008", i18Context.findLocaleByLanguageAndCountry( "en", "GB" ), DateFormat.MEDIUM );
      //TimeValue v = new TimeValue(4, QuantityHelper.unitFinder("yr"));
      
      //Excercise SUT
      t.add(tv);
      
      //Examine outcome
      assertEquals("31/01/2012", t.format(i18Context.findLocaleByLanguageAndCountry("en", "GB"), DateFormat.MEDIUM));
      
      // assertEquals("11/10/2000 00:00",repository.getDefaultLocale("en",
      // "GB").getDateFormat().format(t.getValue(),DateFormat.MEDIUM,DateFormat.MEDIUM));
      // v=new TimeValue(2,QuantityHelper.unitFinder("wk"));
      // t.add(v);
      // assertEquals("25/10/2000 00:00",repository.getDefaultLocale("en",
      // "GB").getDateFormat().format(t.getValue(),DateFormat.MEDIUM,DateFormat.MEDIUM));
   }

   /**
    * Test method for
    * {@link com.processpuzzle.fundamental_types.quantity.domain.TimeValue#TimeValue(double, com.processpuzzle.fundamental_types.quantity.domain.Unit)}.
    */
   @Test
   public final void testTimeValueDoubleUnit() {
      Double amount = Double.valueOf(30.5);
      // Unit unit = new Unit("min");
      assertThat(30.5, equalTo(amount));
   }

   /**
    * Test method for
    * {@link com.processpuzzle.fundamental_types.quantity.domain.TimeValue#TimeValue(java.lang.Integer, com.processpuzzle.fundamental_types.quantity.domain.Unit)}.
    */
   @Test
   public final void testTimeValueIntegerUnit() {
      Integer amount = new Integer(20);
      // Unit unit = new Unit("sec");
      assertEquals(new Integer(20), amount);
   }

   @After
   public void afterEachTests() {}

}
