package com.processpuzzle.artifact.service;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import hu.itkodex.litest.template.ArtifactServiceTestTemplate;

import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.artifact.domain.ArtifactView;
import com.processpuzzle.artifact.domain.CommonArtifactServiceTestFixture;
import com.processpuzzle.artifact.domain.DefaultArtifactRepository;
import com.processpuzzle.commons.persistence.UnitOfWork;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;
import com.processpuzzle.user_session.domain.UserRequestManager;

public class ArtifactServiceTest extends ArtifactServiceTestTemplate<ArtifactService, CommonArtifactServiceTestFixture> {
   private ProcessPuzzleContext applicationContext;
   private DefaultArtifactRepository artifactRepository;
   private FindArtifactResponse response;
   @SuppressWarnings( "unchecked" )
   private ArtifactView returnedView;

   public ArtifactServiceTest() {
      super( DomainTierTestConfiguration.FIXTURE_CONTAINER_DEFINITION_PATH );
   }

   @Override
   public void beforeEachTest() {
      super.beforeEachTest();

      applicationContext = UserRequestManager.getInstance().getApplicationContext();
      artifactRepository = applicationContext.getRepository( DefaultArtifactRepository.class );
   }

   @Test
   public void findById_ReturnsAResponseObject() {
      invokeFindById();

      assertThat( response, notNullValue() );
      assertThat( response, instanceOf( FindArtifactResponse.class ) );
   }

   @SuppressWarnings( { "static-access", "unchecked" } )
   @Test
   public void findById_ReturnsRequestedView() {
      invokeFindById();

      ArtifactView expectedView = templatedFixture.getMockArtifact().findViewByName( templatedFixture.REQUESTED_VIEW_NAME );
      assertThat( returnedView, equalTo( expectedView ) );
   }

   @SuppressWarnings( "unchecked" )
   @Test
   public void findById_ReturnsDefaultViewIfNotSpecifiedInRequest() {
      FindArtifactRequest request = templatedFixture.getRequestForFindById();
      request.setRequestedViewName( null );
      returnedView = sut.findById( request ).getArtfifactView();

      ArtifactView expectedDefaultView = templatedFixture.getMockArtifact().getDefaultView();
      assertThat( returnedView, equalTo( expectedDefaultView ) );
   }

   @Test
   public void findById_UsesArtifactRepository() {
      invokeFindById();

      Integer id = templatedFixture.getRequestForFindById().getId();
      verify( artifactRepository ).findById( any( UnitOfWork.class ), eq( id ) );
   }

   @Ignore
   @Test
   public void findAll_ReturnsCoolectionOfViews() {
      invokeFindAll();

   }

   private void invokeFindAll() {
      response = sut.findAll( templatedFixture.getRequestForFindAll() );

      assertThat( response.getNumberOfArtifact(), equalTo( 3 ) );
   }

   private void invokeFindById() {
      response = sut.findById( templatedFixture.getRequestForFindById() );
      returnedView = response.getArtfifactView();
   }

}
