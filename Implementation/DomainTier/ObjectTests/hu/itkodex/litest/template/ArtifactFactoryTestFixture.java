package hu.itkodex.litest.template;

import hu.itkodex.commons.generics.GenericTypeParameterInvestigator;
import hu.itkodex.commons.persistence.UnitOfWork;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.artifact.domain.ArtifactFactory;
import com.processpuzzle.artifact.domain.ArtifactFolder;
import com.processpuzzle.artifact.domain.ArtifactFolderFactory;
import com.processpuzzle.artifact.domain.ArtifactFolderRepository;
import com.processpuzzle.artifact_type.domain.ArtifactTypeFactory;
import com.processpuzzle.artifact_type.domain.ArtifactTypeRepository;
import com.processpuzzle.artifact_type_group.domain.ArtifactTypeGroupFactory;
import com.processpuzzle.artifact_type_group.domain.ArtifactTypeGroupRepository;

public abstract class ArtifactFactoryTestFixture<S extends ArtifactFactory<A>, A extends Artifact<?>> extends GenericTemplatedFixture<S> {
   private DefaultApplicationFixture applicationFixture;
   protected ArtifactTypeFactory artifactTypeFactory;
   protected ArtifactTypeGroupFactory artifactTypeGroupFactory;
   protected ArtifactTypeGroupRepository artifactTypeGroupRepository;
   protected ArtifactTypeRepository artifactTypeRepository;
   protected Class<A> artifactClass;
   protected ProcessPuzzleContext applicationContext;
   protected S factory;
   protected ArtifactFolderFactory artifactFolderFactory;
   protected ArtifactFolderRepository artifactFolderRepository;

   protected ArtifactFactoryTestFixture( ArtifactFactoryTestEnvironment<S, ?> testEnvironment ) {
      super( testEnvironment );
      this.applicationFixture = testEnvironment.getApplicationFixture();
   }

   @SuppressWarnings("unchecked")
   @Override
   protected void configureBeforeSutInstantiation() {
      artifactClass = (Class<A>) GenericTypeParameterInvestigator.getTypeParameter( this.getClass(), 1 );
      applicationContext = applicationFixture.getApplicationContext();
      lookUpArtifactTypeGroupRepositoryAndFactory();
      lookUpArtifactTypeRepositoryAndFactory();
   }
   
   protected ArtifactFolder discoverContainingFolder( String instanceFolderPath, String instanceFolderName, UnitOfWork work ) {
      int indexOfNameStart = instanceFolderPath.lastIndexOf( instanceFolderName );
      String containingFolderPath = instanceFolderPath.substring( 0, indexOfNameStart -1 );
      ArtifactFolder containingFolder = artifactFolderRepository.findByPath( work, containingFolderPath );
      return containingFolder;
   }


   @Override
   protected S instantiateSUT() {
      factory = (S) applicationContext.getEntityFactory( sutClass );
      return factory;
   }

   private void lookUpArtifactTypeGroupRepositoryAndFactory(){
      artifactTypeGroupFactory = applicationContext.getEntityFactory( ArtifactTypeGroupFactory.class );
      artifactTypeGroupRepository = applicationContext.getRepository( ArtifactTypeGroupRepository.class );
   }

   private void lookUpArtifactTypeRepositoryAndFactory(){
      artifactTypeFactory = applicationContext.getEntityFactory( ArtifactTypeFactory.class );
      artifactTypeRepository = applicationContext.getRepository( ArtifactTypeRepository.class );
      artifactFolderFactory = applicationContext.getEntityFactory( ArtifactFolderFactory.class );
      artifactFolderRepository = applicationContext.getRepository( ArtifactFolderRepository.class );
   }
}
