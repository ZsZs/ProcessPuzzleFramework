package hu.itkodex.litest.template;

import javax.servlet.http.HttpServlet;

public abstract class ServletTestFixture<S extends HttpServlet> extends GenericTemplatedFixture<S>{

   protected ServletTestFixture( ServletTestEnvironment<S, ?> testEnvironment ) {
      super( testEnvironment );
   }

}
