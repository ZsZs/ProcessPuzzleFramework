package com.processpuzzle.fitnesse.fundamental_types;

import com.processpuzzle.application.configuration.domain.InternalizationContext;
import com.processpuzzle.internalization.domain.ProcessPuzzleLocale;
import com.processpuzzle.sharedfixtures.domaintier.DomainTier_ConfigurationFixture;
import com.processpuzzle.user_session.domain.UserRequestManager;

import fit.ColumnFixture;

public class DecimalSeparatorTest extends ColumnFixture {
   public String language;
   public String country;
   DomainTier_ConfigurationFixture fixture;

   public DecimalSeparatorTest() {
      fixture = DomainTier_ConfigurationFixture.getInstance();
      fixture.setUp();
      // LocaleLoader loader = new LocaleLoader("classpath:com/itcodex/objectpuzzle/framework/internalization/domain/LocaleDefinitions.xml");
      // loader.loadData();

   }

   public String currency() {
      InternalizationContext i18Context = UserRequestManager.getInstance().getApplicationContext().getInternalizationContext();
      ProcessPuzzleLocale locale = i18Context.findLocaleByLanguageAndCountry( language, country );
      return locale.getLegalTender().getSymbol();
   }

   public char separator() {
      InternalizationContext i18Context = UserRequestManager.getInstance().getApplicationContext().getInternalizationContext();
      ProcessPuzzleLocale locale = i18Context.findLocaleByLanguageAndCountry( language, country );
      return locale.getQuantityFormat().getDecimalSeparator();
   }
}