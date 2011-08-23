package com.processpuzzle.util.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.processpuzzle.fundamental_types.domain.TimePoint;

public class SingleTemporalCollection extends TemporalCollection {
   private Map<TimePoint, Object> contents = new HashMap<TimePoint, Object>();

   public Object get( TimePoint when ) {
      Iterator<TimePoint> it = milestones().iterator();
      while( it.hasNext() ){
         TimePoint thisDate = it.next();
         if( thisDate.before( when ) || thisDate.equals( when ) )
            return contents.get( thisDate );
      }
      throw new IllegalArgumentException( "no records that early" );
   }

   public void put( TimePoint at, Object item ) {
      contents.put( at, item );
      clearMilestoneCache();
   }

   private List<TimePoint> _milestoneCache;

   private List<TimePoint> milestones() {
      if( _milestoneCache == null )
         calculateMilestones();
      return _milestoneCache;
   }

   private void calculateMilestones() {
      _milestoneCache = new ArrayList<TimePoint>( contents.size() );
      _milestoneCache.addAll( contents.keySet() );
      Collections.sort( _milestoneCache, Collections.reverseOrder() );
   }

   private void clearMilestoneCache() {
      _milestoneCache = null;
   }

   public Object get( int year, int month, int date ) {
      return get( new TimePoint( year, month, date ) );
   }

   public Object get() {
      return get( TimePoint.today() );
   }

   public void put( Object item ) {
      put( TimePoint.today(), item );
   }

   public TemporalCollection copy() {
      SingleTemporalCollection result = new SingleTemporalCollection();
      result.contents.putAll( this.contents );
      return result;
   }
}