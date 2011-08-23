package com.processpuzzle.sharedfixtures.domaintier;

import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.artifact.domain.ArtifactFolderRepository;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.artifact_type.domain.ArtifactTypeFactory;
import com.processpuzzle.artifact_type.domain.ArtifactTypeRepository;
import com.processpuzzle.artifact_type.domain.SystemArtifactTypes;
import com.processpuzzle.user_session.domain.UserRequestManager;

public class ArtifactFixture {
   private ProcessPuzzleContext applicationContext;
   private ArtifactTypeRepository artifacTypeRepository;
   private ArtifactFolderRepository artifactFolderRepository;
   private ArtifactType folderType;
   private static ArtifactTypeFactory artifactTypeFactory;
   
   public void setUp() {
      applicationContext = UserRequestManager.getInstance().getApplicationContext();
      artifactTypeFactory = applicationContext.getEntityFactory( ArtifactTypeFactory.class );
      folderType = artifactTypeFactory.createArtifactType( SystemArtifactTypes.ARTIFACT_FOLDER.getName() );
      artifacTypeRepository = applicationContext.getRepository( ArtifactTypeRepository.class );
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      artifacTypeRepository.add( work, folderType );
      work.finish();
      
      artifactFolderRepository = applicationContext.getRepository( ArtifactFolderRepository.class );
   }
   
   public void tearDown() {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      artifacTypeRepository.delete( work, folderType );
      work.finish();
   }

   public ArtifactFolderRepository getArtifactFolderRepository() { return artifactFolderRepository; }
   
   public ProcessPuzzleContext getContext() { return applicationContext; }
}
