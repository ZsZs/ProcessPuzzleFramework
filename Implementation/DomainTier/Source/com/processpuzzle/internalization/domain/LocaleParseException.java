package com.processpuzzle.internalization.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

public class LocaleParseException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = -2657210622704877156L;
   private static String defaultMessagePattern = "Parsing locale specifier: ''{0}'' caused an error.";
   private String sourceText = null;

   LocaleParseException( String sourceText ){
      super( ExceptionHelper.defineMessage(
            LocaleParseException.class,
            new Object[] {sourceText},
            defaultMessagePattern));
      this.sourceText = sourceText;
   }
   
// Properties
   public String getSourceText() { return sourceText; }
}
