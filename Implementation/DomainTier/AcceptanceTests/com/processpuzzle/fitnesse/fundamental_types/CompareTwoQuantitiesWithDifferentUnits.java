package com.processpuzzle.fitnesse.fundamental_types;

import com.processpuzzle.application.configuration.domain.MeasurementContext;
import com.processpuzzle.fundamental_types.quantity.domain.Quantity;
import com.processpuzzle.fundamental_types.quantity.domain.Unit;
import com.processpuzzle.user_session.domain.UserRequestManager;

import fit.ColumnFixture;

public class CompareTwoQuantitiesWithDifferentUnits extends ColumnFixture {
   public String unitSymbol_1;
   public String unitSymbol_2;
   public String quantity1;
   public String quantity2;

   public CompareTwoQuantitiesWithDifferentUnits() {
   }
   
   public int compareTo() {
      MeasurementContext measurementContext = UserRequestManager.getInstance().getApplicationContext().getMeasurementContext();
      Unit unit_1 = measurementContext.findUnitBySymbol( unitSymbol_1 );
      Unit unit_2 = measurementContext.findUnitBySymbol( unitSymbol_2 );
      Quantity quantityObject1 = new Quantity(Double.valueOf(quantity1), unit_1 );
      Quantity quantityObject2 = new Quantity(Double.valueOf(quantity2), unit_2 );
      
      return quantityObject1.compareTo(quantityObject2);
   }
}
