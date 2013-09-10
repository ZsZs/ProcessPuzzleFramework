package com.processpuzzle.artifact_type.domain;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


import java.sql.SQLException;

import org.junit.Test;

import com.processpuzzle.litest.template.RepositoryTestTemplate;
import com.processpuzzle.persistence.query.domain.DefaultQuery;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class ArtifactTypeRepositoryTest extends RepositoryTestTemplate<ArtifactTypeRepository, ArtifactTypeRepositoryTestFixture, ArtifactType> {

   public ArtifactTypeRepositoryTest() {
      super( DomainTierTestConfiguration.FIXTURE_CONTAINER_DEFINITION_PATH );
   }

   @Override
   @Test
   public void testAdd_ForOwnedAttributesAndComponents() throws SQLException {
      assertEquals( root.getName(), databaseSpy.retrieveColumnFromRow( "T_ARTIFACT_TYPE", root.getId(), String.class, "name" ) );
      assertThat( rootRecord.getBoolean( "isSystem" ), is( true ) );

      ListQueryViewType queryViewType = (ListQueryViewType) root.findView( ArtifactTypeRepositoryTestFixture.QUERY_VIEW_TYPE_NAME );
      assertThat( root.findView( ArtifactTypeRepositoryTestFixture.QUERY_VIEW_TYPE_NAME ), notNullValue() );

      DefaultQuery predefinedQuery = queryViewType.findPredefinedQuery( ArtifactTypeRepositoryTestFixture.PREDEFINED_QUERY_NAME );
      assertThat( predefinedQuery.getDescription(), equalTo( ArtifactTypeRepositoryTestFixture.PREDEFINED_QUERY_DESCRIPTION ) );
      assertThat( predefinedQuery.getPredefinedStatement(), equalTo( ArtifactTypeRepositoryTestFixture.PREDEFINED_QUERY_STATEMENT ) );
   }

   @Override
   public void testAdd_ForReferencedAggregateRoots() {}

   @Override
   public void testDelete_ForOwnedAttributesAndComponents() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testFindAll_ForResultCount() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testFindById() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testFindById_ForEagerLoadedComponents() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testFindById_ForLazyLoadedComponents() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testFindByQuery_ForComponentAttributes() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testFindByQuery_ForDirectAttributes() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testUpdate_ForOwnedAttributesAndComponents() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testUpdate_ForReferencedAggregateRoots() {
   // TODO Auto-generated method stub

   }

}
