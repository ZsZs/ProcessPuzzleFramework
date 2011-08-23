package com.processpuzzle.fundamental_types.possiblevalue.domain;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.processpuzzle.internalization.domain.ProcessPuzzleLocale;
import com.processpuzzle.sharedfixtures.domaintier.ProcessPuzzleContextFixture;

public class StringEnumerationTest {

   private StringEnumeration enumeration;
   private static ProcessPuzzleLocale hungarianLocale = new ProcessPuzzleLocale("hu");
   private static ProcessPuzzleContextFixture context = null;
   
   @BeforeClass
   public static void beforeAllTests() {
      context = ProcessPuzzleContextFixture.getInstance();
      context.setUp();
   }
   

   @Before public void beforeEachTest() {

      enumeration = new StringEnumeration();
      enumeration.addPossibleValue("kék");
      enumeration.addPossibleValue("zöld");
      enumeration.addPossibleValue("piros");
   }
   
   @Test public void checkValue_ShouldExamineValueEquality() {
      //Setup
      
      //Verify
      assertThat(enumeration.getValues().size(), is(3));
      assertThat(enumeration.checkValue("kék"), is(true));
      assertThat(enumeration.checkValue("zöld"), is(true));
      assertThat(enumeration.checkValue("piros"), is(true));
   }
   
   @Test public void asText() {
      assertThat(enumeration.asText(hungarianLocale), is("( kék, zöld, piros )"));
   }
   
   @Test public void checkValue_ForNegativeOutcome() {
      assertThat(enumeration.checkValue( "fekete"), is(false));
   }
   
   @Test public void stringValue() {
      assertThat(enumeration.stringValue(), is("kék;zöld;piros"));
   }
   
   @Test public void parseFromString() {
      StringEnumeration newEnumeration = new StringEnumeration();
      String possibleValuesDef = "lila;fekete;fehér";
      newEnumeration.parseFromString( newEnumeration, possibleValuesDef );
      assertThat(newEnumeration.getValues().size(), is(3));
      assertThat(newEnumeration.getValues().contains( "lila" ), is(true));
      assertThat(newEnumeration.getValues().contains( "fekete" ), is(true));
      assertThat(newEnumeration.getValues().contains( "fehér" ), is(true));
   }

   @AfterClass
   public static void afterAllTests() {
      context.tearDown();
   }
}
