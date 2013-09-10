/*
 * Created on May 4, 2006
 */
package com.processpuzzle.artifact.domain;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.litest.template.RepositoryTestTemplate;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

@SuppressWarnings( "unchecked" )
public class ArtifactRepositoryTest extends RepositoryTestTemplate<DefaultArtifactRepository, ArtifactRepositoryTestFixture, Artifact> {

   public ArtifactRepositoryTest() {
      super( DomainTierTestConfiguration.FIXTURE_CONTAINER_DEFINITION_PATH );
   }

   @Override
   @Test
   public void testAdd_ForOwnedAttributesAndComponents() {
      // assertThat( (Integer)retrieveColumnFromRow( "T_ASSET", root.getId(), Integer.class, "ID" ), equalTo( root.getId() ));
      assertThat( databaseSpy.retrieveColumnFromRow( "T_ARTIFACT_SUB_CLASS", root.getId(), Integer.class, "ID" ), equalTo( root.getId() ) );
      // assertThat( (String)retrieveColumnFromRow( "T_ARTIFACT", root.getId(), String.class, "name" ), equalTo( root.getName() ));
   }

   @Override
   @Ignore
   public void testAdd_ForReferencedAggregateRoots() {
      assertThat( databaseSpy.retrieveColumnFromRow( "T_ARTIFACT_SUB_CLASS", root.getId(), Integer.class, "type" ), equalTo( fixture.getSubClassType().getId() ) );
   }

   @Override
   @Ignore
   public void testDelete_ForOwnedAttributesAndComponents() {
   // TODO Auto-generated method stub

   }

   @Override
   @Ignore
   public void testFindAll_ForResultCount() {
   // TODO Auto-generated method stub

   }

   @Override
   @Ignore
   public void testFindById() {
   // TODO Auto-generated method stub

   }

   @Override
   @Ignore
   public void testFindById_ForEagerLoadedComponents() {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      Artifact artifact = repository.findById( work, root.getId() );
      assertThat( artifact.getArtifactType().isSystem(), is( true ) );
      work.finish();
   }

   @Override
   @Ignore
   public void testFindById_ForLazyLoadedComponents() {
   // TODO Auto-generated method stub

   }

   @Override
   @Ignore
   public void testFindByQuery_ForComponentAttributes() {
   // TODO Auto-generated method stub

   }

   @Override
   @Ignore
   public void testFindByQuery_ForDirectAttributes() {
      assertThat( repository.findByName( ArtifactRepositoryTestFixture.ARTIFACT_NAME ), notNullValue() );
   }

   @Override
   @Ignore
   public void testUpdate_ForOwnedAttributesAndComponents() {
   // TODO Auto-generated method stub

   }

   @Override
   @Ignore
   public void testUpdate_ForReferencedAggregateRoots() {
   // TODO Auto-generated method stub
   }

}