package com.processpuzzle.artifact_management.control;

//import org.jdom.Element;

import com.mockrunner.servlet.BasicServletTestCaseAdapter;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.control.control.CommandControllerServlet;
import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.artifact.domain.DefaultArtifactRepository;
import com.processpuzzle.artifact.domain.DocumentFactory;
import com.processpuzzle.configuration.webtier.WebTestConfiguration;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.user_session.domain.UserRequestManager;

public class GenericArtifactPropertyViewTest extends BasicServletTestCaseAdapter {
   private ProcessPuzzleContext applicationContext;
   private DocumentFactory documentFactory;
   
   public void setUp() throws Exception {
      super.setUp();
      new WebTestConfiguration();
      createServlet(CommandControllerServlet.class);
      
      applicationContext = UserRequestManager.getInstance().getApplicationContext();
      documentFactory = applicationContext.getEntityFactory( DocumentFactory.class );
   }

   public void tearDown() throws Exception {
      super.tearDown();
   }

   public void testJSP_ForTitle() throws Exception {
      DefaultUnitOfWork work = new DefaultUnitOfWork(true);
      DefaultArtifactRepository artifactRepository = applicationContext.getRepository( DefaultArtifactRepository.class);
      Artifact<?> artifact = documentFactory.createDocument("document");
      artifactRepository.add(work, artifact);

      addRequestParameter("action", "ShowView");
      addRequestParameter("artifactId", artifact.getId().toString());
      addRequestParameter("viewName", "propertyView");
      doPost();

      // Element root = getOutputAsJDOMDocument().getRootElement();
      // assertEquals("html", root.getName());
      // Element head = root.getChild("head");
      // Element title = head.getChild("title");

      // assertEquals("Invoking the command returns page with title:",
      // "GenericArtifactPropertyView.jsp", title.getText());

      artifactRepository.delete(work, artifact);
      work.finish();
   }

}