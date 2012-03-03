package com.processpuzzle.address.domain;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import hu.itkodex.litest.template.RepositoryTestTemplate;

import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.address.domain.Country;
import com.processpuzzle.address.domain.CountryRepository;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class CountryRepositoryTest extends RepositoryTestTemplate<CountryRepository, CountryRepositoryTestFixture, Country> {

   public CountryRepositoryTest() {
      super( DomainTierTestConfiguration.FIXTURE_CONTAINER_DEFINITION_PATH );
   }

   @Override
   @Test
   public void testAdd_ForOwnedAttributesAndComponents() {
      assertThat( (String) databaseSpy.retrieveColumnFromRow( CountryRepositoryTestFixture.COUNTRY_TABLE_NAME, templatedFixture.getItaly().getId(), String.class, "name" ), equalTo( templatedFixture.getItaly().getName() ) );
   }

   
   @Override
   public void testAdd_ForReferencedAggregateRoots() {
      Integer countryId = (Integer) databaseSpy.retrieveColumnFromRow( CountryRepositoryTestFixture.COUNTRY_TABLE_NAME, templatedFixture.getItaly().getId(), Integer.class, "ID" );
      assertThat( templatedFixture.getItaly().getId(), equalTo( countryId ) );
      assertThat( (Integer) databaseSpy.retrieveColumnFromRow( CountryRepositoryTestFixture.SETTLEMENT_TABLE_NAME, templatedFixture.getMilano().getId(), Integer.class, "COUNTRY_ID" ), equalTo( templatedFixture.getItaly().getId() ));

      Set<Settlement> settlements = templatedFixture.getItaly().getSettlements();
      assertThat( "From country settlements are also accessible by lazy loading.", settlements.size(), equalTo( 2 ) );
   }

   @Test
   public void createCountryWithSettlementsAndZipcodes() {
      // SETUP :
      // create country
      Country country = templatedFixture.getCountryFactory().createCountry( CountryRepositoryTestFixture.COUNTRY_HUNGARY );
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      Integer countryId = sut.add( work, country );

      // create settlement and add to country
      Settlement settlement1 = templatedFixture.getSettlementFactory().createSettlement( CountryRepositoryTestFixture.SETTLEMENT_NAME_BUDAPEST, country );
      templatedFixture.getSettlementFactory().createDistrict( "XX.", settlement1 );
      templatedFixture.getSettlementRepository().addSettlement( work, settlement1 );

      // create settlement and add to country
      Settlement settlement2 = templatedFixture.getSettlementFactory().createSettlement( CountryRepositoryTestFixture.SETTLEMENT_NAME_CEGLED, country );
      templatedFixture.getZipCodeFactory().createZipCode( new Integer( "2700" ), settlement2 );
      templatedFixture.getSettlementRepository().addSettlement( work, settlement2 );

      // EXERCISE :
      work.finish();

      // VERIFY :
      work = new DefaultUnitOfWork( true );
      country = sut.findCountryByName( "Magyarország" );
      Set<Settlement> settlements = country.getSettlements();

      assertThat( settlements, notNullValue() );
      assertThat( settlements.size(), is( 2 ) );
      assertThat( (Integer) databaseSpy.retrieveColumnFromRow( CountryRepositoryTestFixture.COUNTRY_TABLE_NAME, countryId, Integer.class, "ID" ), equalTo( countryId ) );

      for( Settlement settlement : settlements ){
         assertThat( (Integer) databaseSpy.retrieveColumnFromRow( CountryRepositoryTestFixture.SETTLEMENT_TABLE_NAME, settlement.getId(), Integer.class, "COUNTRY_ID" ), equalTo( countryId ) );
         settlement = templatedFixture.getSettlementRepository().findSettlementById( work, settlement.getId() );
         for( ZipCode zip : settlement.getZipCodes() ){
            assertThat( (Integer) databaseSpy.retrieveColumnFromRow( CountryRepositoryTestFixture.ZIPCODE_TABLE_NAME, zip.getId(), Integer.class, "SETTLEMENT_ID" ), equalTo( settlement.getId() ) );
         }
         for( District district : settlement.getDistricts() ){
            assertThat( (Integer) databaseSpy.retrieveColumnFromRow( CountryRepositoryTestFixture.DISTRICT_TABLE_NAME, district.getId(), Integer.class, "SETTLEMENT_ID" ), equalTo( settlement.getId() ) );
            for( ZipCode zip : district.getZipCodes() ){
               assertThat( (Integer) databaseSpy.retrieveColumnFromRow( CountryRepositoryTestFixture.ZIPCODE_TABLE_NAME, zip.getId(), Integer.class, "DISTRICT_ID" ), equalTo( district.getId() ) );
            }
         }
      }
      work.finish();

      // TEARDOWN
      work = new DefaultUnitOfWork( true );
      templatedFixture.getSettlementRepository().deleteSettlement( work, settlement1 );
      templatedFixture.getSettlementRepository().deleteSettlement( work, settlement2 );
      sut.deleteCountry( work, country );
      work.finish();

   }

   @Override
   public void testDelete_ForOwnedAttributesAndComponents() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testFindAll_ForResultCount() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testFindById() {
   // TODO Auto-generated method stub

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
   public void testFindByQuery_ForComponentAttributes() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testFindByQuery_ForDirectAttributes() {
   // TODO Auto-generated method stub

   }

   @Ignore
   @Override
   public void testUpdate_ForOwnedAttributesAndComponents() {
   // TODO Auto-generated method stub

   }

   @Ignore
   @Override
   public void testUpdate_ForReferencedAggregateRoots() {
   // TODO Auto-generated method stub

   }

   // private Country country;
   //
   // public CountryRepositoryTest() {
   // super(OPConfigurationFixture.getInstance());
   // }
   //
   // @Before
   // public void setUp() throws Exception {
   // super.setUp();
   // UnitOfWork work = new UnitOfWork(true);
   // country = CountryFactory.createCountry("Hungary");
   // OPConfigurationFixture.getCountryRepository().addCountry(work, country);
   // work.finish();
   // }
   //
   // @After
   // public void tearDown() throws Exception {
   // UnitOfWork work = new UnitOfWork(true);
   // OPConfigurationFixture.getCountryRepository().deleteCountry(work, country);
   // work.finish();
   // super.tearDown();
   // }
   //
   // @Ignore
   // @Test
   // public void testAddCountry() {
   // assertNotNull(country.getId());
   // }
   //
   // @Ignore
   // @Test
   // public void testFindByIdentityExpression() {
   // UnitOfWork work = new UnitOfWork(true);
   // QueryContext context = new QueryContext();
   // context.addTextValueFor("name", "Hungary");
   // assertNotNull(OPConfigurationFixture.getCountryRepository().findByIdentityExpression(work, Country.getDefaultIdentity(context)));
   // assertEquals(country.getId(), ((Country) OPConfigurationFixture.getCountryRepository().findByIdentityExpression(work,
   // Country.getDefaultIdentity(context))).getId());
   // work.finish();
   // }
   //
   // @Ignore
   // @Test
   // public void testFindById() {
   // UnitOfWork work = new UnitOfWork(true);
   // assertNotNull(OPConfigurationFixture.getCountryRepository().findById(work, country.getId()));
   // assertEquals(country.getId(), ((Country) OPConfigurationFixture.getCountryRepository().findById(work, country.getId())).getId());
   // work.finish();
   // }
   //
   // @Ignore
   // @Test
   // public void testAddTwoCountryWhenThoseNameAreNotSame() {
   // UnitOfWork work = new UnitOfWork(true);
   // Country country1 = new Country("country1");
   // OPConfigurationFixture.getCountryRepository().addCountry(work, country1);
   // assertNotNull(country1.getId());
   // assertEquals(OPConfigurationFixture.getCountryRepository().findAllCountry(work).size(), 2);
   // OPConfigurationFixture.getCountryRepository().deleteCountry(work, country1);
   // work.finish();
   // }
   //
   // @Ignore
   // @Test
   // public void testAddTwoCountryWhenThoseNameAreSame() {
   // UnitOfWork work = new UnitOfWork(true);
   // Country country1 = new Country("country1");
   // try {
   // OPConfigurationFixture.getCountryRepository().addCountry(work, country1);
   // } catch (Exception e) {
   // assertNull(country1.getId());
   // assertEquals(OPConfigurationFixture.getCountryRepository().findAllCountry(work).size(), 1);
   // }
   // OPConfigurationFixture.getCountryRepository().deleteCountry(work, country1);
   // work.finish();
   // }
   //
   // @Ignore
   // @Test
   // public void testUpdateCountry() {
   // UnitOfWork work = new UnitOfWork(true);
   // QueryContext context = new QueryContext();
   // country.setName("country1");
   // context.addTextValueFor("name", country);
   // OPConfigurationFixture.getCountryRepository().updateCountry(work, country);
   // assertNotNull(OPConfigurationFixture.getCountryRepository().findByIdentityExpression(work, Country.getDefaultIdentity(context)));
   // work.finish();
   // }
   //
   // @Ignore
   // @Test
   // public void testUpdateCountryAfterAddingASettlement() {
   // UnitOfWork work = new UnitOfWork(true);
   // Settlement settlement = SettlementFactory.createSettlement("settlement");
   // OPConfigurationFixture.getSettlementRepository().addSettlement(work, settlement);
   // assertNotNull(settlement.getId());
   // country.addSettlement(settlement);
   // try {
   // OPConfigurationFixture.getCountryRepository().updateCountry(work, country);
   // assertNotNull(OPConfigurationFixture.getCountryRepository().findById(work, country.getId()).getSettlements());
   // OPConfigurationFixture.getSettlementRepository().updateSettlement(work, settlement);
   // assertNotNull(OPConfigurationFixture.getSettlementRepository().findSettlementById(work, settlement.getId()).getCountry());
   // } catch (Exception e) {
   // assertNull(OPConfigurationFixture.getCountryRepository().findById(work, country.getId()).getSettlements());
   // }
   // country.setSettlements(null);
   // OPConfigurationFixture.getCountryRepository().updateCountry(work, country);
   // OPConfigurationFixture.getSettlementRepository().deleteSettlement(work, settlement);
   // work.finish();
   // }
   //
   // @Ignore
   // @Test
   // public void testFindAllCountry() {
   // UnitOfWork work = new UnitOfWork(true);
   // Country country1 = new Country("country1");
   // OPConfigurationFixture.getCountryRepository().addCountry(work, country1);
   // assertEquals(2, OPConfigurationFixture.getCountryRepository().findAllCountry(work).size());
   // OPConfigurationFixture.getCountryRepository().deleteCountry(work, country1);
   // work.finish();
   // }

}
