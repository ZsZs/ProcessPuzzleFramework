package com.processpuzzle.fundamental_types.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.processpuzzle.fundamental_types.domain.TimePeriod;

public class TimePeriodTest {

   /**
    * @throws java.lang.Exception
    */
   @Before
   public void setUp() throws Exception {}

   /**
    * @throws java.lang.Exception
    */
   @After
   public void tearDown() throws Exception {}

   /**
    * Test method for {@link com.processpuzzle.fundamental_types.domain.TimePeriod#TimePeriod(com.processpuzzle.fundamental_types.domain.TimePoint, com.processpuzzle.fundamental_types.domain.TimePoint)}.
    */
   @Test
   public final void testTimePeriodTimePointTimePoint() {
      TimePoint begin = new TimePoint( 2007, 01, 01 );
      TimePoint end = new TimePoint( 2007, 01, 01 );
      assertNotSame(begin, end);
   }

   /**
    * Test method for {@link com.processpuzzle.fundamental_types.domain.TimePeriod#TimePeriod(com.processpuzzle.fundamental_types.domain.TimePoint, com.processpuzzle.fundamental_types.domain.TimePoint)}.
    */
   @Test (expected = AssertionException.class)
   public final void testConstructor_wrongbeginend() {
      TimePoint begin = new TimePoint( 2008, 02, 01 );
      TimePoint end = new TimePoint( 2008, 01, 01 );
      new TimePeriod(begin, end);
   }

   @Test (expected = AssertionException.class)
   public final void testConstructor_openintervalinboth() {
      new TimePeriod(null, null);
   }

   @Test
   public final void testConstructor_openbegin() {
      TimePoint end = new TimePoint( 2008, 12, 01 );
      TimePeriod timePeriod = new TimePeriod(null, end);
      assertEquals(null, timePeriod.getBegin());
   }

   @Test
   public final void testConstructor_openend() {
      TimePoint begin = new TimePoint( 2008, 01, 01 );
      TimePeriod timePeriod = new TimePeriod(begin, null);
      assertEquals(null, timePeriod.getEnd());
   }

   /**
    * Test method for {@link com.processpuzzle.fundamental_types.domain.TimePeriod#compareTo(java.lang.Object)}.
    */
   @Test
   public final void testCompareTo_ForLessThan() {
      TimePoint begin = new TimePoint( 2007, 01, 01 );
      TimePoint end = new TimePoint( 2007, 11, 01 );
      assertEquals(-1, begin.compareTo(end));
   }

   @Test
   public final void testCompareTo_ForEquals() {
      TimePoint begin = new TimePoint( 2007, 01, 01 );
      TimePoint end = new TimePoint( 2007, 01, 01 );
      assertEquals(0, begin.compareTo(end));
   }

   @Test
   public final void testCompareTo_ForGreaterThan() {
      TimePoint begin = new TimePoint( 2007, 01, 01 );
      TimePoint end = new TimePoint( 2007, 07, 15 );
      assertEquals(1, end.compareTo(begin));
   }
   
   @Test
   public final void testIsIn() {
      TimePoint begin = new TimePoint( 2008, 01, 01 );
      TimePoint end = new TimePoint( 2008, 12, 31 );
      TimePeriod timePeriod1 = new TimePeriod( begin, end );
      TimePeriod timePeriod2 = new TimePeriod( begin, null );
      
      assertEquals( timePeriod1.isIn( new TimePoint(2008, 9, 17) ), true);
      assertEquals( timePeriod2.isIn( new TimePoint(2008, 9, 18) ), true);
  }

}
