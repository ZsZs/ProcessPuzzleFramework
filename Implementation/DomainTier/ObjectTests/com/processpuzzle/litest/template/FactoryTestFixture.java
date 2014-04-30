package com.processpuzzle.litest.template;


import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.domain.ConfigurableApplicationFixture;
import com.processpuzzle.commons.generics.GenericTypeParameterInvestigator;
import com.processpuzzle.commons.persistence.AggregateRoot;
import com.processpuzzle.commons.persistence.Entity;
import com.processpuzzle.commons.persistence.Repository;
import com.processpuzzle.litest.template.GenericTemplatedFixture;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.domain.EntityFactory;

public abstract class FactoryTestFixture<S extends EntityFactory<E>, E extends Entity>  extends GenericTemplatedFixture<S> {
   private ConfigurableApplicationFixture applicationFixture;
   protected ProcessPuzzleContext applicationContext;
   protected S factory;

   //Properties
   public ProcessPuzzleContext getApplicationContext(){ return applicationContext; }
   
   //Protected, private helper methods
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
   }

   @SuppressWarnings("unchecked")
   protected void saveAggregateRoot( AggregateRoot aggregateRoot ) {
      @SuppressWarnings( "rawtypes" )
      Repository repository = applicationContext.getRepositoryByEntityClass( aggregateRoot.getClass() );
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      repository.add( work, aggregateRoot );
      work.finish();
   }
   
   @SuppressWarnings("unchecked")
   protected void deleteAggregateRoot( AggregateRoot aggregateRoot ) {
      @SuppressWarnings( "rawtypes" )
      Repository repository = applicationContext.getRepositoryByEntityClass( aggregateRoot.getClass() );
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      repository.delete( work, aggregateRoot );
      work.finish();
   }
}
