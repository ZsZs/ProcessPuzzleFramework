package com.processpuzzle.fitnesse.fundamental_types;

import com.processpuzzle.application.configuration.domain.InternalizationContext;
import com.processpuzzle.fundamental_types.domain.TimePeriod;
import com.processpuzzle.fundamental_types.domain.TimePoint;
import com.processpuzzle.user_session.domain.UserRequestManager;

import fit.ColumnFixture;

public class CalculateTimeTo extends ColumnFixture{
	
	public String timepoint1;
	public String timepoint2;
    public String style;
    private InternalizationContext internalizationContext = null;
    
    public CalculateTimeTo(){
       internalizationContext = UserRequestManager.getInstance().getApplicationContext().getInternalizationContext();
       
    }
	public long timeto(){
		TimePoint t1=TimePoint.parse(timepoint1, internalizationContext.findLocaleByLanguageAndCountry("en", "US"));
		TimePoint t2=TimePoint.parse(timepoint2, internalizationContext.findLocaleByLanguageAndCountry("en", "US"));
		
		TimePeriod tp=new TimePeriod(t1,t2);
		
		return tp.daysBetween();

	}
	
	public String timeunit(){
		return new String("d");
	}
}