package com.processpuzzle.address.domain;

import hu.itkodex.commons.persistence.AggregateRoot;

import com.processpuzzle.fundamental_types.domain.GenericEntity;

public class ZipCode extends GenericEntity<ZipCode> implements Comparable<Object>, AggregateRoot {
   public static final int MAX_ZIP_VALUE = 9999;
   private Integer zipCode;
   private Settlement settlement;
   private ZipCodeIdentity defaultIdentity;

   // Constructors
   public ZipCode( Integer zipCode ) {
      if( validate( zipCode ) ){
         this.zipCode = zipCode;
         defaultIdentity = new ZipCodeIdentity();
      }else
         throw new ZipCodeConstraintViolationException( 0, zipCode.toString() );
   }

   protected ZipCode() {}
   
   //Public accessors and mutators
   public String asString() {
      return zipCode.toString();
   }

   public int compareTo( Object o ) {
      if( !(o instanceof ZipCode) )
         return -1;
      ZipCode z = (ZipCode) o;
      int c;
      if( (c = zipCode.compareTo( z.getZipCode() )) != 0 )
         return c;
      return c;
   }
   
   //Properties
   @SuppressWarnings( "unchecked" ) @Override public ZipCodeIdentity getDefaultIdentity() { return null; }
   public Settlement getSettlement() { return settlement; }
   public Integer getZipCode() { return zipCode; }
   public void setSettlement( Settlement settlement ) { this.settlement = settlement; }
   public void setZipCode( Integer zipCode ) {
      if( validate( zipCode ) ){
         this.zipCode = zipCode;
      }else
         throw new ZipCodeConstraintViolationException( 0, zipCode.toString() );
   }

   protected void defineIdentityExpressions() {
      defaultIdentity = new ZipCodeIdentity();
      identities.add( defaultIdentity );
   }


   private boolean validate( Integer zipCode ) {
      if( zipCode.intValue() < 0 )
         return false;
      if( zipCode.intValue() > MAX_ZIP_VALUE )
         return false;
      return true;
   }
}
