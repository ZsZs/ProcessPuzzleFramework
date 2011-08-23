package com.processpuzzle.artifact_management.control;

import org.mockito.MockitoAnnotations;
import org.mockito.MockitoAnnotations.Mock;

import com.mockrunner.mock.web.MockHttpServletRequest;
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
import com.processpuzzle.artifact.domain.PropertyView;
import com.processpuzzle.configuration.webtier.ConfigurationConstants;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;

/**
 * @author zsolt.zsuffa
 */
public class ShowArtifactViewCommandTest extends BasicServletTestCaseAdapter {
   private static ProcessPuzzleContext config = null;
   private static DefaultArtifactRepository arepository;
   private static UserRepository userRepository;
   private @Mock
   Application mockApplication;
   private UserFactory userFactory;
   private DocumentFactory documentFactory;
   private Artifact<?> artifact;
   private User user;

   protected void setUp() throws Exception {
      super.setUp();
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      MockitoAnnotations.initMocks( ShowArtifactViewCommandTest.class );
      config = ApplicationContextFactory.create( mockApplication, ConfigurationConstants.CONFIGURATION_PROPERTY_FILE );
      config.setUp( Application.Action.start );
      arepository = (DefaultArtifactRepository) config.getRepository( DefaultArtifactRepository.class );
      userRepository = (UserRepository) config.getRepository( UserRepository.class );

      userFactory = config.getEntityFactory( UserFactory.class );
      documentFactory = config.getEntityFactory( DocumentFactory.class );

      user = userFactory.createUser( "User", "psw" );
      userRepository.addUser( work, user );

      artifact = documentFactory.createDocument( "document" );
      artifact.addView( "PropertyView", new PropertyView<Artifact<?>>( artifact, "PropertyView", null ) );
      arepository.add( work, artifact );

      createServlet( CommandControllerServlet.class );
      addRequestParameter( "action", "ShowView" );
      work.finish();
   }

   protected void tearDown() throws Exception {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      arepository.delete( work, artifact );
      userRepository.deleteUser( work, user );
      work.finish();
      super.tearDown();
   }

   public final void testExecute_ForArtifactObjectResponse() {
      addRequestParameter( "artifactId", artifact.getId().toString() );
      addRequestParameter( "viewName", "PropertyView" );
      doPost();
      MockHttpServletRequest mockRequest = getWebMockObjectFactory().getMockRequest();
      assertNotNull( mockRequest.getAttribute( "artifactView" ) );
   }

   public final void testExecute_ForReletedArtifactsView() {
      addRequestParameter( "artifactName", artifact.getName() );
      addRequestParameter( "viewName", "RelatedArtifactsListView" );
      doPost();
      MockHttpServletRequest mockRequest = getWebMockObjectFactory().getMockRequest();
      assertNotNull( mockRequest.getAttribute( "artifactView" ) );
   }
}