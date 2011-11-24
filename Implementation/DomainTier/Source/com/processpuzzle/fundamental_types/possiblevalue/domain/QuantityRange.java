/*
Name: 
    - QuantityRange

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
