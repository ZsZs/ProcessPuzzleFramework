/*
 * Created on Jun 25, 2006
 */
package com.processpuzzle.persistence.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

/**
 * @author zsolt.zsuffa
 */
public class EntityIdentityCollitionException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = -325756507198654604L;
   private static String defaultMessagePattern = "The given entity's identity is in coallision whit one other entity.";
   private String entityClassName = "";
   private String identityExpression = "";
   private String context = "";

   public EntityIdentityCollitionException(String entityClassName, String identityExpression, String context) {
      super(ExceptionHelper.defineMessage(EntityIdentityCollitionException.class, new Object[] { entityClassName, identityExpression,
            context }, defaultMessagePattern));
      // super(messageBody + "\nEntity class name: " + entityClassName +
      // "\tidentitiyExpression: " + identityExpression + "\tcontext: " +
      // context);
      this.entityClassName = entityClassName;
      this.identityExpression = identityExpression;
      this.context = context;
   }

   public String getEntityClassName() {
      return entityClassName;
   }

   public String getIdentityExpression() {
      return identityExpression;
   }

   public String getContext() {
      return context;
   }
}
