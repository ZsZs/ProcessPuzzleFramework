package com.processpuzzle.artifact.service;

import com.processpuzzle.litest.template.ServiceMessageTestFixture;

public class FindArtifactRequestTestFixture extends ServiceMessageTestFixture<FindArtifactRequest>{
   private static final String MESSAGE_FILE = "com/processpuzzle/artifact/service/SOAPRequest.xml";
   private static final String MESSAGE_SCHEMA = "com/processpuzzle/artifact/service/SOAPRequest.xsd";

   public FindArtifactRequestTestFixture() {
      super( MESSAGE_FILE, MESSAGE_SCHEMA );
   }

   @Override protected void configureAfterSutInstantiation() {
   }

   @Override protected void releaseResources() {
   }

   @Override protected FindArtifactRequest setUpMessageObject() {
      return null;
   }

}
