package com.processpuzzle.persistence.query.domain;

import hu.itkodex.commons.persistence.query.ConditionElementType;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.processpuzzle.fundamental_types.domain.OpAssertion;

public class DateAttributeCondition extends DefaultAttributeCondition {

   private String sql_date_pattern = "YYYY-MM-DD HH:MI:SS";
/*   public DateAttributeCondition( String attributeName, QueryVariable variable, ComparisonOperators operator ) {
      super(attributeName, variable, operator);
   }*/

   public DateAttributeCondition( String attributeName, Date value, ComparisonOperators operator ) {
      super(attributeName, value, operator);
   }

   public DateAttributeCondition( String attributeName, ComparisonOperators operator ) {
      super( attributeName, null, operator );

      OpAssertion.ppAssert(((operator == ComparisonOperators.IS_NULL) || (operator == ComparisonOperators.IS_NOT_NULL )), 
      "AttributeCondition without attribute value can use only: 'is null, is not null' operators.");      
   }

   @Override
   public String toString() {
      StringBuffer textualRepresentation = new StringBuffer();
      textualRepresentation.append( attributeName ).append( " " ).append( operator.getHqlVariant() ) ;
      if( value != null ) {
         Calendar calendar = new GregorianCalendar();
         calendar.setTime( (Date)value );
         textualRepresentation.append( " to_date('" )
            .append( calendar.get(Calendar.YEAR) ).append("-")
            .append( calendar.get(Calendar.MONTH) ).append("-")
            .append( calendar.get(Calendar.DAY_OF_WEEK) ).append(" ")
            .append( calendar.get(Calendar.HOUR_OF_DAY)).append(":")
            .append( calendar.get(Calendar.MINUTE) ).append(":")
            .append( calendar.get(Calendar.SECOND) )
        .append("', '")
           .append(sql_date_pattern)
        .append("')");
      }
      return textualRepresentation.toString();
   }

   @Override
   public ConditionElementType getType() {
      // TODO Auto-generated method stub
      return null;
   }
}
