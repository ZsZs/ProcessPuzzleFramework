package com.processpuzzle.application.domain;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import com.processpuzzle.application.resource.domain.DataLoader;

public class DataLoaderMatcher extends TypeSafeMatcher<DataLoader> {
   public DataLoaderMatcher () {}
   
   public void describeTo( Description description ) {
      description.appendText( "dataLoader is loaded" );
   }
   
   @Factory public static <T> Matcher<DataLoader> isLoaded() {
      return new DataLoaderMatcher();
   }

   @Override
   public boolean matchesSafely( DataLoader dataLoader ) {
      return dataLoader.isLoaded();
   }

   @Override
   protected void describeMismatchSafely( DataLoader item, Description mismatchDescription ) {
      // TODO Auto-generated method stub
      
   }

}
