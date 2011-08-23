package com.processpuzzle.fundamental_types.domain;

import java.util.Date;


public class SystemTime extends TimePoint {

   public SystemTime() {
      super(new Date(System.currentTimeMillis()));
   }
}
