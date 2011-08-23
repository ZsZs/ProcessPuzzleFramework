package com.processpuzzle.fundamental_types.domain;

import java.text.MessageFormat;

public abstract class ProcessPuzzleException extends Exception {
   private static final long serialVersionUID = -1210758244388234902L;
   protected static String formatPattern = null;
   protected String resourceKey;
   protected String text;
   protected Throwable cause = null;

   public ProcessPuzzleException( Object[] arguments, Throwable cause ) {
      super( MessageFormat.format( formatPattern, arguments ), cause );
      this.cause = cause;
   }
   
   public ProcessPuzzleException( ExceptionHelper helper ) {
      this( helper, null );
   }
   
   public ProcessPuzzleException( ExceptionHelper helper, Throwable cause ) {
      super( MessageFormat.format( helper.getMessagePattern(), helper.getArguments() ), cause );
      this.text = MessageFormat.format( helper.getMessagePattern(), helper.getArguments() );
      this.cause = cause;
   }

   public String getText() { return text; }
   
   public String getStackTraceAsText() {
      String stackTraceText = "";
      for (int i = 0; i < cause.getStackTrace().length; i++) {
         stackTraceText += cause.getStackTrace()[i].toString();
      }
      return stackTraceText;
   }
}
