package com.processpuzzle.application.configuration.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

public class UndefinedPropertyDescriptorException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = 7733136640533283357L;
   private static String defaultMessagePattern = "Property descriptor ''{0}'' is undefined.";
   private String descriptorPath = null;

   public UndefinedPropertyDescriptorException( String descriptorPath, Throwable cause ) {
      super( ExceptionHelper.defineMessage( 
                  UndefinedPropertyDescriptorException.class, 
                  new Object[] {descriptorPath}, 
                  defaultMessagePattern ), 
                  cause );
      this.descriptorPath = descriptorPath;
   }

//Properties
   public String getDescriptorPath() { return descriptorPath; }
}
