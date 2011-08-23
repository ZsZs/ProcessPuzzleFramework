package hu.itkodex.litest.template;

import hu.itkodex.commons.persistence.Entity;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.sharedfixtures.domaintier.MockProcessPuzzleContext;

public abstract class DomainObjectTestFixture<S extends Entity> extends GenericTemplatedFixture<S> {
   protected MockProcessPuzzleContext mockApplicationContextFixture;
   protected ProcessPuzzleContext mockApplicationContext;
   
   protected DomainObjectTestFixture( DomainObjectTestEnvironment<S, ?> testEnvironment ) {
      super( testEnvironment );
      this.mockApplicationContextFixture = testEnvironment.getApplicationContextFixture();
   }
   
   @Override protected void configureBeforeSutInstantiation() {
      mockApplicationContext = mockApplicationContextFixture.getApplicationContext();
   }
}
