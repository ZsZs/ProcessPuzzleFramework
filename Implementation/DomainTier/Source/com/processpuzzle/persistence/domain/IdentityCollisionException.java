/*
 * Created on Nov 29, 2006
 */
package com.processpuzzle.persistence.domain;

import hu.itkodex.commons.persistence.Entity;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

public class IdentityCollisionException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = -3548255639565760641L;
   private static String defaultMessagePattern = "An object of entity: ''{0}'' already exists.";
   private Class<? extends Entity> entityClass;
   
   public IdentityCollisionException( Class<? extends Entity> entityClass ) {
      super(ExceptionHelper.defineMessage(IdentityCollisionException.class, new Object[] { entityClass.getName() }, defaultMessagePattern));
   }

   public Class<? extends Entity> getEntity() { return entityClass; }

   public Object getIdentityExpression() {
      // TODO Auto-generated method stub
      return null;
   }

   public Object getContext() {
      // TODO Auto-generated method stub
      return null;
   }

}
