package com.processpuzzle.sharedfixtures.domaintier;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.business.definition.domain.BusinessDataLoader;
import com.processpuzzle.business.definition.domain.BusinessDefinitionLoader;
import com.processpuzzle.business.definition.domain.BusinessDataLoaderTest;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class BusinessDefinitionFixture {
   private static BusinessDefinitionFixture soleInstance;
   private boolean isConfigured = false;
   private ProcessPuzzleContextFixture contextFixture;
   private ProcessPuzzleContext applicationContext;
   private BusinessDataLoader<?> dataLoader;
   
   public static BusinessDefinitionFixture getInstance() {
      return getInstance( null );
   }
   
   public static BusinessDefinitionFixture getInstance(  ProcessPuzzleContext applicationContext ) {
      if( soleInstance == null ) {
         soleInstance = new BusinessDefinitionFixture( applicationContext );
      }
      return soleInstance;
   }
   
   protected BusinessDefinitionFixture( ProcessPuzzleContext applicationContext ) {
      this.applicationContext = applicationContext;
   }
   
   public void setUp() throws Exception {
      if( !isConfigured ) {
         if( applicationContext == null ) {
            contextFixture = ProcessPuzzleContextFixture.getInstance( DomainTierTestConfiguration.APPLICATION_CONFIGURATION_DESCRIPTOR_PATH );
            contextFixture.setUp();
            applicationContext = contextFixture.getApplicationContext();
         }
         
         dataLoader = BusinessDataLoaderTest.instantiateBusinessDataLoader( 
                           DomainTierTestConfiguration.BUSINESS_DEFINITION_PATH, BusinessDefinitionLoader.class );
         dataLoader.loadData();
         isConfigured = true;
      }      
   }
   
   public void tearDown() {
      dataLoader = null;
      isConfigured = false;
   }
   
   public ProcessPuzzleContext getApplicationContext() { return applicationContext; }
   
   public boolean isConfigured() { return isConfigured; }
}
