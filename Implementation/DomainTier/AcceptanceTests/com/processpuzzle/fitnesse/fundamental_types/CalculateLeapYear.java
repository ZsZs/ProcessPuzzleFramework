package com.processpuzzle.fitnesse.fundamental_types;

import java.util.GregorianCalendar;

import com.processpuzzle.fundamental_types.domain.TimePoint;

import fit.ColumnFixture;

public class CalculateLeapYear extends ColumnFixture {

   public int year;
   private TimePoint timePoint = null;
   private GregorianCalendar calendar = new GregorianCalendar();
   
   public boolean leapYear() {
      timePoint = new TimePoint(year,10,31);
      return timePoint.isLeapYear();
   }
   
   public int days() {
      calendar.setTime(timePoint.getValue());
      return calendar.getActualMaximum(GregorianCalendar.DAY_OF_YEAR);
   }
   
   public int february() {
	  if (timePoint.isLeapYear()) return 29;
	  else return 28;
   }
   
}
