package com.processpuzzle.fundamental_types.domain;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class ParameterDefinitionTest {
   public static final String PARAMETER_DEFINITION_TEXT = "parameterName : String[1..n] //Parameter description.";
   
   @SuppressWarnings("unchecked")
   @Test
   public void parse_DeterminesAllProperties() throws ClassNotFoundException {
      ParameterDefinition parameterDefinition = ParameterDefinition.parse( PARAMETER_DEFINITION_TEXT );
      
      assertThat( parameterDefinition.getName(), equalTo( "parameterName" ));
      assertThat( (Class<String>)parameterDefinition.getType(), equalTo( String.class ));
      assertThat( parameterDefinition.getMultiplicity(), equalTo( "1..n" ));
      assertThat( parameterDefinition.getDescription(), equalTo( "Parameter description." ));
   }
}
