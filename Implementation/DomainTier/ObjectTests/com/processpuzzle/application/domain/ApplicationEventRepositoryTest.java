package com.processpuzzle.application.domain;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import hu.itkodex.litest.template.RepositoryTestTemplate;

import java.sql.Timestamp;

import org.junit.Test;

import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class ApplicationEventRepositoryTest extends RepositoryTestTemplate<ApplicationEventRepository, ApplicationEventRepositoryTestFixture, ApplicationEvent> {
   private static final String BACKUP_EVENT_TABLE = "T_BACKUP_EVENT";

   public ApplicationEventRepositoryTest() {
      super( DomainTierTestConfiguration.FIXTURE_CONTAINER_DEFINITION_PATH );
   }

   @Override
   @Test
   public void testAdd_ForOwnedAttributesAndComponents() {
      assertThat( "ID correct", root.getId(), equalTo( databaseSpy.retrieveColumnFromRow( BACKUP_EVENT_TABLE, root.getId(), Integer.class, "id" ) ) );
      assertEquals( root.getOccuredOn().getValue(), databaseSpy.retrieveColumnFromRow( BACKUP_EVENT_TABLE, root.getId(), Timestamp.class, "timeStamp"));

      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      ApplicationEvent e = repository.findBackupEventById( work, root.getId() );
      work.finish();

      assertThat( "occuredOn correct", root.getOccuredOn(), equalTo( e.getOccuredOn() ));
   }

   @Override
   public void testAdd_ForReferencedAggregateRoots() {
   }

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
