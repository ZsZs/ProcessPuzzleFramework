package com.processpuzzle.fundamental_types.domain;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class ParameterValueTest {
   private static final String PARAMETER_DEFINITION_TEXT = "parameterName : String[1..n] = blabla //Parameter description.";

   @Test
   public void parse_DeterminesAllProperties() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
      ParameterValue parameterValue = ParameterValue.parse( PARAMETER_DEFINITION_TEXT );
      
      assertThat( parameterValue.getDefinition(), equalTo( ParameterDefinition.parse( ParameterDefinitionTest.PARAMETER_DEFINITION_TEXT )));
      assertThat( (String)parameterValue.getValue(), equalTo( "blabla" ));
   }
}
