package com.processpuzzle.fitnesse.fundamental_types;

import fit.ColumnFixture;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleParseException;
import com.processpuzzle.fundamental_types.quantity.domain.Quantity;
import com.processpuzzle.fundamental_types.quantity.domain.QuantityHelper;
import com.processpuzzle.fundamental_types.quantity.money.domain.Money;
public class CalculateAdd extends ColumnFixture {

   public String quantity1, quantity2, currency1, currency2;
   private Quantity money3=null;
   
   public double quantity() throws ProcessPuzzleParseException{ 
	  try{
		  Double d1=new Double(quantity1);
		  Double d2=new Double(quantity2);
		  
		  Money money1=new Money(d1,QuantityHelper.unitFinder(currency1));
		  Money money2=new Money(d2,QuantityHelper.unitFinder(currency2));
		  
		  Quantity q2=money2.convertTo(QuantityHelper.unitFinder(currency1));
		  
		  money3=money1.add(q2);
		  
		  // Formatted return
		  java.text.DecimalFormat df=new java.text.DecimalFormat("###.##");
		  try{
			  return df.parse(df.format(money3.getAmount())).doubleValue();
		  }catch(java.text.ParseException e ){
			  throw new ProcessPuzzleParseException(money3.toString(),"Round doubles",e);
		  }
		  
	  }catch(NumberFormatException e){
		  throw new ProcessPuzzleParseException(quantity1+" or "+quantity2,"Double",e);
		  
	  }

   }
   public String currency(){
	   return money3.getUnit().getSymbol();
   }
}
