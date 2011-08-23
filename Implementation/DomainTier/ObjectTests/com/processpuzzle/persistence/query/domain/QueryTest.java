package com.processpuzzle.persistence.query.domain;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import hu.itkodex.commons.persistence.query.AttributeFilter;
import hu.itkodex.commons.persistence.query.AttributeSelector;
import hu.itkodex.commons.persistence.query.BooleanOperator;
import hu.itkodex.commons.persistence.query.BooleanOperators;
import hu.itkodex.commons.persistence.query.OrderSpecifier;
import hu.itkodex.commons.persistence.query.OrderingDirections;
import hu.itkodex.commons.persistence.query.QueryCondition;
import hu.itkodex.commons.persistence.query.QueryContext;
import hu.itkodex.commons.persistence.query.QueryOrder;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.persistence.domain.TestEntity;
import com.processpuzzle.persistence.query.transformer.domain.QueryTransformerTest;

public class QueryTest {
   private static final String REALLY_SIMPLE_HQL = "select * from com.processpuzzle.framework.persistence.domain.TestEntity as testEntity";
   private DefaultQuery reallySimpleQuery;
   private DefaultQuery fullFlaggedQuery;
   private DefaultQuery cloneOfFullFlaggedQuery;
   private DefaultQuery yetAnotherCloneOfFullFlaggedQuery;

   @Before
   public void beforeEachTests() {
      reallySimpleQuery = createReallySimpleQueryForTest();
      fullFlaggedQuery = createFullFlaggedQueryForTest();
      cloneOfFullFlaggedQuery = fullFlaggedQuery.clone();
      yetAnotherCloneOfFullFlaggedQuery = fullFlaggedQuery.clone();
   }

   @Test
   public void testClone() {
      assertEquals( fullFlaggedQuery.getTargetClass(), cloneOfFullFlaggedQuery.getTargetClass() );
      assertEquals( fullFlaggedQuery.getName(), cloneOfFullFlaggedQuery.getName() );
      assertEquals( fullFlaggedQuery.getDescription(), cloneOfFullFlaggedQuery.getDescription() );
      assertEquals( fullFlaggedQuery.getAttributeFilter(), cloneOfFullFlaggedQuery.getAttributeFilter() );
      assertEquals( fullFlaggedQuery.getQueryCondition(), cloneOfFullFlaggedQuery.getQueryCondition() );
      assertEquals( fullFlaggedQuery.getQueryOrder(), cloneOfFullFlaggedQuery.getQueryOrder() );
      assertEquals( fullFlaggedQuery.getQueryContext(), cloneOfFullFlaggedQuery.getQueryContext() );
   }

   @Test
   public void testEquals() {
      assertTrue( "Check reflexivity.", fullFlaggedQuery.equals( fullFlaggedQuery ) );

      assertTrue( "Check symmetry.", cloneOfFullFlaggedQuery.equals( fullFlaggedQuery ) );
      assertTrue( "Check symmetry.", fullFlaggedQuery.equals( cloneOfFullFlaggedQuery ) );

      assertTrue( "Check transitivity.", fullFlaggedQuery.equals( yetAnotherCloneOfFullFlaggedQuery ) );

      assertFalse( "Null chekc.", fullFlaggedQuery.equals( null ) );
   }

   @SuppressWarnings( "unchecked" )
   @Ignore
   @Test
   public void testParseOQLStatement() throws ClassNotFoundException {
      DefaultQuery parsedQuery = DefaultQuery.parseOQLStatement( REALLY_SIMPLE_HQL );
      assertThat( (Class<TestEntity>) parsedQuery.getTargetClass(), equalTo( TestEntity.class ) );
   }

   @Test
   public void testToString() {
      assertThat( reallySimpleQuery.toString(), equalTo( QueryTransformerTest.REALLY_SIMPLE_HQL ) );
   }

   @Test
   public void testHashCode() {
      assertEquals( "If two objects equals than their hash codes also equals.", fullFlaggedQuery.hashCode(), cloneOfFullFlaggedQuery.hashCode() );
   }

   @After
   public void afterEachTests() {
      fullFlaggedQuery = null;
      cloneOfFullFlaggedQuery = null;
      yetAnotherCloneOfFullFlaggedQuery = null;
   }

   // Static helper functions
   public static DefaultQuery createReallySimpleQueryForTest() {
      DefaultQuery query = new DefaultQuery( TestEntity.class );
      return query;
   }

   public static DefaultQuery createSimpleQueryForTest() {
      DefaultQuery query = new DefaultQuery( TestEntity.class, "Simple test query", "Used to test the functionality of Query class." );
      AttributeFilter filter = query.getAttributeFilter();
      filter.addAttributeSelector( new AttributeSelector( "id" ) );
      filter.addAttributeSelector( new AttributeSelector( "name" ) );
      filter.addAttributeSelector( new AttributeSelector( "textAttribute" ) );
      filter.addAttributeSelector( new AttributeSelector( "numberAttribute" ) );
      filter.addAttributeSelector( new AttributeSelector( "dateAttribute" ) );

      // Please note that QueryCondition uses the so called "Reverse Polish Notation" to define logical expression
      QueryCondition condition = query.getQueryCondition();
      condition.addAttributeCondition( new IntegerAttributeCondition( "numberAttribute", 256, ComparisonOperators.GREATER_OR_EQUAL_TO ) );
      condition.addAttributeCondition( new TextAttributeCondition( "name", "bakfity", ComparisonOperators.EQUAL_TO ) );
      condition.addBooleanOperator( new BooleanOperator( BooleanOperators.AND ) );
      return query;
   }

   public static DefaultQuery createFullFlaggedQueryForTest() {
      DefaultQuery query = createSimpleQueryForTest();

      QueryOrder order = query.getQueryOrder();
      order.addOrderSpecifier( new OrderSpecifier( "textAttribute", OrderingDirections.Ascending ) );
      order.addOrderSpecifier( new OrderSpecifier( "dateAttribute", OrderingDirections.Descending ) );

      QueryContext context = query.getQueryContext();
      context.addIntegerValueFor( "numberAttribute", 111 );
      context.addTextValueFor( "textAttribute", "actual text" );

      return query;
   }
}
