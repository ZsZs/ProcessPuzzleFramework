package com.processpuzzle.application.configuration.control;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.web.context.support.ServletContextResourceLoader;

import com.processpuzzle.application.configuration.domain.WebApplicationContext;
import com.processpuzzle.application.domain.ApplicationException;
import com.processpuzzle.application.domain.ProcessPuzzleWebApplication;
import com.processpuzzle.application.domain.WebApplication;
import com.processpuzzle.application.domain.WebApplicationFactory;
import com.processpuzzle.application.domain.Application.ExecutionStatus;
import com.processpuzzle.sharedfixtures.webtier.MockServletContextFixture;
import com.processpuzzle.sharedfixtures.webtier.WebTierTestConfiguration;

public class WebApplicationTest {
   private static MockServletContextFixture fixture;
   private WebApplication application = null;
   
   @BeforeClass public static void BeforeAllTests() throws FileNotFoundException {
      fixture = new MockServletContextFixture();
      fixture.setUp();
   }
   
   @Before
   public void beforeEachTests() throws ApplicationException {
      application = WebApplicationFactory.create( ProcessPuzzleWebApplication.class, WebTierTestConfiguration.CONFIGURATION_DESCRIPTOR_PATH, fixture.getServletContext() );
      application.start();         
   }
   
   @Test
   public final void testCreateInstance() {
      assertTrue("WebApplicaton instantiates: ", application.getContext() instanceof WebApplicationContext );
   }
   
   @Test
   public final void testStart() {
      assertTrue("WebApplicationContext uses a resource loder:", application.getContext().getResourceLoader() instanceof ServletContextResourceLoader );
      assertThat( application.getExecutionStatus(), equalTo( ExecutionStatus.running ));
   }
   
   @After public void afterEachTests() {
      application.stop();
   }
   
   @AfterClass public static void afterAllTests() {
      fixture.tearDown();
   }
}
