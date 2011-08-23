package com.processpuzzle.fundamental_types.uniqueidentifier.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;



public class UniqueIdentifierInstantiationException extends ProcessPuzzleProgrammingException {

   /**
    * 
    */
   private static final long serialVersionUID = -5207827292985847604L;
   private static String defaultMessagePattern = "Serious error occured during instantiation of unique identifer ''{1}'' of type ''{0}''.";

   UniqueIdentifierInstantiationException(String identiferTypeName, String identifier) {
      super(ExceptionHelper.defineMessage(UniqueIdentifierInstantiationException.class, new Object[] { identiferTypeName, identifier },
            defaultMessagePattern));
   }
}
