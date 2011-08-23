package com.processpuzzle.fundamental_types.uniqueidentifier.domain;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import hu.itkodex.litest.template.RepositoryTestTemplate;

import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class LastIdNumberRepositoryTest extends RepositoryTestTemplate<LastIdNumberRepository, LastIdNumberRepositoryTestFixture, LastIdNumber> {

   public LastIdNumberRepositoryTest() {
      super( DomainTierTestConfiguration.FIXTURE_CONTAINER_DEFINITION_PATH );
   }

   @Override
   public void testAdd_ForOwnedAttributesAndComponents() {
      assertEquals( databaseSpy.retrieveColumnFromRow( "T_LAST_ID_NUMBER", root.getId(), String.class, "idType" ), root.getIdType() );
      assertEquals( databaseSpy.retrieveColumnFromRow( "T_LAST_ID_NUMBER", root.getId(), Integer.class, "latestNumber" ), root.getLatestNumber() );
   }

   @Ignore
   @Test
   public void initializeLastIdNumber() {
      String idType = LastIdNumberRepositoryTestFixture.orderIdType;
      Integer initialNumber = 1;
      LastIdNumber initialIdNumber = repository.initializeLastIdNumber( idType, initialNumber );
       
      assertThat(initialIdNumber.getLatestNumber(), is(1));
   }
   
   @Ignore @Test
   public void findLatestIdByType() {
      LastIdNumber lastIdNumber = repository.findLatestIdByType( LastIdNumberRepositoryTestFixture.orderIdType );
      
      assertThat(lastIdNumber.getIdType(), equalTo( LastIdNumberRepositoryTestFixture.orderIdType ));
      assertThat(lastIdNumber.getLatestNumber(), is( 1 ));
     
   }
   
   @Override
   public void testAdd_ForReferencedAggregateRoots() {
   // TODO Auto-generated method stub

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
   public void testUpdate_ForOwnedAttributesAndComponents() throws Exception {
   // TODO Auto-generated method stub

   }

   @Override
   public void testUpdate_ForReferencedAggregateRoots() {
   // TODO Auto-generated method stub

   }
}
