package com.processpuzzle.artifact_management.control;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.processpuzzle.application.configuration.control.ServletContextIO;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.control.control.CommandDispatcher;
import com.processpuzzle.application.resource.domain.FoToPdfConverter;
import com.processpuzzle.application.resource.domain.XmlTransformer;
import com.processpuzzle.artifact.domain.PrintView;
import com.processpuzzle.artifact_type.domain.ArtifactPrintViewType;
import com.processpuzzle.user_session.domain.UserRequestManager;

public class ShowArtifactPrintViewCommand extends ShowArtifactViewCommand {
   private static String DTD_LOCATION = "/stylesheet/iso-lat1.dtd";
   private ResourceLoader resourceLoader;

   public void init( CommandDispatcher dispatcher ) {
      super.init( dispatcher );
      ProcessPuzzleContext applicationContext = UserRequestManager.getInstance().getApplicationContext();
      resourceLoader = applicationContext.getResourceLoader();
   }

   public String execute( CommandDispatcher dispatcher ) throws Exception {
      ServletContextIO io = new ServletContextIO( dispatcher.getServletContext() );
      HttpServletResponse out = dispatcher.getResponse();

      PrintView<?> printView = (PrintView<?>) subjectArtifact.getPrintView();
      String dtdRealPath = io.getRealPath( DTD_LOCATION );

      printView.setCharacterEntitiesDtd( dtdRealPath );

      String inputXml = printView.buildXml();
      ArtifactPrintViewType viewType = (ArtifactPrintViewType) printView.getType();
      String xslPath = viewType.getXmlToFoXsltPath();

      if( inputXml != null && xslPath != null ){
         ByteArrayOutputStream pdfStream = null;
         try{
            Resource xslResource = resourceLoader.getResource( xslPath );
            InputStream inputXsl = xslResource.getInputStream();
            String fo = XmlTransformer.transform( inputXml, inputXsl );
            pdfStream = FoToPdfConverter.convertFO2PDF( fo );
         }catch( Exception e ){
            throw new ShowArttifactPrintViewCommentException( subjectArtifact, xslPath, e );
         }

         byte[] content = pdfStream.toByteArray();

         out.setContentType( "application/pdf" );
         out.setContentLength( content.length );

         OutputStream outputStream = out.getOutputStream();
         outputStream.write( content );
         out.getOutputStream().flush();
      }

      return "";
   }

   public String getName() {
      return "ShowPrintView";
   }

}
