/*
 * Created on May 25, 2006
 */
package com.processpuzzle.application.security.domain;

import hu.itkodex.commons.persistence.PersistenceStrategy;
import hu.itkodex.commons.persistence.Repository;
import hu.itkodex.commons.persistence.RepositoryResultSet;
import hu.itkodex.commons.persistence.UnitOfWork;
import hu.itkodex.commons.persistence.query.BooleanOperator;
import hu.itkodex.commons.persistence.query.BooleanOperators;
import hu.itkodex.commons.persistence.query.Query;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
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
