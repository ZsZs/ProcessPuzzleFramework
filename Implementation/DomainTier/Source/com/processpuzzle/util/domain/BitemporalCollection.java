package com.processpuzzle.util.domain;

import com.processpuzzle.fundamental_types.domain.TimePoint;

public class BitemporalCollection extends TemporalCollection {

   /*
    * The key data structure of this class is a temporal collection of temporal collections. The inner temporal collections are different versions of the valid
    * history. The outer temporal collection represents the transaction history.
    */

   // <codeFragment name="currentAccess">
   private SingleTemporalCollection contents = new SingleTemporalCollection();

   public BitemporalCollection() {
      contents.put( TimePoint.today(), new SingleTemporalCollection() );
   }

   public Object get( TimePoint when ) {
      return currentValidHistory().get( when );
   }

   private SingleTemporalCollection currentValidHistory() {
      return (SingleTemporalCollection) contents.get();
   }

   // </codeFragment>

   // <codeFragment name="bitemporalGetter">
   public Object get( TimePoint validDate, TimePoint transactionDate ) {
      return validHistoryAt( transactionDate ).get( validDate );
   }

   private TemporalCollection validHistoryAt( TimePoint transactionDate ) {
      return (TemporalCollection) contents.get( transactionDate );
   }

   // </codeFragment>

   // <codeFragment name="update">
   public void put( TimePoint validDate, Object item ) {
      contents.put( TimePoint.today(), currentValidHistory().copy() );
      currentValidHistory().put( validDate, item );
   }

   public void put( Object item ) {
      put( TimePoint.today(), item );
   }

   // </codeFragment>

   public Object get() {
      return get( TimePoint.today() );
   }

   public Object get( int year, int month, int date ) {
      return get( new TimePoint( year, month, date ) );
   }
}