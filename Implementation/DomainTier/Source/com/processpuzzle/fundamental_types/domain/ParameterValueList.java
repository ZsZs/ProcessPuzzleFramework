package com.processpuzzle.fundamental_types.domain;
import java.util.ArrayList;

import org.apache.commons.lang.text.StrMatcher;
import org.apache.commons.lang.text.StrTokenizer;

public class ParameterValueList extends ArrayList<ParameterValue> {
   public static final String LIST_DELIMITERS = ";";
   private static final long serialVersionUID = 1341134724325891672L;

   public ParameterValueList() {
      super();
   }

   public Class<?>[] getParameterTypes() {
      Class<?>[] parameterTypes = new Class<?>[this.size()];
      int index = 0;
      for( ParameterValue parameterValue : this ) {
         parameterTypes[index] = parameterValue.getDefinition().getType();
         index++;
      }
      return parameterTypes;
   }
   
   public static ParameterValueList parse( String parameters ) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
      ParameterValueList valueList = new ParameterValueList();
      
      StrMatcher delimiters = StrMatcher.charSetMatcher( LIST_DELIMITERS.toCharArray() );
      StrTokenizer tokenizer = new StrTokenizer( parameters, delimiters );
      while( tokenizer.hasNext() ) {
         ParameterValue parameterValue = ParameterValue.parse( tokenizer.nextToken() );
         valueList.add( parameterValue );
      }
      return valueList;
   }

   @Override
   public String toString() {
      return null;
   }
   
   public Object[] getValues() {
      Object[] values = new Object[this.size()];
      int index = 0;
      for( ParameterValue parameterValue : this ) {
         values[index] = parameterValue.getValue();
         index++;
      }
      return values;
   }
}
