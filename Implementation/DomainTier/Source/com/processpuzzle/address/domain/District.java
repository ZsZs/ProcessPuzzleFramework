package com.processpuzzle.address.domain;

import java.util.HashSet;
import java.util.Set;

import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

public class District extends GenericEntity<District> implements Comparable<District> {
   private String name;
   private Set<ZipCode> zipCodes = new HashSet<ZipCode>();

   protected District() {}

   District( String name ) {
      this.name = name;
   }

   public void addZipCode( ZipCode zipCode ) {
      this.zipCodes.add( zipCode );
   }

   @SuppressWarnings("unchecked")
   @Override
   public DefaultIdentityExpression<District> getDefaultIdentity() {
      return null;
   }

   public int compareTo( District objectToCompare ) {
      if( !(objectToCompare instanceof District) ) return -1;
      
      District anotherDistrict = (District) objectToCompare;
      int nameComparisonResult;
      if(( nameComparisonResult = this.name.compareTo( anotherDistrict.getName() )) != 0 )
         return nameComparisonResult;
      return nameComparisonResult;
   }

   public String getName() {
      return name;
   }

   public void setName( String name ) {
      this.name = name;
   }

   public Set<ZipCode> getZipCodes() {
      return zipCodes;
   }

   @Override
   protected void defineIdentityExpressions() {
   // TODO Auto-generated method stub
   
   }
}
