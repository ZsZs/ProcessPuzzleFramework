package com.processpuzzle.address.artifact;

import hu.itkodex.commons.persistence.UnitOfWork;
import hu.itkodex.litest.template.ArtifactFactoryTestEnvironment;
import hu.itkodex.litest.template.ArtifactFactoryTestFixture;
import hu.itkodex.litest.template.DefaultApplicationFixture;

import com.processpuzzle.address.artifact.SettlementDataSheetFactory;
import com.processpuzzle.address.artifact.SettlementDataSheetRepository;
import com.processpuzzle.address.domain.Country;
import com.processpuzzle.address.domain.CountryFactory;
import com.processpuzzle.address.domain.CountryRepository;
import com.processpuzzle.address.domain.Settlement;
import com.processpuzzle.address.domain.SettlementRepository;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;

public class SettlementDataSheetFactoryTestFixture extends ArtifactFactoryTestFixture<SettlementDataSheetFactory, SettlementDataSheet> {
   public static final String COUNTRY_NAME = "Magyarország";
   private static final String SETTLEMENT_DATA_SHEET_TYPE_NAME = "SettlementDataSheet";
   private static final String SETTLEMENT_NAME = "Szada";
   private Country country;
   private CountryFactory countryFactory;
   private CountryRepository countryRepository;
   private SettlementDataSheetRepository settlementDataSheetRepository;
   private SettlementRepository settlementRepository;

   //Constructors
   public SettlementDataSheetFactoryTestFixture( ArtifactFactoryTestEnvironment<SettlementDataSheetFactory, ?> testEnvironment ) {
      super( testEnvironment );
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
      createArtifactTypeForSettlementDataSheet();
   }

   @Override protected void configureAfterSutInstantiation() {
      country = countryFactory.createCountry( COUNTRY_NAME );
      UnitOfWork work = new DefaultUnitOfWork( true );
      countryRepository.add( work, country );
      work.finish();
   }

   @Override protected void releaseResources() {
      UnitOfWork work = new DefaultUnitOfWork( true );
      countryRepository.delete( work, country );
      work.finish();
   }
   
   private void createArtifactTypeForSettlementDataSheet(){
      ArtifactType settlementDataSheetType = artifactTypeFactory.createArtifactType( SETTLEMENT_DATA_SHEET_TYPE_NAME, DefaultApplicationFixture.SYSTEM_ADMINISTRATION_ARTIFACT_TYPE_GROUP, SettlementDataSheet.class );
      settlementDataSheetType.setDomainClassName( Settlement.class.getName() );
      UnitOfWork work = new DefaultUnitOfWork( true );
      artifactTypeRepository.add( work, settlementDataSheetType );
      work.finish();
   }

   private void lookUpRepositoriesAndFactories() {
      settlementDataSheetRepository = applicationContext.getRepository( SettlementDataSheetRepository.class );
      settlementRepository = applicationContext.getRepository( SettlementRepository.class );
      countryFactory = applicationContext.getEntityFactory( CountryFactory.class );
      countryRepository = applicationContext.getRepository( CountryRepository.class );
   }
}
