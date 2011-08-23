package com.processpuzzle.artifact_management.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidParameterException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import com.processpuzzle.application.control.control.CommandDispatcher;
import com.processpuzzle.artifact.domain.EditableHTMLDocument;
import com.processpuzzle.artifact.domain.EditableHTMLDocumentRepository;
import com.processpuzzle.artifact.domain.HTMLText;
import com.processpuzzle.party.domain.PartyRepository;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;

public class SaveHTMLTextCommand extends SaveArtifactCommand {

   private EditableHTMLDocument eHTMLDoc;

   public void init( CommandDispatcher dispatcher ) {
      super.init( dispatcher );

      boolean ccerror = false;
      try{
         eHTMLDoc = (EditableHTMLDocument) subjectArtifact;
      }catch( ClassCastException e ){
         ccerror = true;
      }
      if( ccerror || eHTMLDoc == null )
         throw new InvalidParameterException( "SaveHTMLText: Wrong ID!" );

      setUpResponse( dispatcher.getResponse() );
   }

   public String getName() {
      return "SaveHTMLText";
   }

   public String execute( CommandDispatcher dispatcher ) throws Exception {
      Properties properties = dispatcher.getProperties();
      PartyRepository partyRepository = (PartyRepository) applicationContext.getRepository( PartyRepository.class );
      EditableHTMLDocumentRepository htmlDocumentRepository = applicationContext.getRepository( EditableHTMLDocumentRepository.class );
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );

      boolean modified = false;

      Iterator<HTMLText> htmlTexts = eHTMLDoc.getHtmlTexts().iterator();
      while( htmlTexts.hasNext() ){
         HTMLText aHTMLText = (HTMLText) htmlTexts.next();
         String htmlText = properties.getProperty( aHTMLText.getDivId() );
         if( htmlText != null && !htmlText.equals( aHTMLText.getText() ) ){
            aHTMLText.setText( htmlText );
            modified = true;
         }
      }

      String newText = properties.getProperty( "newDivId" );
      if( newText != null ){
         Random rnd = new Random();
         String generatedId;
         while( existsDivId( generatedId = "editorDiv" + rnd.nextInt( 1000 ), eHTMLDoc.getHtmlTexts() ) )
            ;
         HTMLText newHTMLText = new HTMLText( generatedId, newText );
         newHTMLText.setCreationTimeStamp( Calendar.getInstance().getTime() );
         eHTMLDoc.getHtmlTexts().add( newHTMLText );
         addLineToResponse( "<divId>" + generatedId + "</divId>", dispatcher.getResponse() );
         modified = true;
      }

      if( modified )
         htmlDocumentRepository.updateEditableHTMLDocument( work, eHTMLDoc );

      work.finish();

      return "";
   }

   private boolean existsDivId( String divId, Set<HTMLText> htmlTexts ) {
      if( divId == null )
         return false;
      Iterator<HTMLText> hts = htmlTexts.iterator();
      while( hts.hasNext() ){
         HTMLText aHTMLText = (HTMLText) hts.next();
         if( divId.equals( aHTMLText.getDivId() ) )
            return false;
      }
      return false;
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

   private void addLineToResponse( String line, HttpServletResponse response ) {
      try{
         PrintWriter printWritter = response.getWriter();
         printWritter.println( line );
      }catch( IOException e ){}
   }
}