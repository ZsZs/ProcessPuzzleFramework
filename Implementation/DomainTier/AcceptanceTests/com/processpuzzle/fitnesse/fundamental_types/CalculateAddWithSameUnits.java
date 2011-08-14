package com.processpuzzle.fitnesse.fundamental_types;

import com.processpuzzle.fundamental_types.quantity.domain.Quantity;
import com.processpuzzle.fundamental_types.quantity.domain.Unit;
import com.processpuzzle.fundamental_types.quantity.domain.UnitMismatchException;
import com.processpuzzle.user_session.domain.UserRequestManager;

import fit.ColumnFixture;

public class CalculateAddWithSameUnits extends ColumnFixture {


   public String unit;
   public String quantity1;
   public String quantity2;
   
   public CalculateAddWithSameUnits() {
   }
   
   public double add() throws UnitMismatchException {
      Unit unitObject = UserRequestManager.getInstance().getApplicationContext().getMeasurementContext().findUnitBySymbol(unit);
      
      Quantity result = null;
      Quantity quantityObject1 = new Quantity(Double.valueOf(quantity1), unitObject);
      Quantity quantityObject2 = new Quantity(Double.valueOf(quantity2), unitObject);
      
      result = quantityObject1.add(quantityObject2); 
      return result.getAmount();
   }
   
   
}
