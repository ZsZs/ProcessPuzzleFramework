package com.processpuzzle.party.domain;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.processpuzzle.address.domain.Country;
import com.processpuzzle.address.domain.CountryFactory;
import com.processpuzzle.address.domain.Settlement;
import com.processpuzzle.address.domain.SettlementFactory;
import com.processpuzzle.address.domain.ZipCode;
import com.processpuzzle.address.domain.ZipCodeFactory;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.domain.ConfigurableApplicationFixture;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class AddressFactoryTest {
   private AddressFactory addressFactory;
   private static ProcessPuzzleContext applicationContext;
   private static ConfigurableApplicationFixture applicationFixture;
   private CountryFactory countryFactory;
   private SettlementFactory settlementFactory;
   private ZipCodeFactory zipCodeFactory;

   @BeforeClass
   public static void beforeAllTests() {
      applicationFixture = new ConfigurableApplicationFixture( DomainTierTestConfiguration.APPLICATION_CONFIGURATION_DESCRIPTOR_PATH );
      applicationFixture.setUp();
      
      applicationContext = applicationFixture.getApplicationContext();
   }

   @Before
   public void beforeEachTest() {
      addressFactory = applicationContext.getEntityFactory( AddressFactory.class );
      countryFactory = applicationContext.getEntityFactory( CountryFactory.class );
      settlementFactory = applicationContext.getEntityFactory( SettlementFactory.class );
      zipCodeFactory = applicationContext.getEntityFactory( ZipCodeFactory.class );
   }
   
   @AfterClass public static void afterAllTests(){
      applicationFixture.tearDown();
   }

   @Test
   public void testCreateGeographicAddress() {
      Country country = countryFactory.createCountry( "country" );
      Settlement settlement = settlementFactory.createSettlement( "settlement", country );
      ZipCode zipCode = zipCodeFactory.createZipCode( new Integer( 1 ), settlement );
      GeographicAddress geographicAddress = addressFactory.createGeographicAddress( "street", "1/b", zipCode, settlement );
      assertThat( geographicAddress, notNullValue() );
      assertThat( geographicAddress.getSettlement(), equalTo( settlement ));
      assertThat( geographicAddress.getZipCode(), equalTo( zipCode ));
   }
}
