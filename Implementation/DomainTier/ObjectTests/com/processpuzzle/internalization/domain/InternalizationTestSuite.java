package com.processpuzzle.internalization.domain;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
   ProcessPuzzleLocaleTest.class,
   XMLBundleParserTest.class,
   XMLResourceBundleTest.class
})

public class InternalizationTestSuite {}
