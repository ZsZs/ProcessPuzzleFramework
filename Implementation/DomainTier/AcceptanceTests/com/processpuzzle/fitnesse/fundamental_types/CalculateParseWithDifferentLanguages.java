package com.processpuzzle.fitnesse.fundamental_types;

import com.processpuzzle.application.configuration.domain.InternalizationContext;
import com.processpuzzle.fundamental_types.quantity.domain.InvalidUnitException;
import com.processpuzzle.fundamental_types.quantity.domain.Quantity;
import com.processpuzzle.fundamental_types.quantity.domain.UnitMismatchException;
import com.processpuzzle.user_session.domain.UserRequestManager;

import fit.ColumnFixture;

public class CalculateParseWithDifferentLanguages extends ColumnFixture{
   public String sourceText;
   public String language;
   public String country;

   private InternalizationContext internalizationContext = null;
       
	public CalculateParseWithDifferentLanguages(){
          internalizationContext = UserRequestManager.getInstance().getApplicationContext().getInternalizationContext();
	}
	
	public double quantity() throws InvalidUnitException{
		Quantity q=null;
		q=Quantity.parse(sourceText, internalizationContext.findLocaleByLanguageAndCountry(language, country));
		return q.getAmount();
	}
	public String unit() throws UnitMismatchException,InvalidUnitException{
		Quantity q=null;
		q=Quantity.parse(sourceText, internalizationContext.findLocaleByLanguageAndCountry(language, country));
		return q.getUnit().getSymbol();
	}
}