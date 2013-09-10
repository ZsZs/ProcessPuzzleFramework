package com.processpuzzle.party.artifact;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import com.processpuzzle.litest.template.RepositoryTestTemplate;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class CompanyDataSheetRepositoryTest extends RepositoryTestTemplate<CompanyDataSheetRepository, CompanyDataSheetRepositoryTestFixture, CompanyDataSheet> {

   public CompanyDataSheetRepositoryTest() {
      super( DomainTierTestConfiguration.FIXTURE_CONTAINER_DEFINITION_PATH );
   }

   public @Override void testAdd_ForOwnedAttributesAndComponents() throws Exception {
      //EXCERCISE:
      CompanyDataSheet dataSheet = repository.findByName( fixture.getArtifactName() );
    
      //VERIFY:
      assertThat( dataSheet, notNullValue() );
      assertThat( dataSheet.getCompany(), notNullValue() ); 
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
      CompanyDataSheet dataSheet = repository.findById( testWork, root.getId() );
      
      //VERIFY:
      assertThat( dataSheet, notNullValue() );
      assertThat( dataSheet.getCompany(), notNullValue() );
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
