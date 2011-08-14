package com.processpuzzle.fitnesse.fundamental_types;

import com.processpuzzle.application.configuration.domain.InternalizationContext;
import com.processpuzzle.sharedfixtures.domaintier.DomainTier_ConfigurationFixture;

import fit.Fixture;

public class EmptyLocaleRepository extends Fixture{
	DomainTier_ConfigurationFixture fixture;
	InternalizationContext repository;
	
	public EmptyLocaleRepository(){
		fixture=DomainTier_ConfigurationFixture.getInstance();
		fixture.setUp();
		repository=DomainTier_ConfigurationFixture.getInternalizationRepository();
	}
	public void empty(){
//		repository.empty();
		System.out.println("Repo empty.");
	}
}