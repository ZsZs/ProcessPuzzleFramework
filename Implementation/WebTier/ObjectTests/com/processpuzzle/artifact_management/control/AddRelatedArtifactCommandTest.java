/*
 * Created on 2006.06.02.
 */
package com.processpuzzle.artifact_management.control;

import org.mockito.MockitoAnnotations;
import org.mockito.MockitoAnnotations.Mock;

import com.mockrunner.mock.web.MockHttpServletResponse;
import com.mockrunner.servlet.BasicServletTestCaseAdapter;
import com.processpuzzle.application.configuration.domain.ApplicationContextFactory;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.control.control.CommandControllerServlet;
import com.processpuzzle.application.domain.Application;
import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.application.security.domain.UserFactory;
import com.processpuzzle.application.security.domain.UserRepository;
import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.artifact.domain.DefaultArtifactRepository;
import com.processpuzzle.artifact.domain.DocumentFactory;
import com.processpuzzle.artifact_management.control.AddRelatedArtifactCommand;
import com.processpuzzle.artifact_management.control.ArtifactLocator;
import com.processpuzzle.configuration.webtier.ConfigurationConstants;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;

/**
 * @author peter.krima
 */
public class AddRelatedArtifactCommandTest extends BasicServletTestCaseAdapter {
   private static ProcessPuzzleContext applicationContext;
   @Mock
   private Application mockApplication;
   private DocumentFactory documentFactory;
   private DefaultArtifactRepository artifactRepository;
   private UserFactory userFactory;
   private UserRepository partyRepository;
   private String subjectArtifactName;
   private String targetArtifactName;
   private Artifact<?> artifact = null;
   private User gipszJakab;

   protected void setUp() throws Exception {
      super.setUp();

      MockitoAnnotations.initMocks( AddRelatedArtifactCommandTest.class );

      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      applicationContext = ApplicationContextFactory.create( mockApplication, ConfigurationConstants.CONFIGURATION_PROPERTY_FILE );
      applicationContext.setUp( Application.Action.start );

      userFactory = applicationContext.getEntityFactory( UserFactory.class );
      documentFactory = applicationContext.getEntityFactory( DocumentFactory.class );

      artifactRepository = (DefaultArtifactRepository) applicationContext.getRepository( DefaultArtifactRepository.class );
      partyRepository = (UserRepository) applicationContext.getRepository( UserRepository.class );

      gipszJakab = userFactory.createUser( "Gipsz Jakab", "psw" );
      partyRepository.addUser( work, gipszJakab );

      Artifact<?> subjectArtifact = documentFactory.createDocument( "document_1" );
      artifactRepository.add( work, subjectArtifact );
      subjectArtifactName = subjectArtifact.getName();

      Artifact<?> targetArtifact = documentFactory.createDocument( "document_2" );
      artifactRepository.add( work, targetArtifact );
      targetArtifactName = targetArtifact.getName();

      createServlet( CommandControllerServlet.class );
      executeCommand();
      work.finish();
   }

   protected void tearDown() throws Exception {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      artifactRepository.deleteByName( work, subjectArtifactName );
      artifactRepository.deleteByName( work, targetArtifactName );
      partyRepository.deleteUser( work, gipszJakab );
      artifactRepository.delete( work, artifact );
      work.finish();
      super.tearDown();
   }

   private final void executeCommand() {
      addRequestParameter( "action", AddRelatedArtifactCommand.ADD_RELATED_ARTIFACT_COMMAND_NAME );
      addRequestParameter( ArtifactLocator.ARTIFACTNAME_IDENTIFIERNAME, subjectArtifactName );
      addRequestParameter( AddRelatedArtifactCommand.TARGETARTIFACT_NAME_PARAM, targetArtifactName );
      doPost();
   }

   public void testAddRelatedArtifact_ContentType() {
      MockHttpServletResponse mockResponse = getWebMockObjectFactory().getMockResponse();
      assertEquals( "The content type should be:", "text/xml", mockResponse.getContentType() );
   }

   public void testAddRelatedArtifact_Relation() {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      Artifact<?> resultArtifact = artifactRepository.findByName( work, subjectArtifactName );
      assertEquals( "Number of related artifacts is 1", 1, resultArtifact.getRelatedArtifacts().size() );
      work.finish();
   }
}