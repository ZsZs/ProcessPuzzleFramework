package com.processpuzzle.fitnesse.fundamental_types;

import com.processpuzzle.fundamental_types.quantity.domain.Quantity;
import com.processpuzzle.fundamental_types.quantity.domain.QuantityHelper;

import fit.ColumnFixture;

public class CalculateDivide extends ColumnFixture {

   public String unit1, unit2;
   public double quantity1, quantity2;
   
   public CalculateDivide() {
   }
   
   public double divide() {
      Quantity numerator = QuantityHelper.quantityCreator(quantity1, unit1);
      Quantity denominator = QuantityHelper.quantityCreator(quantity2, unit2);
      
      return numerator.divide(denominator).getAmount();
   }
   
}
