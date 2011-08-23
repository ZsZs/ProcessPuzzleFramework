/*
 * Created on 2005.08.12.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.fundamental_types.quantity.domain;

import hu.itkodex.commons.persistence.PersistentObject;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.processpuzzle.fundamental_types.domain.HashCodeUtil;

public class Unit implements PersistentObject {
   private String name;
   private String symbol;
   private Set<ConversionRatio> possibleTargets = new HashSet<ConversionRatio>();
   private Set<ConversionRatio> possibleSources = new HashSet<ConversionRatio>();

   // Public constructors
   public Unit( String name, String symbol ) {
      this.name = name;
      this.symbol = symbol;
   }

   protected Unit() {}

   // Public accessors
   public void addConversionRatio( double ratio, Unit unit ) {
      ConversionRatio ratioObject = new ConversionRatio( ratio, this, unit );
      possibleTargets.add( ratioObject );
      unit.possibleSources.add( ratioObject );
   }

   // Public mutator methods
   public Double findConversionRatio( Unit unit ) {
      if( this.equals( unit ) )
         return Double.valueOf( 1 );
      for( Iterator<ConversionRatio> iter = possibleTargets.iterator(); iter.hasNext(); ){
         ConversionRatio element = (ConversionRatio) iter.next();
         if( element.getTo().equals( unit ) )
            return element.getRatio();
      }
      for( Iterator<ConversionRatio> iter = possibleSources.iterator(); iter.hasNext(); ){
         ConversionRatio element = (ConversionRatio) iter.next();
         if( element.getFrom().equals( unit ) )
            return 1 / element.getRatio();
      }
      return null;

   }

   public int hashCode() {
      int result = HashCodeUtil.SEED;
      result = HashCodeUtil.hash( result, name );
      result = HashCodeUtil.hash( result, symbol );

      return result;
   }

   // Getters and setters
   public String getSymbol() {
      return symbol;
   }

   public String getName() {
      return name;
   }

   public Integer getId() {
      // TODO Auto-generated method stub
      return null;
   }

   public boolean equals( Unit unit ) {
      return(this.symbol.equals( unit.getSymbol() ));
   }
}
