package com.processpuzzle.fitnesse.fundamental_types;

import com.processpuzzle.application.configuration.domain.MeasurementContext;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleParseException;
import com.processpuzzle.fundamental_types.quantity.domain.TimeUnit;
import com.processpuzzle.user_session.domain.UserRequestManager;

import fit.ColumnFixture;

public class CalculateConvert extends ColumnFixture{
	
	public String timeQuantity;
	public String timeunit1;
	public String timeunit2;

	public CalculateConvert() {
	}	
	   

	public double convert(){
		TimeUnit from=null;
		TimeUnit to=null;
		Double quantity=null;
		try {
			quantity=new Double(timeQuantity);
		}catch(Exception e){
			
		}
		try{
           MeasurementContext measurementContext = UserRequestManager.getInstance().getApplicationContext().getMeasurementContext(); 
			from = (TimeUnit) measurementContext.findUnitBySymbol(timeunit1);
			to = (TimeUnit) measurementContext.findUnitBySymbol(timeunit2);
	         
			double d=quantity.doubleValue()*from.findConversionRatio(to);
			
			// 0.9-tol egeszre kerekitunk
			if (Math.abs(Math.rint(d)-d)<=0.1) d=Math.rint(d);
			
			java.text.DecimalFormat df=new java.text.DecimalFormat("#.##");
			  try{
				  return df.parse(df.format(d)).doubleValue();
			  }catch(java.text.ParseException e ){
				  throw new ProcessPuzzleParseException("??","Round doubles",e);
			  }
	    } catch (Exception e) {
	         //e.printStackTrace();
	         throw new ProcessPuzzleParseException(timeunit1+"->"+timeunit2,"Converting units",e);
	    }
	    
		
	}
	
	public String timeunit(){
		try{
           MeasurementContext measurementContext = UserRequestManager.getInstance().getApplicationContext().getMeasurementContext(); 
			TimeUnit to=(TimeUnit) measurementContext.findUnitBySymbol(timeunit2);
			return to.getSymbol().toString();
		}catch(Exception e){
			throw new ProcessPuzzleParseException(timeunit2,"Looking for time unit",e);
		}
	}
}