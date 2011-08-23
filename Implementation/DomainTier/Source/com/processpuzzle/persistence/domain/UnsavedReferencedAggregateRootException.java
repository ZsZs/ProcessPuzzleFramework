package com.processpuzzle.persistence.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;

public class UnsavedReferencedAggregateRootException extends RepositoryException {
   private static final long serialVersionUID = -8149069736715986056L;
   protected static String defaultMessagePattern = "Action: ''{0}'' can't be performed because object: ''{1}'' referenced by: ''{2}'' is not saved.";

   public UnsavedReferencedAggregateRootException( String actionName, String subjectAggregateRootName, String referencedAggregateRootName, Throwable cause) {
      super( ExceptionHelper.defineMessage( 
            UnsavedReferencedAggregateRootException.class, 
            new Object[] {actionName, referencedAggregateRootName, subjectAggregateRootName}, 
            defaultMessagePattern ), cause);
   }
}
