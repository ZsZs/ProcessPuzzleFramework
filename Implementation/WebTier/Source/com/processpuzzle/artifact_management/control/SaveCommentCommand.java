package com.processpuzzle.artifact_management.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidParameterException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import com.processpuzzle.application.control.control.CommandDispatcher;
import com.processpuzzle.application.security.control.LoggedUserLocator;
import com.processpuzzle.artifact.domain.Comment;
import com.processpuzzle.artifact.domain.CommentList;
import com.processpuzzle.artifact.domain.DefaultArtifactRepository;
import com.processpuzzle.fundamental_types.domain.TimePoint;
import com.processpuzzle.party.domain.PartyRepository;
import com.processpuzzle.party.domain.Person;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;

public class SaveCommentCommand extends ArtifactCommand {

   protected CommentList commentList;

   public void init( CommandDispatcher dispatcher ) {
      super.init( dispatcher );

      boolean ccerror = false;
      try{
         commentList = (CommentList) subjectArtifact;
      }catch( ClassCastException e ){
         ccerror = true;
      }
      if( ccerror || commentList == null )
         throw new InvalidParameterException( "SaveComment: Wrong ID!" );

      setUpResponse( dispatcher.getResponse() );
   }

   public String getName() {
      return "SaveComment";
   }

   public String execute( CommandDispatcher dispatcher ) throws Exception {
      Properties properties = dispatcher.getProperties();
      PartyRepository partyRepository = (PartyRepository) applicationContext.getRepository( PartyRepository.class );
      DefaultArtifactRepository artifactRepository = (DefaultArtifactRepository) applicationContext.getRepository( DefaultArtifactRepository.class );
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );

      boolean modified = false;

      Iterator<?> htmlTexts = commentList.getComments().iterator();
      while( htmlTexts.hasNext() ){
         Comment comment = (Comment) htmlTexts.next();
         if( comment.getDivId() == null || (comment.getDivId() != null && comment.getDivId().equals( "" )) ){
            comment.setDivId( commentList.getGeneratedDivId() );
            modified = true;
         }
         String htmlText = properties.getProperty( comment.getDivId() );
         if( htmlText != null && !htmlText.equals( comment.getText() ) ){
            comment.setText( htmlText );
            modified = true;
         }
      }

      String newText = properties.getProperty( "newDivId" );
      if( newText != null ){
         String generatedId = commentList.getGeneratedDivId();
         Person loggedUser = LoggedUserLocator.locate( dispatcher );
         Comment newComment = new Comment( loggedUser, null, newText );
         newComment.setDivId( generatedId );
         Date now = Calendar.getInstance().getTime();
         newComment.setCreationTimeStamp( now );
         newComment.setCreationDate( new TimePoint( now ) );
         commentList.appendComment( newComment );
         addLineToResponse( "<divId>" + generatedId + "</divId>", dispatcher.getResponse() );
         modified = true;
      }

      if( modified )
         artifactRepository.update( work, commentList );

      work.finish();

      return "";
   }

   private void setUpResponse( HttpServletResponse response ) {
      response.setContentType( "text/xml" );
      response.setCharacterEncoding( "UTF-8" );
      response.setHeader( "Cache-Control", "no-cache" );
      try{
         PrintWriter responseWriter = response.getWriter();
         responseWriter.println( "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" );
      }catch( IOException e ){}
   }

   public void addLineToResponse( String line, HttpServletResponse response ) {
      try{
         PrintWriter printWritter = response.getWriter();
         printWritter.println( line );
      }catch( IOException e ){}
   }

   protected void retrieveRequestParameters( CommandDispatcher dispatcher ) {}

   protected void retrieveResponseDocument() {}
}