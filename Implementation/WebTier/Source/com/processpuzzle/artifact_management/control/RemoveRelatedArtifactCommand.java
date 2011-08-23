/*
 * Created on 2006.06.01.
 */
package com.processpuzzle.artifact_management.control;

import java.util.ArrayList;
import java.util.Iterator;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.control.control.CommandDispatcher;
import com.processpuzzle.application.security.control.AuthorizationException;
import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.artifact.domain.DefaultArtifactRepository;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.user_session.domain.UserRequestManager;

/**
 * @author peter.krima
 */
public class RemoveRelatedArtifactCommand extends ArtifactViewCommand {
   public static final String REMOVE_RELATED_ARTIFACT_COMMAND_NAME = "RemoveRelatedArtifact";
   public static final String TARGETARTIFACT_NAME_PARAM = "targetArtifactName";
   public static final String TARGETARTIFACT_ID_PARAM = "targetArtifactId";
   protected String targetArtifactName;
   protected String targetArtifactId;
   protected Artifact<?> targetArtifact;

   public void init( CommandDispatcher dispatcher ) {
      super.init( dispatcher );
      findTargetArtifact( dispatcher );
      if( loggedInUser != null ){
         removeRelatedArtifact( dispatcher );
      }else
         throw new AuthorizationException( loggedInUser.getUserName(), RemoveRelatedArtifactCommand.class.getSimpleName() );
   }

   protected void retrieveRequestParameters( CommandDispatcher dispatcher ) {
      super.retrieveRequestParameters( dispatcher );
      targetArtifactName = (String) dispatcher.getProperties().getProperty( TARGETARTIFACT_NAME_PARAM );
      targetArtifactId = (String) dispatcher.getProperties().getProperty( TARGETARTIFACT_ID_PARAM );
   }

   private void findTargetArtifact( CommandDispatcher dispatcher ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      if( targetArtifactId != null || targetArtifactName != null ){
         ProcessPuzzleContext applicationContext = UserRequestManager.getInstance().getApplicationContext();
         DefaultArtifactRepository artifactRepository = (DefaultArtifactRepository) applicationContext
               .getRepository( DefaultArtifactRepository.class );
         targetArtifact = artifactRepository.findByName( work, targetArtifactName );
         if( targetArtifact == null )
            targetArtifact = artifactRepository.findById( work, new Integer( targetArtifactId ) );
      }
      work.finish();
   }

   @SuppressWarnings("unchecked")
   protected void removeRelatedArtifact( CommandDispatcher dispatcher ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      ArrayList<String> targetArtifacts = null;
      if( dispatcher.getProperties().getProperty( TARGETARTIFACT_ID_PARAM ) != null ){
         targetArtifacts = new ArrayList<String>();
         targetArtifacts.add( dispatcher.getProperties().getProperty( TARGETARTIFACT_ID_PARAM ) );
      }else if( dispatcher.getPropertyValues().get( TARGETARTIFACT_ID_PARAM ) != null ){
         targetArtifacts = (ArrayList<String>) dispatcher.getPropertyValues().get( TARGETARTIFACT_ID_PARAM );
      }
      if( targetArtifacts != null ){
         ProcessPuzzleContext applicationContext = UserRequestManager.getInstance().getApplicationContext();
         DefaultArtifactRepository artifactRepository = (DefaultArtifactRepository) applicationContext
               .getRepository( DefaultArtifactRepository.class );
         for( Iterator<String> iter = targetArtifacts.iterator(); iter.hasNext(); ){
            Artifact targetArtifact = (Artifact) artifactRepository.findById( work, new Integer( iter.next() ) );
            subjectArtifact.removeRelatedArtifact( targetArtifact.getName() );
         }
         artifactRepository.update( work, subjectArtifact );
      }
      work.finish();
   }

   public String execute( CommandDispatcher dispatcher ) throws Exception {
      return subjectArtifactView.getType().getPresentationUri();
   }

   protected void retrieveResponseDocument() {}
}