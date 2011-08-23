package com.processpuzzle.artifacttype.service;

import org.springframework.ws.server.endpoint.AbstractDomPayloadEndpoint;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ArtifactTypeService extends AbstractDomPayloadEndpoint {

   @Override
   protected Element invokeInternal( Element requestElement, Document document ) throws Exception {
      String requestText = requestElement.getTextContent();
      System.out.println("Request text: " + requestText);
      Element responseElement = document.createElementNS("http://samples", "response");
      responseElement.setTextContent( "Hello." );
      return responseElement;
   }
}
