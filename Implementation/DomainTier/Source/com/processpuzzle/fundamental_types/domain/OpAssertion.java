/*
 * Created on Jul 2, 2006
 */
package com.processpuzzle.fundamental_types.domain;


/**
 * @author zsolt.zsuffa
 */
public class OpAssertion {
   public static boolean NDEBUG = true;

   private static void printStack(String why) {
      AssertionException exception = new AssertionException(why);
      //exception.printStackTrace();
      throw exception;
   }

   public static void ppAssert(boolean expression, String why) {
      if (NDEBUG && !expression) {
         printStack(why);
      }
   }
}
