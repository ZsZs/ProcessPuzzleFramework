package com.processpuzzle.artifact.domain;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import hu.itkodex.litest.template.ArtifactServiceTestEnvironment;
import hu.itkodex.litest.template.ArtifactServiceTestFixture;

import com.processpuzzle.artifact.service.ArtifactService;
import com.processpuzzle.artifact.service.FindArtifactRequest;
import com.processpuzzle.artifact.service.FindArtifactResponse;
import com.processpuzzle.commons.persistence.UnitOfWork;

public class CommonArtifactServiceTestFixture extends ArtifactServiceTestFixture<ArtifactService>{
   public static final Integer ARTIFACT_ID = 111;
   public static final String ARTIFACT_NAME = "Test Artifact";
   public static final String REQUESTED_VIEW_NAME = "Custom form view";
   private Artifact<?> mockArtifact;
   @SuppressWarnings("unchecked")
   private ArtifactView mockArtifactView;
   private ArtifactService artifactService;
   private FindArtifactResponse mockFindArtifactViewResponse;
   private FindArtifactRequest mockFindArtifactRequest;

   //Constructors
   public CommonArtifactServiceTestFixture( ArtifactServiceTestEnvironment<ArtifactService, ?> testEnvironment ) {
      super( testEnvironment );
   }
   
   //Public accessors, mutators
   public FindArtifactResponse getExpectedResponseForFindById() { return mockFindArtifactViewResponse; }
   public FindArtifactRequest getRequestForFindAll() { return mockFindArtifactRequest; }
   public FindArtifactRequest getRequestForFindById() { return mockFindArtifactRequest; }
   public Artifact<?> getMockArtifact() { return mockArtifact; }
   public ArtifactView<?> getMockArtifactView() { return mockArtifactView; }

   //Protected, private helper methods
   @Override
   protected void configureAfterSutInstantiation() {
      mockFindArtifactViewResponse = mock( FindArtifactResponse.class );
   }

   @Override
   protected void configureBeforeSutInstantiation() {
      super.configureBeforeSutInstantiation();
      
      stubArtifact();
      stubArtifactRequest();
      stubArtifactRepository();
      
   }

   @Override
   protected ArtifactService instantiateSUT() {
      artifactService = new ArtifactService();
      return artifactService;
   }

   @Override
   protected void releaseResources() {
   }

   @SuppressWarnings("unchecked")
   private void stubArtifact() {
      mockArtifact = mock( Artifact.class );
      mockArtifactView = mock( PropertyView.class );
      
      when( mockArtifactView.getParentArtifact() ).thenReturn( (Artifact)mockArtifact );
      
      when( mockArtifact.getName() ).thenReturn( ARTIFACT_NAME );
      when( mockArtifact.findViewByName( REQUESTED_VIEW_NAME )).thenReturn( mockArtifactView );
      when( mockArtifact.getDefaultView() ).thenReturn(  (ArtifactView)mockArtifactView );
   }

   @SuppressWarnings("unchecked")
   private void stubArtifactRepository() {
      DefaultArtifactRepository artifactRepository = mockApplicationContextFixture.getMockDefaultArtifactRepository();
      when( artifactRepository.findById( any( UnitOfWork.class ), eq( ARTIFACT_ID ))).thenReturn( (Artifact) mockArtifact );
   }

   private void stubArtifactRequest() {
      mockFindArtifactRequest = mock( FindArtifactRequest.class );
      when( mockFindArtifactRequest.getId() ).thenReturn( ARTIFACT_ID );
      when( mockFindArtifactRequest.getRequestedViewName() ).thenReturn(  REQUESTED_VIEW_NAME );
   }

}
