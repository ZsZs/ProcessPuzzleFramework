package com.processpuzzle.artifact.domain;

import org.junit.After;
import org.junit.Before;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.domain.Application;


public class AssetBackupTest {
   
 //  private AssetRepository assetRepository = null;

   @Before
   public void setUp() throws Exception {
//      ProcessPuzzleContext config = ProcessPuzzleContext.createInstance(  ConfigurationConstants.CONFIGURATION_DESCRIPTOR_FILE );
      ProcessPuzzleContext config = ProcessPuzzleContext.getInstance();
      config.setUp( Application.Action.start );
  //    assetRepository = (AssetRepository)config.getRepository(AssetRepository.class);

   }

   @After
   public void tearDown() throws Exception {}

}
