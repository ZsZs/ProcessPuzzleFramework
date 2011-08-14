package com.processpuzzle.fitnesse.fundamental_types;

import java.text.DateFormat;

import com.processpuzzle.application.configuration.domain.InternalizationContext;
import com.processpuzzle.fundamental_types.domain.TimePoint;
import com.processpuzzle.fundamental_types.quantity.domain.QuantityHelper;
import com.processpuzzle.fundamental_types.quantity.domain.TimeValue;
import com.processpuzzle.user_session.domain.UserRequestManager;

import fit.ColumnFixture;

public class CalculateTimePoint extends ColumnFixture{
   public String timeValueDay;
   public String timeValueWeek;
   public String timeValueMonth;
   public String timeValueYear;
   public String timeValueHour;
   public String timeValueMin;
   public String timeValueSec;
   public String timeValueMsec;
   
   public String timepoint;
   public String style;
   
   private InternalizationContext internalizationContext = null;
   
   public CalculateTimePoint(){
      internalizationContext = UserRequestManager.getInstance().getApplicationContext().getInternalizationContext();
   }
   
   public String timePoint(){
      String[] styles=style.split(",");
      TimePoint t=null;
      if (styles.length > 1) {
        t=internalizationContext.findLocaleByLanguageAndCountry("en", "US").getDateFormat().parse(timepoint,getStyle(styles[0]),getStyle(styles[1]));         
      } else {
         t=internalizationContext.findLocaleByLanguageAndCountry("en", "US").getDateFormat().parse(timepoint,getStyle(styles[0]));
      }
      try {
         int y = Integer.parseInt(timeValueYear);
         int m = Integer.parseInt(timeValueMonth);
         int w = Integer.parseInt(timeValueWeek);
         int d = Integer.parseInt(timeValueDay);
         int h = Integer.parseInt(timeValueHour);
         int min = Integer.parseInt(timeValueMin);
         int sec = Integer.parseInt(timeValueSec);
         int msec = Integer.parseInt(timeValueMsec);
         t.add(new TimeValue(y,QuantityHelper.unitFinder("yr")));
         t.add(new TimeValue(m,QuantityHelper.unitFinder("mth")));
         t.add(new TimeValue(w,QuantityHelper.unitFinder("wk")));
         t.add(new TimeValue(d,QuantityHelper.unitFinder("d")));
         t.add(new TimeValue(h,QuantityHelper.unitFinder("h")));
         t.add(new TimeValue(min,QuantityHelper.unitFinder("min")));
         t.add(new TimeValue(sec,QuantityHelper.unitFinder("s")));
         t.add(new TimeValue(msec,QuantityHelper.unitFinder("msec")));
         if (styles.length > 1) {
            return t.format(internalizationContext.findLocaleByLanguageAndCountry("en", "US"),getStyle(styles[0]),getStyle(styles[1]));         
         } else {
            return t.format(internalizationContext.findLocaleByLanguageAndCountry("en", "US"),getStyle(styles[0]));
         }
      } catch (NumberFormatException e) {
         System.out.println("There is shit in the pancake.");
      }
      return null;
   }
   
   private int getStyle(String str){
      if (str.equals("short")){
         return DateFormat.SHORT;
      }
      if (str.equals("medium")){
         return DateFormat.MEDIUM;
      }
      if (str.equals("long")){
         return DateFormat.LONG;
      }
      if (str.equals("full")){
         return DateFormat.FULL;
      }
      return 0;
   }
}