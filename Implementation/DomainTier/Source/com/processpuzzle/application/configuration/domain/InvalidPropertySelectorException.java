package com.processpuzzle.application.configuration.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

public class InvalidPropertySelectorException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = -8922126736908795062L;
   private static String defaultMessagePattern = "Property selector: ''{0}'' is invalid or doesn't returned any property.";
   private String propertySelector;
   
   public InvalidPropertySelectorException( String propertySelector, Throwable cause ) {
      super( ExceptionHelper.defineMessage( InvalidPropertySelectorException.class, new Object[] { propertySelector }, defaultMessagePattern ), cause );
      this.propertySelector = propertySelector;
   }

   public String getPropertySelector() { return propertySelector; }
}
