/**
 * 
 */
package com.processpuzzle.fundamental_types.domain;

import static org.junit.Assert.*;
import static org.junit.Assume.*;

import static org.hamcrest.Matchers.*;

import java.text.ParseException;
import java.util.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.processpuzzle.internalization.domain.ProcessPuzzleLocale;

public class TimePointTest {
   private static final int SAMPLE_SECONDS = 25;
   private static final int SAMPLE_MINUTES = 33;
   private static final int SAMPLE_HOUR = 8;
   private static final int SAMPLE_DAY = 23;
   private static final int SAMPLE_YEAR = 2011;
   private static final int SAMPLE_MONTH = 03;

   private static final String DATE_AS_STRING = "2011.03.20";
   private ProcessPuzzleLocale aLocale;

   @Before public void beforeEachTests() {
      Date date = new Date();
      assumeThat( date, equalTo( date ));
      aLocale = new ProcessPuzzleLocale( "hu", "HU" );
   }
   
   @Test public final void instantiation_WhenDateWithPrecissionSpecified(){
      TimePoint aTimePoint = new TimePoint( SAMPLE_YEAR, SAMPLE_MONTH, SAMPLE_DAY, SAMPLE_HOUR, SAMPLE_MINUTES, SAMPLE_SECONDS );
      
      TimePoint newTimePoint = new TimePoint( aTimePoint.getValue(), TimePrecision.YEAR );
      assertThat( newTimePoint.getYear(), equalTo( SAMPLE_YEAR ));
      assertThat( newTimePoint.getMonth(), nullValue() );
      assertThat( newTimePoint.getDay(), nullValue() );
   }
   
   @Test public final void instantiation_WhenTimeIsSpecifiedAsString_UsesDefaultPrecission(){
      TimePoint aTimePoint = new TimePoint( DATE_AS_STRING, aLocale );
      assertThat( aTimePoint.getPrecision(), equalTo( TimePrecision.DAY ));
   }

   @Test
   public final void equal_ShouldEqualWithItself() {
      TimePoint tPoint = new TimePoint( 2007, 1, 1 );
      assertThat( tPoint, equalTo( tPoint ) );
      assertThat( tPoint.getPrecision(), equalTo( TimePrecision.DAY ));
   }
   
   @Test 
   public final void equal_IsFalseWhenPrecisionDiffers() {
      TimePoint timeWithDayPrecision = new TimePoint( 2009, 5, 31 );
      TimePoint timeWithHourPrecision = new TimePoint( 2009, 5, 31, 0, null );
      
      assertThat( timeWithDayPrecision.equals( timeWithHourPrecision ), is( false ));
   }

   @Test
   public final void testCompareTo_ForLessThan() {
      TimePoint tPoint1 = new TimePoint( 2007, 03, 15 );
      TimePoint tPoint2 = new TimePoint( 2007, 03, 02 );
      assertEquals( -1, tPoint2.compareTo( tPoint1 ) );
   }

   @Test
   public final void testCompareTo_ForEquals() {
      TimePoint tPoint1 = new TimePoint( 2007, 03, 11 );
      TimePoint tPoint2 = new TimePoint( 2007, 03, 11 );
      assertEquals( 0, tPoint1.compareTo( tPoint2 ) );
   }

   @Test
   public final void testCompareTo_ForGreaterThan() {
      TimePoint tPoint1 = new TimePoint( 2007, 03, 15 );
      TimePoint tPoint2 = new TimePoint( 2007, 03, 02 );
      assertEquals( 1, tPoint1.compareTo( tPoint2 ) );
   }

   @Test
   public final void testDayOfMonts() throws ParseException {
      TimePoint aTimePoint = new TimePoint( 1999, 1, 11 );
      assertEquals( "The day of month is: ", 11, aTimePoint.dayOfMonth() );
   }

   @Test
   public final void testIsLeapYear() {
      TimePoint noneLeapYear = new TimePoint( 2007, 03, 11 );
      TimePoint leapYear = new TimePoint( 2008, 01, 11 );
      assertFalse( noneLeapYear.isLeapYear() );
      assertTrue( leapYear.isLeapYear() );
   }
   
   @Test
   public final void testEqualsWithDateValues() {
      TimePoint timePointOne = new TimePoint( 2008, 4, 28 );
      TimePoint timePointTwo = new TimePoint( 2008, 4, 28 );
      
      assertThat( timePointOne, equalTo( timePointTwo ) );
   }
   
   @Test
   public final void testEqualsWithDateAndTimeValues() {
      TimePoint timePointOne = new TimePoint( 2008, 4, 28, 8, 32, 11 );
      TimePoint timePointTwo = new TimePoint( 2008, 4, 28, 8, 32, 11 );
      
      assertThat( timePointOne, equalTo( timePointTwo ) );

      assertThat( timePointOne.getPrecision(), equalTo( TimePrecision.SECOND ));
   }
   
   @Test
   public final void testEqualsWithTimeInMillis() {
      Date currentTime = new Date( System.currentTimeMillis() );
      TimePoint timePointOne = new TimePoint( currentTime );
      TimePoint timePointTwo = new TimePoint( currentTime );
      
      assertThat( timePointOne, equalTo( timePointTwo ) );
      assertThat( timePointOne.getPrecision(), equalTo( TimePrecision.MILLISECOND ));
   }
   
   @Test public final void equals_WhenTimeIsSpecifiedByString() {
      TimePoint timePointOne = new TimePoint( DATE_AS_STRING, aLocale );
      TimePoint timePointTwo = new TimePoint( DATE_AS_STRING, aLocale );
      assertThat( timePointOne.equals( timePointTwo ), is( true ));
      assertThat( timePointTwo.equals( timePointOne ), is( true ));
   }

   @After
   public void afterEachTest() throws Exception {}
}
