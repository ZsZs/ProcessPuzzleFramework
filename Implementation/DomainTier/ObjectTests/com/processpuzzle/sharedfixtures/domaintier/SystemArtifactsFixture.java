package com.processpuzzle.sharedfixtures.domaintier;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.business.definition.domain.SystemArtifactsLoader;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class SystemArtifactsFixture {
   private static SystemArtifactsFixture soleInstance;
   private ProcessPuzzleContextFixture contextFixture;
   private ProcessPuzzleContext applicationContext;
   private boolean isConfigured = false;
   private SystemArtifactsLoader artifactsLoader;
   
   protected SystemArtifactsFixture( ProcessPuzzleContext applicationContext ) {
      super();
      this.applicationContext = applicationContext;
   }

   public static SystemArtifactsFixture getInstance( ProcessPuzzleContext applicationContext ) {
      if( soleInstance == null ) {
         soleInstance = new SystemArtifactsFixture( applicationContext );
      }
      return soleInstance;
   }
   
   public void setUp() throws Exception {
      if( !isConfigured ) {
         if( applicationContext == null ) {
            contextFixture = ProcessPuzzleContextFixture.getInstance( DomainTierTestConfiguration.APPLICATION_CONFIGURATION_DESCRIPTOR_PATH );
            contextFixture.setUp();
            applicationContext = contextFixture.getApplicationContext();
         }
         
         artifactsLoader = new SystemArtifactsLoader();
         artifactsLoader.loadData();
         isConfigured = true;
      }      
   }
   
   public void tearDown() {
      artifactsLoader = null;
      isConfigured = false;
   }
   
   public ProcessPuzzleContext getApplicationContext() { return applicationContext; }
   
   public boolean isConfigured() { return isConfigured; }
}
