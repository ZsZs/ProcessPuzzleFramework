package com.processpuzzle.application.domain;

import java.io.FileNotFoundException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.IsEqual.*;
import static org.hamcrest.core.IsInstanceOf.*;

import com.processpuzzle.application.domain.Application.ExecutionStatus;
import com.processpuzzle.sharedfixtures.webtier.MockServletContextFixture;

public class WebApplicationManagerTest {
   private static MockServletContextFixture contextFixture;
   private WebApplicationManager applicationManager;
   private WebApplication application;
   
   @BeforeClass public static void beforeAllTests() throws FileNotFoundException {
      contextFixture = new MockServletContextFixture();
      contextFixture.setUp();
   }
   
   @Before public void beforeEachTests() throws InstantiationException {
      applicationManager = new WebApplicationManager( contextFixture.getApplicationStoragePath(), contextFixture.getResourceLoader(), contextFixture.getServletContext() );      
   }
   
   @Test public void install_InstantiatesSubclassOfWebApplication() throws ApplicationInstallationException, ApplicationUninstallationException {
      //EXCERCISE:
      application = applicationManager.installWebApplication( "NewWebApplication", ProcessPuzzleWebApplication.class, contextFixture.getConfigurationDescriptorPath() );
      
      //VERIFY:
      assertThat( application, instanceOf( ProcessPuzzleWebApplication.class ));
      assertThat( application.getExecutionStatus(), equalTo( ExecutionStatus.running ));
      
      //TEARDOWN:
      applicationManager.unInstall( application );
   }
   
   @AfterClass public static void afterAllTests() { contextFixture.tearDown(); }
}
