package com.processpuzzle.address.domain;


import com.processpuzzle.litest.template.FactoryTestEnvironment;
import com.processpuzzle.litest.template.FactoryTestFixture;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;

public class CountryFactoryTestFixture extends FactoryTestFixture<CountryFactory, Country> {
   public static final String ANOTHER_COUNTRY_NAME = "Hungary";
   public static final String COUNTRY_NAME = "Italy";
   public static final String SETTLEMENT_NAME = "Budapest";
   private CountryFactory countryFactory;
   private CountryRepository countryRepository;
   private Settlement settlement;
   private SettlementFactory settlementFactory;
   private Country newCountry;
   private Country newCountryWithSettlement;

   public CountryFactoryTestFixture( FactoryTestEnvironment<CountryFactory, ?> testEnvironment ) {
      super( testEnvironment );
   }
   
   //Properties
   public Country getNewCountry() { return newCountry; }
   public Country getNewCountryWithSettlement() { return newCountryWithSettlement; }
   public Settlement getSettlement() { return settlement; }

   //Protected, private helper methods
   @Override protected void configureAfterSutInstantiation() {
      countryRepository = applicationContext.getRepository( CountryRepository.class );
      settlementFactory = applicationContext.getEntityFactory( SettlementFactory.class );
      
      createCountry();
      createCountryWithSettlement();
      
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      countryRepository.addCountry( work, newCountry );
      work.finish();
   }

   @Override protected void configureBeforeSutInstantiation() {
      super.configureBeforeSutInstantiation();
   }

   @Override protected CountryFactory instantiateSUT() {
      countryFactory = applicationContext.getEntityFactory( CountryFactory.class );
      return countryFactory;
   }

   @Override protected void releaseResources() {
      super.deleteAggregateRoot( newCountry );
      super.releaseResources();
   }

   private void createCountry() {
      newCountry = countryFactory.createCountry( COUNTRY_NAME );
   }

   private void createCountryWithSettlement() {
      settlement = settlementFactory.createSettlement( SETTLEMENT_NAME );
      newCountryWithSettlement = sut.createCountry( ANOTHER_COUNTRY_NAME, settlement );
   }
}
