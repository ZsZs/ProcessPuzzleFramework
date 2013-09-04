package hu.itkodex.litest.template;


import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.domain.Application;
import com.processpuzzle.litest.fixture.GenericTestFixture;
import com.processpuzzle.litest.fixture.TransientFreshFixture;

public abstract class ApplicationObjectTestFixture<S> extends GenericTestFixture<S> implements TransientFreshFixture<S> {
   protected Application application;
   protected ProcessPuzzleContext applicationContext;
}
