package com.processpuzzle.business.definition.domain;

import java.lang.reflect.Constructor;
import java.util.List;

import org.junit.BeforeClass;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.configuration.domain.PropertyContext;
import com.processpuzzle.application.resource.domain.DataLoader;
import com.processpuzzle.business.definition.domain.BusinessDataLoader;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;
import com.processpuzzle.sharedfixtures.domaintier.ProcessPuzzleContextFixture;

@SuppressWarnings("unchecked")
public abstract class BusinessDataLoaderTest<L extends BusinessDataLoader> {
   protected static String xPathKey;
   protected static ProcessPuzzleContextFixture contextFixture;
   protected static ProcessPuzzleContext applicationContext;
   protected static DataLoader dataLoader;
   protected static Class<? extends BusinessDataLoader> dataLoaderClass;

   @BeforeClass
   public static void beforeAllTests() throws Exception {
      contextFixture = ProcessPuzzleContextFixture.getInstance( DomainTierTestConfiguration.APPLICATION_CONFIGURATION_DESCRIPTOR_PATH );
      contextFixture.setUp();

      applicationContext = contextFixture.getApplicationContext();

      dataLoader = instantiateBusinessDataLoader( determineResoucePaths() );
      dataLoader.loadData();
   }
   
   public static BusinessDataLoader instantiateBusinessDataLoader( String resourcePath ) throws Exception {
      return instantiateBusinessDataLoader( resourcePath, dataLoaderClass );
   }
   
   public static BusinessDataLoader instantiateBusinessDataLoader( String resourcePath, Class<? extends BusinessDataLoader> dataLoaderClass ) throws Exception {
      Class<?>[] argumentClasses = { String.class };
      Object[] arguments = { resourcePath };
      Constructor<BusinessDataLoader> applicationConstructor;
      BusinessDataLoader dataLoader = null;
      try{
         applicationConstructor = (Constructor<BusinessDataLoader>) dataLoaderClass.getConstructor( argumentClasses );
         dataLoader = (BusinessDataLoader) applicationConstructor.newInstance( arguments );
      }catch( Exception e ){
         e.printStackTrace();
         throw e;
      }
      return dataLoader;
   }

   protected static String determineResoucePaths() {
      PropertyContext propertyContext = applicationContext.getPropertyContext();
      List<String> businessDefinitionPaths = propertyContext.getPropertyList( xPathKey );

      String combinedPath = "";
      for( String path : businessDefinitionPaths ){
         combinedPath += path + ";";
      }

      return combinedPath;
   }
}
