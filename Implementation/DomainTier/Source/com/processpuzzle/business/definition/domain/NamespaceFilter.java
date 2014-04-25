package com.processpuzzle.business.definition.domain;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.XMLFilterImpl;

public class NamespaceFilter extends XMLFilterImpl {

   @Override
   public void endElement( String uri, String localName, String qName ) throws SAXException {
      super.endElement( determineNamespace( uri ), localName, qName );
   }

   @Override
   public void startElement( String uri, String localName, String qName, Attributes atts ) throws SAXException {
      super.startElement( determineNamespace( uri ), localName, qName, atts );
   }
   
   //Protected, private helper methods
   private String determineNamespace( final String uri ){
      return ProcessPuzzleNamespaces.findNamespaceByUri( uri );
   }
}
