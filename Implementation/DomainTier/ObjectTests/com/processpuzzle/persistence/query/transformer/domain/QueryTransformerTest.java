package com.processpuzzle.persistence.query.transformer.domain;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.commons.persistence.query.BooleanOperator;
import com.processpuzzle.commons.persistence.query.BooleanOperators;
import com.processpuzzle.commons.persistence.query.Count;
import com.processpuzzle.commons.persistence.query.Maximum;
import com.processpuzzle.commons.persistence.query.OrderSpecifier;
import com.processpuzzle.commons.persistence.query.OrderingDirections;
import com.processpuzzle.persistence.domain.TestEntity;
import com.processpuzzle.persistence.query.domain.ComparisonOperators;
import com.processpuzzle.persistence.query.domain.DateAttributeCondition;
import com.processpuzzle.persistence.query.domain.DefaultQuery;
import com.processpuzzle.persistence.query.domain.IntegerAttributeCondition;
import com.processpuzzle.persistence.query.domain.QueryTest;
import com.processpuzzle.persistence.query.domain.QueryVariable;
import com.processpuzzle.persistence.query.domain.TextAttributeCondition;

public class QueryTransformerTest {
   public static String REALLY_SIMPLE_HQL = "select testEntity from TestEntity as testEntity";
   public static String REALLY_SIMPLE_SQL = "SELECT * FROM TestEntity";
   public static String SIMPLE_HQL = "select testEntity.id, testEntity.name, testEntity.textAttribute, testEntity.numberAttribute, testEntity.dateAttribute from TestEntity as testEntity where (testEntity.name = 'bakfity') and (testEntity.numberAttribute >= 256)";
   public static String SIMPLE_SQL = "SELECT testEntity.id, testEntity.name, testEntity.textAttribute, testEntity.numberAttribute, testEntity.dateAttribute FROM TestEntity WHERE (testEntity.name = 'bakfity') and (testEntity.numberAttribute >= 256)";
   private HQLQueryTransformer hqlTransformer = null;
   private SQLQueryTransformer sqlTransformer = null;
   private DefaultQuery reallySimpleQuery = null;
   private DefaultQuery simpleQuery = null;
   private DefaultQuery fullFlaggedQuery = null;

   @Before
   public void beforeEachTests() {
      reallySimpleQuery = QueryTest.createReallySimpleQueryForTest();
      simpleQuery = QueryTest.createSimpleQueryForTest();
      fullFlaggedQuery = QueryTest.createFullFlaggedQueryForTest();
      hqlTransformer = QueryTransformerFactory.createHQLQueryTransformer();
      sqlTransformer = QueryTransformerFactory.createSQLQueryTransformer();
   }

   @After
   public void afterEachTests() {
      hqlTransformer = null;
      sqlTransformer = null;
      reallySimpleQuery = null;
      simpleQuery = null;
   }

   @Test
   public void createStatement_ForImmutability() {
      DefaultQuery cloneOfOriginal = fullFlaggedQuery.clone();
      hqlTransformer.createStatement( fullFlaggedQuery );
      sqlTransformer.createStatement( fullFlaggedQuery );
      assertEquals( "QueryTransformer works on a cloned query object. Given query should be immutable.", cloneOfOriginal, fullFlaggedQuery );
   }

   @Test
   public void createStatement_ForHQL() {
      assertEquals( REALLY_SIMPLE_HQL, hqlTransformer.createStatement( reallySimpleQuery ) );
      assertEquals( SIMPLE_HQL, hqlTransformer.createStatement( simpleQuery ) );
   }

   @Test
   public void createStatement_ForSQL() {
      assertEquals( REALLY_SIMPLE_SQL, sqlTransformer.createStatement( reallySimpleQuery ) );
      assertEquals( SIMPLE_SQL, sqlTransformer.createStatement( simpleQuery ) );
   }

   @Test
   public void queryWithNotNull() {
      String HQL_WITH_NOT_NULL = "select testEntity from TestEntity as testEntity where testEntity.dateAttribute is not null";
      DefaultQuery query = new DefaultQuery( TestEntity.class );
      query.getQueryCondition().addAttributeCondition( new DateAttributeCondition( "dateAttribute", ComparisonOperators.IS_NOT_NULL ) );

      assertEquals( HQL_WITH_NOT_NULL, hqlTransformer.createStatement( query ) );
   }

   @Test
   public void queryWithLike() {
      String HQL_WITH_LIKE = "select testEntity from TestEntity as testEntity where testEntity.textAttribute like 'bakf%'";
      DefaultQuery query = new DefaultQuery( TestEntity.class );
      query.getQueryCondition().addAttributeCondition( new TextAttributeCondition( "textAttribute", "bakf%", ComparisonOperators.LIKE ) );

      assertEquals( HQL_WITH_LIKE, hqlTransformer.createStatement( query ) );
   }

   @Ignore
   @Test
   public void queryWithOrder() {
      String HQL_WITH_ORDER = "select testEntity from TestEntity as testEntity order by testEntity.name desc, testEntity.numberAttribute asc";
      DefaultQuery query = new DefaultQuery( TestEntity.class );
      query.getQueryOrder().addOrderSpecifier( new OrderSpecifier( "numberAttribute", OrderingDirections.Ascending ) );
      query.getQueryOrder().addOrderSpecifier( new OrderSpecifier( "name", OrderingDirections.Descending ) );

      assertEquals( HQL_WITH_ORDER, hqlTransformer.createStatement( query ) );
   }

   public @Test void queryWithMaxAggregateFunction() {
      String HQL_WITH_MAX_AGGREGATE = "select max( testEntity.numberAttribute ) from TestEntity as testEntity";

      DefaultQuery query = new DefaultQuery( TestEntity.class );
      query.getAttributeFilter().addAggregateFunction( new Maximum( "numberAttribute" ) );

      assertEquals( HQL_WITH_MAX_AGGREGATE, hqlTransformer.createStatement( query ) );
   }

   public @Test void queryWithCountAggregateFunction() {
      String HQL_WITH_MAX_AGGREGATE = "select count( testEntity.name ) from TestEntity as testEntity";

      DefaultQuery query = new DefaultQuery( TestEntity.class );
      query.getAttributeFilter().addAggregateFunction( new Count( "name" ) );

      assertEquals( HQL_WITH_MAX_AGGREGATE, hqlTransformer.createStatement( query ) );
   }

   @Test
   public void testQueryWithContext() {
      String HQL_WITH_CONTEXT = "select testEntity from TestEntity as testEntity where (testEntity.name = 'bakfity') and (testEntity.numberAttribute >= 256)";

      DefaultQuery query = new DefaultQuery( TestEntity.class );
      query.getQueryCondition()
            .addAttributeCondition(
                  new IntegerAttributeCondition( "numberAttribute", new QueryVariable( "numberValue" ),
                        ComparisonOperators.GREATER_OR_EQUAL_TO ) );
      query.getQueryCondition().addAttributeCondition(
            new TextAttributeCondition( "name", new QueryVariable( "nameValue" ), ComparisonOperators.EQUAL_TO ) );
      query.getQueryCondition().addBooleanOperator( new BooleanOperator( BooleanOperators.AND ) );

      query.getQueryContext().addTextValueFor( "nameValue", "bakfity" );
      query.getQueryContext().addIntegerValueFor( "numberValue", 256 );

      assertEquals( HQL_WITH_CONTEXT, hqlTransformer.createStatement( query ) );
   }

}
