package com.processpuzzle.fundamental_types.quantity.money.domain;

import java.util.Locale;

import com.processpuzzle.fundamental_types.domain.ProcessPuzzleParseException;
import com.processpuzzle.fundamental_types.quantity.domain.InvalidUnitException;
import com.processpuzzle.fundamental_types.quantity.domain.Quantity;
import com.processpuzzle.fundamental_types.quantity.domain.Unit;
import com.processpuzzle.internalization.domain.ProcessPuzzleLocale;
import com.processpuzzle.preferences.domain.Preferences;

public class Money extends Quantity {
	
	protected Money() {}

	public Money(double amount, Unit unit) {
		super(amount, unit);
	}

	public Money(Integer amount, Unit unit) {
		super(amount, unit);
	}

   public static Money parse(String source) throws InvalidUnitException{
        return parse(source,new ProcessPuzzleLocale(Locale.getDefault()));
    }

    public static Money parse(String source, Preferences preference) throws InvalidUnitException{       
        return parse(source,preference.getLocale());
    }

    public static Money parse(String source, ProcessPuzzleLocale locale) throws ProcessPuzzleParseException,InvalidUnitException{
        Quantity q=locale.getQuantityFormat().parse(source);
        if (!q.isCurrency()){
           throw new InvalidUnitException(q.getUnit(),"Money");
        }
        else return new Money(q.getAmount(),q.getUnit());
        
    }
}
