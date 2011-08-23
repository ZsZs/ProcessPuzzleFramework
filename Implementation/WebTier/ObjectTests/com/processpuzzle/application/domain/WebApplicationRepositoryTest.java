package com.processpuzzle.application.domain;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeThat;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.processpuzzle.sharedfixtures.webtier.MockServletContextFixture;

public class WebApplicationRepositoryTest {
   private MockServletContextFixture servletContextFixture;
   private ApplicationRepository repository;
   
   @Before public void beforeEachTests() throws InstantiationException, IOException {
      servletContextFixture = new MockServletContextFixture();
      servletContextFixture.setUp();
      
      repository = ApplicationRepository.getInstance( servletContextFixture.getApplicationStoragePath(), servletContextFixture.getResourceLoader() );
      
      assumeThat( repository.getStorageXmlResource().getFile().exists(), is( true ));
   }
   
   @Test public void findApplication_shouldFindAndInstantiateAlreadyInstalledApp() {
      WebApplication application = (WebApplication) repository.findByName( servletContextFixture.getInstalledApplicationName() );
      assertThat( application, instanceOf( WebApplication.class ));
   }
   
   @After public void afterEachTests() {
      repository = null;
      servletContextFixture.tearDown();
   }
}
