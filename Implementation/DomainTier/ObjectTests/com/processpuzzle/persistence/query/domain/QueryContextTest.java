package com.processpuzzle.persistence.query.domain;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.processpuzzle.persistence.query.domain.DefaultQueryContext;

public class QueryContextTest {
   private DefaultQueryContext aContext = null;
   private DefaultQueryContext cloneOfAContext = null;
   
   @Before
   public void beforeEachTests() {
      aContext = new DefaultQueryContext();
      aContext.addIntegerValueFor("someIntegerAttribute", 222 );
      aContext.addTextValueFor("someTextAttribute", "bakfitty");
      cloneOfAContext = aContext.clone();
   }
   
   @Test
   public void testClone() {
      for (Iterator<?> iter = aContext.attributeValuesIterator(); iter.hasNext();) {
         Map.Entry attributeValueEntry = (Map.Entry) iter.next();
         System.out.println( attributeValueEntry.getKey() + " = " + attributeValueEntry.getValue() );
         assertEquals( aContext.getAttributeValue( (String) attributeValueEntry.getKey() ), cloneOfAContext.getAttributeValue( (String) attributeValueEntry.getKey() ));
      }
      
      for (Iterator<?> iter = cloneOfAContext.attributeValuesIterator(); iter.hasNext();) {
         Map.Entry attributeValueEntry = (Map.Entry) iter.next();
         System.out.println( attributeValueEntry.getKey() + " = " + attributeValueEntry.getValue() );
         assertEquals( cloneOfAContext.getAttributeValue( (String) attributeValueEntry.getKey() ), aContext.getAttributeValue( (String) attributeValueEntry.getKey() ));
      }
   }
   
   @Test
   public void testEquals() {
      assertEquals( aContext.attributeValuesEntrySet(), cloneOfAContext.attributeValuesEntrySet() );
      assertEquals( aContext, cloneOfAContext );
      assertEquals( cloneOfAContext, aContext );
   }
   
   @After
   public void afterEachTests() {
      aContext = null;
      cloneOfAContext = null;
   }
}
