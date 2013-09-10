package com.processpuzzle.address.domain;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import com.processpuzzle.litest.template.FactoryTestTemplate;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class CountryFactoryTest extends FactoryTestTemplate<CountryFactory, CountryFactoryTestFixture, Country> {

   public CountryFactoryTest() {
      super( DomainTierTestConfiguration.FIXTURE_CONTAINER_DEFINITION_PATH );
   }

   @Override public void create_ForSuccess() {
      assertThat( templatedFixture.getNewCountry(), notNullValue() );
      assertThat( templatedFixture.getNewCountry().getName(), equalTo( CountryFactoryTestFixture.COUNTRY_NAME));

      assertThat( templatedFixture.getNewCountryWithSettlement(), notNullValue() );
      assertThat( templatedFixture.getNewCountryWithSettlement().getName(), equalTo( CountryFactoryTestFixture.ANOTHER_COUNTRY_NAME ));
      assertThat( templatedFixture.getNewCountryWithSettlement().getSettlements(), contains( templatedFixture.getSettlement() ));
   }

   @Override public void create_ForCollision() {
      sut.createCountry( templatedFixture.getNewCountry().getName() );
   }
}