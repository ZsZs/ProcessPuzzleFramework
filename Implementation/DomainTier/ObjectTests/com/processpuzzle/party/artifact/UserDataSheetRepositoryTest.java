package com.processpuzzle.party.artifact;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import hu.itkodex.litest.template.RepositoryTestTemplate;

import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class UserDataSheetRepositoryTest extends RepositoryTestTemplate<UserDataSheetRepository, UserDataSheetRepositoryTestFixture, UserDataSheet> {

   public UserDataSheetRepositoryTest() {
      super( DomainTierTestConfiguration.FIXTURE_CONTAINER_DEFINITION_PATH );
   }

   public @Override void testAdd_ForOwnedAttributesAndComponents() throws Exception {
      //EXCERCISE:
      UserDataSheet dataSheet = repository.findByName( templatedFixture.getArtifactName() );
    
      //VERIFY:
      assertThat( dataSheet, notNullValue() );
      assertThat( dataSheet.getUser(), notNullValue() ); 
   }

   @Override
   public void testAdd_ForReferencedAggregateRoots() {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void testDelete_ForOwnedAttributesAndComponents() throws Exception {
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
      //EXCERCISE:
      UserDataSheet dataSheet = repository.findById( testWork, root.getId() );
      
      //VERIFY:
      assertThat( dataSheet, notNullValue() );
      assertThat( dataSheet.getUser(), notNullValue() );
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
