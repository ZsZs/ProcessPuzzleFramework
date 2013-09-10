package com.processpuzzle.litest.template;


import org.jdom.Document;
import org.junit.Test;

import com.processpuzzle.fundamental_types.domain.ServiceMessage;
import com.processpuzzle.litest.testcase.GenericTestSuite;

public abstract class ServiceMessageTest<S extends ServiceMessage, F extends ServiceMessageTestFixture<S>> extends GenericTestSuite<S, F>{
   protected Document messageAsXml;
   protected S messageAsObject;
   
   protected ServiceMessageTest( String fixtureContainerConfigurationPath ) {
      super( fixtureContainerConfigurationPath );
   }

   @Override public void beforeEachTest() {
      super.beforeEachTest();
      messageAsObject = sut;
      messageAsXml = fixture.getMessageAsXml();
   }
   
   //Test methods
   @Test public abstract void unmarshall_TransformsXmlToObject();
   @Test public abstract void marshall_TransformsObjectToXml() throws Exception;   

   //Protected, private helper methods
}
