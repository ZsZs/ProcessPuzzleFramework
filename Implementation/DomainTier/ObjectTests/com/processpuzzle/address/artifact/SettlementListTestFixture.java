/*
 * Created on Szept 10, 2006
 */
package com.processpuzzle.address.artifact;

import com.processpuzzle.sharedfixtures.domaintier.DomainTier_ConfigurationFixture;
import com.processpuzzle.address.artifact.SettlementList;
import com.processpuzzle.address.artifact.SettlementListFactory;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;

/**
 * @author zsolt.zsuffa
 */
public class SettlementListTestFixture {
   private static DomainTier_ConfigurationFixture configFixture = null;
   private static SettlementDataSheetTestFixture dataSheetFixture = null;
   private static SettlementListTestFixture fixtureInstance = null;
   private ProcessPuzzleContext applicationContext;
   private SettlementList settlementList = null;
   private SettlementListFactory settlementListFactory;

   public static SettlementListTestFixture getInstance() {
      if (fixtureInstance == null)
         return new SettlementListTestFixture();
      return fixtureInstance;
   }

   protected void setUp() {
      configFixture = DomainTier_ConfigurationFixture.getInstance();
      configFixture.setUp();
      
      applicationContext = DomainTier_ConfigurationFixture.getConfig();
      
      dataSheetFixture = SettlementDataSheetTestFixture.getInstance();
      dataSheetFixture.setUp();
      
      settlementListFactory = applicationContext.getEntityFactory( SettlementListFactory.class ); 
      settlementList = settlementListFactory.create();
   }

   protected void tearDown() {
      dataSheetFixture.tearDown();
	   configFixture.tearDown();
   }

   public DomainTier_ConfigurationFixture getConfigurationFixture() {
      return configFixture;
   }

   public SettlementList getSettlementList() {
      return settlementList;
   }
}