package hu.itkodex.litest.template;

import hu.itkodex.commons.persistence.AggregateRoot;
import hu.itkodex.commons.persistence.Repository;
import hu.itkodex.commons.persistence.UnitOfWork;
import hu.itkodex.commons.rdbms.DatabaseSpy;
import hu.itkodex.litest.fixture.CompositeFixture;

import java.sql.ResultSet;

import org.junit.Test;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.domain.GenericRepository;

/* Some important notes, about this code's history.
 * Originally I intended to instantiate only the repository - and a persistence strategy for it - under test.
 * It turned out that there is a need to write test whith the referenced aggregate roots.
 * This required to set up the hole ProcessPuzzleContext.
 * This 'safety' I only commented out the methods used by the original idea.
 */

public abstract class RepositoryTestTemplate<R extends GenericRepository<?>, F extends RepositoryTestFixture<R,?>, A extends AggregateRoot> extends GenericTestTemplate<R, F, RepositoryTestEnvironment<R,F>> implements RepositoryTest {
   protected static ProcessPuzzleContext applicationContext;
   protected String contextDescriptorPath;
   protected DatabaseSpy databaseSpy;
   protected F fixture;
   protected R repository;
   protected Class<R> repositoryClass;
   protected A root;
   protected ResultSet rootRecord;
   protected UnitOfWork testWork;

   protected RepositoryTestTemplate( String fixtureContainerConfigurationPath ) {
      super( fixtureContainerConfigurationPath, RepositoryTestEnvironment.class );
   }

   @Override public void beforeAllTests() {
      super.beforeAllTests();
      DefaultApplicationFixture applicationFixture = (DefaultApplicationFixture) ((CompositeFixture<?>) fixtureStrategy.getFixture()).getFixture( ConfigurableApplicationFixture.class );
      applicationContext = applicationFixture.getApplication().getContext();
   }
   
   @Override public void beforeEachTest() {
      super.beforeEachTest();
      databaseSpy = templatedFixture.getDatabaseSpy();
      root = (A) templatedFixture.getRoot();
   }

   @Test public abstract void testAdd_ForOwnedAttributesAndComponents() throws Exception;
   @Test public abstract void testAdd_ForReferencedAggregateRoots();
   @Test public abstract void testUpdate_ForOwnedAttributesAndComponents() throws Exception;
   @Test public abstract void testUpdate_ForReferencedAggregateRoots();
   @Test public abstract void testDelete_ForOwnedAttributesAndComponents() throws Exception;
   @Test public abstract void testFindById();
   @Test public abstract void testFindById_ForEagerLoadedComponents();
   @Test public abstract void testFindById_ForLazyLoadedComponents();
   @Test public abstract void testFindAll_ForResultCount();
   @Test public abstract void testFindByQuery_ForDirectAttributes();
   @Test public abstract void testFindByQuery_ForComponentAttributes();

   @SuppressWarnings("unchecked")
   protected void saveAggregateRoot( AggregateRoot aggregateRoot, ProcessPuzzleContext applicationContext ) {
      Repository repository = applicationContext.getRepositoryByEntityClass( aggregateRoot.getClass() );
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      repository.add( work, aggregateRoot );
      work.finish();
   }

   @SuppressWarnings("unchecked")
   protected void deleteAggregateRoot( AggregateRoot aggregateRoot, ProcessPuzzleContext applicationContext ) {
      Repository repository = applicationContext.getRepositoryByEntityClass( aggregateRoot.getClass() );
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      repository.delete( work, aggregateRoot );
      work.finish();
   }
   
}
