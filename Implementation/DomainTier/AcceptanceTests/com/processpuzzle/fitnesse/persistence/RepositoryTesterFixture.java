package com.processpuzzle.fitnesse.persistence;


import com.processpuzzle.litest.fixture.GenericCompositeFixture;
import com.processpuzzle.sharedfixtures.domaintier.MockProcessPuzzleContext;
import com.processpuzzle.sharedfixtures.domaintier.RequestContextInMockedApplicationFixture;

public class RepositoryTesterFixture extends GenericCompositeFixture<RepositoryTester> {
   
   @Override
   protected void defineComponentTypes() {
      componentTypes.add( RequestContextInMockedApplicationFixture.class );
      componentTypes.add( MockProcessPuzzleContext.class );
      componentTypes.add( InstantiatedRepositoryTester.class );
   }

   @Override
   protected void configureAfterSutInstantiation() {
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
