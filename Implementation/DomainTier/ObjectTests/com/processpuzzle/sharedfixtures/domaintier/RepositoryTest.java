package com.processpuzzle.sharedfixtures.domaintier;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class RepositoryTest {

	protected DomainTier_ConfigurationFixture configurationFixture = null;

    
	public RepositoryTest(DomainTier_ConfigurationFixture fixture) {
		this.configurationFixture = fixture;
	}

    @Before
	public void setUp() {
		configurationFixture.setUp();
	}

    @After
	public void tearDown() {
		configurationFixture.tearDown();
	}

    @Ignore
    @Test
	// temp
	public void commit() {
//		((HibernatePersistenceProvider) ProcessPuzzleContext.getStrategy( HibernatePersistenceProvider.class.getName())).commit();
	}

}
