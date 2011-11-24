/*
Name: 
    - UserFactory

Description: 
    -  

Requires:
    - 
    
Provides:
    - 

Part of: ProcessPuzzle Framework, Domain and Business Model Ready Architecture. Provides content, workflow and social networking functionality. 
http://www.processpuzzle.com

ProcessPuzzle - Content and Workflow Management Integration Business Platform

Author(s): 
    - Zsolt Zsuffa

Copyright: (C) 2011 This program is free software: you can redistribute it and/or modify it under the terms of the 
GNU General Public License as published by the Free Software Foundation, either version 3 of the License, 
or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

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
