package com.processpuzzle.fundamental_types.quantity.domain;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.application.configuration.domain.InternalizationContext;
import com.processpuzzle.fundamental_types.quantity.domain.InvalidUnitException;
import com.processpuzzle.internalization.domain.ProcessPuzzleLocale;
import com.processpuzzle.sharedfixtures.domaintier.DomainTier_ConfigurationFixture;
import com.processpuzzle.user_session.domain.UserRequestManager;

public class QuantityParseTest {
   DomainTier_ConfigurationFixture fixture;
   InternalizationContext repository;

   @Before
   public void SetUp() {
      fixture = DomainTier_ConfigurationFixture.getInstance();
      fixture.setUp();
      repository = DomainTier_ConfigurationFixture.getInternalizationRepository();
      // LocaleLoader loader = new LocaleLoader("classpath:com/itcodex/objectpuzzle/framework/internalization/domain/LocaleDefinitions.xml");
      // loader.loadData();
   }
   
   @After
   public void tearDown() throws Exception {}

   @Ignore
   @Test
   public void testQuantity_forParse() throws InvalidUnitException {
      InternalizationContext i18Context = UserRequestManager.getInstance().getApplicationContext().getInternalizationContext();
      ProcessPuzzleLocale locale = i18Context.findLocaleByLanguageAndCountry( "hu", "HU" );
      Quantity quantity = Quantity.parse( "1 000 000,34 kg", locale );
      assertThat( "Amount ok", quantity.getAmount(), equalTo( 1000000.34 ) );
      assertThat( "Unit ok", quantity.getUnit().getSymbol(), equalTo( "kg" ) );
   }
}
