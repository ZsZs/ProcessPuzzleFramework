package hu.itkodex.litest.template;

import com.processpuzzle.application.control.control.CommandInterface;

public abstract class FrontCommandTestFixture<S extends CommandInterface> extends GenericTemplatedFixture<S>{
   protected FrontCommandTestEnvironment<S, ?> testEnvironment;

   protected FrontCommandTestFixture( FrontCommandTestEnvironment<S, ?> testEnvironment ) {
      super( testEnvironment );
      this.testEnvironment = testEnvironment;
   }

}
