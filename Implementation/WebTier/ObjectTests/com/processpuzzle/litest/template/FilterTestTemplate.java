package com.processpuzzle.litest.template;

import javax.servlet.Filter;
import javax.servlet.RequestDispatcher;

import com.processpuzzle.litest.template.GenericTestTemplate;

public abstract class FilterTestTemplate<S extends Filter, F extends FilterTestFixture<S>> extends GenericTestTemplate<S,F,FilterTestEnvironment<S,F>>{
   protected S filter;

   protected FilterTestTemplate( String fixtureContainerConfigurationPath ) {
      super( fixtureContainerConfigurationPath, FilterTestEnvironment.class );
   }
   
   protected void doDelete() { testEnvironment.getServletRunner().doDelete(); }
   protected void doFilter() { testEnvironment.getServletRunner().doFilter(); }
   protected void doGet() { testEnvironment.getServletRunner().doGet(); }
   protected void doHead() { testEnvironment.getServletRunner().doHead(); }
   protected void doOptions() { testEnvironment.getServletRunner().doOptions(); }
   protected void doPost() { testEnvironment.getServletRunner().doPost(); }
   protected void doPut() { testEnvironment.getServletRunner().doPut(); }
   protected void doTrace() { testEnvironment.getServletRunner().doTrace(); }
   
   protected Object getRequestAttribute( String attributeName ){
      return testEnvironment.getServletRunner().getRequestAttribute( attributeName );
   }
   
   protected RequestDispatcher getRequestDispatcher( String pageName ){
      return testEnvironment.getServletRunner().getRequestDispathcer( pageName );
   }
   
   protected String getRequestDispatcherPath( String pageName ){
      return testEnvironment.getServletRunner().getRequestDispatcherPath( pageName );
   }
   
   protected void setServletContextAttribute( String attributeName, Object attributeValue ){
      testEnvironment.getServletRunner().setServletContextAttribute( attributeName, attributeValue );
   }
   
}
