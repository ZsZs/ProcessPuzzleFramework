package com.processpuzzle.fundamental_types.possiblevalue.domain;

import java.text.FieldPosition;
import java.text.MessageFormat;

import com.processpuzzle.fundamental_types.quantity.domain.Quantity;
import com.processpuzzle.internalization.domain.ProcessPuzzleLocale;

public class QuantityRange extends PossibleValueDefinition { 
   private Quantity minValue, maxValue;
   private static String ASTEXT_START_DELIMITER = "( ";
   private static String ASTEXT_RANGE_SIGN = " - ";
   private static String ASTEXT_END_DELIMITER = " )";
   
   public QuantityRange(Quantity minValue, Quantity maxValue) {
      if ( maxValue.compareTo(minValue) < 0 ) {
         throw new InvalidValueRangeException(minValue, maxValue);
      }
      this.minValue = minValue;
      this.maxValue = maxValue;
   }

   @Override
   public boolean checkValue(Object value) {
      if (! (value instanceof Quantity) ) return false;
      if( (((Quantity)value).compareTo(minValue) >= 0) && (((Quantity)value).compareTo(maxValue) <= 0)) {
         return true;
      } else
         return false;
   }
   
   @Override
   public void addPossibleValue(Object value) {
      // do nothing, ValueRange is immutable
   }
   
   @Override
   public String asText(ProcessPuzzleLocale locale) {
      StringBuffer pattern = new StringBuffer(ASTEXT_START_DELIMITER);
      pattern.append("{0}").append(ASTEXT_RANGE_SIGN).append("{1}");
      pattern.append(ASTEXT_END_DELIMITER);
      MessageFormat format = new MessageFormat(pattern.toString());
      
      StringBuffer result = new StringBuffer();
      String minValueStr = minValue.asText(locale);
      String maxValueStr = maxValue.asText(locale);
      String[] args = new String[]{minValueStr, maxValueStr};
      format.format(args, result, new FieldPosition(0));
      return result.toString();
   }

   public Quantity getMaxValue() {
      return maxValue;
   }

   public Quantity getMinValue() {
      return minValue;
   }

}
