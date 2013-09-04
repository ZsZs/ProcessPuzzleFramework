package hu.itkodex.litest.template;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.commons.persistence.Repository;
import com.processpuzzle.litest.template.GenericTestEnvironment;


public class RepositoryTestEnvironment<R extends Repository<?>, F extends RepositoryTestFixture<R,?>> extends GenericTestEnvironment<R> {
   private Class<F> repositoryTestFixtureClass;

   //Constructors
   public RepositoryTestEnvironment( Class<F> repositoryTestFixtureClass ) {
      this.repositoryTestFixtureClass = repositoryTestFixtureClass;
   }
   
   //Public accessors and mutators
   public ProcessPuzzleContext getApplicationContext() { return getApplicationFixture().getApplicationContext(); }
   
   @Override
   public R getSUT() {
      F repositoryTestFixture = this.getFixture( repositoryTestFixtureClass );
      if( repositoryTestFixture != null ) return repositoryTestFixture.getRepository();
      else return null;
   }
   
   
   //Properties
   public ConfigurableApplicationFixture getApplicationFixture() { return getFixture( ConfigurableApplicationFixture.class ); }
   public F getTestFixture() { return getFixture( repositoryTestFixtureClass ); }

   @Override
   protected void defineComponentTypes() {
      componentTypes.add( ConfigurableApplicationFixture.class );
      componentTypes.add( repositoryTestFixtureClass );
   }

   @Override
   protected void configureAfterSutInstantiation() {
   }

   @Override
   protected void configureBeforeSutInstantiation() {
   }

   @Override
   protected void releaseResources() {
   }
}
