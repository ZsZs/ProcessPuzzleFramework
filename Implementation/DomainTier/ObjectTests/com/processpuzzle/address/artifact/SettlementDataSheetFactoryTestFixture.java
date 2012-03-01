package com.processpuzzle.address.artifact;

import hu.itkodex.litest.template.ArtifactFactoryTestEnvironment;
import hu.itkodex.litest.template.ArtifactFactoryTestFixture;

import com.processpuzzle.address.artifact.SettlementDataSheetFactory;
import com.processpuzzle.address.artifact.SettlementDataSheetRepository;
import com.processpuzzle.address.domain.SettlementRepository;

public class SettlementDataSheetFactoryTestFixture extends ArtifactFactoryTestFixture<SettlementDataSheetFactory, SettlementDataSheet> {
   public static final String COUNTRY_NAME = "Magyarország";
   private static final String SETTLEMENT_NAME = "Szada";
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
      settlementDataSheetRepository = applicationContext.getRepository( SettlementDataSheetRepository.class );
      settlementRepository = applicationContext.getRepository( SettlementRepository.class );
   }

   @Override protected void configureAfterSutInstantiation() {}

   @Override protected void releaseResources() {
      // TODO Automatikusan elõállított metóduscsonk
   }

}
