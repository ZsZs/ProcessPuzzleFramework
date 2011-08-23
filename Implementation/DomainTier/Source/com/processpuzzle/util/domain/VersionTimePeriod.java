package com.processpuzzle.util.domain;

import com.processpuzzle.fundamental_types.domain.TimePoint;

public class VersionTimePeriod implements Comparable<Object> {
   private Integer id;
   private TimePoint begin;
   private TimePoint end;

   public VersionTimePeriod() {
      super();
   }

   public void inlcudes() {}

   public TimePoint getBegin() {
      return begin;
   }

   public void setBegin( TimePoint begin ) {
      this.begin = begin;
   }

   public TimePoint getEnd() {
      return end;
   }

   public void setEnd( TimePoint end ) {
      this.end = end;
   }

   public Integer getId() {
      return id;
   }

   public int compareTo( Object o ) {
      VersionTimePeriod timePeriod = (VersionTimePeriod) o;
      int c = 0;
      if( (c = this.begin.compareTo( timePeriod.begin )) != 0 )
         return c;
      if( (c = this.end.compareTo( timePeriod.end )) != 0 )
         return c;
      return 0;
   }

}
