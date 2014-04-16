package com.processpuzzle.litest.template;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.mockrunner.mock.web.WebMockObjectFactory;
import com.processpuzzle.application.control.control.CommandInterface;
import com.processpuzzle.commons.generics.GenericTypeParameterInvestigator;
import com.processpuzzle.litest.template.GenericTestEnvironment;
import com.processpuzzle.sharedfixtures.domaintier.MockProcessPuzzleContext;
import com.processpuzzle.sharedfixtures.domaintier.RequestContextInMockedApplicationFixture;

public class FrontCommandTestEnvironment<S extends CommandInterface, F extends FrontCommandTestFixture<S>> extends GenericTestEnvironment<S> {
   protected F commandTestFixture;
   protected Class<F> commandTestFixtureClass;
   protected MockServletRunner servletRunner;
   
   @SuppressWarnings( "unchecked" )
   public FrontCommandTestEnvironment( Class<F> commandTestFixtureClass ){
      super();
      this.commandTestFixtureClass = commandTestFixtureClass;
      this.sutClass = (Class<S>) GenericTypeParameterInvestigator.getTypeParameter( commandTestFixtureClass, 0 );
   }
   
   //Public accessors and mutators
   public ServletContext getServletContext() { return servletRunner.getServletContext(); }
   public HttpServletRequest getRequest() { return servletRunner.getRequest(); }
   public HttpServletResponse getResponse() { return servletRunner.getResponse(); }

   public WebMockObjectFactory getWebMockObjectFactory() { return servletRunner.getWebMockObjectFactory(); }

   //Protected, private helper methods
   @Override protected void configureAfterSutInstantiation() {
   }

   @Override protected void configureBeforeSutInstantiation() {
      servletRunner = this.getFixture( MockServletRunner.class );
      commandTestFixture = this.getFixture( commandTestFixtureClass );
   }

   @Override protected void defineComponentTypes() {
      componentTypes.add( RequestContextInMockedApplicationFixture.class );
      componentTypes.add( MockProcessPuzzleContext.class );
      componentTypes.add( MockServletRunner.class );
      componentTypes.add( commandTestFixtureClass );
   }

   @Override protected void releaseResources() {
   }


}
