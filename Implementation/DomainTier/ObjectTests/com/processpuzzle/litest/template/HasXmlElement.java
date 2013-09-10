package com.processpuzzle.litest.template;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.xpath.XPath;

public class HasXmlElement extends TypeSafeMatcher<Document>{
   private final XPath xpathSelector;
   
   public HasXmlElement( XPath xpathSelector ){
      this.xpathSelector = xpathSelector;
   }
   
   @Factory public static Matcher<Document> hasXmlElement( XPath xpathSelector ) {
     return new HasXmlElement( xpathSelector );
   }
   
   @Override public void describeTo( Description description ) {
      description.appendText( "document has element value" );
   }

   @Override public boolean matchesSafely( Document xmlDocument ) {
      String searchedElement = null;
      try{
         searchedElement = xpathSelector.valueOf( xmlDocument );
      }catch( JDOMException e ){
         e.printStackTrace();
      } 
      return searchedElement != null && searchedElement != "";
   }
}
