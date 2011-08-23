package com.processpuzzle.sharedfixtures.domaintier;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import hu.itkodex.litest.fixture.GenericTestFixture;
import hu.itkodex.litest.fixture.TransientFreshFixture;

import com.processpuzzle.address.domain.CountryFactory;
import com.processpuzzle.address.domain.SettlementFactory;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.configuration.domain.PropertyKeys;
import com.processpuzzle.application.domain.Application;
import com.processpuzzle.application.security.domain.UserFactory;
import com.processpuzzle.artifact.domain.DefaultArtifactRepository;
import com.processpuzzle.persistence.domain.TestEntityRepository;

public class MockProcessPuzzleContext extends GenericTestFixture<ProcessPuzzleContext> implements TransientFreshFixture<ProcessPuzzleContext> {
   private UserFactory userFactory;
   private CountryFactory countryFactory;
   private RequestContextInMockedApplicationFixture mockedReqestContext; 
   private Application mockApplication;
   private ProcessPuzzleContext mockApplicationContext;
   private DefaultArtifactRepository mockDefaultArtifactRepository;
   private TestEntityRepository mockTestEntitityRepository;
   private SettlementFactory settlementFactory;
   
   public MockProcessPuzzleContext( RequestContextInMockedApplicationFixture mockedReqestContext ) {
      super();
      this.mockedReqestContext = mockedReqestContext;
   }
   
   public Application getApplication() { return mockApplication; }
   public ProcessPuzzleContext getApplicationContext() { return mockApplicationContext; }
   public DefaultArtifactRepository getMockDefaultArtifactRepository() { return mockDefaultArtifactRepository; }
   public TestEntityRepository getMockTestEntityRepository() { return mockTestEntitityRepository; }

   @Override
   protected void configureAfterSutInstantiation() {
   }

   @Override
   protected void configureBeforeSutInstantiation() {
      mockApplicationContext = mockedReqestContext.getApplicationContext();
      stubApplication();
      stubApplicationContext();
   }

   @Override
   protected ProcessPuzzleContext instantiateSUT() {
      return mockApplicationContext;
   }
   
   protected void stubApplication() {
      mockApplication = mockedReqestContext.getApplication();
   }
   
   protected void stubApplicationContext() {
      when( mockApplicationContext.getProperty( PropertyKeys.PRRODUCT_CATALOG_SCHEMA_PATH.getDefaultKey() )).thenReturn( "classpath:ProductCatalog.xsd" );
      
      stubFactories();
      stubRepositories();
   }

   protected void stubFactories() {
      userFactory = mock( UserFactory.class );
      when( mockApplicationContext.getEntityFactory( UserFactory.class )).thenReturn( userFactory );
      
      countryFactory = mock( CountryFactory.class );
      when( mockApplicationContext.getEntityFactory( CountryFactory.class )).thenReturn( countryFactory );
      
      settlementFactory = mock( SettlementFactory.class );
      when( mockApplicationContext.getEntityFactory( SettlementFactory.class )).thenReturn( settlementFactory );
   }

   protected void stubRepositories() {
      mockTestEntitityRepository = mock( TestEntityRepository.class );
      when( mockApplicationContext.getRepository( TestEntityRepository.class )).thenReturn(  mockTestEntitityRepository );
      
      mockDefaultArtifactRepository = mock( DefaultArtifactRepository.class );
      when( mockApplicationContext.getRepository( DefaultArtifactRepository.class )).thenReturn(  mockDefaultArtifactRepository );
   }

   @Override
   protected void releaseResources() {
      // TODO Auto-generated method stub
      
   }
}
