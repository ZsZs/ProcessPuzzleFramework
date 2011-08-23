package hu.itkodex.litest.template;

import hu.itkodex.commons.generics.GenericTypeParameterInvestigator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.After;
import org.junit.Before;

import com.processpuzzle.application.control.control.CommandDispatcher;
import com.processpuzzle.application.control.control.CommandInterface;

public abstract class FrontCommandTestTemplate<S extends CommandInterface, F extends FrontCommandTestFixture<S>> extends GenericTestTemplate<S,F,FrontCommandTestEnvironment<S, F>>{
   protected S command;
   protected CommandDispatcher commandDispatcher;
   protected HttpServletResponse response;
   protected HttpServletRequest request;
   
   protected FrontCommandTestTemplate( String fixtureContainerConfigurationPath ) {
      super( fixtureContainerConfigurationPath, FrontCommandTestEnvironment.class );
   }

   @Before public void beforeEachTests() throws Exception {
      request = testEnvironment.getRequest();
      response = testEnvironment.getResponse();
      commandDispatcher = new CommandDispatcher( request, response, testEnvironment.getServletContext() );
      
      instantiateCommand();
      command.init( commandDispatcher );
   }

   @After public void afterEachTests() throws Exception {}

   @SuppressWarnings( "unchecked" )
   protected void instantiateCommand() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
      Class<S> commandClass = (Class<S>) GenericTypeParameterInvestigator.getTypeParameter( this.getClass(), 0 );
      command = (S) Class.forName( commandClass.getName() ).newInstance();
   }
}
