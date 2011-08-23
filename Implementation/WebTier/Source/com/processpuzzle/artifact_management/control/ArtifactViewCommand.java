/*
 * Created on Jul 18, 2006
 */
package com.processpuzzle.artifact_management.control;

import java.security.InvalidParameterException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.processpuzzle.application.control.control.CommandDispatcher;
import com.processpuzzle.artifact.domain.ArtifactView;

public abstract class ArtifactViewCommand extends ArtifactCommand {
   public static final String METHOD_PARAMETER_NAME = "method";
   public static final String ID_PARAMETER_NAME = "id";
   public static String ARTIFACTVIEW_IDENTIFIERNAME = "viewName";
   public static String HELPER_NAME = "artifactView";
   private Logger log = LoggerFactory.getLogger( ArtifactViewCommand.class );
   private String method;
   private String id;
   protected String artifactViewIdentifier = "";
   protected ArtifactView<?> subjectArtifactView;

   public void init( CommandDispatcher dispatcher ) {
      super.init( dispatcher );
      retrieveArtifactView();
      subjectArtifactView.setCodeBase( dispatcher.getRequest().getRequestURL().toString() );
      subjectArtifactView.initializeView();
      subjectArtifactView.setLoggedInUser( loggedInUser );
      performAction();
      log.debug( subjectArtifactView.getLoggedInUser().getUserName() + " opening " + subjectArtifactView.getName() );
      dispatcher.getRequest().setAttribute( HELPER_NAME, subjectArtifactView );
   }

   protected void retrieveRequestParameters( CommandDispatcher dispatcher ) {
      method = dispatcher.getProperties().getProperty( METHOD_PARAMETER_NAME );
      id = dispatcher.getProperties().getProperty( ID_PARAMETER_NAME );
      artifactViewIdentifier = dispatcher.getProperties().getProperty( ARTIFACTVIEW_IDENTIFIERNAME );
      if( artifactViewIdentifier == null )
         throw new InvalidParameterException( "The parameter '" + ARTIFACTVIEW_IDENTIFIERNAME + "' is required!" );
   }

   protected void retrieveArtifactView() {
      subjectArtifactView = (ArtifactView<?>) subjectArtifact.getView( artifactViewIdentifier );
      if( subjectArtifactView == null )
         throw new InvalidParameterException( "Missing view or wrong view name!" );
   }

   public void performAction() {
      if( method != null && method.equals( "del" ) ){
         System.out.println( "Method: " + method );
         System.out.println( "Id: " + id );
         subjectArtifactView.delete( id );

         // to resolve bug
         retrieveArtifactView();
      }
   }

   protected ArtifactView<?> getSubjectArtifactView() {
      return subjectArtifactView;
   }
}
