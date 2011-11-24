/*
Name: 
    - ParameterValue

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
