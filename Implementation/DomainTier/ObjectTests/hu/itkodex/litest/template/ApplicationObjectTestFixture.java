package hu.itkodex.litest.template;

import hu.itkodex.litest.fixture.GenericTestFixture;
import hu.itkodex.litest.fixture.TransientFreshFixture;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.domain.Application;

public abstract class ApplicationObjectTestFixture<S> extends GenericTestFixture<S> implements TransientFreshFixture<S> {
   protected Application application;
   protected ProcessPuzzleContext applicationContext;
}
