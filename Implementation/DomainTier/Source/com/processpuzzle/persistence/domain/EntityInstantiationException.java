package com.processpuzzle.persistence.domain;

import hu.itkodex.commons.persistence.Entity;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

public class EntityInstantiationException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = 8988275764874004878L;
   private static final String defaultMessagePattern = "Creating entity: ''{0}'' with parameter: ''{1}'' = ''{2}'' caused error.";
   private Class<? extends Entity> entityClass;
   private String parameterName, parameterValue;
   
   public EntityInstantiationException( Class<? extends Entity> entityClass, String parameterName, String parameterValue, Throwable cause ) {
      super( ExceptionHelper.defineMessage(
            EntityInstantiationException.class,
            new Object[] { entityClass.getName(), parameterName, parameterValue },
            defaultMessagePattern ), 
            cause );
      
   }

   public Class<? extends Entity> getEntityClass() {
      return entityClass;
   }

   public String getParameterName() {
      return parameterName;
   }

   public String getParameterValue() {
      return parameterValue;
   }
}
