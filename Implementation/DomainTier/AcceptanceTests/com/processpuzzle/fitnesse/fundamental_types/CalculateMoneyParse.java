package com.processpuzzle.fitnesse.fundamental_types;

import com.processpuzzle.application.configuration.domain.InternalizationContext;
import com.processpuzzle.fundamental_types.quantity.money.domain.Money;
import com.processpuzzle.sharedfixtures.domaintier.DomainTier_ConfigurationFixture;
import com.processpuzzle.user_session.domain.UserRequestManager;

import fit.ColumnFixture;

public class CalculateMoneyParse extends ColumnFixture {
   public String sourceText;
   public String language;
   public String country;
   DomainTier_ConfigurationFixture fixture;

   public CalculateMoneyParse() {
      fixture = DomainTier_ConfigurationFixture.getInstance();
      fixture.setUp();
      // LocaleLoader loader=new LocaleLoader("classpath:com/itcodex/objectpuzzle/framework/internalization/domain/LocaleDefinitions.xml");
      // loader.loadData();
   }

   public double quantity() throws Exception {
      Money q = null;
      InternalizationContext i18Context = UserRequestManager.getInstance().getApplicationContext().getInternalizationContext();
      q = Money.parse( sourceText, i18Context.findLocaleByLanguageAndCountry( language, country ) );
      return q.getAmount();
   }

   public String currency() throws Exception {
      Money q = null;
      InternalizationContext i18Context = UserRequestManager.getInstance().getApplicationContext().getInternalizationContext();
      q = Money.parse( sourceText, i18Context.findLocaleByLanguageAndCountry( language, country ) );

      return q.getUnit().getSymbol();
   }
}