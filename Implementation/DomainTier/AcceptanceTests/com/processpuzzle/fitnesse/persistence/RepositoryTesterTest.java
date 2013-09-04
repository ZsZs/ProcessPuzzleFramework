package com.processpuzzle.fitnesse.persistence;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.hamcrest.Matchers.typeCompatibleWith;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeThat;
import static org.mockito.Mockito.verify;
import fit.RowFixture;

import org.junit.Before;
import org.junit.Test;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.commons.persistence.Repository;
import com.processpuzzle.fundamental_types.domain.ParameterValueList;
import com.processpuzzle.litest.testcase.GenericTestSuite;
import com.processpuzzle.persistence.domain.TestEntityRepository;
import com.processpuzzle.sharedfixtures.domaintier.MockProcessPuzzleContext;

public class RepositoryTesterTest extends GenericTestSuite<RepositoryTester, RepositoryTesterFixture>{
   private static final String containerConfigurationPath = "classpath:com/processpuzzle/fitnesse/sharedfixtures/fixtures.xml";
   private MockProcessPuzzleContext applicationContextFixture;
   private InstantiatedRepositoryTester repositoryTesterFixture;
   private TestEntityRepository repository;
   
   public RepositoryTesterTest() {
      super( containerConfigurationPath );
   }

   @SuppressWarnings("unchecked")
   @Before
   public void beforeEachTests() throws ClassNotFoundException {
      
      applicationContextFixture = fixture.getFixture( MockProcessPuzzleContext.class );
      repositoryTesterFixture = fixture.getFixture( InstantiatedRepositoryTester.class );
      
      ProcessPuzzleContext applicationContext = applicationContextFixture.getApplicationContext(); 
      assumeThat( applicationContext.getRepository( TestEntityRepository.class ), notNullValue() );
      assumeThat( applicationContext.getRepository( TestEntityRepository.class ), equalTo( applicationContextFixture.getMockTestEntityRepository() ) );
      
      Class<? extends Repository<?>> repositoryClass = (Class<? extends Repository<?>>) Class.forName( repositoryTesterFixture.getRepositoryClassName() );
      Repository<?> repository = applicationContext.getRepository( repositoryClass );
      assumeThat( repository, instanceOf( TestEntityRepository.class ));

      sut.useRepository( repositoryTesterFixture.getRepositoryClassName() );
   }
   
   @Test
   public void useRepository_LazyInitializesTester() {
      assertThat( sut.getApplicationContext(), notNullValue() );
      assertThat( sut.getApplicationContext(), sameInstance( applicationContextFixture.getApplicationContext() ));
   }
   
   @Test
   public void useRepository_DeterminesRepository() {
      assertThat( sut.getRepositoryClass().equals( TestEntityRepository.class ), is( true ));
      assertThat( sut.getRepository(), notNullValue() );
   }
   
   @Test
   public void execute_ParsesParameterString() throws NoSuchRepositoryMethodException, RepositoryMethodExecutionException, ClassNotFoundException, InstantiationException, IllegalAccessException {
      sut.executeWithParameter( InstantiatedRepositoryTester.REPOSITORY_METHOD_NAME, InstantiatedRepositoryTester.METHOD_PARAMETERS );
      ParameterValueList parameterList = sut.getParameterValueList();
      
      assertThat( parameterList.size(), is( 1 ));
   }
   
   @Test
   public void execute_invokesRepositoryMethod() throws NoSuchRepositoryMethodException, RepositoryMethodExecutionException, ClassNotFoundException, InstantiationException, IllegalAccessException {
      sut.executeWithParameter( InstantiatedRepositoryTester.REPOSITORY_METHOD_NAME, InstantiatedRepositoryTester.METHOD_PARAMETERS );

      repository = (TestEntityRepository) sut.getRepository();
      verify( repository ).findTestEntityByName( InstantiatedRepositoryTester.METHOD_PARAMETER_VALUE );
      assertThat( sut.getRepositoryResultSet().size(), is( 1 ));
   }
   
   @Test
   public void expectedResultSet_ContainsPreviouslyRetrievedAggregateRoots() throws Exception {
      sut.executeWithParameter( InstantiatedRepositoryTester.REPOSITORY_METHOD_NAME, InstantiatedRepositoryTester.METHOD_PARAMETERS );
      
      RowFixture expectedResultSet = sut.expectedResultSet(); 
      assertThat( expectedResultSet, instanceOf( RowFixture.class ));
      assertThat( expectedResultSet.getTargetClass(), typeCompatibleWith( PersistentObjectWrapper.class ));
      assertThat( expectedResultSet.query().length, equalTo( 1 ));
   }
}
