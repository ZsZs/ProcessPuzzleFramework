package com.processpuzzle.litest.template;


import javax.servlet.Filter;

import com.processpuzzle.commons.generics.GenericTypeParameterInvestigator;
import com.processpuzzle.litest.template.GenericTestEnvironment;
import com.processpuzzle.sharedfixtures.domaintier.MockProcessPuzzleContext;
import com.processpuzzle.sharedfixtures.domaintier.RequestContextInMockedApplicationFixture;

public class FilterTestEnvironment<S extends Filter, F extends FilterTestFixture<S>> extends GenericTestEnvironment<S>{
   protected Class<F> filterTestFixtureClass;
   
   @SuppressWarnings( "unchecked" )
   public FilterTestEnvironment( Class<F> filterTestFixtureClass ){
      super();
      this.filterTestFixtureClass = filterTestFixtureClass;
      this.sutClass = (Class<S>) GenericTypeParameterInvestigator.getTypeParameter( filterTestFixtureClass, 0 );
   }
   
   //Public accessors and mutators
   
   public MockServletRunner getServletRunner() { return getFixture( MockServletRunner.class ); }
   
   //Protected, private helper methods
   @Override protected void configureAfterSutInstantiation() {
   }

   @Override protected void configureBeforeSutInstantiation() {
      getServletRunner().setFilter( sutClass );
   }

   @Override protected void defineComponentTypes() {
      componentTypes.add( RequestContextInMockedApplicationFixture.class );
      componentTypes.add( MockProcessPuzzleContext.class );
      componentTypes.add(  MockServletRunner.class );
      componentTypes.add( filterTestFixtureClass );
   }

   @Override protected void releaseResources() {
   }

}
