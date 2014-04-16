package com.processpuzzle.application.control.control;

import com.processpuzzle.litest.template.FrontCommandTestEnvironment;
import com.processpuzzle.litest.template.FrontCommandTestFixture;

public class BuildXmlCommandFixture extends FrontCommandTestFixture<BuildXmlCommand> {

   public BuildXmlCommandFixture( FrontCommandTestEnvironment<BuildXmlCommand, ?> testEnvironment ) {
      super( testEnvironment );
   }

   @Override protected void configureBeforeSutInstantiation() {
   }

   @Override protected BuildXmlCommand instantiateSUT() {
      return null;
   }

   @Override protected void releaseResources() {
   }

   @Override protected void configureAfterSutInstantiation() {
   }

}
