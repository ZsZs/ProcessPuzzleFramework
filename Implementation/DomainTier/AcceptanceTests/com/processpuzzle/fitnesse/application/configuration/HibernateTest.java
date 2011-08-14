package com.processpuzzle.fitnesse.application.configuration;

import com.processpuzzle.application.configuration.domain.InternalizationContext;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.configuration.domain.PropertyKeys;
import com.processpuzzle.sharedfixtures.domaintier.DomainTier_ConfigurationFixture;
import com.processpuzzle.user_session.domain.UserRequestManager;

import fit.ColumnFixture;

public class HibernateTest extends ColumnFixture{
	DomainTier_ConfigurationFixture fixture;
	InternalizationContext repository; //TODO ez igy most mar nem repository, jo a teszt?
	
	public String getMappingProperty(){
       ProcessPuzzleContext applicationContext = UserRequestManager.getInstance().getApplicationContext(); 
		return (String) applicationContext.getProperty( PropertyKeys.CLASS_REPOSITORY_MAPPING.getDefaultKey() ); 
	}
}