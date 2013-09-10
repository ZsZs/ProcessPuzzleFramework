package com.processpuzzle.persistence.domain;

import com.processpuzzle.litest.template.RepositoryTestEnvironment;
import com.processpuzzle.litest.template.RepositoryTestFixture;

public class ComponentRepositoryTestFixture extends RepositoryTestFixture<ComponentRepository, Component> {
   public static final String COMPONENT_TABLE_NAME = "T_COMPONENT";
   public static final String COMPOSITE_TABLE_NAME = "T_COMPOSITE";
   public static final String LEAF_TABLE_NAME = "T_LEAF";
   public Leaf leafOne;
   public Composite composite;

   protected ComponentRepositoryTestFixture( RepositoryTestEnvironment<ComponentRepository, RepositoryTestFixture<ComponentRepository, Component>> testEnvironment ) {
      super( testEnvironment );
   }

   //Properties
   public Leaf getLeafOne() {
      return leafOne;
   }

   public Composite getComposite() {
      return composite;
   }

   @Override
   protected void configureBeforeSutInstantiation() {
      composite = (Composite) repository.findById( testWork, composite.getId() );
      leafOne = (Leaf) repository.findById( testWork, leafOne.getId() );
   }

   //Protected, private helper methods
   @Override
   protected Component createNewAggregate() throws Exception {
      composite = new Composite( "Composite" );
      
      leafOne = new Leaf( "Leaf_1" );
      composite.addComponent( leafOne );
      
      return composite;
   }

   @Override
   protected void afterAggregateCreation() {
   }

   @Override
   protected void afterAggregateDeletion() {
   }

   @Override
   protected void beforeAggregateCreation() {
   }
}
