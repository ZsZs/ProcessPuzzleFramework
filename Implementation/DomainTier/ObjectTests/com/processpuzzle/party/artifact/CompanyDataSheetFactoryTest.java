package com.processpuzzle.party.artifact;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.artifact.domain.ArtifactFactoryTest;
import com.processpuzzle.party.domain.Company;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class CompanyDataSheetFactoryTest extends ArtifactFactoryTest<CompanyDataSheetFactory, CompanyDataSheetFactoryTestFixture, CompanyDataSheet> {

   public CompanyDataSheetFactoryTest() {
      super( DomainTierTestConfiguration.FIXTURE_CONTAINER_DEFINITION_PATH );
   }

   @Override
   public void create_ForSuccess() {
      CompanyDataSheet companyDataSheet = sut.create( "ITKodex" );

      assertThat( companyDataSheet, notNullValue() );
      assertThat( companyDataSheet.getOrganizationName().getName(), equalTo( "ITKodex" ) );
   }

   @Ignore
   @Override
   @Test
   public void create_ForCollision() {}

   @Ignore
   @Test
   public void getCompany() {
      CompanyDataSheet companyDataSheet = sut.create( "ITKodex" );

      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      fixture.getArtifactRepository().add( work, companyDataSheet );
      work.finish();

      Integer companyId = companyDataSheet.getId();
      work = new DefaultUnitOfWork( true );
      companyDataSheet = (CompanyDataSheet) fixture.getArtifactRepository().findById( work, CompanyDataSheet.class, companyId );
      Company company = (Company) companyDataSheet.getCompany();
      work.finish();

      assertThat( company, notNullValue() );
      assertThat( company.getId(), notNullValue() );
   }
}
