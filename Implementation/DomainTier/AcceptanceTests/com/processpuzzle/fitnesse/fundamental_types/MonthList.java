package com.processpuzzle.fitnesse.fundamental_types;

import java.text.ParseException;

import fit.ColumnFixture;

public class MonthList extends ColumnFixture {

   public String monthName;

   public int days() throws ParseException {
//      DateFormat formatter = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ENGLISH);
//      TimePoint timepoint = new TimePoint(formatter.parse("31. " + monthName + " 1999."));
//      System.out.println(timepoint.dayOfMonth());
//      return timepoint.dayOfMonth();
   
//      GregorianCalendar cal = new GregorianCalendar();
//       
//      cal.setTime(new Date(2007, new Integer(monthName), 1));
      return 0;

   }
}
