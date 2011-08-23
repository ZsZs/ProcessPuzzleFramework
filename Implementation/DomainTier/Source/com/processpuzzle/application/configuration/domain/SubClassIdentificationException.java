package com.processpuzzle.application.configuration.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

public class SubClassIdentificationException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = 6087072778247446168L;
   private static String defaultMessagePattern = "Unexpected exception was catched during loking for subclasses of: '''{0}'''.";
   private Class<?> parentClass;
   
   public SubClassIdentificationException( Class<?> parentClass, Throwable e ) {
      super( ExceptionHelper.defineMessage( SubClassIdentificationException.class, new Object[] { parentClass.getName()}, defaultMessagePattern ), e );
      this.parentClass = parentClass;
   }

   public Class<?> getParentClass() { return parentClass; }
}
