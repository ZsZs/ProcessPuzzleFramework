package com.processpuzzle.application.domain;


import com.processpuzzle.application.domain.Application;
import com.processpuzzle.application.domain.ApplicationException;
import com.processpuzzle.application.domain.ApplicationFactory;
import com.processpuzzle.litest.template.DefaultApplicationFixture;
import com.processpuzzle.sharedfixtures.domaintier.ApplicationTwo;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class InstalledAndStoppedApplicationFixture extends DefaultApplicationFixture<Application> {
   
   public InstalledAndStoppedApplicationFixture() {
      super( DomainTierTestConfiguration.FIXTURE_CONTAINER_DEFINITION_PATH );
   }

   @Override
   protected Application instantiateSUT() {
      application = ApplicationFactory.create( ApplicationTwo.class, configurationPath );
      
      try {
         application.install();
      } catch( ApplicationException e ) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
      application.stop();
      applicationRepository.add( application );
      return application;
   }

   @Override
   protected void configureBeforeSutInstantiation() {
      // TODO Auto-generated method stub
      
   }
}
