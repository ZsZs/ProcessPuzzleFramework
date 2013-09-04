package com.processpuzzle.fitnesse.persistence;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.processpuzzle.commons.persistence.PersistentObject;
import com.processpuzzle.commons.persistence.RepositoryResultSet;
import com.processpuzzle.litest.fixture.GenericTestFixture;
import com.processpuzzle.litest.fixture.TransientFreshFixture;
import com.processpuzzle.persistence.domain.SimpleResultSet;
import com.processpuzzle.persistence.domain.TestEntity;
import com.processpuzzle.persistence.domain.TestEntityRepository;
import com.processpuzzle.sharedfixtures.domaintier.MockProcessPuzzleContext;

public class InstantiatedRepositoryTester extends GenericTestFixture<RepositoryTester> implements TransientFreshFixture<RepositoryTester> {
   public static final String REPOSITORY_METHOD_NAME = "findTestEntityByName";
   public static final String METHOD_PARAMETER_TYPE = "String";
   public static final String METHOD_PARAMETER_NAME = "name";
   public static final String METHOD_PARAMETER_VALUE = "blabla";
   public static final String METHOD_PARAMETERS = METHOD_PARAMETER_NAME + ":" + METHOD_PARAMETER_TYPE + " = " + METHOD_PARAMETER_VALUE;
   private static final String REPOSITORY_CLASS_NAME = TestEntityRepository.class.getName();
   private MockProcessPuzzleContext mockApplicationContext;
   private TestEntity foundEntity;
   private RepositoryResultSet<? extends PersistentObject> expectedForFindTestEntityByName;

   public InstantiatedRepositoryTester( MockProcessPuzzleContext mockApplicationContext ) {
      super();
      this.mockApplicationContext = mockApplicationContext;
   }
   
   public RepositoryResultSet<? extends PersistentObject> getExpectedForFindTestEntityByName() {
      return expectedForFindTestEntityByName;
   }
   
   public TestEntity getFoundEntity() {
      return foundEntity;
   }
   
   public String getRepositoryClassName() {
      return REPOSITORY_CLASS_NAME;
   }

   @Override
   protected void configureAfterSutInstantiation() {
      foundEntity = mock( TestEntity.class );
      
      TestEntityRepository mockTestEntityRepository = mockApplicationContext.getMockTestEntityRepository();
      when( mockTestEntityRepository.findTestEntityByName( METHOD_PARAMETER_VALUE )).thenReturn( foundEntity );
//      when( mockTestEntityRepository.getSupportedAggregateRootClass() ).thenReturn( (Class<? extends AggregateRoot>) TestEntity.class );
      
      List<TestEntity> resultList = new ArrayList<TestEntity>();
      resultList.add( foundEntity );
      expectedForFindTestEntityByName = new SimpleResultSet<TestEntity>( resultList ); 
   }

   @Override
   protected RepositoryTester instantiateSUT() {
      return new RepositoryTester();
   }

   @Override
   protected void configureBeforeSutInstantiation() {
      // TODO Auto-generated method stub
      
   }

   @Override
   protected void releaseResources() {
      // TODO Auto-generated method stub
      
   }

}
