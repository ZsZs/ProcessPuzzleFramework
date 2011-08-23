package com.processpuzzle.artifact_management.control;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.control.control.CommandDispatcher;
import com.processpuzzle.application.control.control.CommandInterface;
import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.artifact.domain.ArtifactFolder;
import com.processpuzzle.artifact.domain.ArtifactFolderRepository;
import com.processpuzzle.user_session.domain.UserRequestManager;

public class RetrieveArtifactFolderStructure implements CommandInterface {
   private final static String STRUCTURE_ROOT_TAGNAME = "artifactFolderStructure";
   private final static String ARTIFACTFOLDER_TAGNAME = "artifactFolder";
   private final static String ARTIFACT_TAGNAME = "artifact";
   protected XmlActionResponse actionResponse = null;
   private ArtifactFolderRepository artifactFolderRepository;

   public void init( CommandDispatcher dispatcher ) {
      ProcessPuzzleContext applicationContext = UserRequestManager.getInstance().getApplicationContext();
      artifactFolderRepository = applicationContext.getRepository( ArtifactFolderRepository.class );
      actionResponse = new XmlActionResponse();
   }

   @SuppressWarnings( "unchecked" )
   public String execute( CommandDispatcher dispatcher ) throws Exception {
      Collection<ArtifactFolder> rootArtifactFolders = (Collection<ArtifactFolder>) artifactFolderRepository.findRootArtifactFolders();

      actionResponse.addStringToBody( "<" + STRUCTURE_ROOT_TAGNAME + ">" );

      for( Iterator i = rootArtifactFolders.iterator(); i.hasNext(); )
         processFolder( (ArtifactFolder) i.next() );

      actionResponse.addStringToBody( "</" + STRUCTURE_ROOT_TAGNAME + ">" );

      buildResponse( dispatcher );
      return "";
   }

   private void processFolder( ArtifactFolder artifactFolder ) {
      actionResponse.addStringToBody( "<" + ARTIFACTFOLDER_TAGNAME + " name='" + artifactFolder.getName() + "'>" );
      for( Iterator<Artifact<?>> i = artifactFolder.getChildArtifacts().iterator(); i.hasNext(); ){
         Artifact<?> artifact = i.next();
         if( artifact instanceof ArtifactFolder )
            processFolder( (ArtifactFolder) artifact );
         else{
            actionResponse.addStringToBody( "<" + ARTIFACT_TAGNAME + " name='" + artifact.getName() + "' />" );
         }
      }
      actionResponse.addStringToBody( "</" + ARTIFACTFOLDER_TAGNAME + ">" );
   }

   protected void buildResponse( CommandDispatcher dispatcher ) throws IOException {
      HttpServletResponse response = dispatcher.getResponse();
      response.setContentType( "text/xml" );
      response.setCharacterEncoding( "UTF-8" );
      response.setHeader( "Cache-Control", "no-cache" );
      response.getWriter().write( actionResponse.getAsString() );

      System.out.println( actionResponse.getAsString() );
   }

   public String getName() {
      return RetrieveArtifactFolderStructure.class.getName();
   }

}