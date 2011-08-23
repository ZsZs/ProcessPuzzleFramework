package com.processpuzzle.fundamental_types.quantity.domain;

import com.processpuzzle.application.configuration.domain.MeasurementContext;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.user_session.domain.UserRequestManager;


public class QuantityHelper {
   
   public static Quantity quantityCreator(double amount, String symbol) {
      Unit unit = UserRequestManager.getInstance().getApplicationContext().getMeasurementContext().findUnitBySymbol(symbol);
      return new Quantity(Double.valueOf(amount), unit );
   }

   
   public static Unit unitFinder(String symbol) {
	  symbol=symbol.trim();
      Unit unit = UserRequestManager.getInstance().getApplicationContext().getMeasurementContext().findUnitBySymbol(symbol);
      return unit;
   }
   
   public static void setRatio(String from,String to,double ratio){
      ProcessPuzzleContext applicationContext = UserRequestManager.getInstance().getApplicationContext();
      MeasurementContext measurementContext = applicationContext.getMeasurementContext();
	   Unit fromUnit = measurementContext.findUnitBySymbol( from );
	   Unit toUnit = measurementContext.findUnitBySymbol( to );
	   // Itt lehet elszamolva nagyon a dolog!!
	   fromUnit.addConversionRatio(ratio, toUnit);
	   //toUnit.addConversionRatio(ratio, fromUnit);
   }
}
