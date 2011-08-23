package hu.itkodex.litest.template;

import javax.servlet.http.HttpServlet;

public class ServletTestEnvironment<S extends HttpServlet, F extends ServletTestFixture<S>> extends GenericTestEnvironment<S> {

   @Override protected void configureAfterSutInstantiation() {
   }

   @Override protected void configureBeforeSutInstantiation() {
   }

   @Override protected void defineComponentTypes() {
   }

   @Override protected void releaseResources() {
   }
}
