/*
 * Created on Jul 2, 2006
 */
package com.processpuzzle.fundamental_types.domain;


public class AssertionException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = 4532971403010928830L;
   private static String defaultMessagePattern = "Assertion: ''{0}'' failed.";
   private String assertion = "";

   public AssertionException( String assertion ) {
      super( ExceptionHelper.defineMessage(
            AssertionException.class,
            new Object[] {assertion},
            defaultMessagePattern ));
      this.assertion = assertion;
   }

   public String getAssertion() { return assertion; }
}