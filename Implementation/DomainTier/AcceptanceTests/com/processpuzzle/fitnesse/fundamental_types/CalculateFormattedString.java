package com.processpuzzle.fitnesse.fundamental_types;

import java.text.DateFormat;
import java.util.Calendar;

import com.processpuzzle.application.configuration.domain.InternalizationContext;
import com.processpuzzle.fundamental_types.domain.TimePoint;
import com.processpuzzle.user_session.domain.UserRequestManager;

import fit.ColumnFixture;

public class CalculateFormattedString extends ColumnFixture{
   public String source;
   public String day;  
   public String month;   
   public String year;    
   public String hour;    
   public String minute;  
   public String sec;     
   public String msec;
   public String style;
   
   private InternalizationContext internalizationContext = null;
   Calendar cal;
   
   public CalculateFormattedString(){
      internalizationContext = UserRequestManager.getInstance().getApplicationContext().getInternalizationContext();

      cal=Calendar.getInstance();
      cal.setLenient(false);
   }
   
   public String string() {
      String[] styles=style.split(",");
      TimePoint t;
      try {
         int y = Integer.parseInt(year);
         int m = Integer.parseInt(month);
         int d = Integer.parseInt(day);
         int h = Integer.parseInt(hour);
         int min = Integer.parseInt(minute);
         int sec = Integer.parseInt(this.sec);
         int msec = Integer.parseInt(this.msec);

         t=new TimePoint(y,m,d,h,min,sec,msec);
         if (styles.length > 1) {
            return t.format( internalizationContext.findLocaleByLanguageAndCountry("en", "US"),getStyle(styles[0]),getStyle(styles[1]));         
         } else {
            return t.format( internalizationContext.findLocaleByLanguageAndCountry("en", "US"),getStyle(styles[0]));
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
