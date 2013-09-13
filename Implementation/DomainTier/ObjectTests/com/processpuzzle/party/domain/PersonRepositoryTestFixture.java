package com.processpuzzle.party.domain;


import java.util.HashSet;
import java.util.Set;

import com.processpuzzle.fundamental_types.domain.TimePeriod;
import com.processpuzzle.fundamental_types.domain.TimePoint;
import com.processpuzzle.litest.template.RepositoryTestEnvironment;
import com.processpuzzle.litest.template.RepositoryTestFixture;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRoleType;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRoleTypeFactory;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRoleTypeRepository;
import com.processpuzzle.party.partytype.domain.PartyType;
import com.processpuzzle.party.partytype.domain.PartyTypeRepository;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;

public class PersonRepositoryTestFixture extends RepositoryTestFixture<PersonRepository, Person> {
   public static final String PARTY_TABLE_NAME = "T_PARTY";
   public static final String PARTY_TYPE_TABLE_NAME = "T_PARTY_TYPE";
   public static final String PARTY_ROLE_TYPE_TABLE_NAME = "T_PARTY_ROLE_TYPE";
   private PartyRoleTypeRepository roleTypeRepository;
   private PartyTypeRepository partyTypeRepository;
   private PersonFactory personFactory;
   private PartyRepository partyRepository;
   private PartyType partyType;
   private PartyRoleType testRoleType;

   protected PersonRepositoryTestFixture( RepositoryTestEnvironment<PersonRepository, RepositoryTestFixture<PersonRepository,Person>> testEnvironment ) {
      super( testEnvironment );
   }
   
   public void addPartyRoles( Party<?> party, Set<PartyRole> partyRoles ) {
      for( PartyRole partyRole : partyRoles ) {
         party.addPartyRole( partyRole );
      }
   }

   public Person createTestPerson() {
      Set<PartyRole> testPartyRoles = new HashSet<PartyRole>();
   
      Person testPerson = personFactory.create( "L�nyai", "Alajos", "lbela", "lonyal" );
      // Person testParty = PartyFactory.createPerson("L�nyai", "Alajos", new TimePoint(1960,11,12));
   
      testPartyRoles.add( new PartyRole( "testRole2", testRoleType ) );
      addPartyRoles( testPerson, testPartyRoles );
      testPerson.setValid( new TimePeriod( new TimePoint( 2007, 1, 1 ), new TimePoint( 2007, 2, 1 ) ) );
   
      DefaultUnitOfWork anotherWork = new DefaultUnitOfWork( true );
      repository.add( anotherWork, testPerson );
      anotherWork.finish();
   
      return testPerson;
   }

   //Properties
   public PartyRepository getPartyRepository() { return partyRepository; }
   public PartyTypeRepository getPartyTypeRepository() { return partyTypeRepository; }
   public PersonFactory getPersonFactory() { return personFactory; }

   @Override
   protected void configureAfterSutInstantiation() { }

   @Override
   protected void configureBeforeSutInstantiation() {
      roleTypeRepository = applicationContext.getRepository( PartyRoleTypeRepository.class );
      partyTypeRepository = applicationContext.getRepository( PartyTypeRepository.class );

      partyRepository = applicationContext.getRepository( PartyRepository.class );
      personFactory = applicationContext.getEntityFactory( PersonFactory.class );
   }

   @Override
   protected PersonRepository instantiateSUT() {
      return applicationContext.getRepository( PersonRepository.class );
   }

   @Override
   protected Person createNewAggregate() throws Exception {
      try{
         Person testParty = personFactory.create( "Bárczi", "Benő", new TimePoint( 2008, 10, 1 ) );
         Set<PartyRole> testPartyRoles = new HashSet<PartyRole>();

         testRoleType = PartyRoleTypeFactory.create( "testRoleType" );
         roleTypeRepository.add( setUpWork, testRoleType );

         partyType = new PartyType( "PersonType" );
         partyTypeRepository.add( setUpWork, partyType );

         testPartyRoles.add( new PartyRole( "testRole", testRoleType ) );
         addPartyRoles( testParty, testPartyRoles );
         testParty.setValid( new TimePeriod( new TimePoint( 2007, 1, 1 ), new TimePoint( 2007, 2, 1 ) ) );
         return testParty;
      }catch( Exception e ){
         e.printStackTrace();
      }
      return null;
   }

   @Override
   protected void releaseResources() {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      partyTypeRepository.delete( work, partyType );
      roleTypeRepository.delete( work, testRoleType );
      work.finish();
   }

   @Override
   protected void afterAggregateCreation() {
   }

   @Override
   protected void afterAggregateDeletion() {
   }

   @Override
   protected void beforeAggregateCreation() {
   }

}
