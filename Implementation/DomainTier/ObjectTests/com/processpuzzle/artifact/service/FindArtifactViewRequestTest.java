package com.processpuzzle.artifact.service;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import hu.itkodex.litest.template.ServiceMessageTest;

public class FindArtifactViewRequestTest extends ServiceMessageTest<FindArtifactRequest, FindArtifactRequestTestFixture>{
   
   public FindArtifactViewRequestTest( String fixtureContainerConfigurationPath ) {
      super( fixtureContainerConfigurationPath );
   }

   @Override public void unmarshall_TransformsXmlToObject() {
      assertThat( messageAsObject.getId(), equalTo( 1 ));
   }

   @Override public void marshall_TransformsObjectToXml() {
   }

}
