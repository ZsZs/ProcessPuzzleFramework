package com.processpuzzle.fundamental_types.domain;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ProcessPuzzleProgrammingException extends RuntimeException {
   private static final long serialVersionUID = 0;
   protected String resourceKey;
   protected Logger log = LoggerFactory.getLogger( ProcessPuzzleProgrammingException.class );
   protected static String formatPattern = null;
   protected String text;

   public ProcessPuzzleProgrammingException( Object[] arguments, Throwable cause ) {
      super( MessageFormat.format( formatPattern, arguments ), cause );
      this.text = MessageFormat.format( formatPattern, arguments );
      this.text += appendTextFromCause( cause );
      log.debug( this.text );
   }

   public ProcessPuzzleProgrammingException( Object[] arguments ) {
      this( arguments, null );
   }

   public ProcessPuzzleProgrammingException( ExceptionHelper helper, Throwable cause ) {
      super( MessageFormat.format( helper.getMessagePattern(), helper.getArguments() ), cause );
      this.text = MessageFormat.format( helper.getMessagePattern(), helper.getArguments() );
      this.text += appendTextFromCause( cause );
      log.debug( this.text );
   }

   public ProcessPuzzleProgrammingException( ExceptionHelper helper ) {
      this( helper, null );
   }

   public ProcessPuzzleProgrammingException( String message ) {
      super( message );
   }

   public ProcessPuzzleProgrammingException( String message, Throwable cause ) {
      super( message, cause );
   }

   public String getText() {
      return text;
   }

   public static final long getSerialVersionUID() {
      return serialVersionUID;
   }
   
   public static String getStackTraceFor( Throwable aThrowable ) {
      final Writer result = new StringWriter();
      final PrintWriter printWriter = new PrintWriter( result );
      aThrowable.printStackTrace( printWriter );
      return result.toString();
   }

   /**
    * Defines a custom format for the stack trace as String.
    */
   public static String getCustomStackTraceFor( Throwable aThrowable ) {
      // add the class name and any message passed to constructor
      final StringBuilder result = new StringBuilder( "ProcessPuzzle: " );
      result.append( aThrowable.toString() );
      final String NEW_LINE = System.getProperty( "line.separator" );
      result.append( NEW_LINE );

      // add each element of the stack trace
      for( StackTraceElement element : aThrowable.getStackTrace() ){
         result.append( element );
         result.append( NEW_LINE );
      }
      return result.toString();
   }

   private String appendTextFromCause( Throwable cause ) {
      String textFromCause = "";

      if( cause != null ) {
         if( cause instanceof ProcessPuzzleProgrammingException ){
            textFromCause = "\n\nCause: " + ((ProcessPuzzleProgrammingException) cause).getMessage();
         }else {
            final Writer stringWriter = new StringWriter();
            final PrintWriter printWriter = new PrintWriter( stringWriter );
            cause.printStackTrace( printWriter );
            textFromCause = "\n\nCause: " + stringWriter.toString();
         }
      }
      return textFromCause;
   }
}
