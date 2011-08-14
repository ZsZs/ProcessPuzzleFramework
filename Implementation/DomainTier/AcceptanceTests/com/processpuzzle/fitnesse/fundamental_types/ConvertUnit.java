package com.processpuzzle.fitnesse.fundamental_types;

import com.processpuzzle.fundamental_types.quantity.domain.Quantity;
import com.processpuzzle.fundamental_types.quantity.domain.QuantityHelper;

import fit.ColumnFixture;

public class ConvertUnit extends ColumnFixture {

   public String fromUnit;
   public String toUnit;
   public double quantity1;
   

   public ConvertUnit() {
   }
   
   public double convertTo() {
      return convert().getAmount();
   }
   
   public String resultUnit() {
      return convert().getUnit().getSymbol();
   }
   
   private Quantity convert() {
      Quantity quantityObject1 = QuantityHelper.quantityCreator(quantity1, fromUnit);
      return quantityObject1.convertTo(QuantityHelper.unitFinder(toUnit));
   }
}

