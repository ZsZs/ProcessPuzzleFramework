package com.processpuzzle.fundamental_types.domain;

public class TimeStamp {
   private java.sql.Timestamp sqlValue;
   
   public TimeStamp( java.sql.Timestamp sqlTimeStamp ) {
      this.sqlValue = sqlTimeStamp;
   }
   
   public static java.util.Date toDate( java.sql.Timestamp timestamp ) {
      long milliseconds = timestamp.getTime() + (timestamp.getNanos() / 1000000);
      return new java.util.Date( milliseconds );
   }
   
   public java.util.Date getDate() {
      return toDate( this.sqlValue );
   }
}
