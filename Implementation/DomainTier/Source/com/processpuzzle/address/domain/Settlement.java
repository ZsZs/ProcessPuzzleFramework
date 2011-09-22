package com.processpuzzle.address.domain;

import hu.itkodex.commons.persistence.AggregateRoot;

import java.util.HashSet;
import java.util.Set;

import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.persistence.query.domain.DefaultQueryContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.not;

public class Settlement extends GenericEntity<Settlement> implements Comparable<Settlement>, AggregateRoot {
   private String name;
   private Country country;
   private Set<ZipCode> zipCodes = new HashSet<ZipCode>();
   private Set<District> districts = new HashSet<District>();
   private SettlementIdentity defaultIdentity;

   //Constructors
   protected Settlement() {}

   public Settlement( String name ) {
      this.name = name;
   }
   
   //Public accessor and mutator methods
   public void addDistrict( District district ) {
      this.districts.add( district );
   }

   public void addZipCode( ZipCode zipCode ) {
      assertThat( "Zip codes should be unique within a settlement.", this.zipCodes, not( contains( zipCode ) ));
      
      this.zipCodes.add( zipCode );
   }

   public int compareTo( Settlement o ) {
      if( !(o instanceof Settlement) )
         return -1;
      Settlement s = (Settlement) o;
      int c;
      if( (c = this.name.compareTo( s.getName() )) != 0 )
         return c;
      return c;
   }
   
   public ZipCode zipCodeByNumber( Integer thZipCode ) {
      for( ZipCode zipCode : zipCodes ){
         if( zipCode.getZipCode().intValue() == thZipCode.intValue() ){
            return zipCode;
         }
      }
      return null;
   }

   //Properties
   public Country getCountry() { return country; }
   @SuppressWarnings("unchecked") @Override public SettlementIdentity getDefaultIdentity() { return null; }
   public Set<District> getDistricts() { return districts; }
   public String getName() { return name; }
   public Set<ZipCode> getZipCodes() { return zipCodes; }

   public void setCountry( Country country ) { this.country = country; }
   public void setName( String name ) { this.name = name; }
   public void setZipCodes( Set<ZipCode> zipCodes ) { this.zipCodes = zipCodes; }

   //Protected, private helper methods
   protected void defineIdentityExpressions() {
      DefaultQueryContext context = new DefaultQueryContext();
      defaultIdentity = new SettlementIdentity( context );
      identities.add( defaultIdentity );
   }

}