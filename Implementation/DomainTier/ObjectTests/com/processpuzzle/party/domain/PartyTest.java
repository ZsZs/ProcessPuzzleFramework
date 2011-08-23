package com.processpuzzle.party.domain;

import hu.itkodex.litest.template.DomainObjectTestTemplate;

public class PartyTest extends DomainObjectTestTemplate<Party<?>, PartyTestFixture>{

   protected PartyTest( String fixtureContainerConfigurationPath ) {
      super( fixtureContainerConfigurationPath );
   }

   /*
   @Test
   public void testAddPerson() {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      Person beno = personFactory.create( "Benõ", "Bárczi", new TimePoint( new Date( System.currentTimeMillis() ) ) );
      personRepository.addPerson( work, beno );
      work.finish();
   }

   @Test
   public void addPersonAndSetUserName() {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      Person beno = personFactory.create( "Benõ", "Bárczi", new TimePoint( new Date( System.currentTimeMillis() ) ) );
      User user = userFactory.createUser( "barczi.beno", "hello" );
      beno.setSystemUser( user );
      personRepository.addPerson( work, beno );
      assertTrue( "The person have username with password.", personRepository.findPersonByUserName( work, "barczi.beno" ) != null );
      work.finish();
   }

   @Test
   public void testGetPartiesByRelationshipType() {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      PartyType company = partyTypeRepository.findByName( "CompanyType" );//partyTypeFactory.create( "CompanyType" );
      Company bank = companyFactory.create( "Bank" );
      companyRepository.addCompany( work, bank );

      PartyType organizationUnit = partyTypeRepository.findByName("OrganizationUnitType");//partyTypeFactory.create( "OrganizationUnit" );
      OrganizationUnit bankFiok1 = organizationUnitFactory.create( "Bank fiók1" );
      OrganizationUnit bankFiok2 = organizationUnitFactory.create( "Bank fiók2" );
      OrganizationUnit bankFiok3 = organizationUnitFactory.create( "Bank fiók3" );

      partyRepository.addOrganizationUnit( work, bankFiok1 );
      partyRepository.addOrganizationUnit( work, bankFiok2 );
      partyRepository.addOrganizationUnit( work, bankFiok3 );

      PartyRoleType companyRoleType = PartyRoleTypeFactory.create( "SuperiorOrganization", "Legal business entity.", company );
      PartyRoleType organizationUnitRoleType = PartyRoleTypeFactory.create( "InferiorOrganization", "Unit of an organization.", organizationUnit );

      partyRoleTypeRepository.addPartyRoleType( work, companyRoleType );
      partyRoleTypeRepository.addPartyRoleType( work, organizationUnitRoleType );

      PartyRelationshipType organizationHierarchy = PartyRelationshipTypeFactory.create( "Organization hierarchy", companyRoleType,
            organizationUnitRoleType );

      partyRelationshipTypeRepository.add( work, organizationHierarchy );

      PartyRelationshipFactory.createPartyRelationship( organizationHierarchy, bank, bankFiok1 );
      PartyRelationshipFactory.createPartyRelationship( organizationHierarchy, bank, bankFiok2 );
      PartyRelationshipFactory.createPartyRelationship( organizationHierarchy, bank, bankFiok3 );

      assertEquals( "The bank has got 3 branches.", 3, bank.getPartiesByRelationshipType( "Organization hierarchy" ).size() );
      assertEquals( "The bank has got branches, but the name was wrong!!!", 0, bank.getPartiesByRelationshipType( "Organization hierarch" )
            .size() );
      work.finish();
   }
   
   @Test
   public void getFaxNumber() {
      // SETUP :
      Person jakab = personFactory.create( "Buga", "Jakab", new TimePoint( new Date( System.currentTimeMillis() ) ) );
      AddressFactory addressFactory = applicationContext.getEntityFactory( AddressFactory.class );
      TelecomAddress telecomAddress = addressFactory.createTelecomAddress( "36", "28", "222-333" );
      telecomAddress.setUsedFor( TelecomAddress.USED_FOR_FAX );
      jakab.addAddress( telecomAddress );
      
     
      // EXERCISE :
      String faxNumber = jakab.getFaxNumber(); 
      
      // VERIFY:
      assertThat(faxNumber, notNullValue());
      
      String expectedFaxNumber = "+36 (28) 222-333";
      assertThat(faxNumber, notNullValue());
      assertThat(faxNumber, equalTo(expectedFaxNumber));
   }
   */
}
