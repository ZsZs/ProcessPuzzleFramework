package com.processpuzzle.party.domain;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Timestamp;
import java.util.List;

import org.junit.Test;

import com.processpuzzle.commons.persistence.RepositoryResultSet;
import com.processpuzzle.commons.rdbms.NoDataAvailableException;
import com.processpuzzle.fundamental_types.domain.TimePeriod;
import com.processpuzzle.fundamental_types.domain.TimePoint;
import com.processpuzzle.litest.template.RepositoryTestTemplate;
import com.processpuzzle.party.partytype.domain.PartyType;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

//Tests Party aggregation root's persistence related functionality.
//Doesn't deal with transient, domain logic specific stuff.
public class PartyRepositoryTest extends RepositoryTestTemplate<PartyRepository, PartyRepositoryTestFixture, Party<?>> {

   public PartyRepositoryTest() {
      super( DomainTierTestConfiguration.FIXTURE_CONTAINER_DEFINITION_PATH );
   }

   @Override
   @Test
   public void testAdd_ForOwnedAttributesAndComponents() {
      assertThat( databaseSpy.retrieveColumnFromRow( "T_PARTY_NAME", root.getPartyName().getId(), String.class, "name" ), equalTo( root.getPartyName().getName() ) );
   }

   @Override
   @Test
   public void testAdd_ForReferencedAggregateRoots() {}

   @Override
   @Test
   public void testDelete_ForOwnedAttributesAndComponents() {
      Party<?> testParty = fixture.createTestPerson();
      Integer partyNameForeignKey = testParty.getPartyName().getId();

      DefaultUnitOfWork deleteWork = new DefaultUnitOfWork( true );
      repository.delete( deleteWork, testParty );
      deleteWork.finish();

      try{
         databaseSpy.retrieveColumnFromRow( "T_PARTY_NAME", partyNameForeignKey, String.class, "name" );
         fail();
      }catch( NoDataAvailableException e ){
         assertTrue( "Party name is deleted", true );
      }
   }

   @Test
   public void findPartyByPartySummary() {
      // SETUP :
      PartySummary partySummary = fixture.getPartySummaryFactory().createPartySummary( root );

      // EXERCISE

      Party<?> party = repository.findPartyByPartySummary( partySummary );

      // VERIFY
      assertThat( party, notNullValue() );

      // TEARDOWN
   }

   @Test
   public void findPartiesByPartyName() {
      findPersonByPartyName();
      findCompanyByPartyName();
      findCompanyByShort();
   }

   private void findCompanyByPartyName() {
      String companyShortName = "IT K�dex Kft.";
      String organisationName = "IT K�dex Sz�m�t�stechnikai Tan�csad� �s Szolg�ltat� Kft.";
      Party<?> createdCompany = fixture.createTestCompany( organisationName, companyShortName );

      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      List<Party> parties = repository.findPartiesByPartyName( work, organisationName );

      assertThat( parties, notNullValue() );
      assertThat( parties.size(), is( 1 ) );

      Party<?> foundCompanyAsParty = parties.get( 0 );

      assertThat( foundCompanyAsParty, notNullValue() );
      assertThat( foundCompanyAsParty.getName(), equalTo( organisationName ) );

      work.finish();

      work = new DefaultUnitOfWork( true );
      repository.delete( work, createdCompany );
      work.finish();

   }

   private void findCompanyByShort() {
      String companyShortName = "IT Kódex Kft.";
      String organisationName = "IT Kódex Számítástechnikai Tanácsadó és Szolgáltató Kft.";
      Party<?> createdCompany = fixture.createTestCompany( organisationName, companyShortName );

      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      List<Company> companies = fixture.getCompanyRepository().findCompaniesByShortName( work, companyShortName );
      Company foundCompanyAsCompany = (companies != null && companies.size() > 0) ? companies.get( 0 ) : null;

      assertThat( foundCompanyAsCompany, notNullValue() );
      assertThat( foundCompanyAsCompany.getPartyName().getName(), equalTo( organisationName ) );
      assertThat( foundCompanyAsCompany.getShortName(), equalTo( companyShortName ) );

      work.finish();

      work = new DefaultUnitOfWork( true );
      repository.delete( work, createdCompany );
      work.finish();

   }

