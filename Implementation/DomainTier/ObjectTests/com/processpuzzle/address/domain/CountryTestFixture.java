package com.processpuzzle.address.domain;


import com.processpuzzle.address.domain.AlreadyExistingSettlementInCountryException;
import com.processpuzzle.address.domain.Country;
import com.processpuzzle.litest.template.DomainObjectTestEnvironment;
import com.processpuzzle.litest.template.DomainObjectTestFixture;

public class CountryTestFixture extends DomainObjectTestFixture<Country> {
   public static final String COUNTRY_NAME = "Hungary";
   public static final String NEW_SETTLEMENT_NAME = "New Settlement";
   public static final String FIRST_SETTLEMENT_NAME = "First Settlement";
   public static final String SECOND_SETTLEMENT_NAME = "Second Settlement";
   private Settlement firstSettlement;
   private Settlement newSettlement;
   private Settlement secondSettlement;
   private static SettlementFactory settlementFactory;
   private Country aCountry = null;

   public CountryTestFixture( DomainObjectTestEnvironment<Country, ?> testEnvironment ) {
      super( testEnvironment );
   }

   //Properties
   public Object getCountryName() { return COUNTRY_NAME; }
   public Settlement getFirstSettlement() { return firstSettlement; }
   public Settlement getNewSettlement() { return newSettlement; }
   public Settlement getSecondSettlement() { return secondSettlement; }
   public SettlementFactory getSettlementFactory() { return settlementFactory; }

   //Protected, private helper methods
   @Override
   protected void configureBeforeSutInstantiation() {
      super.configureBeforeSutInstantiation();
      settlementFactory = new SettlementFactory();
      setUpSettlements();
   }

   @Override
   protected void configureAfterSutInstantiation() {
      try{
         aCountry.addSettlement( firstSettlement );
         aCountry.addSettlement( secondSettlement );
      }catch( AlreadyExistingSettlementInCountryException e ){
         e.printStackTrace();
      }
   }

   @Override
   protected Country instantiateSUT() {
      aCountry = new Country( COUNTRY_NAME );
      return aCountry;
   }

   @Override
   protected void releaseResources() { }
   
   private void setUpSettlements() {
      firstSettlement = new Settlement( FIRST_SETTLEMENT_NAME );
      secondSettlement = new Settlement( SECOND_SETTLEMENT_NAME );
      newSettlement = new Settlement( NEW_SETTLEMENT_NAME );
  }
}
