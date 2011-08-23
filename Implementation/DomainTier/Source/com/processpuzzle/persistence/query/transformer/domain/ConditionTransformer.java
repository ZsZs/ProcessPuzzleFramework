package com.processpuzzle.persistence.query.transformer.domain;

import hu.itkodex.commons.persistence.query.BooleanOperator;
import hu.itkodex.commons.persistence.query.ConditionElement;
import hu.itkodex.commons.persistence.query.Operator;
import hu.itkodex.commons.persistence.query.QueryCondition;
import hu.itkodex.commons.persistence.query.QueryContext;

import java.util.Stack;

import com.processpuzzle.persistence.query.domain.DefaultAttributeCondition;
import com.processpuzzle.persistence.query.domain.DefaultQueryContext;

public abstract class ConditionTransformer {
   protected Stack<String> operandRegister = new Stack<String>();

   String createConditionFragment( String targetAlias, QueryCondition<?> condition, QueryContext context ) {
      if( condition.elementsCount() > 0 ){
         for( ConditionElement conditionElement : condition.getElements() ){
            if( conditionElement instanceof DefaultAttributeCondition ){
               DefaultAttributeCondition attributeCondition = ((DefaultAttributeCondition) conditionElement);
               String conditionText = attributeCondition.toString();
               conditionText = replaceVariablesWithValues( conditionText, context );
               conditionText = replace( conditionText, attributeCondition.getAttributeName(), targetAlias + "." + attributeCondition.getAttributeName() );
               operandRegister.push( conditionText );
            }else if( conditionElement instanceof BooleanOperator ){
               String operand_1 = operandRegister.pop();
               String operand_2 = operandRegister.pop();
               String operator = ((Operator) conditionElement).toString();
               String result = converToInfixNotation( operand_1, operand_2, operator );
               operandRegister.push( result );
            }
         }
         return operandRegister.pop();
      }else
         return "";
   }

   protected String converToInfixNotation( String operand_1, String operand_2, String operator ) {
      return encloseInParentheses( operand_1 ) + " " + operator + " " + encloseInParentheses( operand_2 );
   }

   protected String encloseInParentheses( String operand ) {
      String enhancedOperand = "(" + operand + ")";
      return enhancedOperand;
   }

   // Private helper methods
   private String replaceVariablesWithValues( String condition, QueryContext context ) {
      String variableInCondition = DefaultQueryContext.findVariable( condition );
      if( variableInCondition != null ){
         String variableName = DefaultQueryContext.stripVariableName( variableInCondition );
         Object variableValue = context.getAttributeValue( variableName );
         if( variableValue == null )
            throw new MissingQueryConditionVariable( variableName );
         return replace( condition, variableInCondition, variableValue.toString() );
      }else
         return condition;

   }

   private String replace( String str, String pattern, String replace ) {
      int s = 0;
      int e = 0;
      StringBuffer result = new StringBuffer();

      while( (e = str.indexOf( pattern, s )) >= 0 ){
         result.append( str.substring( s, e ) );
         result.append( replace );
         s = e + pattern.length();
      }
      result.append( str.substring( s ) );
      return result.toString();
   }
}
