package com.processpuzzle.party.domain;

import hu.itkodex.litest.template.DomainObjectTestEnvironment;
import hu.itkodex.litest.template.DomainObjectTestFixture;

import java.util.Date;

import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.application.security.domain.UserFactory;
import com.processpuzzle.fundamental_types.domain.TimePoint;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;

public class PersonTestFixture extends DomainObjectTestFixture<Person> {
   private static PersonFactory personFactory;
   private static PersonRepository personRepository;
   private static UserFactory userFactory;
   private static Person beno = null;

   protected PersonTestFixture( DomainObjectTestEnvironment<Person, ?> testEnvironment ) {
      super( testEnvironment );
   }

   //Properties
   public PersonRepository getPersonRepository() { return personRepository; }
   
   @Override
   protected void configureAfterSutInstantiation() {
   }

   @Override
   protected void configureBeforeSutInstantiation() {
      super.configureBeforeSutInstantiation();
      userFactory = mockApplicationContext.getEntityFactory( UserFactory.class );
      personFactory = mockApplicationContext.getEntityFactory( PersonFactory.class );
      personRepository = mockApplicationContext.getRepository( PersonRepository.class );
   }

   @Override
   protected Person instantiateSUT() {
      beno = createTestPerson();
      return beno;
   }

   @Override
   protected void releaseResources() { }

   private static Person createTestPerson() {
      beno = personFactory.create( "Benõ", "Bárczi", new TimePoint( new Date( System.currentTimeMillis() ) ) );
      User user = userFactory.createUser( "barczi.beno", "hello" );
      beno.setSystemUser( user );
      
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      personRepository.addPerson( work, beno );
      work.finish();
      
      return beno;
   }
}
