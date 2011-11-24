/*
Name: 
    - QuantityEnumeration

Description: 
    -  

Requires:
    - 
    
Provides:
    - 

Part of: ProcessPuzzle Framework, Domain and Business Model Ready Architecture. Provides content, workflow and social networking functionality. 
http://www.processpuzzle.com

ProcessPuzzle - Content and Workflow Management Integration Business Platform

Author(s): 
    - Zsolt Zsuffa

Copyright: (C) 2011 This program is free software: you can redistribute it and/or modify it under the terms of the 
GNU General Public License as published by the Free Software Foundation, either version 3 of the License, 
or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/


package com.processpuzzle.fundamental_types.possiblevalue.domain;

import java.text.FieldPosition;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

import com.processpuzzle.application.configuration.domain.MeasurementContext;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.fundamental_types.quantity.domain.Quantity;
import com.processpuzzle.fundamental_types.quantity.domain.Unit;
import com.processpuzzle.internalization.domain.ProcessPuzzleLocale;
import com.processpuzzle.user_session.domain.UserRequestManager;

public class QuantityEnumeration extends PossibleValueDefinition implements StringParseable {

   private Set<Quantity> values = new TreeSet<Quantity>();
   private static String ASTEXT_START_DELIMITER = "( ";
   private static String ASTEXT_VALUE_SEPARATOR = ", ";
   private static String ASTEXT_END_DELIMITER = " )";

   public static final String POSSIBLEVALUE_SEPARATOR = ";";
   public static final String AMOUNT_UNIT_SEPARATOR = " ";

   public QuantityEnumeration() {
   }
   
   public QuantityEnumeration(String possibleValuesDef) {
      this();
      parseFromString( this, possibleValuesDef );
   }

   
   @Override
   public boolean checkValue(Object value) {
      if (! (value instanceof Quantity) ) return false;
      for (Iterator<Quantity> iter = values.iterator(); iter.hasNext();) {
         Quantity quantity = (Quantity) iter.next();
         if (quantity.compareTo((Quantity)value) == 0) {
            return true;
         }
      }
      return false;
   }

   @Override
   public void addPossibleValue(Object value) {
      if (value instanceof Quantity) {
         values.add((Quantity)value);
      }
    }

   public Set<Quantity> getValues() {
      return values;
   }
   
   @Override
   public String asText(ProcessPuzzleLocale locale) {
      StringBuffer pattern = new StringBuffer(ASTEXT_START_DELIMITER);
      List<String> args = new ArrayList<String>();
      int i=0;
      for (Object quantity : values) {
         if (i>0) pattern.append(ASTEXT_VALUE_SEPARATOR);
         pattern.append("{"+ i + "}");
         args.add(((Quantity)quantity).asText(locale));
         i++;
      }
      pattern.append(ASTEXT_END_DELIMITER);
      MessageFormat format = new MessageFormat(pattern.toString());
      StringBuffer result = new StringBuffer();
      format.format(args.toArray(), result, new FieldPosition(0));
      return result.toString();
   }

   public static void setASTEXT_END_DELIMITER(String tostring_end_delimiter) {
      ASTEXT_END_DELIMITER = tostring_end_delimiter;
   }

   public static void setASTEXT_START_DELIMITER(String tostring_start_delimiter) {
      ASTEXT_START_DELIMITER = tostring_start_delimiter;
   }

   public static void setASTEXT_VALUE_SEPARATOR(String tostring_value_separator) {
      ASTEXT_VALUE_SEPARATOR = tostring_value_separator;
   }
   
   public String stringValue() {
      // 20 cm; 30 cm; 40 cm
      StringBuffer strBuff = new StringBuffer();
      int i = 0;
      for(Iterator<Quantity> it = values.iterator(); it.hasNext();){
         Quantity value = (Quantity)it.next();
         if (i > 0) {
            strBuff.append(POSSIBLEVALUE_SEPARATOR);
         }
         
         strBuff.append(value.getAmount()).append(AMOUNT_UNIT_SEPARATOR)
                .append(value.getUnit().getSymbol());
         
         i++;
      }
      return strBuff.toString();
   }
   
   public void parseFromString(Object enumeration, String possibleValuesDef) {
      if ( (enumeration instanceof QuantityEnumeration) && (possibleValuesDef != null) ) {
         StringTokenizer tokenizer = new StringTokenizer(possibleValuesDef, QuantityEnumeration.POSSIBLEVALUE_SEPARATOR);
         ProcessPuzzleContext applicationContext = UserRequestManager.getInstance().getApplicationContext();
         MeasurementContext measurementContext = applicationContext.getMeasurementContext();
         while(tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().trim();
            int idxOfSeparator = token.indexOf(QuantityEnumeration.AMOUNT_UNIT_SEPARATOR);
            String amountStr = token.substring(0, idxOfSeparator);
            String unitSymbolStr = token.substring(idxOfSeparator);
            Double amount = Double.parseDouble(amountStr.trim());
            Unit unit = measurementContext.findUnitBySymbol(unitSymbolStr.trim());
            Quantity quantity = new Quantity(amount, unit);
            
            ((QuantityEnumeration)enumeration).addPossibleValue(quantity);
         }
      }
     
   }


}
