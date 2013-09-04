/*
Name: 
    - Quantity

Description: 
    -  

Requires:
    - 
    
Provides:
    - 

Part of: ProcessPuzzle Framework, Domain and Business Model Ready Architecture. Provides content, workflow and social networking functionality. 
http://www.processpuzzle.com

ProcessPuzzle - Content and Workflow Management Integration Business Platform

Author(s): 
    - Zsolt Zsuffa

Copyright: (C) 2011 This program is free software: you can redistribute it and/or modify it under the terms of the 
GNU General Public License as published by the Free Software Foundation, either version 3 of the License, 
or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

/*
 * Created on 2005.07.19.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.fundamental_types.quantity.domain;


import java.util.Locale;

import com.processpuzzle.commons.persistence.ValueObject;
import com.processpuzzle.fundamental_types.domain.AssertionException;
import com.processpuzzle.fundamental_types.domain.HashCodeUtil;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleParseException;
import com.processpuzzle.fundamental_types.domain.ZeroDenominatorException;
import com.processpuzzle.internalization.domain.ProcessPuzzleLocale;
import com.processpuzzle.preferences.domain.CommonPreferences;
import com.processpuzzle.preferences.domain.Preferences;

public class Quantity implements ValueObject, Comparable<Quantity>{
	protected Double amount;
	private Unit unit;
	
	// Constructors
	public Quantity(Double theAmount, Unit theUnit) throws AssertionException {
		if (theAmount == null)
			throw new AssertionException("Amount param can not be null!");
		if (theUnit == null)
			throw new AssertionException("Unit param can not be null!");

		this.amount = theAmount;
		this.unit = theUnit;
	}

	public Quantity(Integer theAmount, Unit theUnit) {
		if (theAmount == null)
			throw new AssertionException("Amount param can not be null!");
		if (theUnit == null)
			throw new AssertionException("Unit param can not be null!");

		this.amount = Double.valueOf(theAmount);
		this.unit = theUnit;
	}

	protected Quantity() {
	}
	
	public static Quantity parse(String source) throws InvalidUnitException{
		return parse(source,new ProcessPuzzleLocale(Locale.getDefault()));
	}

	public static Quantity parse(String source, Preferences preference) throws InvalidUnitException{		
		return parse(source,preference.getLocale());
	}

	public static Quantity parse(String source, ProcessPuzzleLocale locale) throws ProcessPuzzleParseException,InvalidUnitException {
		return locale.getQuantityFormat().parse(source);
	}

	// Public accessors
	public String asText(ProcessPuzzleLocale locale) {
		Preferences defaultPreferences = new CommonPreferences(locale);
		return asText(defaultPreferences);
	}

	public String asText(Preferences preferences) {
		return preferences.getQuantityFormatSpecifier().toString(this);
	}

	public int compareTo(Quantity subject) throws UnitMismatchException {
		if (unit.equals(subject.getUnit())) {
			return amount.compareTo(subject.getAmount());
		} else {
			Double ratio = unit.findConversionRatio(subject.getUnit());
			Double convertedAmount;
			if (ratio != null) {
				convertedAmount = amount * ratio;
				return convertedAmount.compareTo(subject.getAmount());
			} else
				throw new UnitMismatchException(this.unit, subject.getUnit());
		}
	}

   public boolean equals( Object objectToCheck ) {
      if( !(objectToCheck instanceof Quantity )) return false;
      Quantity anotherQuantity = (Quantity) objectToCheck;
      
      return anotherQuantity.equals( this );
   }
   
	public boolean equals( Quantity quantity ) {
		if (quantity.getAmount().equals(this.amount) && quantity.getUnit().equals(this.unit))
			return true;
		else {
			return false;
		}
	}

	// Public mutator methods
	/**
	 * @param targetUnit
	 * @return
	 * @generated "UML to Java V5.0
	 *            (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Quantity convertTo(Unit targetUnit) throws UnitMismatchException {
		// begin-user-code
		if (this.unit.equals(targetUnit))
			return new Quantity(amount, targetUnit);
		else {
			Double ratio = this.unit.findConversionRatio(targetUnit);
			if (ratio != null) {
				Double convertedAmount = amount * ratio;
				return new Quantity(convertedAmount, targetUnit);
			} else
				throw new UnitMismatchException(this.unit, targetUnit);
		}
		// end-user-code
	}

	/**
	 * @param argument
	 * @return
	 * @generated "UML to Java V5.0
	 *            (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Quantity add(Quantity argument) throws UnitMismatchException {
		// begin-user-code
		// TODO Auto-generated method stub
		double convertedAmount = argument.getUnit().findConversionRatio(this.unit) * argument.getAmount();
		double resultAmount = this.amount.doubleValue() + convertedAmount;

		return new Quantity(resultAmount, this.unit);
		// end-user-code
	}

	/**
	 * @param argument
	 * @return
	 * @generated "UML to Java V5.0
	 *            (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Quantity subtract(Quantity argument) throws UnitMismatchException {
		// begin-user-code
		// TODO Auto-generated method stub
		double result = this.amount - argument.getAmount();
		amount.compareTo(argument.getAmount());
		if (unit.equals(argument.getUnit())) {
			return new Quantity(result, unit);
		} else {
			Double ratio = unit.findConversionRatio(argument.getUnit());
			Double convertedAmount;
			if (ratio != null) {
				convertedAmount = argument.getAmount() / ratio;
				convertedAmount = convertedAmount - this.getAmount();
				return new Quantity(convertedAmount, unit);
			} else
				throw new UnitMismatchException(this.unit, argument.getUnit());
		}
		// end-user-code
	}

	/**
	 * @param multiplier
	 * @return
	 * @generated "UML to Java V5.0
	 *            (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Quantity multiply(Quantity multiplier) throws UnitMismatchException {
		// begin-user-code
		// TODO Auto-generated method stub
		double result = this.amount * multiplier.getAmount();
		amount.compareTo(multiplier.getAmount());
		if (unit.equals(multiplier.getUnit())) {
			return new Quantity(result, unit);
		} else {
			Double ratio = unit.findConversionRatio(multiplier.getUnit());
			Double convertedAmount;
			if (ratio != null) {
				convertedAmount = multiplier.getAmount() / ratio;
				convertedAmount = convertedAmount * this.getAmount();
				return new Quantity(convertedAmount, unit);
			} else
				throw new UnitMismatchException(this.unit, multiplier.getUnit());
		}
		// end-user-code
	}

	public double multiplyingValues(Quantity multiplier) throws UnitMismatchException {
		double value = 0;
		// double result = this.amount * multiplier.getAmount();
		amount.compareTo(multiplier.getAmount());
		if (unit.equals(this.unit)) {
			return new Double(value).doubleValue();
		} else
			return 0;
	}

	/**
	 * @param denominator
	 * @return
	 * @generated "UML to Java V5.0
	 *            (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Quantity divide(Quantity denominator) throws UnitMismatchException {
		// begin-user-code
		if (denominator.getAmount() == 0)
			throw new ZeroDenominatorException();
		double result = this.amount / denominator.getAmount();
		amount.compareTo(denominator.getAmount());
		if (unit.equals(denominator.getUnit())) {
			return new Quantity(result, unit);
		} else {
			Double ratio = unit.findConversionRatio(denominator.getUnit());
			Double convertedAmount;
			if (ratio != null) {
				convertedAmount = denominator.getAmount() / ratio;
				convertedAmount = convertedAmount / this.getAmount();
				return new Quantity(convertedAmount, unit);
			} else
				throw new UnitMismatchException(this.unit, denominator.getUnit());
		}
		// end-user-code
	}

	// Getters and setters
	public Double getAmount() {
		return amount;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
    public boolean isCurrency(){
       //System.out.println(unit.getName()+":"+unit.getClass().getName());
       if (unit.getClass().getName().equals("com.processpuzzle.framework.fundamental_types.domain.Currency")) return true;
       return false;
    }

    public int hashCode() {
       int result = HashCodeUtil.SEED;
       result = HashCodeUtil.hash( result, amount );
       result = HashCodeUtil.hash( result, unit );

       return result;
    }
}