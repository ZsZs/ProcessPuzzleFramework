package hu.itkodex.litest.template;

import hu.itkodex.commons.generics.GenericTypeParameterInvestigator;
import hu.itkodex.commons.persistence.AggregateRoot;
import hu.itkodex.commons.persistence.Entity;
import hu.itkodex.commons.persistence.Repository;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.domain.EntityFactory;

public abstract class FactoryTestFixture<S extends EntityFactory<E>, E extends Entity>  extends GenericTemplatedFixture<S> {
   private ConfigurableApplicationFixture applicationFixture;
   protected ProcessPuzzleContext applicationContext;
   protected S factory;

   protected FactoryTestFixture( FactoryTestEnvironment<S, ?> testEnvironment ) {
      super( testEnvironment );
      this.applicationFixture = testEnvironment.getApplicationFixture();
   }
   
   @SuppressWarnings("unchecked")
   @Override
   protected void configureBeforeSutInstantiation() {
      Class<? extends Entity> entityClass = (Class<? extends Entity>) GenericTypeParameterInvestigator.getTypeParameter( this.getClass(), 1 );
      applicationContext = applicationFixture.getApplicationContext();
      factory = (S) applicationContext.getEntityFactoryByEntityClass( entityClass );
   }

   @Override
   protected S instantiateSUT() {
      return factory;
   }

   @Override
   protected void releaseResources() {
      // TODO Auto-generated method stub
      
   }

   @SuppressWarnings("unchecked")
   protected void saveAggregateRoot( AggregateRoot aggregateRoot ) {
      Repository repository = applicationContext.getRepositoryByEntityClass( aggregateRoot.getClass() );
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      repository.add( work, aggregateRoot );
      work.finish();
   }
   
   @SuppressWarnings("unchecked")
   protected void deleteAggregateRoot( AggregateRoot aggregateRoot ) {
      Repository repository = applicationContext.getRepositoryByEntityClass( aggregateRoot.getClass() );
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      repository.delete( work, aggregateRoot );
      work.finish();
   }
}
