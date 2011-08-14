package com.processpuzzle.fitnesse.fundamental_types;

import fit.ColumnFixture;

import com.processpuzzle.fundamental_types.quantity.domain.InvalidUnitException;
import com.processpuzzle.fundamental_types.quantity.domain.Quantity;
import com.processpuzzle.fundamental_types.quantity.domain.UnitMismatchException;

public class CalculateParse extends ColumnFixture{
	public String sourceText;
	
	
	public double quantity() throws InvalidUnitException{
		Quantity q=null;
		q=Quantity.parse(sourceText);
		
		return q.getAmount();
	}
	public String unit() throws UnitMismatchException, InvalidUnitException{
		Quantity q=null;
		q=Quantity.parse(sourceText);
		
		return q.getUnit().getSymbol();
	}
}