   private void findPersonByPartyName() {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      String expectedPartyName = "B�rczi Ben�";
      List<Party> parties = repository.findPartiesByPartyName( work, expectedPartyName );
      work.finish();

      assertThat( parties, notNullValue() );
      assertThat( parties.size(), is( 1 ) );
   }

   @Test
   public void testFindByPartyTypeName() {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      String partyTypeName = root.getType().getName();
      RepositoryResultSet<Person> result = fixture.getPersonRepository().findByPartyTypeName( work, partyTypeName );
      Person p = (Person) result.iterator().next();
      assertEquals( "Person found by PartyType name", root.getId(), p.getId() );
      work.finish();
   }

   @Override
   @Test
   public void testFindAll_ForResultCount() {
      assertEquals( 1, repository.findAll( testWork ).size() );
   }

   @Override
   @Test
   public void testFindById() {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      Person savedPerson = fixture.getPersonRepository().findById( work, root.getId() );

      assertThat( savedPerson.getId(), equalTo( root.getId() ) );
      assertThat( savedPerson.getPartyName(), notNullValue() );
      assertThat( savedPerson.getPersonName().getName(), equalTo( root.getPartyName().getName() ) );
      work.finish();

   }

   @Override
   public void testFindById_ForEagerLoadedComponents() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testFindById_ForLazyLoadedComponents() {
   // TODO Auto-generated method stub

   }

   @Override
   @Test
   public void testFindByQuery_ForComponentAttributes() {}

   @Override
   public void testFindByQuery_ForDirectAttributes() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testUpdate_ForOwnedAttributesAndComponents() {
      root.renameName( "newname" );
      root.setValid( new TimePeriod( new TimePoint( 2008, 1, 1 ), new TimePoint( 2008, 12, 31 ) ) );
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      repository.update( work, root );

      assertEquals( "New name is updated", root.getName(), repository.findById( work, root.getId() ).getName() );
      work.finish();

      assertThat( "Valid TimePeriod is updated", databaseSpy.retrieveColumnFromRow( "T_PARTY", root.getId(), Timestamp.class, "validBegin" ),
            equalTo( new Timestamp( root.getValid().getBegin().getValue().getTime() ) ) );
      assertThat( "Valid TimePeriod is updated", databaseSpy.retrieveColumnFromRow( "T_PARTY", root.getId(), Timestamp.class, "validEnd" ),
            equalTo( new Timestamp( root.getValid().getEnd().getValue().getTime() ) ) );

   }

   @Override
   @Test
   public void testUpdate_ForReferencedAggregateRoots() {

      // SETUP
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      PartyType type = new PartyType( "OtherType" );
      fixture.getPartyTypeRepository().add( work, type );

      // EXERCISE
      // root.setPartyType( type );
      repository.update( work, root );
      work.finish();

      // VERIFY
      assertEquals( "PartyType is updated", root.getType().getName(), databaseSpy.retrieveColumnFromRow( "T_PARTY_TYPE", root.getType().getId(), String.class, "name" ) );

      // TEARDOWN
      DefaultUnitOfWork work2 = new DefaultUnitOfWork( true );
      // root.setPartyType( partyType );
      repository.update( work2, root );
      fixture.getPartyTypeRepository().delete( work2, type );
      work2.finish();
   }

