/*
Name: 
    - DefaultQueryTransformer

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


import com.processpuzzle.commons.persistence.query.AttributeFilter;
import com.processpuzzle.commons.persistence.query.Query;
import com.processpuzzle.commons.persistence.query.QueryCondition;
import com.processpuzzle.commons.persistence.query.QueryContext;
import com.processpuzzle.commons.persistence.query.QueryOrder;
import com.processpuzzle.commons.persistence.query.QueryTransformer;
import com.processpuzzle.persistence.query.domain.OrderTransformer;

public abstract class DefaultQueryTransformer implements QueryTransformer {
   protected ConditionTransformer conditionTransformer = null;
   protected FilterTransformer filterTransformer = null;
   protected OrderTransformer orderTransformer = null;
   protected QueryContextTransformer contextTransformer = null;
   protected Query query = null;
   protected String filterFragment = "";
   protected String conditionFragment = "";
   protected String orderFragment = "";
   
   public String createStatement( Query query ) {
      this.query = query.clone();
      String targetAlias = defineAliasFor( query.getTargetClass() ); 
//      replaceVariablesWithConcreteValues();
      filterFragment = createFilterFragment( targetAlias, query.getAttributeFilter() );
      conditionFragment = createConditionFragment( targetAlias, query.getQueryCondition(), query.getQueryContext() );
      orderFragment = createOrderFragment( targetAlias, query.getQueryOrder() );
      return buildStatementFromFragments();
   }
   
   protected String createFilterFragment( String targetAlias, AttributeFilter filter ){
      String fragment = filterTransformer.cretateFilterFragment( targetAlias, filter );
      return fragment;
   }
   protected String createConditionFragment( String targetAlias, QueryCondition condition, QueryContext context ){
      String fragment = conditionTransformer.createConditionFragment( targetAlias, condition, context );
      return fragment;
   }
   
   protected String createOrderFragment( String targetAlias, QueryOrder order ) {
      String fragment = orderTransformer.createOrderFragment( targetAlias, order );
      return fragment;
   }
   
   protected String defineAliasFor( Class<?> targetClass ) {
      String shortClassName = targetClass.getSimpleName();
      String firstChar = shortClassName.substring(0, 1).toLowerCase();
      return firstChar + shortClassName.substring(1);
   }

   protected abstract String buildStatementFromFragments();
}
