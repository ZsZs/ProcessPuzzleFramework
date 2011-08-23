/*
 * Created on Jul 2, 2006
 */
package com.processpuzzle.application.security.domain;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
   UserFactoryTest.class,
   UserRepositoryBackUpTest.class,
   UserRepositoryRestoreTest.class,
   UserRepositoryTest.class,
   UserTest.class
})
/**
 * @author zsolt.zsuffa
 */
public class ApplicationSecurityTestSuite {}
