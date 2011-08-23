package com.processpuzzle.artifact_management.control;

import org.hibernate.exception.ConstraintViolationException;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.control.control.CommandDispatcher;
import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.artifact.domain.ArtifactFolder;
import com.processpuzzle.artifact.domain.DefaultArtifactRepository;
import com.processpuzzle.persistence.domain.RepositoryException;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.user_session.domain.UserRequestManager;

/**
 * @generatedBy CodePro at 2005.11.13. 6:28
 * @author zsolt.zsuffa
 */
public class DeleteArtifactCommand extends ArtifactActionCommand {
   public static final String COMMAND_NAME = "DeleteArtifact";
   protected Artifact<?> artifact;

   public void init( CommandDispatcher dispatcher ) {
      super.init( dispatcher );
   }

   public String getName() {
      return COMMAND_NAME;
   }

   protected void retrieveRequestParameters( CommandDispatcher dispatcher ) {}

   protected void retrieveResponseDocument() {}

   protected void doAction() {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      if( (subjectArtifact != null)
            && (((subjectArtifact.getType().getName().equals( "ArtifactFolder" )) && (((ArtifactFolder) subjectArtifact).getChildArtifacts() != null) && (((ArtifactFolder) subjectArtifact)
                  .getChildArtifacts().size() == 0)) || (!subjectArtifact.getType().getName().equals( "ArtifactFolder" ))) ){
         try{
            ProcessPuzzleContext applicationContext = UserRequestManager.getInstance().getApplicationContext();
            DefaultArtifactRepository artifactRepository = (DefaultArtifactRepository) applicationContext.getRepository( DefaultArtifactRepository.class );
            artifactRepository.delete( work, subjectArtifact );
            // if (artifactRepository.findArtifactByName( artifactName ) != null
            // ) {
            // actionResponse.addErrorDescription( "Artifact: '" + artifactName
            // + "' was not deleted!" );
            // actionResponse.setOutcome(false);
            // } else {
            // }
            actionResponse.setOutcome( true );
         }catch( ConstraintViolationException e ){
            actionResponse.addErrorDescription( e.getMessage() );
            actionResponse.setOutcome( false );
         }catch( RepositoryException e ){
            actionResponse.addErrorDescription( e.getMessage() );
            actionResponse.setOutcome( false );
         }catch( Exception e ){
            actionResponse.addErrorDescription( e.getMessage() );
            actionResponse.setOutcome( false );
         }
      }else if( (subjectArtifact != null)
            && (((subjectArtifact.getType().getName().equals( "ArtifactFolder" )) && (((ArtifactFolder) subjectArtifact).getChildArtifacts() != null) && (((ArtifactFolder) subjectArtifact)
                  .getChildArtifacts().size() > 0))) ){
         actionResponse.addErrorDescription( "artifactFolderNotEmpty" );
         actionResponse.setOutcome( false );
      }else{
         actionResponse.addErrorDescription( "artifactNotExist" );
         actionResponse.setOutcome( false );
      }
      work.finish();
   }
}