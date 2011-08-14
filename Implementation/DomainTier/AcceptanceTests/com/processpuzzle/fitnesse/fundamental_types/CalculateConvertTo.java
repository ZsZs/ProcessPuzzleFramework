package com.processpuzzle.fitnesse.fundamental_types;


import fit.ColumnFixture;

import com.processpuzzle.fundamental_types.domain.ProcessPuzzleParseException;
import com.processpuzzle.fundamental_types.quantity.domain.Quantity;
import com.processpuzzle.fundamental_types.quantity.domain.QuantityHelper;
import com.processpuzzle.fundamental_types.quantity.money.domain.Money;

public class CalculateConvertTo extends ColumnFixture{
	
	public String quantity1;
	public String currency1;
	public String currency2;
	
	private Quantity q2;
	public double convertTo(){
		 try{
			  Double d1=new Double(quantity1);

			  Money money1=new Money(d1,QuantityHelper.unitFinder(currency1));
			  q2=money1.convertTo(QuantityHelper.unitFinder(currency2));
			  java.text.DecimalFormat df=new java.text.DecimalFormat("###.##");
			 
			  // Formatted return
			  try{
				  return df.parse(df.format(q2.getAmount())).doubleValue();
			  }catch(java.text.ParseException e ){
				  throw new ProcessPuzzleParseException(q2.toString(),"Round doubles",e);
			  }
			  
		 }catch(NumberFormatException e){
			 throw new ProcessPuzzleParseException(quantity1,"Conversion to Double",e);
		 }
		
	}
	public String currency(){
		return q2.getUnit().getSymbol();
	}
} 