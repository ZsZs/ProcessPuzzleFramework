/*
Name: 
    - DefaultQueryContext

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

package com.processpuzzle.persistence.query.domain;


import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.processpuzzle.commons.persistence.query.QueryContext;
import com.processpuzzle.fundamental_types.domain.HashCodeUtil;
import com.processpuzzle.fundamental_types.domain.OpAssertion;

public class DefaultQueryContext implements QueryContext {
   private Map<String, Object> attributeValues = new HashMap<String, Object>();

//Constructors
   public DefaultQueryContext() {}
   
//Public accessors
   public boolean equals( Object objectToCheck ) {
      if( !( objectToCheck instanceof DefaultQueryContext )) return false;
      DefaultQueryContext anotherContext = (DefaultQueryContext) objectToCheck;
      boolean retval = attributeValues.equals( anotherContext.attributeValues ); 
      return retval;
   }
   
   public DefaultQueryContext clone() {
      DefaultQueryContext clone = null;
      try {
         clone = (DefaultQueryContext) super.clone();
      } catch (CloneNotSupportedException e) {
         throw new Error("Assertion error."); //Should not happen
      }
      clone.attributeValues = new HashMap<String, Object>();
      clone.attributeValues.putAll( this.attributeValues );
      return clone;
   }
   
   public int hashCode() {
      int result = HashCodeUtil.SEED;
      result = HashCodeUtil.hash( result, attributeValues );
      return result;
   }
   
   public static String findVariable(String subjectText ) {
      int variableStart = subjectText.indexOf( QueryVariable.VARIABLE_INDICATOR_BEGIN );
      int variableEnd = subjectText.indexOf( QueryVariable.VARIABLE_INDICATOR_END );
      if( variableStart >= 0 && variableEnd > 0 ) 
         return subjectText.substring( variableStart, variableEnd + 1);
      else return null;
   }
   
   public static String stripVariableName(String variable ) {
      OpAssertion.ppAssert( findVariable(variable) != null, "It expects a query variable string.");
      String variableName = variable.substring(1, variable.length() - 1 );
      return variableName.trim();
   }

//Collection accessors
   public Set<Entry<String, Object>> attributeValuesEntrySet() {
      return attributeValues.entrySet();
   }
   public Iterator<?> attributeValuesIterator() {
      return attributeValues.entrySet().iterator();
   }
   
   public Object getAttributeValue( String attributeName ) {
      return attributeValues.get( attributeName );
   }
   
//Public mutators
   public void addTextValueFor( String attributeName, String value ) {
      attributeValues.put( attributeName, value);
   }
   
   public void addIntegerValueFor( String attributeName, Integer value ) {
      attributeValues.put( attributeName, value );
   }

   public void addDateValueFor( String attributeName, Date value ) {
      attributeValues.put( attributeName, value );
   }

//Private, protected helper methods   
}
