package com.processpuzzle.fundamental_types.domain;

public enum TimePrecision{
   YEAR( "year", 1), 
   MONTH( "month", 2), 
   WEEK( "week", 3), 
   DAY( "day", 4), 
   HOUR( "hour", 5), 
   MINUTE( "minute", 6), 
   SECOND( "second", 7), 
   MILLISECOND( "millisecond", 8);
   private static final int GREATHER = +1;
   private static final int EQUAL = 0;
   private static final int LESS = -1;
   private String name;
   private int value;
   
   TimePrecision( String name, int value ){
      this.name = name;
      this.value = value;
   }
   
   public int compare( TimePrecision otherPrecision ){
      if( value > otherPrecision.getValue() ) return GREATHER;
      if( value == otherPrecision.getValue() ) return EQUAL;
      if( value < otherPrecision.getValue() ) return LESS;
      return value;
   }
   public String getName() { return name; }
   public int getValue() { return value; }
}
