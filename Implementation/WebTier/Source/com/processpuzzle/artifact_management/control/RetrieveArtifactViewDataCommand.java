package com.processpuzzle.artifact_management.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.processpuzzle.application.control.control.CommandDispatcher;
import com.processpuzzle.artifact.domain.ArtifactView;

public class RetrieveArtifactViewDataCommand extends ArtifactViewCommand {
   private static final String MODE_PARAMETER_NAME = "mode";
   public static final String METHOD_PARAMETER_NAME = "method";
   public static final String COMMAND_NAME = "RetrieveDataView";
   protected XmlActionResponse xmlData = null;

   public void init( CommandDispatcher dispatcher ) {
      super.init( dispatcher );
   }

   public String execute( CommandDispatcher dispatcher ) throws Exception {
      buildXmlResponse( dispatcher, this.subjectArtifactView );
      return "";
   }

   @SuppressWarnings("unchecked")
   private void buildXmlResponse( CommandDispatcher dispatcher, ArtifactView<?> view ) throws IOException {
      HttpServletResponse response = dispatcher.getResponse();
      String dataRetrieveMode = dispatcher.getRequest().getParameter( MODE_PARAMETER_NAME );
      String dataRetrieverMethod = dispatcher.getRequest().getParameter( METHOD_PARAMETER_NAME );
      Map<?, ?> requestParameters = dispatcher.getRequest().getParameterMap();
      Map<String, String> dataRetrieverMethodParameters = new HashMap<String, String>();
      for( Iterator requestParametersIterator = requestParameters.entrySet().iterator(); requestParametersIterator.hasNext(); ){
         Map.Entry<?,?> reqestParametersEntry = (Map.Entry<?,?>) requestParametersIterator.next();
         String key = reqestParametersEntry.getKey().toString();
         if( key.startsWith( "par" ) ){
            String value = ((String[]) reqestParametersEntry.getValue())[0];
            dataRetrieverMethodParameters.put( key, value );
         }
      }
      String responseValue = "";
      if( dataRetrieveMode.equals( "xml" ) ){
         responseValue += "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
         response.setContentType( "text/xml; charset=UTF-8" );
      }else if( dataRetrieveMode.equals( "text" ) ){
         response.setContentType( "text/plain" );
      }
      responseValue += view.getData( dataRetrieverMethod, dataRetrieverMethodParameters );
      response.setCharacterEncoding( "UTF-8" );
      response.setHeader( "Cache-Control", "no-cache" );
      System.out.println( "data: " + responseValue );
      response.getWriter().write( responseValue );
   }

   public String getName() {
      return "RetreiveDataView";
   }

   protected void retrieveRequestParameters( CommandDispatcher dispatcher ) {
      super.retrieveRequestParameters( dispatcher );
   }

   protected void retrieveResponseDocument() {

   }
}
