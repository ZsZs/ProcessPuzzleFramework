package com.processpuzzle.artifact.domain;


import java.io.IOException;

import org.dom4j.DocumentException;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import com.processpuzzle.commons.xml.XmlDocumentComparator;

public class IsSameXml extends TypeSafeDiagnosingMatcher<org.dom4j.Document> {
   private final org.dom4j.Document expectedXml;
   
   public IsSameXml( org.dom4j.Document expectedXml ) {
      this.expectedXml = expectedXml;
   }
   
   @Override
   public void describeTo( Description description ) {
      description.appendText( "not the same xml" );
   }

   @Override
   protected boolean matchesSafely( org.dom4j.Document subjectXml, Description mismatchDescription ) {
      boolean comparisonResult = false;
      try{
         comparisonResult = XmlDocumentComparator.compareGeneratedDocumentToExpectedDocument( subjectXml, expectedXml );
      }catch( IOException e ){
         e.printStackTrace();
      }catch( DocumentException e ){
         e.printStackTrace();
      }
      return comparisonResult;
   }

   @Factory
   public static <T> Matcher<org.dom4j.Document> isSameXml( org.dom4j.Document operand ) {
       return new IsSameXml( operand );
   }
}
