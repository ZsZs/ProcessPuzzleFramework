package com.processpuzzle.fitnesse.sharedfixtures;

import hu.itkodex.litest.template.DefaultApplicationFixture;

import com.processpuzzle.litest.fixture.FixtureFactory;
import com.processpuzzle.sharedfixtures.domaintier.ApplicationFixture;

import fitlibrary.DoFixture;

public class WebServiceFitClient extends DoFixture {
   private ApplicationFixture<?> applicationFixture;
   private String responseMessege = "";
   
   public WebServiceFitClient() {
      super();
   }
   
   
   public void invokeActionWithRequest( String actionName, String requestMessage ) {
      
   }
   
   public void setUp( String configurationDescriptorPath ) {
      applicationFixture = FixtureFactory.createInstance().createPersistentSharedFixture( DefaultApplicationFixture.class );
      applicationFixture.setUp();
   }
   
   public void tearDown() {
      applicationFixture.tearDown();
   }
   
   public boolean verifyResponseToExpectedResponse( String actionName, String expectedResponse ) {
      return responseMessege.equals( expectedResponse );
   }
}
