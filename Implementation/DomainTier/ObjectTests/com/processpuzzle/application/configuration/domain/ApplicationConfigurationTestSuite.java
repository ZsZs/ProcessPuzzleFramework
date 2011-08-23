/*
 * Created on Feb 13, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.application.configuration.domain;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.processpuzzle.application.domain.ApplicationEventRepositoryTest;
import com.processpuzzle.application.domain.ApplicationTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
   ApplicationEventRepositoryTest.class,
   ApplicationTest.class,
   BeanContainerTest.class,
   InternalizationContextTest.class,
   LocaleLoaderTest.class,
   MeasurementContextTest.class,
   PersistenceContextTest.class,
   ProcessPuzzleContextTest.class,
   PropertyContextTest.class,
   RepositoryMappingsTest.class
})

public class ApplicationConfigurationTestSuite {}
