package com.processpuzzle.fitnesse.fundamental_types;

import com.processpuzzle.fundamental_types.quantity.domain.QuantityHelper;

import fitlibrary.SetUpFixture;

public class SetUpExchangeRates extends SetUpFixture {
/*   public SetUpExchangeRates () {
      
   }
  */

   public void fromCurrencyToCurrencyRate ( String fromCurrency, String toCurrency, double rate ) {
	   QuantityHelper.setRatio(fromCurrency, toCurrency, rate);
   }
}
