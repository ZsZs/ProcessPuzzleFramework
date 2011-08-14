package com.processpuzzle.address.artifact;

import hu.itkodex.litest.template.ArtifactFactoryTestEnvironment;
import hu.itkodex.litest.template.ArtifactFactoryTestFixture;

import com.processpuzzle.address.artifact.SettlementDataSheetFactory;
import com.processpuzzle.address.artifact.SettlementDataSheetRepository;
import com.processpuzzle.address.domain.SettlementRepository;

public class SettlementDataSheetFactoryTestFixture extends ArtifactFactoryTestFixture<SettlementDataSheetFactory> {
   private SettlementDataSheetRepository settlementDataSheetRepository;
   private SettlementRepository settlementRepository;

   //Constructors
   protected SettlementDataSheetFactoryTestFixture( ArtifactFactoryTestEnvironment<SettlementDataSheetFactory, ?> testEnvironment ) {
      super( testEnvironment );
   }

   //Properties
   public SettlementDataSheetRepository getSettlementDataSheetRepository() {
      return settlementDataSheetRepository;
   }

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

   @Override
   protected SettlementDataSheetFactory instantiateSUT() {
      // TODO Automatikusan elõállított metóduscsonk
      return null;
   }

   @Override
   protected void releaseResources() {
      // TODO Automatikusan elõállított metóduscsonk
      
   }
}
