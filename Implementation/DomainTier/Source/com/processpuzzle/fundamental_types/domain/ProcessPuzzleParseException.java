package com.processpuzzle.fundamental_types.domain;


public class ProcessPuzzleParseException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = -8017178918256385768L;

   public ProcessPuzzleParseException( String source, String formatExpression, Throwable cause) {
      super( defineMessage( source, formatExpression ), cause );
   }

   protected static Object[] defineMessage( String source, String formatExpression ) {
      formatPattern = "Parsing source: ''{0}'' with formatter: ''{1}'' caused error.";
      Object[] arguments = {source, formatExpression};
      return arguments;
   }
}
