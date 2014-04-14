/*
 * Created on May 25, 2006
 */
package com.processpuzzle.artifact_management.control;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.w3c.dom.NodeList;

import com.mockrunner.mock.web.MockHttpServletResponse;
import com.mockrunner.servlet.BasicServletTestCaseAdapter;
import com.processpuzzle.application.configuration.domain.ApplicationContextFactory;
import com.processpuzzle.application.configuration.domain.ConfigurationSetUpException;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.control.control.CommandControllerServlet;
import com.processpuzzle.application.domain.Application;
import com.processpuzzle.application.security.control.LoginCommand;
import com.processpuzzle.artifact.domain.DefaultArtifactRepository;
import com.processpuzzle.artifact.domain.Document;
import com.processpuzzle.artifact.domain.DocumentFactory;
import com.processpuzzle.artifact.domain.DocumentRepository;
import com.processpuzzle.artifact_management.control.ArtifactLocator;
import com.processpuzzle.artifact_management.control.ReserveArtifactCommand;
import com.processpuzzle.artifact_management.control.XmlActionResponse;
import com.processpuzzle.configuration.webtier.ConfigurationConstants;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;

/**
 * @author zsolt.zsuffa
 */
public class ReserveArtifactCommandTest extends BasicServletTestCaseAdapter {
   @Mock
   private static Application mockApplicaton;
   private static ProcessPuzzleContext config = null;
   private static DefaultArtifactRepository repository;
   private static DocumentFactory documentFactory;
   private static DocumentRepository documentRepository;
   private Document aDocument;

   @BeforeClass public static void beforeAllTests() {
      MockitoAnnotations.initMocks( ReserveArtifactCommandTest.class );
      config = ApplicationContextFactory.create( mockApplicaton, ConfigurationConstants.CONFIGURATION_PROPERTY_FILE );
      try{
         config.setUp( Application.Action.start );
      }catch( ConfigurationSetUpException e ){
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      repository = config.getRepository( DefaultArtifactRepository.class );
      documentFactory = config.getEntityFactory( DocumentFactory.class );
      documentRepository = config.getRepository( DocumentRepository.class );
   }

   public void setUp() throws Exception {
      super.setUp();
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      aDocument = documentFactory.createDocument( "aDocument" );
      aDocument.setVersionControlled( true );
      repository.add( work, aDocument );
      createServlet( CommandControllerServlet.class );
      loginUser();
      executeCommand();
      work.finish();
   }

   public void tearDown() throws Exception {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      repository.delete( work, aDocument );
      aDocument = null;
      work.finish();
      super.tearDown();
   }

   private void executeCommand() {
      addRequestParameter( "action", ReserveArtifactCommand.COMMAND_NAME );
      addRequestParameter( ArtifactLocator.ARTIFACTNAME_IDENTIFIERNAME, aDocument.getName() );
      addRequestParameter( ReserveArtifactCommand.COMMENT_NAME_PARAM, aDocument.getName() );
      doPost();
   }

   private void loginUser() {
      addRequestParameter( "action", "Login" );
      addRequestParameter( LoginCommand.USER_PARAM_NAME, "1" );
      addRequestParameter( LoginCommand.PASSWORD_PARAM_NAME, "1" );
      doPost();
   }

   public final void testForXmlResponse() {
      MockHttpServletResponse mockResponse = getWebMockObjectFactory().getMockResponse();
      assertEquals( "The content type should be:", "text/xml", mockResponse.getContentType() );

      org.w3c.dom.Document xmlResponse = getOutputAsW3CDocument();
      org.w3c.dom.NodeList outcome = (NodeList) xmlResponse.getElementsByTagName( XmlActionResponse.OUTCOME_TAG_NAME );
      assertNotNull( "The response xml has outcome tag.", outcome );
      assertEquals( "The response xml has only one outcome tag.", 1, outcome.getLength() );
      assertNotNull( "The response value can be null.", outcome.item( 0 ).getNodeValue() );
   }

   public final void testForResponseOutcomeStatus() {
      org.w3c.dom.NodeList outcome = (NodeList) getOutputAsW3CDocument().getElementsByTagName( XmlActionResponse.OUTCOME_TAG_NAME );
      assertEquals( "The reservation should be succesful", "true", outcome.item( 0 ).getNodeValue() );
   }

   public final void testForArtifactReservationStatus() {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      Document reservedDocument = documentRepository.findByName( work, aDocument.getName() );
      assertTrue( "The document should be reserved.", reservedDocument.isReserved() );
      work.finish();
   }
}