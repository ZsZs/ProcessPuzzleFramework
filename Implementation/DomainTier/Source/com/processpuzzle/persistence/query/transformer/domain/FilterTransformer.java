/*
Name: 
    - FilterTransformer

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

package com.processpuzzle.persistence.query.transformer.domain;

import hu.itkodex.commons.persistence.query.AggregateFunction;
import hu.itkodex.commons.persistence.query.AttributeFilter;
import hu.itkodex.commons.persistence.query.AttributeFilterExpression;
import hu.itkodex.commons.persistence.query.AttributeSelector;
import hu.itkodex.commons.persistence.query.QueryTransformer;

import java.util.Iterator;

public abstract class FilterTransformer {
   protected String fragment = "";
   
   String cretateFilterFragment( String targetAlias, AttributeFilter filter){
      for( Iterator<?> iter = filter.attributesIterator(); iter.hasNext();) {
         AttributeFilterExpression filterExpression = (AttributeFilterExpression) iter.next();
         String attributeName = filterExpression.getAttributeName();
         if( filterExpression instanceof AttributeSelector ) {
            if(fragment == "" ) fragment = targetAlias + "." + attributeName;
            else fragment += ", " + targetAlias + "." + attributeName;            
         } else if( filterExpression instanceof AggregateFunction ) {
            AggregateFunction function = (AggregateFunction) filterExpression;
            String functionName = function.getNameFor( QueryTransformer.SupportedTrasformers.HQL );

            if(fragment == "" ) fragment = functionName + "( " + targetAlias + "." + attributeName + " )";
            else fragment += ", " + functionName + "( " + targetAlias + "." + attributeName + " )";            
         }
      }
      
      return fragment;
   }
}
