package com.processpuzzle.address.domain;


import com.processpuzzle.address.domain.Country;
import com.processpuzzle.address.domain.CountryFactory;
import com.processpuzzle.address.domain.CountryRepository;
import com.processpuzzle.litest.template.RepositoryTestEnvironment;
import com.processpuzzle.litest.template.RepositoryTestFixture;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;

public class CountryRepositoryTestFixture extends RepositoryTestFixture<CountryRepository, Country> {
   public static final String DISTRICT_TABLE_NAME = "T_DISTRICT";
   public static final String COUNTRY_TABLE_NAME = "T_COUNTRY";
   public static final String SETTLEMENT_TABLE_NAME = "T_SETTLEMENT";
   public static final String ZIPCODE_TABLE_NAME = "T_ZIP_CODE";
   public static final String COUNTRY_ITALY = "Olaszország";
   public static final String COUNTRY_HUNGARY = "Magyarország";
   public static final String SETTLEMENT_NAME_ROMA = "Róma";
   public static final String SETTLEMENT_NAME_MILANO = "Milánó";
   public static final String SETTLEMENT_NAME_BUDAPEST = "Budapest";
   public static final String SETTLEMENT_NAME_CEGLED = "Cegléd";
   private Country italy;
   private static CountryFactory countryFactory;
   private static SettlementFactory settlementFactory;
   private static ZipCodeFactory zipCodeFactory;
   private static Settlement roma;
   private static Settlement milano;
   private static SettlementRepository settlementRepository;

   public CountryFactory getCountryFactory() { return countryFactory; }
   public Settlement getMilano() { return milano; }
   public Country getItaly() { return italy; }
   public Settlement getRoma() { return roma; }
   public SettlementFactory getSettlementFactory() { return settlementFactory; }
   public SettlementRepository getSettlementRepository() { return settlementRepository; }
   public ZipCodeFactory getZipCodeFactory() { return zipCodeFactory; }
   
   public CountryRepositoryTestFixture( RepositoryTestEnvironment<CountryRepository, RepositoryTestFixture<CountryRepository,Country>> testEnvironment ) {
      super( testEnvironment );
   }

   @Override
   protected void afterAggregateCreation() {
   }
   
   @Override
   protected void afterAggregateDeletion() {
   }
   
   @Override
   protected void beforeAggregateCreation() {
      countryFactory = applicationContext.getEntityFactory( CountryFactory.class );
      settlementFactory = applicationContext.getEntityFactory( SettlementFactory.class );
      settlementRepository = applicationContext.getRepository( SettlementRepository.class );
      zipCodeFactory = applicationContext.getEntityFactory( ZipCodeFactory.class );
      
      roma = settlementFactory.createSettlement( SETTLEMENT_NAME_ROMA );
      milano = settlementFactory.createSettlement( SETTLEMENT_NAME_MILANO );

      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      settlementRepository.add( work, roma );
      settlementRepository.add( work, milano );
      work.finish();
   }
   
   @Override
   protected Country createNewAggregate() throws Exception {
      italy = countryFactory.createCountry( COUNTRY_ITALY );
      italy.addSettlement( roma );
      italy.addSettlement( milano );
      return italy;
   }
   
}
