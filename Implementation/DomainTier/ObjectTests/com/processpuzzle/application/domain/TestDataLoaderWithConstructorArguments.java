package com.processpuzzle.application.domain;

import com.processpuzzle.application.resource.domain.HardCodedDataLoader;

public class TestDataLoaderWithConstructorArguments extends HardCodedDataLoader {
   private static String argumentOne;
   private static String argumentTwo;
   
   public TestDataLoaderWithConstructorArguments( String argumentOne, String argumentTwo ) {
      TestDataLoaderWithConstructorArguments.argumentOne = argumentOne;
      TestDataLoaderWithConstructorArguments.argumentTwo = argumentTwo;
   }
   
   public static String getConstructorArgumentOne() { return argumentOne; }
   public static String getConstructorArgumentTwo() { return argumentTwo; }
}
