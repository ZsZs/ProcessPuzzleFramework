/*
Name: 
    - DefaultQuery

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


import java.util.StringTokenizer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.processpuzzle.commons.persistence.Entity;
import com.processpuzzle.commons.persistence.query.AttributeFilter;
import com.processpuzzle.commons.persistence.query.AttributeSelector;
import com.processpuzzle.commons.persistence.query.Query;
import com.processpuzzle.commons.persistence.query.QueryCondition;
import com.processpuzzle.commons.persistence.query.QueryContext;
import com.processpuzzle.commons.persistence.query.QueryOrder;
import com.processpuzzle.fundamental_types.domain.HashCodeUtil;
import com.processpuzzle.persistence.query.transformer.domain.HQLQueryTransformer;
import com.processpuzzle.persistence.query.transformer.domain.QueryTransformerFactory;

@XmlRootElement(name = "query")
@XmlAccessorType( XmlAccessType.NONE )
public class DefaultQuery implements Query {
   private Integer id;
   private @XmlAttribute String name;
   private @XmlElement(name = "description", namespace="http://www.processpuzzle.com/GlobalElements" ) String description;
   private @XmlElement(name = "statement", namespace="http://www.processpuzzle.com/ArtifactTypeDefinition" ) String predefinedStatement;
   protected static Class<? extends Entity> targetClass = null;
   protected DefaultQueryCondition condition = new DefaultQueryCondition();
   protected DefaultQueryContext context = new DefaultQueryContext();
   protected AttributeFilter filter = new DefaultAttributeFilter();
   protected QueryOrder order = new DefaultQueryOrder();
   protected Integer maxResults;
   protected Integer firstResult;

   // Constructors
   DefaultQuery( Class<? extends Entity> targetClass, DefaultQueryCondition condition, DefaultQueryContext context, DefaultAttributeFilter filter ) {
      DefaultQuery.targetClass = targetClass;
      this.condition = condition;
      this.context = context;
      this.filter = filter;
   }

   public DefaultQuery( Class<? extends Entity> targetClass, String name, String description ) {
      super();
      DefaultQuery.targetClass = targetClass;
      this.name = name;
      this.description = description;
   }

   public DefaultQuery( Class<? extends Entity> targetClass ) {
      this( targetClass, null, null );
   }

   protected DefaultQuery() {
      super();
   }

   @SuppressWarnings( "static-access" )
   public DefaultQuery clone() {
      DefaultQuery clone = null;
      try{
         clone = (DefaultQuery) super.clone();
      }catch( CloneNotSupportedException e ){
         throw new Error( "Assertion failure" );
      }
      clone.targetClass = this.targetClass;
      clone.name = this.name;
      clone.description = this.description;
      clone.condition = this.condition.clone();
      clone.context = this.context.clone();
      clone.filter = this.filter.clone();
      clone.order = this.order.clone();
      return clone;
   }

   // Public accessors
   @SuppressWarnings( "static-access" )
   public boolean equals( Object objectToCheck ) {
      if( !(objectToCheck instanceof DefaultQuery) )
         return false;
      DefaultQuery anotherQuery = (DefaultQuery) objectToCheck;
      return // name.equals( anotherQuery.name ) &&
      // description.equals( anotherQuery.description ) &&
      targetClass.equals( anotherQuery.targetClass ) && condition.equals( anotherQuery.condition ) && context.equals( anotherQuery.context )
            && filter.equals( anotherQuery.filter ) && order.equals( anotherQuery.order );
   }

   public int hashCode() {
      int result = HashCodeUtil.SEED;
      result = HashCodeUtil.hash( result, targetClass );
      // result = HashCodeUtil.hash( result, name );
      // result = HashCodeUtil.hash( result, description );
      result = HashCodeUtil.hash( result, condition );
      result = HashCodeUtil.hash( result, context );
      result = HashCodeUtil.hash( result, filter );
      result = HashCodeUtil.hash( result, order );

      return result;
   }

   public static DefaultQuery parse( String str ) {
      return new DefaultQuery();
   }

   // Public mutators
   public static DefaultQuery parseOQLStatement( String statement ) throws ClassNotFoundException {
      Class<? extends Entity> targetClass = determineTargetClass( statement );
      DefaultQueryCondition condition = determineQueryCondition( statement );
      DefaultQueryContext context = determineQueryContext( statement );
      DefaultAttributeFilter filter = determineAttributeFilter( statement );

      DefaultQuery query = new DefaultQuery( targetClass, condition, context, filter );
      return query;
   }

   public void replaceContext( DefaultQueryContext newContext ) {
      context = newContext;
   }

   public @Override
   String toString() {
      HQLQueryTransformer hqlTransformer = QueryTransformerFactory.createHQLQueryTransformer();
      return hqlTransformer.createStatement( this );
   }

   // Properties
   public Integer getId() { return id; }
   public Class<? extends Entity> getTargetClass() { return targetClass; }
   public String getDescription() { return description; }
   public Integer getFirstResult() { return firstResult; }
   public void setFirstResult( int firstResult ) { this.firstResult = firstResult; }
   public Integer getMaxResults() { return maxResults; }
   public void setMaxResults( int maxResults ) { this.maxResults = maxResults; }
   public String getName() { return name; }
   public String getPredefinedStatement() { return predefinedStatement; }
   public void setPredefinedStatement( String statement ) { this.predefinedStatement = statement; }
   public QueryCondition<?> getQueryCondition() { return condition; }
   public QueryOrder getQueryOrder() { return order; }
   public QueryContext getQueryContext() { return context; }
   public AttributeFilter getAttributeFilter() { return filter; }

   // Private helper methods
   @SuppressWarnings("unchecked")
   private static Class<? extends Entity> determineTargetClass( String statement ) throws ClassNotFoundException {
      String statementPart = extractStatementPart( statement, " from ", " as " ); 
      Class<? extends Entity> targetClass = (Class<? extends Entity>) Class.forName( statementPart );
      return targetClass;
   }

   private static DefaultQueryCondition determineQueryCondition( String statement ) {
      String statementPart = extractStatementPart( statement, " where ", null );
      DefaultQueryCondition condition = new DefaultQueryCondition(); 
      
      StringTokenizer tokenizer = new StringTokenizer( statementPart, ",;" );
      while( tokenizer.hasMoreTokens() ) {
         String conditionElement = tokenizer.nextToken();
         parseConditionElement( condition, conditionElement );
      }
      return condition;
   }

   private static DefaultQueryContext determineQueryContext( String statement ) {
      return new DefaultQueryContext();
   }

   private static DefaultAttributeFilter determineAttributeFilter( String statement ) {
      String statementPart = extractStatementPart( statement, " select ", " from " );
      DefaultAttributeFilter attributeFilter = new DefaultAttributeFilter();

      StringTokenizer tokenizer = new StringTokenizer( statementPart, ",;" );
      while( tokenizer.hasMoreTokens() ) {
         String attribute = tokenizer.nextToken();
         if( attribute.equals( "*" ) ) break;
         else attributeFilter.addAttributeSelector( new AttributeSelector( tokenizer.nextToken()) );
      }
      return attributeFilter;
   }
   
   private static String extractStatementPart( String statement, String keyWordStartFrom, String keyWordEndTo ) {
      int beginning = statement.indexOf( keyWordStartFrom ) + keyWordStartFrom.length() ;
      int finish = keyWordEndTo != null ? statement.indexOf( keyWordEndTo ) : statement.length();
      return statement.substring( beginning, finish );
   }
   
   private static void parseConditionElement( DefaultQueryCondition condition, String conditionElement ) {
      
   }
}
