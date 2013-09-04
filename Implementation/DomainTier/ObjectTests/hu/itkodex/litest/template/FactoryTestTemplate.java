package hu.itkodex.litest.template;


import org.junit.Test;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.domain.Application;
import com.processpuzzle.commons.persistence.AggregateRoot;
import com.processpuzzle.commons.persistence.Repository;
import com.processpuzzle.litest.template.GenericTestTemplate;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.domain.EntityFactory;
import com.processpuzzle.persistence.domain.EntityIdentityCollitionException;

public abstract class FactoryTestTemplate<S extends EntityFactory<A>, F extends FactoryTestFixture<S,A>, A extends AggregateRoot> extends GenericTestTemplate<S, F, FactoryTestEnvironment<S, F>> {
   protected DefaultApplicationFixture applicationFixture;
   protected Application application;
   protected ProcessPuzzleContext applicationContext;
   protected String configurationDescriptorPath;
   
   protected FactoryTestTemplate( String fixtureContainerConfigurationPath ) {
      super( fixtureContainerConfigurationPath, FactoryTestEnvironment.class );
   }

   @Test public abstract void create_ForSuccess();
   @Test (expected = EntityIdentityCollitionException.class ) public abstract void create_ForCollision();
   
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
