package com.processpuzzle.fitnesse.fundamental_types;


import fit.ColumnFixture;

import com.processpuzzle.fundamental_types.domain.ProcessPuzzleParseException;
import com.processpuzzle.fundamental_types.quantity.domain.Quantity;
import com.processpuzzle.fundamental_types.quantity.domain.QuantityHelper;
import com.processpuzzle.fundamental_types.quantity.money.domain.Money;

public class CalculateCompareTo extends ColumnFixture{
	
	public String quantity1;
	public String currency1;
	public String quantity2;
	public String currency2;
	
	public int compareTo(){
		 try{
			  Double d1=new Double(quantity1);
			  Double d2=new Double(quantity2);
			  
			  Money money1=new Money(d1,QuantityHelper.unitFinder(currency1));
			  Money money2=new Money(d2,QuantityHelper.unitFinder(currency2));
			  
			  Quantity q2=money2.convertTo(QuantityHelper.unitFinder(currency1));
			  
			  return q2.compareTo(money1)*(-1);
		 }catch(NumberFormatException e){
			 throw new ProcessPuzzleParseException(quantity1+" or "+quantity2,"Double",e);
			 
		 }
		 
	}
} 