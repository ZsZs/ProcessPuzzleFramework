package com.processpuzzle.fitnesse.fundamental_types;

import java.text.DateFormat;
import java.util.Calendar;

import com.processpuzzle.application.configuration.domain.InternalizationContext;
import com.processpuzzle.fundamental_types.domain.TimePoint;
import com.processpuzzle.user_session.domain.UserRequestManager;

import fit.ColumnFixture;

public class ParseTime extends ColumnFixture{
   public String source;
   public String style;
   
   private InternalizationContext internalizationContext = null;
   TimePoint time=null;
   Calendar cal=null;
   
   public ParseTime(){
      internalizationContext = UserRequestManager.getInstance().getApplicationContext().getInternalizationContext();

      cal=Calendar.getInstance();
   }
   
   private void parseSource(){
      String[] styles=style.split(",");
      if (styles.length > 1) {
         time=internalizationContext.findLocaleByLanguageAndCountry("en", "US").getDateFormat().parse(source,getStyle(styles[0]),getStyle(styles[1]));
      }else{
         time=internalizationContext.findLocaleByLanguageAndCountry("en", "US").getDateFormat().parse(source,getStyle(styles[0]));         
      }
      cal.setTime(time.getValue());
   };
 
   public int hourValue(){
      parseSource();
      return cal.get(Calendar.HOUR_OF_DAY);
   }  

   public int minValue() {
      parseSource();
      return cal.get(Calendar.MINUTE);
   }

   public int secValue() {
      parseSource();
      return cal.get(Calendar.SECOND);  
   }

   public int msecValue() {
      parseSource();
      return cal.get(Calendar.MILLISECOND);   
   }

   public int dayValue() {
      parseSource();
      return cal.get(Calendar.DAY_OF_MONTH);
   }

   public int monthValue() {
      parseSource();
      return cal.get(Calendar.MONTH)+1; // Zero Based months!
   }

   public int yearValue() {
      parseSource();
      return cal.get(Calendar.YEAR);     
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