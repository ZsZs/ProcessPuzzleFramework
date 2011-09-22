package com.processpuzzle.party.domain;

import org.junit.BeforeClass;

import junit.framework.TestCase;

import com.processpuzzle.address.domain.Country;
import com.processpuzzle.address.domain.CountryFactory;
import com.processpuzzle.address.domain.Settlement;
import com.processpuzzle.address.domain.SettlementFactory;
import com.processpuzzle.address.domain.ZipCode;
import com.processpuzzle.address.domain.ZipCodeFactory;
import com.processpuzzle.application.configuration.domain.ConfigurationSetUpException;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.domain.Application;


public class AddressFactoryTest extends TestCase {
	private static AddressFactory addressFactory;
    private static CountryFactory countryFactory;
    private static SettlementFactory settlementFactory;
    private static ZipCodeFactory zipCodeFactory;
    
    @BeforeClass public static void beforeAllTests() {
        ProcessPuzzleContext applicationContext = ProcessPuzzleContext.getInstance();
        addressFactory = applicationContext.getEntityFactory( AddressFactory.class );
        countryFactory = applicationContext.getEntityFactory( CountryFactory.class );
        settlementFactory = applicationContext.getEntityFactory( SettlementFactory.class );
        zipCodeFactory = applicationContext.getEntityFactory(ZipCodeFactory.class);
        
		try {
           applicationContext.setUp( Application.Action.start );
      } catch (ConfigurationSetUpException e) {
         e.printStackTrace();
      }
	}
	
	public void testCreateGeographicAddress() {
		Country country = countryFactory.createCountry("country");
		Settlement settlement = settlementFactory.createSettlement("settlement", country);
		ZipCode zipCode = zipCodeFactory.createZipCode(new Integer(1), settlement);
		GeographicAddress geographicAddress = addressFactory.createGeographicAddress("street", "1/b", zipCode, settlement);
		assertNotNull(geographicAddress);
		assertEquals(settlement, geographicAddress.getSettlement());
		assertEquals(zipCode, geographicAddress.getZipCode());
	}
}
