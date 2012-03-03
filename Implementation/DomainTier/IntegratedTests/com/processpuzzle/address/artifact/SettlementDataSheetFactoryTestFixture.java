package com.processpuzzle.address.artifact;

import hu.itkodex.commons.persistence.UnitOfWork;
import hu.itkodex.litest.template.ArtifactFactoryTestEnvironment;
import hu.itkodex.litest.template.ArtifactFactoryTestFixture;

import com.processpuzzle.address.domain.Country;
import com.processpuzzle.address.domain.CountryFactory;
import com.processpuzzle.address.domain.CountryRepository;
import com.processpuzzle.address.domain.SettlementRepository;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;

public class SettlementDataSheetFactoryTestFixture extends ArtifactFactoryTestFixture<SettlementDataSheetFactory, SettlementDataSheet> {
   public static final String COUNTRY_NAME = "Magyarország";
   private static final String SETTLEMENT_NAME = "Szada";
   private Country country;
   private CountryFactory countryFactory;
   private CountryRepository countryRepository;
   private SettlementDataSheet settlementDataSheet;
   private SettlementDataSheetRepository settlementDataSheetRepository;
   private SettlementRepository settlementRepository;

   //Constructors
   public SettlementDataSheetFactoryTestFixture( ArtifactFactoryTestEnvironment<SettlementDataSheetFactory, ?> testEnvironment ) {
      super( testEnvironment );
   }

   public void createAndSaveTheSubjectSettlement() {
      settlementDataSheet = sut.create( SETTLEMENT_NAME, COUNTRY_NAME );      
      settlementDataSheetRepository.add( settlementDataSheet );
   }

   //Properties
   public String getCountryName() { return COUNTRY_NAME; }
   public SettlementDataSheetRepository getSettlementDataSheetRepository() { return settlementDataSheetRepository; }
   public String getSettlementName() { return SETTLEMENT_NAME; }

   public SettlementRepository getSettlementRepository() {
      return settlementRepository;
   }

   //Protected, private helper methods
   @Override
   protected void configureBeforeSutInstantiation() {
      super.configureBeforeSutInstantiation();
      lookUpRepositoriesAndFactories();
   }

   @Override protected void configureAfterSutInstantiation() {
      country = countryFactory.createCountry( COUNTRY_NAME );
      UnitOfWork work = new DefaultUnitOfWork( true );
      countryRepository.add( work, country );
      work.finish();
   }

   @Override protected void releaseResources() {
      UnitOfWork work = new DefaultUnitOfWork( true );
      if( settlementDataSheet != null ) settlementDataSheetRepository.delete( work, settlementDataSheet );

      countryRepository.delete( work, country );
      work.finish();
   }
   
   private void lookUpRepositoriesAndFactories() {
      settlementDataSheetRepository = applicationContext.getRepository( SettlementDataSheetRepository.class );
      settlementRepository = applicationContext.getRepository( SettlementRepository.class );
      countryFactory = applicationContext.getEntityFactory( CountryFactory.class );
      countryRepository = applicationContext.getRepository( CountryRepository.class );
   }
}
