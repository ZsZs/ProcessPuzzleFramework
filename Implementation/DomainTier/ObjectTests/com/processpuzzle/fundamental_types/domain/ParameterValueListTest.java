package com.processpuzzle.fundamental_types.domain;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class ParameterValueListTest {
   public static final String PARAMETER_VALUES_TEXT = "greeting : String[1] = Hello; count : Integer[0..1] = 25";
   
   @Test
   public void parse_InstantiatesParameterValues() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
      ParameterValueList valueList = ParameterValueList.parse( PARAMETER_VALUES_TEXT );
      
      assertThat( valueList.size(), equalTo( 2 ));
      assertThat( (String)valueList.get( 0 ).getValue(), equalTo( "Hello" ));
      assertThat( (Integer)valueList.get( 1 ).getValue(), equalTo( new Integer( 25 )));
   }
}
