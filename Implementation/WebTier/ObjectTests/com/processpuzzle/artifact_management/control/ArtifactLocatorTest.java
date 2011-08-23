/*
 * Created on May 19, 2006
 */
package com.processpuzzle.artifact_management.control;

import com.mockrunner.servlet.BasicServletTestCaseAdapter;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.control.control.CommandControllerServlet;
import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.artifact.domain.ArtifactSubClass;
import com.processpuzzle.artifact.domain.DefaultArtifactRepository;
import com.processpuzzle.artifact_type.domain.ArtifactTypeTestFixture;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.sharedfixtures.domaintier.ProcessPuzzleContextFixture;
import com.processpuzzle.sharedfixtures.webtier.WebTierTestConfiguration;
import com.processpuzzle.user_session.domain.UserRequestManager;

/**
 * @author zsolt.zsuffa
 */
public class ArtifactLocatorTest extends BasicServletTestCaseAdapter {
   private static DefaultArtifactRepository repository = (DefaultArtifactRepository) UserRequestManager.getInstance().getApplicationContext().getRepository(DefaultArtifactRepository.class);
   private ProcessPuzzleContextFixture applicationContextFixture;
   private ProcessPuzzleContext applicationContext;
   private ArtifactTypeTestFixture typeFixture = null;
   private User currentUser;
   private Artifact<?> anArtifact;

   protected void setUp() throws Exception {
      super.setUp();
      DefaultUnitOfWork work = new DefaultUnitOfWork(true);

      applicationContextFixture = ProcessPuzzleContextFixture.getInstance( WebTierTestConfiguration.CONFIGURATION_DESCRIPTOR_PATH );
      applicationContextFixture.setUp();
      
      applicationContext = applicationContextFixture.getApplicationContext();
      
      typeFixture = ArtifactTypeTestFixture.getInstance( applicationContext );
      typeFixture.setUp();

      currentUser = UserRequestManager.getInstance().currentUser();
      
      anArtifact = new ArtifactSubClass("anArtifact", typeFixture.getArtifactSubClassType(), currentUser);
      repository.add(work, anArtifact);
      createServlet(CommandControllerServlet.class);
      work.finish();
   }

   protected void tearDown() throws Exception {
      DefaultUnitOfWork work = new DefaultUnitOfWork(true);
      repository.delete(work, anArtifact);
      typeFixture.tearDown();
      work.finish();
      super.tearDown();
   }

   public final void testFindById() {
      addRequestParameter("artifactId", anArtifact.getId().toString());
   }

   public final void testFindByName() {
   // TODO Implement findByName().
   }

   public final void testFindByType() {
   // TODO Implement findByType().
   }

   public final void testFindArtifact() {
   // TODO Implement findArtifact().
   }
}