package com.processpuzzle.fitnesse.fundamental_types;

import fit.ColumnFixture;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleParseException;
import com.processpuzzle.fundamental_types.quantity.domain.Quantity;
import com.processpuzzle.fundamental_types.quantity.domain.QuantityHelper;
import com.processpuzzle.fundamental_types.quantity.money.domain.Money;
public class CalculateMoneyMultiply extends ColumnFixture {

   public String quantity1, quantity2, currency;

   
   public double multiply() throws ProcessPuzzleParseException{ 
	  try{
		  Double d1=new Double(quantity1);
		  Double d2=new Double(quantity2);
		  
		  Money money1=new Money(d1,QuantityHelper.unitFinder(currency));
		  Money money2=new Money(d2,QuantityHelper.unitFinder(currency));
		  
		  Quantity q2=money1.multiply(money2);
		  
		   // Formatted return
		  java.text.DecimalFormat df=new java.text.DecimalFormat("###.##");
		  try{
			  return df.parse(df.format(q2.getAmount())).doubleValue();
		  }catch(java.text.ParseException e ){
			  throw new ProcessPuzzleParseException(q2.toString(),"Round doubles",e);
		  }
		  
	  }catch(NumberFormatException e){
		  throw new ProcessPuzzleParseException(quantity1+" or "+quantity2,"Conversion to Double",e);
		  
	  }

   }

}
