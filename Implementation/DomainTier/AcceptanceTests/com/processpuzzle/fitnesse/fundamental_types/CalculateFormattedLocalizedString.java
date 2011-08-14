package com.processpuzzle.fitnesse.fundamental_types;

import com.processpuzzle.application.configuration.domain.InternalizationContext;
import com.processpuzzle.fundamental_types.domain.TimePoint;
import com.processpuzzle.user_session.domain.UserRequestManager;

import fit.ColumnFixture;

public class CalculateFormattedLocalizedString extends ColumnFixture{
   public String language;
   public String country;
   public String day;
   public String month;
   public String year;
   public String style;

   private InternalizationContext internalizationContext = null;
   
   public CalculateFormattedLocalizedString(){
      internalizationContext = UserRequestManager.getInstance().getApplicationContext().getInternalizationContext();
   }
   
   public String tostring() {

      try {
         int y = Integer.parseInt(year);
         int m = Integer.parseInt(month);
         int d = Integer.parseInt(day);
         TimePoint t = new TimePoint(y, m, d);

         return t.format(internalizationContext.findLocaleByLanguageAndCountry(language, country));

      } catch (NumberFormatException e) {
         System.out.println("There is shit in the pancake!");
      }

      return null;
   }
}