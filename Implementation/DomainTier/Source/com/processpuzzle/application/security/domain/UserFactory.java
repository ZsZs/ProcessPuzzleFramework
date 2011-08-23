/*
 * Created on Jul 2, 2006
 */
package com.processpuzzle.application.security.domain;

import com.processpuzzle.fundamental_types.domain.OpAssertion;
import com.processpuzzle.internalization.domain.ProcessPuzzleLocale;
import com.processpuzzle.persistence.domain.GenericFactory;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

/**
 * @author zsolt.zsuffa
 */
public class UserFactory extends GenericFactory<User>{

   public User createUser( String userName, String password ) {
      return createUser(userName, password, null );
   }

   public User createUser(String userName, String password, ProcessPuzzleLocale locale) {
      OpAssertion.ppAssert(userName != null && !userName.equals(""), "'userName can't be null or empty");
      OpAssertion.ppAssert(password != null && !password.equals(""), "'password can't be null or empty");
      
      User user = new User(userName, password, locale);
      DefaultIdentityExpression identity = user.getDefaultIdentity();
      checkEntityIdentityCollition( identity ); 
      return user;
   }
}
