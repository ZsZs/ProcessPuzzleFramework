package com.processpuzzle.business.definition.domain;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.junit.Test;
import org.w3c.dom.Document;

import com.processpuzzle.artifact_type_group.domain.ArtifactTypeGroup;
import com.processpuzzle.party.domain.Company;
import com.processpuzzle.party.domain.Person;
import com.processpuzzle.party.partytype.domain.PartyType;

public class BusinessDefinitionMarshallTest {
   private BusinessDefinition businessDefinition;
   private Document outputDocument;
   private ArtifactTypeGroup systemAdministrationGroup;

   @Test public void marhallBusinessDefinition() throws TransformerException{
      defineTestData();
      instantiateOutputDocument();
      marshallBusinessDefinition();
      printOutputToConsole();
   }

   //Private helper methods
   private void defineArtifactTypeGroups() {
      systemAdministrationGroup = new ArtifactTypeGroup( "SystemAdministration" );
      businessDefinition.addArtifactTypeGroup( systemAdministrationGroup );
   }

   private void definePartyTypes() {
      PartyType personType = new PartyType( "PersonType" );
      personType.setDescription( "Defines human beings playing a role in different context." );
      personType.setPartyClassName( Person.class.getCanonicalName() );
      
      PartyType companyType = new PartyType( "CompanyType" );
      companyType.setDescription( "Defines a business targeted organization type." );
      companyType.setPartyClassName( Company.class.getCanonicalName() );
      
      businessDefinition.addPartyType( personType );
      businessDefinition.addPartyType( companyType );
   }
   
   private void defineTestData() {
      businessDefinition = new BusinessDefinition();
      definePartyTypes();
      defineArtifactTypeGroups();
    }

   private void instantiateOutputDocument() {
      try{
         DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
         documentBuilderFactory.setNamespaceAware(true);
         DocumentBuilder documentBuilder;
         documentBuilder = documentBuilderFactory.newDocumentBuilder();
         outputDocument = documentBuilder.newDocument();
      }catch( ParserConfigurationException e1 ){
         e1.printStackTrace();
      }
   }

   private void marshallBusinessDefinition() {
      JAXBContext jaxbContext = null;
      try{
         jaxbContext = JAXBContext.newInstance( new Class[] { BusinessDefinition.class } );
         Marshaller marshaller = jaxbContext.createMarshaller();
         marshaller.marshal( businessDefinition, outputDocument );
      }catch( JAXBException e ){
         e.printStackTrace();
      }
   }

   private void printOutputToConsole() throws TransformerException{
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      transformer.setOutputProperty( OutputKeys.INDENT, "yes" );
      transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "3");
      DOMSource source = new DOMSource( outputDocument );
      StringWriter writer = new StringWriter();
      transformer.transform( source, new StreamResult( writer ));
      
      System.out.println( writer.getBuffer().toString() );
   }

}
