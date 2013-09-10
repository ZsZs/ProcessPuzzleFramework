package com.processpuzzle.persistence.domain;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.litest.template.RepositoryTestTemplate;


public class ComponentRepositoryTest extends RepositoryTestTemplate<ComponentRepository, ComponentRepositoryTestFixture, Component> {

   protected ComponentRepositoryTest( String fixtureContainerConfigurationPath ) {
      super( fixtureContainerConfigurationPath );
   }

   @Test public void testAdd_ForComposite() throws SQLException {
      ResultSet leafRecord = databaseSpy.retrieveRecord( ComponentRepositoryTestFixture.LEAF_TABLE_NAME, fixture.getLeafOne().getId() );
      assertThat( (Integer) databaseSpy.retrieveColumnFromRecord( leafRecord, Integer.class, "id" ), equalTo( fixture.getLeafOne().getId() ));

/*
      ResultSet leafAsComponentRecord = retrieveRecord( COMPONENT_TABLE_NAME, leafOne.getId() );
      assertThat( (String) retrieveColumnFromRecord( leafAsComponentRecord, String.class, "name" ), equalTo( leafOne.getName() ));
      assertThat( (Integer) retrieveColumnFromRecord( leafAsComponentRecord, Integer.class, "COMPOSITE_ID" ), equalTo( composite.getId() ));

      ResultSet compositeAsComponentRecord = retrieveRecord( COMPONENT_TABLE_NAME, composite.getId() );
      assertThat( (String) retrieveColumnFromRecord( compositeAsComponentRecord, String.class, "name" ), equalTo( composite.getName() ));
      assertThat( (Integer) retrieveColumnFromRecord( compositeAsComponentRecord, Integer.class, "COMPOSITE_ID" ), equalTo( 0 ) );
 */

      ResultSet compositeRecord = databaseSpy.retrieveRecord( ComponentRepositoryTestFixture.COMPOSITE_TABLE_NAME, fixture.getComposite().getId() );
      assertThat( (Integer) databaseSpy.retrieveColumnFromRecord( compositeRecord, Integer.class, "ID" ), equalTo( fixture.getComposite().getId() ));
      
      //---------------------
      assertThat( (Leaf)findComponentByName( fixture.getLeafOne().getName(), fixture.getComposite() ), equalTo( fixture.getLeafOne() ));
      assertThat( fixture.getComposite().getComponents().size(), equalTo( 1 ));
      
      assertThat( fixture.getLeafOne().getParent(), equalTo( fixture.getComposite() ));
   }
   
   @Override @Ignore
   public void testAdd_ForOwnedAttributesAndComponents() throws Exception { }

   @Override @Ignore
   public void testAdd_ForReferencedAggregateRoots() {
   }

   @Override @Ignore
   public void testDelete_ForOwnedAttributesAndComponents() {
   }

   @Override @Ignore
   public void testFindAll_ForResultCount() {
   }

   @Override @Ignore
   public void testFindById() {
   }

   @Override @Ignore
   public void testFindById_ForEagerLoadedComponents() {
   }

   @Override @Ignore
   public void testFindById_ForLazyLoadedComponents() {
   }

   @Override @Ignore
   public void testFindByQuery_ForComponentAttributes() {
      // TODO Auto-generated method stub
      
   }

   @Override @Ignore
   public void testFindByQuery_ForDirectAttributes() {
      // TODO Auto-generated method stub
      
   }

   @Override @Ignore
   public void testUpdate_ForOwnedAttributesAndComponents() throws Exception {
      // TODO Auto-generated method stub
      
   }

   @Override @Ignore
   public void testUpdate_ForReferencedAggregateRoots() {
      // TODO Auto-generated method stub
      
   }

   private Component findComponentByName( String componentName, Composite composite ) {
      for( Component component : composite.getComponents() ) {
         if( component.getName().equals( componentName )) return component;
      }
      
      return null;
   }

}
