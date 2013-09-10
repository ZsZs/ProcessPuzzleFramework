package com.processpuzzle.application.domain;


import com.processpuzzle.litest.template.DefaultApplicationFixture;
import com.processpuzzle.sharedfixtures.domaintier.ApplicationOne;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class NotInstalledApplicationFixture extends DefaultApplicationFixture {
   
   public NotInstalledApplicationFixture() {
      super( DomainTierTestConfiguration.FIXTURE_CONTAINER_DEFINITION_PATH );
   }

   @Override
   public void setUp() {
      super.setUp();
   }

   @Override
   protected Application instantiateSUT() {
      application = ApplicationFactory.create( ApplicationOne.class, configurationPath );
      return application;
   }

   @Override
   protected void configureBeforeSutInstantiation() {
      // TODO Auto-generated method stub
      
   }

}
