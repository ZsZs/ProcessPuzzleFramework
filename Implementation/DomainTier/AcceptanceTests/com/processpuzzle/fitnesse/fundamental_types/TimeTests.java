package com.processpuzzle.fitnesse.fundamental_types;

import com.processpuzzle.user_session.domain.UserRequestManager;

import fitlibrary.DoFixture;

public class TimeTests extends DoFixture {
   
   public CalculateConversionRatio calculateConversionRatio() {
      System.out.println( "UserRequestManager: " + UserRequestManager.getInstance() );
      System.out.println( "Current user name: " + UserRequestManager.getInstance().currentUser().getUserName() );
      System.out.println( "Application context: " + UserRequestManager.getInstance().getApplicationContext() );
      return new CalculateConversionRatio();
   }
   
   public CalculateConvert calculateConvert() {
      return new CalculateConvert();
   }
   
   public CalculateFormattedLocalizedString calculateFormattedLocalizedString() {
      return new CalculateFormattedLocalizedString();
   }
   
   public CalculateFormattedString calculateFormattedString() {
      return new CalculateFormattedString();
   }
   
   public CalculateTimePoint calculateTimepoint() {
      return new CalculateTimePoint();
   }
   
   public CalculateTimeTo calculateTimeTo() {
      return new CalculateTimeTo();
   }
   
   public CompareTimes compareTimes() {
      return new CompareTimes();
   }
   
   public ParseLocalizedTime parseLocalizedTime() {
      return new ParseLocalizedTime();
   }
   
   public ParseTime parseTime() {
      return new ParseTime();
   }
   
   public SetUpDateFormat setUpDateFormat() {
      return new SetUpDateFormat();
   }
}
