package com.processpuzzle.address.domain;


import com.processpuzzle.address.domain.Country;
import com.processpuzzle.address.domain.CountryFactory;
import com.processpuzzle.litest.template.DomainObjectTestEnvironment;
import com.processpuzzle.litest.template.DomainObjectTestFixture;

public class DistrictTestFixture extends DomainObjectTestFixture<District> {
   public static final String COUNTRY_NAME = "Magyarország";
   public static final String SETTLEMENT_NAME = "Budapest";
   public static final String DISTRIC_NAME = "V.";
   private SettlementFactory settlementFactory;
   private CountryFactory countryFactory;
   private Settlement settlement;
   private Country country;
   private District district;

   public DistrictTestFixture( DomainObjectTestEnvironment<District, ?> testEnvironment ) {
      super( testEnvironment );
   }

   //Properties
   public Country getCountry() { return country; }
   public District getDistrict() { return district; }
   public Settlement getSettlement() { return settlement; }
   
   //Protected, private helper methods
   @Override
   protected void configureAfterSutInstantiation() { }

   @Override
   protected void configureBeforeSutInstantiation() {
      super.configureBeforeSutInstantiation();
      countryFactory = mockApplicationContext.getEntityFactory( CountryFactory.class );
      settlementFactory = mockApplicationContext.getEntityFactory( SettlementFactory.class );
      
      country = countryFactory.createCountry( COUNTRY_NAME );
      settlement = settlementFactory.createSettlement( SETTLEMENT_NAME, country );
      district = new District( DISTRIC_NAME );
   }

   @Override
   protected District instantiateSUT() {
      return district;
   }

   @Override
   protected void releaseResources() {
      // TODO Auto-generated method stub
   }
}
