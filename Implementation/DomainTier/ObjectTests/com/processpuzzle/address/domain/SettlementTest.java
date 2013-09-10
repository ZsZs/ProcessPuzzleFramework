package com.processpuzzle.address.domain;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeThat;

import java.util.Set;

import org.junit.Test;

import com.processpuzzle.litest.template.DomainObjectTestTemplate;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class SettlementTest extends DomainObjectTestTemplate<Settlement, SettlementTestFixture> {

   public SettlementTest() {
      super( DomainTierTestConfiguration.FIXTURE_CONTAINER_DEFINITION_PATH );
   }

   @Test public void instantiation_Requires_SettlementName() {
      assertThat( sut.getName(), equalTo( SettlementTestFixture.SETTLEMENT_NAME ));
   }

   @Test public void addZipCode_MaintainsCollectionOfSettlements() {
      assumeThat( sut.getZipCodes().size(), equalTo( templatedFixture.getInitialNumberOfZipCodex() ));
      assumeThat( sut.getZipCodes(), hasItem( templatedFixture.getZipCode() ) );
      
      sut.addZipCode( templatedFixture.getOtherZipCode() );
      
      assertThat( sut.getZipCodes(), hasItem( templatedFixture.getOtherZipCode() ) );
      assertThat( sut.getZipCodes().size(), equalTo( templatedFixture.getInitialNumberOfZipCodex() +1 ) );
   }

   @Test( expected = AssertionError.class )
   public void addZipCode_WhenAlreadyAssociated_ThrowsException() {
      Set<ZipCode> zipCodes = sut.getZipCodes(); 
      assumeThat( zipCodes, hasItem( templatedFixture.getZipCode() ));
      
      sut.addZipCode( templatedFixture.getZipCode() );
   }
   
   /*   
   @Test
   public void getZipCode() {
      Settlement settlement = new Settlement( "settlement" );
      settlement.setCountry( countryFactory.createCountry( "country5" ) );
      ZipCode zipCode = new ZipCode( new Integer( 1 ) );
      ZipCode zipCode1 = new ZipCode( new Integer( 2 ) );
      settlement.addZipCode( zipCode );
      settlement.addZipCode( zipCode1 );
      
      ZipCode zip = settlement.getZipCode( new Integer( 1 ) );
      assertNotNull( zip );
   }
*/}
