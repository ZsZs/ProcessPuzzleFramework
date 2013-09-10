package com.processpuzzle.litest.template;


import com.processpuzzle.application.domain.Application;
import com.processpuzzle.application.domain.ApplicationException;
import com.processpuzzle.application.domain.ApplicationFactory;
import com.processpuzzle.litest.fixture.UnconfiguredSharedFixtureException;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestApplication;

public class ConfigurableApplicationFixture extends DefaultApplicationFixture {
   
   public ConfigurableApplicationFixture( String configurationPath ) {
      super( configurationPath );
   }

   @Override
   protected void configureAfterSutInstantiation() {
      super.configureAfterSutInstantiation();
      applicationRepository.add( application );
   }

   @Override
   protected void configureBeforeSutInstantiation() {
      application = ApplicationFactory.create( DomainTierTestApplication.class, configurationPath );
      
      try {
         application.install();
      } catch( ApplicationException e ) {
         throw new UnconfiguredSharedFixtureException( this.getClass().getName(), e );
      }
   }

   @Override
   protected Application instantiateSUT() {
      return application;
   }

   @Override
   protected void releaseResources() {
      application.unInstall();
      applicationRepository.delete( application );
      
      super.releaseResources();
   }
}
