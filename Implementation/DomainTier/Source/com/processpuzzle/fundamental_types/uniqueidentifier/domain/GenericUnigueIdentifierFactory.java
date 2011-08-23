package com.processpuzzle.fundamental_types.uniqueidentifier.domain;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.user_session.domain.UserRequestManager;

public abstract class GenericUnigueIdentifierFactory<I extends UniqueIdentifier> implements UniqueIdentifierFactory {
   protected ProcessPuzzleContext applicationContext;
   protected User currentUser;
   
   public GenericUnigueIdentifierFactory() {
      applicationContext = UserRequestManager.getInstance().getApplicationContext();
   }
}
