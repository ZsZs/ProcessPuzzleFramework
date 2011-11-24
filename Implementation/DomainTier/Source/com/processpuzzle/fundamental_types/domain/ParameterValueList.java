/*
Name: 
    - ParameterValueList

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
