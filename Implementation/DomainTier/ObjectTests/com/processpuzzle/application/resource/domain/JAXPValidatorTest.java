package com.processpuzzle.application.resource.domain;

import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;

import com.processpuzzle.application.resource.domain.JAXPValidator;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class JAXPValidatorTest {
   
   @Ignore
   @Test
   public void testValidateForSuccess() {
      JAXPValidator xmlValidator = new JAXPValidator( DomainTierTestConfiguration.TEST_XML_PATH, DomainTierTestConfiguration.TEST_SCHEMA_PATH );
      xmlValidator.validate();
      
      assertTrue( xmlValidator.isValid() );
   }
}
