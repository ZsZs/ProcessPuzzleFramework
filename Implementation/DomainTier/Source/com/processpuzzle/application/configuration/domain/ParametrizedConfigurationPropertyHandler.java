/*
Name: 
    - ParapeterizedConfigurationPropertyHandler 

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

package com.processpuzzle.application.configuration.domain;

import java.text.MessageFormat;
import java.util.List;

import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.lang.StringUtils;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

public class ParametrizedConfigurationPropertyHandler {
   public static final String[] ANY_SELECTOR_SEGMENT_DELIMITER = { PropertyContext.SELECTOR_DELIMITER, "/", PropertyContext.ATTRIBUTE_BEGIN, PropertyContext.ATTRIBUTE_END };
   public static final char[] COMPARISON_OPERATORS = {'=', '<', '>'};
   private HierarchicalConfiguration configuration;
   private String parametrizedSelector;
   private String conditionSegment;
   private String selectorBeforeCondition;
   private String selectorAfterCondition;
   private String conditionPropery;
   private String conditionValue;
   
   //Constructors
   public ParametrizedConfigurationPropertyHandler( HierarchicalConfiguration configuration ) {
      this.configuration = configuration;
   }

   //Public accessors and mutators
   public HierarchicalConfiguration configurationAt( String parametrizedSelector, Object[] parameters ) {
      determineParametrizedSelector( parametrizedSelector, parameters );
      determineSelectorSegments();
      String finalSelector = determineFinalSelector();
      HierarchicalConfiguration subConfiguration = configuration.configurationAt( finalSelector );
      return subConfiguration;
   }

   public String getProperty( String selector, Object[] parameters ) {
      determineParametrizedSelector( selector, parameters );
      determineSelectorSegments();
      String finalSelector = determineFinalSelector();
      String property = configuration.getString( finalSelector );
      return property;
   }

   public List<String> getPropertyList(String selector, Object[] parameters) {
      determineParametrizedSelector( selector, parameters );
      determineSelectorSegments();
      String finalSelector = determineFinalSelector();
      
      List<String> searchedItems = Lists.newArrayList( Lists.transform( configuration.getList( finalSelector ), new Function<Object, String>(){
         @Override public String apply( Object configurationElement ) {
             if( configurationElement != null ) return configurationElement.toString();
             else return "null";
         }}));
      return searchedItems;
   }

   private String determineFinalSelector() {
      String currentSelector = selectorBeforeCondition + selectorAfterCondition;
      
      List<String> parentItems = Lists.transform( configuration.getList( currentSelector ), new Function<Object, String>(){
         @Override public String apply( Object configurationElement ) {
            if( configurationElement != null ) return configurationElement.toString();
            else return "null";
      }});
      
      Integer listIndex = determineParentNodeIdex( parentItems );
      
      String finalSelector = selectorBeforeCondition + PropertyContext.PROPERTY_ARRAY_BEGIN + listIndex + PropertyContext.PROPERTY_ARRAY_END + selectorAfterCondition;
      return finalSelector;
   }

   private Integer determineParentNodeIdex( List<String> parentItems ) {
      Integer listIndex = 0;
      for( ; listIndex < parentItems.size(); listIndex++ ){
         String parentSelector = selectorBeforeCondition + PropertyContext.PROPERTY_ARRAY_BEGIN + listIndex + PropertyContext.PROPERTY_ARRAY_END;
         HierarchicalConfiguration subConfiguration = null;
         
         try{
            subConfiguration = configuration.configurationAt( parentSelector );
         }catch( IllegalArgumentException e ){
            throw new InvalidPropertySelectorException( parametrizedSelector, e );
         }
      
         String currentPropertyValue = subConfiguration.getString( conditionPropery ); 
         if( currentPropertyValue != null && currentPropertyValue.equals( conditionValue )) 
            break;
      }
      return listIndex;
   }

   private void determineParametrizedSelector( String selector, Object[] parameters ) {
      parametrizedSelector = MessageFormat.format( selector, parameters );
   }

   private void determineSelectorSegments() {
      int positionOfComparisonOperator = StringUtils.indexOfAny( parametrizedSelector , COMPARISON_OPERATORS );
      int positionOfConditionBegin = StringUtils.lastIndexOfAny( StringUtils.substring( parametrizedSelector, 0, positionOfComparisonOperator ), ANY_SELECTOR_SEGMENT_DELIMITER );
      int positionOfConditionEnd = StringUtils.indexOfAny( StringUtils.substring( parametrizedSelector, positionOfComparisonOperator +1 ), ANY_SELECTOR_SEGMENT_DELIMITER ) + positionOfComparisonOperator +1;

      conditionSegment = StringUtils.substring( parametrizedSelector, positionOfConditionBegin +1, positionOfConditionEnd );
      selectorBeforeCondition = StringUtils.substring( parametrizedSelector, 0, positionOfConditionBegin );
      selectorAfterCondition = StringUtils.substring( parametrizedSelector, positionOfConditionEnd +1 );
      conditionPropery = StringUtils.substring( conditionSegment, 0, positionOfComparisonOperator - positionOfConditionBegin -1);
      if( conditionPropery.contains( PropertyContext.ATTRIBUTE_SIGNER ) )
         conditionPropery = PropertyContext.ATTRIBUTE_BEGIN + conditionPropery + PropertyContext.ATTRIBUTE_END;
      
      conditionValue = StringUtils.substring( conditionSegment, positionOfComparisonOperator - positionOfConditionBegin );
      if( conditionValue.startsWith( "'" ) || conditionValue.startsWith( "\"" ) );
         conditionValue = StringUtils.substring( conditionValue, 1, conditionValue.length() -1 );
   }

}