   // private static PartyRepository partyRep;
   // private Person person;
   // private static ZipCodeRepository zipCodeRepository;
   // private static CountryRepository countryRepository;
   // private static SettlementRepository settlementRepository;
   //
   // static {
   // ProcessPuzzleContext config = ProcessPuzzleContext.getInstance();
   // try {
   // config.setUp( ConfigurationConstants.CONFIGURATION_PROPERTY_FILE );
   // } catch (ConfigurationSetUpException e) {
   // e.printStackTrace();
   // }
   // settlementRepository = (SettlementRepository)
   // ObjectPuzzleConfiguration.getInstance().getRepository(SettlementRepository.class);
   // zipCodeRepository = (ZipCodeRepository)
   // ObjectPuzzleConfiguration.getInstance().getRepository(ZipCodeRepository.class);
   // countryRepository = (CountryRepository)
   // ObjectPuzzleConfiguration.getInstance().getRepository(CountryRepository.class);
   // partyRep = (PartyRepository)
   // ObjectPuzzleConfiguration.getInstance().getRepository(PartyRepository.class);
   // }

   // @Before
   // public void setUp() throws Exception {
   // UnitOfWork work = new UnitOfWork(true);
   // person = new Person();
   // PersonName name = new PersonName("Pista", "Kiss");
   // EmailAddress address = new EmailAddress("pista.kiss@gmail.com");
   // person.setPartyName(name);
   // person.getAddresses().add(address);
   // partyRep.addPerson(work, person);
   // work.finish();
   // }
   //
   // @After
   // public void tearDown() throws Exception {
   // UnitOfWork work = new UnitOfWork(true);
   // partyRep.deletePerson(work, person.getId().toString());
   // work.finish();
   // }
   //
   // @Test
   // // Person
   // public void testAddPerson() {
   // UnitOfWork work = new UnitOfWork(true);
   // assertEquals("The person's name is Kiss Pista.",
   // partyRep.findPersonById(work, person.getId()).getPartyName().getName(),
   // "Kiss Pista");
   // work.finish();
   // }

   // @Test
   // public void testUpdatePerson() {
   // UnitOfWork work = new UnitOfWork(true);
   // EmailAddress address = new EmailAddress("pista.kiss@gmail.com1");
   // person.getAddresses().add(address);
   // partyRep.updatePerson(work, person);
   // assertEquals("The person's name is Kiss Pista.",
   // partyRep.findPersonById(work, person.getId()).getAddresses().size(), 2);
   // work.finish();
   // }
   //
   // @Test
   // public void testGetPerson() {
   // UnitOfWork work = new UnitOfWork(true);
   // Person person1 = partyRep.findPersonById(work, person.getId().toString());
   // assertNotNull("shouldn't be null", person1);
   // assertEquals("this person is the same, as the one saved in
   // testAddPerson()", person.getPartyName().getName(), person1.getPartyName()
   // .getName());
   // assertTrue("address association was cascaded. person(with lazy loading set
   // to false) should contain it", !person1.getAddresses()
   // .isEmpty());
   // Iterator it = person1.getAddresses().iterator();
   // Address address = (Address) it.next();
   // assertTrue("polimorfism should work", address instanceof EmailAddress);
   // work.finish();
   // }

   // @Ignore
   // @Test
   // public void testUpdatePerson1() {
   // UnitOfWork work = new UnitOfWork(true);
   // PersonName name = new PersonName("Istv�n", "Kiss");
   // person.setPartyName(name);
   // partyRep.updatePerson(work, person);
   //
   // Person person1 = partyRep.findPersonById(work, person.getId().toString());
   // assertEquals("person now has a different name",
   // person1.getPartyName().getName(), " Istv�n");
   // work.finish();
   // }

   // @Ignore
   // @Test
   // public void testDeletePerson() {
   // UnitOfWork work = new UnitOfWork(true);
   // partyRep.deletePerson(work, person.getId().toString());
   // Person person1 = partyRep.findPersonById(work, person.getId().toString());
   // assertNull("there is no such id in the database", person1);
   // Iterator it = person.getAddresses().iterator();
   // Address address = (Address) it.next();
   // //Address address1 = partyRep.findAddressById(work, new
   // Integer(address.getId().toString()));
   // //assertNull("cascaded delete should work, so database integrity is ok",
   // address1);
   // work.finish();
   // }

