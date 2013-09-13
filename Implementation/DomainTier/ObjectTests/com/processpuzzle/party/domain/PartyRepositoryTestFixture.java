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

public class PartyRepositoryTestFixture extends RepositoryTestFixture<PartyRepository, Party> {
   public static final String PARTY_TABLE_NAME = "T_PARTY";
   public static final String PARTY_TYPE_TABLE_NAME = "T_PARTY_TYPE";
   public static final String PARTY_ROLE_TYPE_TABLE_NAME = "T_PARTY_ROLE_TYPE";
   private PartyRoleTypeRepository roleTypeRepository = null;
   private PartyTypeRepository partyTypeRepository = null;
   private PersonFactory personFactory;
   private CompanyRepository companyRepository;
   private PersonRepository personRepository;
   private CompanyFactory companyFactory;
   private AddressFactory addressFactory;
   private PartySummaryFactory partySummaryFactory;
   private PartyType partyType;
   private PartyRoleType testRoleType;

   //Constructors
   protected PartyRepositoryTestFixture( RepositoryTestEnvironment<PartyRepository, RepositoryTestFixture<PartyRepository,Party>> testEnvironment) {
      super( testEnvironment );
   }

   //Public accessor and mutator methods
   public Party<?> createTestCompany( String companyOrganisationName, String companyShortName ) {
   
      Company testParty = companyFactory.create( companyOrganisationName );
      testParty.setShortName( companyShortName );
      
      EmailAddress email = addressFactory.createEmailAddress( "alam@korte.hu" );
      WebPageAddress webAddress = addressFactory.createWebPageAddress( "http://index.hu" );
      testParty.addAddress( email );
      testParty.addAddress( webAddress );
      
      DefaultUnitOfWork anotherWork = new DefaultUnitOfWork( true );
      repository.add( anotherWork, testParty );
      anotherWork.finish();
   
      return testParty;
   }

   public Party<?> createTestPerson() {
      Set<PartyRole> testPartyRoles = new HashSet<PartyRole>();
   
      Person testParty = personFactory.create( "L�nyai", "Alajos", "lbela", "lonyal" );
      // Person testParty = PartyFactory.createPerson("L�nyai", "Alajos", new TimePoint(1960,11,12));
   
      testPartyRoles.add( new PartyRole( "testRole2", testRoleType ) );
      addPartyRoles( testParty, testPartyRoles );
      testParty.setValid( new TimePeriod( new TimePoint( 2007, 1, 1 ), new TimePoint( 2007, 2, 1 ) ) );
   
      DefaultUnitOfWork anotherWork = new DefaultUnitOfWork( true );
      repository.add( anotherWork, testParty );
      anotherWork.finish();
   
      return testParty;
   }
   
   //Properties
   public CompanyRepository getCompanyRepository() { return companyRepository; }
   public PartySummaryFactory getPartySummaryFactory() { return partySummaryFactory; }
   public PartyTypeRepository getPartyTypeRepository() { return partyTypeRepository; }
   public PersonRepository getPersonRepository() { return personRepository; }
   
   //Protected, private helper mehtods
   @Override
   protected void configureBeforeSutInstantiation() {
      roleTypeRepository = applicationContext.getRepository( PartyRoleTypeRepository.class );
      partyTypeRepository = applicationContext.getRepository( PartyTypeRepository.class );

      companyFactory = applicationContext.getEntityFactory( CompanyFactory.class );
      addressFactory = applicationContext.getEntityFactory( AddressFactory.class );
      companyRepository = applicationContext.getRepository( CompanyRepository.class );
      personRepository = applicationContext.getRepository( PersonRepository.class );
      personFactory = applicationContext.getEntityFactory( PersonFactory.class );
      partySummaryFactory = applicationContext.getEntityFactory( PartySummaryFactory.class );
   }

   @Override
   protected Party<?> createNewAggregate() throws Exception {
      try{
         Party<?> testParty = personFactory.create( "Bárczi", "Benő", new TimePoint( 2008, 10, 1 ) );
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

   private void addPartyRoles( Party<?> party, Set<PartyRole> partyRoles ) {
      for( PartyRole partyRole : partyRoles ) {
         party.addPartyRole( partyRole );
      }
      
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
