package com.processpuzzle.party.domain;

import hu.itkodex.commons.persistence.PersistenceStrategy;
import hu.itkodex.commons.persistence.RepositoryResultSet;
import hu.itkodex.commons.persistence.UnitOfWork;
import hu.itkodex.commons.persistence.query.OrderSpecifier;
import hu.itkodex.commons.persistence.query.OrderingDirections;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.persistence.domain.GenericRepository;
import com.processpuzzle.persistence.query.domain.ComparisonOperators;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;
import com.processpuzzle.persistence.query.domain.DefaultQuery;
import com.processpuzzle.persistence.query.domain.DefaultQueryContext;
import com.processpuzzle.persistence.query.domain.TextAttributeCondition;

public class PersonRepository extends GenericRepository<Person> {

   public PersonRepository( PersistenceStrategy strategy, ProcessPuzzleContext applicationContext ) {
      super( strategy, applicationContext );
   }

   public void addPerson( UnitOfWork work, Person thePerson ) {
      add( work, Person.class, thePerson );
   }

   public void deletePerson( UnitOfWork work, String pid ) {
      Person p = findPersonById( work, pid );
      if( p != null ){
         deletePerson( work, p );
      }
   }

   public void deletePerson( UnitOfWork work, Integer id ) {
      Person p = findPersonById( work, id );
      if( p != null ){
         deletePerson( work, p );
      }
   }

   public void deletePerson( UnitOfWork work, Person person ) {
      delete( work, Person.class, person );
   }

   public void updatePerson( UnitOfWork work, Person person ) {
      update( work, Person.class, person );
   }

   public RepositoryResultSet<Person> findAllPerson( UnitOfWork work ) {
      return findAll( work, Person.class );
   }

   public Person findByUserId( UnitOfWork work, String userId ) {
      DefaultQueryContext context = new DefaultQueryContext();
      context.addIntegerValueFor( "systemUserIdValue", new Integer( userId ) );
      PersonUserIdIdentity exp = new PersonUserIdIdentity( context );
      return (Person) findByIdentityExpression( work, exp );
   }

   public RepositoryResultSet<Person> findByPartyTypeName( UnitOfWork work, String name ) {
      DefaultQuery query = new DefaultQuery( Person.class );
      query.getQueryCondition().addAttributeCondition( new TextAttributeCondition( "type.name", name, ComparisonOperators.EQUAL_TO ) );
      return findByQuery( work, query );
   }

   public Person findPersonById( UnitOfWork work, String id ) {
      return findPersonById( work, new Integer( id ) );
   }

   public Person findPersonById( UnitOfWork work, Integer id ) {
      return (Person) findById( work, Person.class, id );
   }

   public Person findPersonByUserName( UnitOfWork work, String userName ) {
      DefaultQueryContext context = new DefaultQueryContext();
      context.addTextValueFor( "userNameValue", userName );
      DefaultIdentityExpression<Person> identityExpression = new PersonUsernameIdentity( context );
      return (Person) findByIdentityExpression( work, identityExpression );
   }

   public Person findPersonByUsernameAndPassword( UnitOfWork work, String userName, String password ) {
      DefaultQueryContext context = new DefaultQueryContext();
      context.addTextValueFor( "systemUser.userName", userName );
      context.addTextValueFor( "systemUser.password", password );
      DefaultIdentityExpression<Person> exp = new PersonUsernameAndPasswordIdentity( context );
      return (Person) findByIdentityExpression( work, exp );
   }

   public RepositoryResultSet<Person> findAllOrderedByPerson( UnitOfWork work ) {
      DefaultQuery query = new DefaultQuery( Person.class );
      query.getQueryOrder().addOrderSpecifier( new OrderSpecifier( "partyName.name", OrderingDirections.Ascending ) );
      return findByQuery( work, query );
   }

   public RepositoryResultSet<Person> findAllOrderedPerson( UnitOfWork work, String propertyName ) {
      DefaultQuery query = new DefaultQuery( Person.class );
      return findByQuery( work, query );
   }

}