   // Division
   /*
    * public void testAddDivision() { OrganizationName name = new OrganizationName("The Division"); WebPageAddress address = new
    * WebPageAddress("www.thedepartment.com"); Set otherNames = new HashSet(); otherNames.add("The Division2"); otherNames.add("The Division3"); Division
    * division = new Division(); division.setOrganizationName(name); division.setOtherNames(otherNames); division.getAddresses().add(address);
    * partyRep.addDivision(division); }
    */
   // Company
   /*
    * public void testAddCompany() { OrganizationName name = new OrganizationName("The Company"); Set softwareSystems = new HashSet();
    * softwareSystems.add("SoftwareSystem1"); softwareSystems.add("SoftwareSyetem2"); Company company = new Company(); company.setOrganizationName(name);
    * company.setSoftwareSystems(softwareSystems); partyRep.addCompany(company); }
    */
   /*
    * public void testGetCompany() { Company company1 = partyRep.findCompanyById(company.getId().toString()); assertEquals("it must be the same Company",
    * company1.getName(), company.getName()); assertNotNull("softwareSystems collection is not lazy loaded", company1.getSoftwareSystems());
    * assertEquals("it contains two systems", company1.getSoftwareSystems().size(), 2); } public void testUpdateCompany() { company.setOrganizationName(new
    * OrganizationName("Different Name")); Set softwareSystems = company.getSoftwareSystems(); softwareSystems.remove("SoftwareSystem2");
    * partyRep.updateCompany(company); Company company1 = partyRep.findCompanyById(company.getId().toString()); assertEquals("Company has a different name",
    * company1.getPartyName().getName(), "Different Name"); assertEquals("1 softwareSystem left", company1.getSoftwareSystems().size(), 2); } public void
    * testDeleteCompany() { partyRep.deleteCompany(company); Company company1 = partyRep.findCompanyById(company.getId().toString());
    * assertNull("it was deleted", company1); //Ilyenkor marad a softwaresystem? }
    */

   // @Ignore
   // @Test
   // public void testGeographicAddress() {
   // UnitOfWork work = new UnitOfWork(true);
   // Settlement settlement = SettlementFactory.createSettlement("settlement");
   // settlementRepository.addSettlement(work, settlement);
   // Country country = CountryFactory.createCountry("country");
   // country.addSettlement(settlement);
   // countryRepository.addCountry(work, country);
   // ZipCode zipCode = new ZipCode(new Integer(1));
   // settlement.addZipCode(zipCode);
   // zipCodeRepository.addZipCode(work, zipCode);
   // settlementRepository.updateSettlement(work, settlement);
   // GeographicAddress geographicAddress =
   // AddressFactory.createGeographicAddress("street", "1", zipCode,
   // settlement);
   // //partyRep.addGeographicAddress(work, geographicAddress);
   // assertNotNull(geographicAddress.getId());
   // //GeographicAddress ga1 = (GeographicAddress)
   // partyRep.findAddressById(work, geographicAddress.getId());
   // //assertNotNull(ga1);
   // //assertEquals(ga1.getZipCode().getId(), zipCode.getId());
   // //assertEquals(ga1.getSettlement().getId(),
   // zipCode.getSettlement().getId());
   // //assertEquals(ga1.getStreet(), "street");
   // //assertEquals(ga1.getBuildingNumber(), "1");
   // //partyRep.deleteAddress(work, geographicAddress.getId());
   // settlement.setCountry(null);
   // settlement.setZipCodes(null);
   // settlementRepository.updateSettlement(work, settlement);
   // countryRepository.deleteCountry(work, country);
   // zipCode.setSettlement(null);
   // zipCodeRepository.deleteZipCode(work, zipCode);
   // settlementRepository.deleteSettlement(work, settlement);
   // work.finish();
   // }
}
