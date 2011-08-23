package com.processpuzzle.fundamental_types.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class ParameterValue {
   public static final String PARAMETER_VALUE_DELIMITERS = ":[]=//";
   private ParameterDefinition definition;
   private Object value;
   
   public ParameterValue( ParameterDefinition definition, Object value ) {
      this.definition = definition;
      this.value = value;
   }
   
   public static ParameterValue parse( String definitionText ) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
      ParameterDefinition definition = ParameterDefinition.parse( definitionText );

      Pattern valuePattern = Pattern.compile( "=([^/]+)/{0,2}" );
      Matcher valueFinder = valuePattern.matcher( definitionText );
      String valueString = null;
      if( valueFinder.find() ) {
         int valueStringLength = valueFinder.group().contains( "//" ) ? valueFinder.group().length() -2 : valueFinder.group().length();
         valueString = StringUtils.strip( valueFinder.group().substring( 1, valueStringLength ));
      }
      
      Object valueObject = null;
      if( definition.getType().equals( String.class )) valueObject = new String( valueString );
      else if( definition.getType().equals( Integer.class )) valueObject = new Integer( valueString );
      else if( definition.getType().equals( Long.class )) valueObject = new Long( valueString );
      else if( definition.getType().equals( Double.class )) valueObject = new Double( valueString );
      
      return new ParameterValue( definition, valueObject );
   }

   public ParameterDefinition getDefinition() {
      return definition;
   }

   public Object getValue() {
      return value;
   }
}
