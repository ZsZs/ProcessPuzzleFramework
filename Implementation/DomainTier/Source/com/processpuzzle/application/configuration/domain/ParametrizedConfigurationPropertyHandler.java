package com.processpuzzle.application.configuration.domain;

import java.text.MessageFormat;
import java.util.List;

import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.lang.StringUtils;

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
      
      @SuppressWarnings( "unchecked" )
      List<String> searchedItems = Lists.newArrayList( configuration.getList( finalSelector ));
      return searchedItems;
   }

   private String determineFinalSelector() {
      String currentSelector = selectorBeforeCondition + selectorAfterCondition;
      
      @SuppressWarnings( "unchecked" )
      List<String> parentItems = configuration.getList( currentSelector );
      
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
