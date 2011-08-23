package com.processpuzzle.persistence.query.transformer.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

public class MissingQueryConditionVariable extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = -5467923402635609388L;
   private static final String defaultMessagePattern = "Value for variable: ''{0} is missing form QueryContext";

   public MissingQueryConditionVariable( String variableName ) {
      super( ExceptionHelper.defineMessage(
            MissingQueryConditionVariable.class, 
            new Object[] { variableName }, 
            defaultMessagePattern));
   }
}
