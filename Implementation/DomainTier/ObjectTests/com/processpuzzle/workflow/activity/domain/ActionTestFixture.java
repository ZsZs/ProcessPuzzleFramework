/*
 * Created on Sep 14, 2006
 */
package com.processpuzzle.workflow.activity.domain;


import com.processpuzzle.litest.fixture.GenericTestFixture;
import com.processpuzzle.litest.fixture.TransientFreshFixture;
import com.processpuzzle.sharedfixtures.domaintier.DomainTier_ConfigurationFixture;

/**
 * @author zsolt.zsuffa
 */
public class ActionTestFixture extends GenericTestFixture<Action<?>> implements TransientFreshFixture<Action<?>> {
   private static String TASK_NAME_1 = "Do something";
   private static String TASK_NAME_2 = "Yet another task";
   private static DomainTier_ConfigurationFixture configurationFixture = null;
   private ProposedAction doSomething = null;
   private ProposedAction doYetOnotherTask = null;

   public ActionTestFixture() {
      super();
   }

   public void beforeEachTests() {
      configurationFixture = DomainTier_ConfigurationFixture.getInstance();
      configurationFixture.setUp();
      
      doSomething = ActionFactory.createProposedAction(TASK_NAME_1);
      doYetOnotherTask = ActionFactory.createProposedAction(TASK_NAME_2);
   }

   public void afterEachTests() {
      configurationFixture.tearDown();
      doSomething = null;
      doYetOnotherTask = null;
   }

   @Override
   protected void configureAfterSutInstantiation() {
      // TODO Auto-generated method stub
      
   }

   @Override
   protected Action<?> instantiateSUT() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   protected void configureBeforeSutInstantiation() {
      // TODO Auto-generated method stub
      
   }

   @Override
   protected void releaseResources() {
      // TODO Auto-generated method stub
      
   }
}
