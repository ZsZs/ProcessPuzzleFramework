/*
 * Created on 2005.08.08.
 */
package com.processpuzzle.fundamental_types.domain;

import java.util.Calendar;
import java.util.Date;

import com.processpuzzle.internalization.domain.ProcessPuzzleLocale;
import com.processpuzzle.preferences.domain.Preferences;

public class TimePeriod implements Comparable<Object> {
   private TimePoint begin = null;
   private TimePoint end   = null;

   // Public constructors
   public TimePeriod(TimePoint begin, TimePoint end) {
      if ( (begin == null || begin.getValue() ==  null) && 
            (end == null || end.getValue() == null) )
         throw new AssertionException("TimePeriod can't be open in both direction: begin AND end.");
      if (end != null && begin != null && begin.compareTo( end )>0) {
         throw new AssertionException("Begin time point can not be greater than end time point.");
      }
      this.begin = begin;
      this.end = end;
   }

   public TimePeriod() {}
   
   public String format(ProcessPuzzleLocale locale) {
      String beginStr = "";
      String endStr = "";
      if ( begin != null ) {
         beginStr = begin.format( locale );
      }
      if ( end != null ) {
         endStr = end.format( locale );
      }
      
      return new StringBuffer(beginStr).append( "-" ).append( endStr ).toString();
   }

   public String format(Preferences preferences) {
      // begin-user-code
      // TODO Auto-generated method stub
      return null;
      // end-user-code
   }

   public Object parse(String source, Object preferences) {
      // begin-user-code
      // TODO Auto-generated method stub
      return null;
      // end-user-code
   }

   public int daysBetween() {
      Date a = begin.getValue();
      Date b = end.getValue();
      int tempDifference = 0;
      int difference = 0;
      Calendar earlier = Calendar.getInstance();
      Calendar later = Calendar.getInstance();

      if (a.compareTo(b) < 0) {
         earlier.setTime(a);
         later.setTime(b);
      } else {
         earlier.setTime(b);
         later.setTime(a);
      }

      while (earlier.get(Calendar.YEAR) != later.get(Calendar.YEAR)) {
         tempDifference = 365 * (later.get(Calendar.YEAR) - earlier.get(Calendar.YEAR));
         difference += tempDifference;

         earlier.add(Calendar.DAY_OF_YEAR, tempDifference);
      }

      if (earlier.get(Calendar.DAY_OF_YEAR) != later.get(Calendar.DAY_OF_YEAR)) {
         tempDifference = later.get(Calendar.DAY_OF_YEAR) - earlier.get(Calendar.DAY_OF_YEAR);
         difference += tempDifference;

         earlier.add(Calendar.DAY_OF_YEAR, tempDifference);
      }
      if (a.compareTo(b) > 0) {
         difference *= -1;
      }
      return difference;
   }
   
   public boolean equals( Object objectToCheck ) {
      if( !(objectToCheck instanceof TimePeriod )) return false;
      
      TimePeriod anotherTimePeriod = (TimePeriod) objectToCheck;
      return anotherTimePeriod.getBegin().equals( begin ) && anotherTimePeriod.getEnd().equals( end );
   }

   public int compareTo(Object timePeriodToCompare) {
      TimePeriod compareTo = (TimePeriod) timePeriodToCompare;

      if (begin.compareTo(compareTo.getBegin()) < 0)
         return -1;
      else if (end.compareTo(compareTo.getEnd()) > 0)
         return +1;
      else
         return 0;
   }

   public int hashCode() {
      int result = HashCodeUtil.SEED;
      result = HashCodeUtil.hash( result, begin );
      result = HashCodeUtil.hash( result, end );
      return result;
   }   

   // Getters and setters
   public TimePoint getBegin() { return begin; }

   public TimePoint getEnd() { return end; }
   
   public boolean isIn(TimePoint timepoint) {
      if (timepoint == null) { return false; }
      if ( ( begin== null || timepoint.compareTo(begin) >= 0 )
          && (end == null || timepoint.compareTo(end) <=0 ) ) {
         return true;
      } else {
         return false;
      }
   }
}
