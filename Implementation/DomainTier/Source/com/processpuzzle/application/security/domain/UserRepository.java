/*
Name: 
    - UserRepository

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
 * Created on May 25, 2006
 */
package com.processpuzzle.application.security.domain;


import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.commons.persistence.PersistenceStrategy;
import com.processpuzzle.commons.persistence.Repository;
import com.processpuzzle.commons.persistence.RepositoryResultSet;
import com.processpuzzle.commons.persistence.UnitOfWork;
import com.processpuzzle.commons.persistence.query.BooleanOperator;
import com.processpuzzle.commons.persistence.query.BooleanOperators;
import com.processpuzzle.commons.persistence.query.Query;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.domain.GenericRepository;
import com.processpuzzle.persistence.query.domain.ComparisonOperators;
import com.processpuzzle.persistence.query.domain.DefaultQuery;
import com.processpuzzle.persistence.query.domain.IntegerAttributeCondition;
import com.processpuzzle.persistence.query.domain.TextAttributeCondition;

/**
 * @author zsolt.zsuffa
 */
public class UserRepository extends GenericRepository<User> implements Repository<User> {

   public UserRepository( PersistenceStrategy strategy, ProcessPuzzleContext applicationContext ) {
      super( strategy, applicationContext );
   }

   public Integer add( User aUser ) {
      UnitOfWork work = new DefaultUnitOfWork( true );
      Integer id = addUser( work, aUser );
      work.finish();
      return id;
   }

   public Integer addUser( UnitOfWork work, User aUser ) {
      return super.add( work, aUser );
   }

   public void updateUser( UnitOfWork work, User aUser ) {
      super.update( work, aUser );
   }

   public void deleteUser( UnitOfWork work, User aUser ) {
      if( aUser != null )
         super.delete( work, aUser );
   }

   public User findUserById( UnitOfWork work, Integer id ) {
      return (User) findById( work, id );
   }

   public User findUserByName( String userName ) {
      DefaultUnitOfWork findWork = new DefaultUnitOfWork( true );
      User user = findUserByName( findWork, userName );
      findWork.finish();
      return user;
   }

   public User findUserByName( UnitOfWork work, String userName ) {
      Query query = new DefaultQuery( User.class );
      query.getQueryCondition().addAttributeCondition( new TextAttributeCondition( "userName", userName, ComparisonOperators.EQUAL_TO ) );
      RepositoryResultSet<User> results = super.findByQuery( work, query );
      if( results.size() == 1 )
         return results.getEntityAt( 0 );
      else
         return null;
   }

   public User findAnonymousUser() {
      return findUserByName( PredefinedUser.ANONYMOUS.getUserName() );
   }

   public SystemAdministrator findSystemAdministrator() {
      return (SystemAdministrator) findUserByName( PredefinedUser.SYSTEM_ADMINISTRATOR.getUserName() );
   }

   public RepositoryResultSet<User> findAllUser( UnitOfWork work ) {
      return findAll( work );
   }

   public RepositoryResultSet<User> findUserWhoHasAccessTo( UnitOfWork work, AccessControlledObject controlledObject ) {
      // TODO Auto-generated method stub
      return null;
   }

   public RepositoryResultSet<User> findUserByAccessRightName( UnitOfWork work, String acObjectName ) {
      DefaultQuery q = new DefaultQuery( AccessRight.class );
      q.getQueryCondition().addAttributeCondition( new TextAttributeCondition( "controlledObjectName", acObjectName, ComparisonOperators.EQUAL_TO ) );
      q.getQueryCondition().addAttributeCondition( new IntegerAttributeCondition( "id", ComparisonOperators.IS_NOT_NULL ) );
      q.getQueryCondition().addBooleanOperator( new BooleanOperator( BooleanOperators.AND ) );
      return findByQuery( work, q );
   }

   // public User findUserByAccessRightId( UnitOfWork work,Integer id ){
   // AccessRight right=(AccessRight)findExternalById(work, AccessRight.class, id );
   // if (right!=null)
   // return right.getTargetUser();
   // else return null;
   //		   
   // }
   // public AccessRight getRightByUserAndAccessControlledObject(String userId, String accessControlledObjectName) {
   // return (AccessRight) super.findByQuery("from AccessRight _right where _right.id is not null and " +
   // "_right.targetUser.id = '" + userId + "' and _right.controlledObjectName = '"
   // + accessControlledObjectName + "'");
   // }
   //   
   // public DefaultAccessRight getRight(String id) {
   // return (DefaultAccessRight) super.findById(new Integer(id));
   // }
   //
}
