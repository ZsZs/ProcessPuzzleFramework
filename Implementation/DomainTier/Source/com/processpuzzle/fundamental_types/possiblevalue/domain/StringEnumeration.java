package com.processpuzzle.fundamental_types.possiblevalue.domain;

import java.text.FieldPosition;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import com.processpuzzle.internalization.domain.ProcessPuzzleLocale;

public class StringEnumeration extends PossibleValueDefinition implements StringParseable {

   List<String> values = new ArrayList<String>();
   private static String ASTEXT_START_DELIMITER = "( ";
   private static String ASTEXT_VALUE_SEPARATOR = ", ";
   private static String ASTEXT_END_DELIMITER = " )";
   public static final String POSSIBLEVALUE_SEPARATOR = ";";
   
   public StringEnumeration() {
   }
   
   public StringEnumeration(String possibleValuesDef) {
      this();
      parseFromString( this, possibleValuesDef );
   }

   @Override
   public void addPossibleValue( Object value ) {
      if (value instanceof String) {
         values.add((String)value);
      }
   }

   @Override
   public String asText( ProcessPuzzleLocale locale ) {
      StringBuffer pattern = new StringBuffer(ASTEXT_START_DELIMITER);
      List<String> args = new ArrayList<String>();
      int i=0;
      for (String value : values) {
         if (i>0) pattern.append(ASTEXT_VALUE_SEPARATOR);
         pattern.append("{"+ i + "}");
         args.add(value);
         i++;
      }
      pattern.append(ASTEXT_END_DELIMITER);
      MessageFormat format = new MessageFormat(pattern.toString());
      StringBuffer result = new StringBuffer();
      format.format(args.toArray(), result, new FieldPosition(0));
      return result.toString();
   }

   @Override
   public boolean checkValue( Object value ) {
      if (! (value instanceof String) ) return false;
      return values.contains( value );
   }

   public void parseFromString( Object enumeration, String possibleValuesDef ) {
      if ( (enumeration instanceof StringEnumeration) && (possibleValuesDef != null) ) {
         StringTokenizer tokenizer = new StringTokenizer(possibleValuesDef, POSSIBLEVALUE_SEPARATOR);
         while(tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().trim();
            
            ((StringEnumeration)enumeration).addPossibleValue(token);
         }
      }
     
   }

   public String stringValue() {
      StringBuffer strBuff = new StringBuffer();
      int i = 0;
      for(Iterator<String> it = values.iterator(); it.hasNext();){
         String value = (String)it.next();
         if (i > 0) {
            strBuff.append(POSSIBLEVALUE_SEPARATOR);
         }
         
         strBuff.append(value);
         
         i++;
      }
      return strBuff.toString();
   }

   public List<String> getValues() {
      return values;
   }

}
