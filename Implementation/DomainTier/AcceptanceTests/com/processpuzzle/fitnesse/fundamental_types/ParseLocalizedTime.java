package com.processpuzzle.fitnesse.fundamental_types;

import java.text.DateFormat;
import java.util.Calendar;

import com.processpuzzle.application.configuration.domain.InternalizationContext;
import com.processpuzzle.fundamental_types.domain.TimePoint;
import com.processpuzzle.user_session.domain.UserRequestManager;

import fit.ColumnFixture;

public class ParseLocalizedTime extends ColumnFixture{
   public String source;
   public String style;
   public String language;
   public String country;
   
   TimePoint time=null;
   Calendar cal=null;
   private InternalizationContext internalizationContext = null;
   
   public ParseLocalizedTime(){
      internalizationContext = UserRequestManager.getInstance().getApplicationContext().getInternalizationContext();
      cal=Calendar.getInstance();
   }
   
   private void parseSource(){
      String[] styles=style.split(",");
      if (styles.length > 1) {
         time=internalizationContext.findLocaleByLanguageAndCountry(language, country).getDateFormat().parse(source,getStyle(styles[0]),getStyle(styles[1]));
      }else{
         time=internalizationContext.findLocaleByLanguageAndCountry(language, country).getDateFormat().parse(source,getStyle(styles[0]));         
      }
      cal.setTime(time.getValue());
      cal.setLenient(false);
   };
 

   public int timevalue3() {
      parseSource();
      return cal.get(Calendar.DAY_OF_MONTH);
   }

   public int timevalue2() {
      parseSource();
      return cal.get(Calendar.MONTH)+1; // Zero-based Months!
   }

   public int timevalue1() {
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
