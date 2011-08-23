package com.processpuzzle.application.security.control;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

public class AuthorizationException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = 1743186847200504271L;
   private static final String defaultMessage = "User logged in: '''{0}''' is not allowed to perfor action: '''{1}'''";
   private String user;
   private String action;

   public AuthorizationException( String user, String action ) {
      this( user, action, null );
   }
   
   public AuthorizationException( String user, String action, Throwable cause ) {
      super( ExceptionHelper.defineMessage( AuthorizationException.class, new Object[] {user, action}, defaultMessage ), cause );
      this.user = user;
      this.action = action;
   }

   public String getUser() {
      return user;
   }

   public String getAction() {
      return action;
   }
}
