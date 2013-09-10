package com.processpuzzle.address.domain;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.address.domain.AlreadyExistingSettlementInCountryException;
import com.processpuzzle.address.domain.Country;
import com.processpuzzle.litest.template.DomainObjectTestTemplate;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class CountryTest extends DomainObjectTestTemplate<Country, CountryTestFixture> {

   public CountryTest() {
      super( DomainTierTestConfiguration.FIXTURE_CONTAINER_DEFINITION_PATH );
   }
   
   @Test 
   public void instantiation_SetsCountryName() {
      assertThat( sut.getName(), equalTo( CountryTestFixture.COUNTRY_NAME ));
   }

   @Ignore @Test public void addSettlement_ExtendsSettlementList() throws AlreadyExistingSettlementInCountryException {
      Settlement aNewSettlement = templatedFixture.getNewSettlement(); 
      sut.addSettlement( aNewSettlement );
      
      assertThat( sut.getSettlements(), hasItem( aNewSettlement ));
   }
   
   @Test( expected = AlreadyExistingSettlementInCountryException.class )
   public void addSettlement_ChecksUniquness() throws AlreadyExistingSettlementInCountryException {
      sut.addSettlement( templatedFixture.getFirstSettlement() );
   }

}
