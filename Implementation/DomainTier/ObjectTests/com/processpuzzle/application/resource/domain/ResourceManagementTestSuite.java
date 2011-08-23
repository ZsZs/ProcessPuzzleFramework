package com.processpuzzle.application.resource.domain;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
   DataFromResourceLoaderTest.class,
   PropertyLoaderTest.class,
   FoToPdfConverterTest.class,
   XmlDataLoaderTest.class,
   XmlTransformerTest.class,
   XmlValidatorTest.class
})

public class ResourceManagementTestSuite {}