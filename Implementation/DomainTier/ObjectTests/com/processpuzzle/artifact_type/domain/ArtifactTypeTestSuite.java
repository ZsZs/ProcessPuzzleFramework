package com.processpuzzle.artifact_type.domain;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.processpuzzle.business.definition.domain.BusinessDefinitionLoaderTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
   ArtifactTypeFactoryTest.class,
   BusinessDefinitionLoaderTest.class,
   ArtifactTypeTest.class,
   ArtifactViewTypeTest.class
})
public class ArtifactTypeTestSuite {}