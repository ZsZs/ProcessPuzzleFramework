package com.processpuzzle.artifact_management.control;

import java.security.InvalidParameterException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.control.control.CommandDispatcher;
import com.processpuzzle.application.control.control.CommandInterface;
import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.user_session.domain.UserRequestManager;

public abstract class ArtifactCommand implements CommandInterface {
   private Log log = LogFactory.getLog( ArtifactCommand.class );
   protected String responseDocument = null;
   protected String subjectArtifactName;
   protected Artifact<?> subjectArtifact;
   protected User loggedInUser = null;
   protected ProcessPuzzleContext applicationContext;

   public ArtifactCommand() {}

   public void init( CommandDispatcher dispatcher ) {
      loggedInUser = UserRequestManager.getInstance().currentUser();
      applicationContext = UserRequestManager.getInstance().getApplicationContext();
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      try {
         ArtifactLocator artifactLocator = new ArtifactLocator( dispatcher );
         subjectArtifact = artifactLocator.findArtifact( work );
         if( subjectArtifact == null )
            throw new InvalidParameterException( "ArtifactCommand: The given artifact not found." );
         subjectArtifact.read( work );
         subjectArtifactName = subjectArtifact.getName();
         subjectArtifact.instantiateViews();
         retrieveRequestParameters( dispatcher );
      } catch ( Exception e) {
         work.discard();
         String message = "Exception raised during initialisation of ArtiFactCommand.";
         log.fatal( message, e );
         throw new ArtifactCommandException ( message, e );
      } finally {
         if( work.isStarted() ) work.finish();
      }
   }

   public String execute( CommandDispatcher dispatcher ) throws Exception {
      retrieveResponseDocument();
      return responseDocument;
   }

   public String getName() {
      return "ArtifactCommand";
   }

   protected abstract void retrieveRequestParameters( CommandDispatcher dispatcher );

   protected abstract void retrieveResponseDocument();
}