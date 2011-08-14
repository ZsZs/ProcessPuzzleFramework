package com.processpuzzle.fitnesse.fundamental_types;
import java.text.DateFormat;
import java.util.Calendar;

import com.processpuzzle.application.configuration.domain.InternalizationContext;
import com.processpuzzle.fundamental_types.domain.TimePoint;
import com.processpuzzle.user_session.domain.UserRequestManager;

import fit.ColumnFixture;

public class CompareTimes extends ColumnFixture{ 
   public String style1;
   public String style2;
   public String timepoint1;
   public String timepoint2;
   public String basecompare;
   
   private InternalizationContext internalizationContext = null;
   Calendar cal;
   
   public CompareTimes(){
      internalizationContext = UserRequestManager.getInstance().getApplicationContext().getInternalizationContext();

      cal=Calendar.getInstance();
      cal.setLenient(false);
   }
   
   public int compareto(){
      if (basecompare==null) basecompare=new String();
      if (basecompare.equals("timepoint1")) style2=style1;
      if (basecompare.equals("timepoint2")) style1=style2;
      String[] styles1=style1.split(",");
      String[] styles2=style2.split(",");
      TimePoint t1,t2;      
      
      if (styles1.length>1){
         t1=internalizationContext.findLocaleByLanguageAndCountry("en", "US").getDateFormat().parse(timepoint1,getStyle(styles1[0]),getStyle(styles1[1]));
      }else{
         t1=internalizationContext.findLocaleByLanguageAndCountry("en", "US").getDateFormat().parse(timepoint1,getStyle(styles1[0]));        
      }
      
      if (styles2.length>1){
         t2=internalizationContext.findLocaleByLanguageAndCountry("en", "US").getDateFormat().parse(timepoint2,getStyle(styles2[0]),getStyle(styles2[1]));
      }else{
         t2=internalizationContext.findLocaleByLanguageAndCountry("en", "US").getDateFormat().parse(timepoint2,getStyle(styles2[0]));        
      }
      
      return t2.compareTo(t1);
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