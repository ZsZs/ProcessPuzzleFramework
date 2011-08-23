package com.processpuzzle.workflow.activity.domain;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    ActionTest.class,
    ActionFactoryTest.class,
    ProposedActionTest.class,
    PlanTest.class
})

public class ProjectPlanningActionTestSuite {}
