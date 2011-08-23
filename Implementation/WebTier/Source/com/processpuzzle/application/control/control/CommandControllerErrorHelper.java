/*
 * Created on Jan 23, 2006
 */
package com.processpuzzle.application.control.control;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

/**
 * @author zsolt.zsuffa
 */
public class CommandControllerErrorHelper {
   private String name;
   private String description;
   private Throwable cause;
   private Collection<String> requestAttributes = new HashSet<String>();

   // public constructors
   public CommandControllerErrorHelper( String name ) {
      this( name, null, null );
   }

   public CommandControllerErrorHelper( String name, String description ) {
      this( name, description, null );
   }
   
   public CommandControllerErrorHelper( String name, String description, Throwable cause ) {
      this.name = name;
      this.description = description;
      this.cause = cause;
   }
   
   //Public accessor and mutator methods
   public String getStackTrace() {
      if( cause != null && (cause instanceof ProcessPuzzleProgrammingException )) {
         ProcessPuzzleProgrammingException processPuzzleException = (ProcessPuzzleProgrammingException) cause;
         return processPuzzleException.getText();
      }else {
         return ProcessPuzzleProgrammingException.getCustomStackTraceFor( cause );
      }
   }

   public void setRequestAttributes( Properties properties ) {
      Set<Object> keys = properties.keySet();
      for( Iterator<Object> iter = keys.iterator(); iter.hasNext(); ){
         String key = (String) iter.next();
         String value = properties.getProperty( key );
         requestAttributes.add( "Key=" + key + ", value=" + value );
      }
   }

   //Properties
   public Throwable getCause() { return cause; }
   public String getDescription() { return description; }
   public String getName() { return name; }
   public Collection<String> getRequestAttributes() { return requestAttributes; }
   
   //Protected, private helper methods
}
