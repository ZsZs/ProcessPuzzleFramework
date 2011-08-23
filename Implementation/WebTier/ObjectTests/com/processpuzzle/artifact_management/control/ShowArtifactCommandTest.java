package com.processpuzzle.artifact_management.control;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.MockitoAnnotations.Mock;

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
import com.processpuzzle.artifact.domain.Document;
import com.processpuzzle.artifact_type.domain.ArtifactTypeRepository;
import com.processpuzzle.artifact_type.domain.DocumentType;
import com.processpuzzle.configuration.webtier.ConfigurationConstants;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;

/**
 * @author zsolt.zsuffa
 */
public class ShowArtifactCommandTest extends BasicServletTestCaseAdapter {
   private static ProcessPuzzleContext config = null;
   @Mock private Application mockApplication;
   private UserFactory userFactory;
   private DefaultArtifactRepository artifactRepository;
   private ArtifactTypeRepository artifactTypeRepository;
   private UserRepository userRepository;
   private Integer artifactId;
   private String artifactName;
   private Integer documentTypeId;
   private User user;

   @Before
   protected void setUp() throws Exception {
      super.setUp();
      DefaultUnitOfWork work = new DefaultUnitOfWork(true);
      MockitoAnnotations.initMocks( ShowArtifactCommandTest.class );
      config = ApplicationContextFactory.create( mockApplication, ConfigurationConstants.CONFIGURATION_PROPERTY_FILE );
      config.setUp( Application.Action.start );

      userFactory = config.getEntityFactory( UserFactory.class );
      
      artifactRepository = (DefaultArtifactRepository) config.getRepository(DefaultArtifactRepository.class);
      artifactTypeRepository = (ArtifactTypeRepository) config.getRepository(ArtifactTypeRepository.class);
      userRepository = (UserRepository) config.getRepository( UserRepository.class );

      DocumentType documentType = new DocumentType("anArtifactType");
      artifactTypeRepository.addArtifactType(work, documentType);
      documentTypeId = documentType.getId();

      user = userFactory.createUser( "Gipsz Jakab", "psw" );
      userRepository.addUser( work, user );

      Artifact<?> artifact = new Document("document", documentType, user);
      artifactRepository.add(work, artifact);
      artifactId = artifact.getId();
      artifactName = artifact.getName();

      createServlet(CommandControllerServlet.class);
      addRequestParameter("action", "Show");
      work.finish();
   }

   @After
   protected void tearDown() throws Exception {
      DefaultUnitOfWork work = new DefaultUnitOfWork(true);
      Artifact<?> artifact = artifactRepository.findById(work, artifactId);
      artifactRepository.delete(work, artifact);
      artifactTypeRepository.deleteArtifactType(work, documentTypeId);
      userRepository.deleteUser(work, user);
      work.finish();
      super.tearDown();
   }

   @Test
   public final void testExecute_ForFindById() {
      addRequestParameter("artifactId", artifactId.toString());
      doPost();
      assertNotNull(getWebMockObjectFactory().getMockRequest().getAttribute("subjectArtifact"));
   }

   @Test
   public final void testExecute_ForFindByName() {
      addRequestParameter("artifactName", artifactName);
      doPost();
      assertNotNull(getWebMockObjectFactory().getMockRequest().getAttribute("subjectArtifact"));
   }
   
//  public final void testExecute_ForFindByType () {
//      addRequestParameter("artifactType", artifact.getType().getName());
//      doPost();
//      assertNotNull(getWebMockObjectFactory().getMockRequest().getAttribute("artifact"));
//  }
}