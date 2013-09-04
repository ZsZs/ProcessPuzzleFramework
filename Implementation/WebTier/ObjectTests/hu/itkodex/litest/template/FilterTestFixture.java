package hu.itkodex.litest.template;

import javax.servlet.Filter;
import javax.servlet.ServletContext;

import com.processpuzzle.litest.template.GenericTemplatedFixture;

public abstract class FilterTestFixture<S extends Filter> extends GenericTemplatedFixture<S>{
   FilterTestEnvironment<S, ?> testEnvironment;
   
   public FilterTestFixture( FilterTestEnvironment<S, ?> testEnvironment ) {
      super( testEnvironment );
      this.testEnvironment = testEnvironment;
   }

   //Public accessors and mutators
   public void setAttribute( String attributeName, Object attributeValue ) {
      testEnvironment.getServletRunner().setServletContextAttribute( attributeName, attributeValue );
   }
   
   //Properties
   public ServletContext getServletContext() { return testEnvironment.getServletRunner().getServletContext(); }
}
