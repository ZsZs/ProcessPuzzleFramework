package com.processpuzzle.artifact_management.control;

import java.net.URL;

import junit.framework.TestCase;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.artifact.domain.Comment;
import com.processpuzzle.artifact.domain.CommentFactory;
import com.processpuzzle.artifact.domain.CommentList;
import com.processpuzzle.artifact.domain.CommentListFactory;
import com.processpuzzle.artifact.domain.Document;
import com.processpuzzle.artifact.domain.DocumentFactory;
import com.processpuzzle.user_session.domain.UserRequestManager;

public class ShowDocumentNotesCommandTest extends TestCase {
   private WebClient webClient;
   private URL url;
   private HtmlPage page;
   private Document aDocument;
   private static final String BASE_URL = "http://localhost:8001/ObjectPuzzleFrameworkWebTier/";
   private CommentFactory commentFactory;
   private CommentListFactory commentListFactory;
   private ProcessPuzzleContext applicationContext;
   private DocumentFactory documentFactory;

   static{
      // ObjectPuzzleConfiguration applicationConfiguration =
      // (ObjectPuzzleConfiguration)ObjectPuzzleConfiguration.getInstance();
      // applicationConfiguration.setUp("configuration/configuration.properties", new
      // ClassLoaderIO(Thread.currentThread().getContextClassLoader()));
      // assetRepository = (AssetRepository) Configuration.getInstance().getRepository(AssetRepository.class);
   }

   protected void setUp() throws Exception {
      super.setUp();
      applicationContext = UserRequestManager.getInstance().getApplicationContext();
      commentFactory = applicationContext.getEntityFactory( CommentFactory.class );
      commentListFactory = applicationContext.getEntityFactory( CommentListFactory.class );
      documentFactory = applicationContext.getEntityFactory( DocumentFactory.class );
      aDocument = documentFactory.createDocument( "aDocument" );
      webClient = new WebClient();
   }

   protected void tearDown() throws Exception {
      super.tearDown();
      // assetRepository.deleteDocument(aDocument.getId().toString());
   }

   public final void testForPage() throws Exception {
      url = new URL( BASE_URL + "CommandControllerServlet?action=ShowDocumentNotes&artifactId=" + aDocument.getId() );
      page = (HtmlPage) webClient.getPage( url );
      assertEquals( "Invoking the command returns page with title:", "Document comments", page.getTitleText() );
   }

   public final void testShowDocumentComments_ForPreviousComments() throws Exception {
      String testerId = "testerId";
      CommentList commentList = commentListFactory.createCommentList( "commentList" );
      Comment comment = commentFactory.create( "Teszt", "Text" );
      comment.setDivId( testerId );
      commentList.addComment( comment );
      aDocument.addComments( commentList );
      // assetRepository.updateDocument(aDocument);
      url = new URL( BASE_URL + "CommandControllerServlet?action=ShowDocumentNotes&artifactId=" + aDocument.getId() );
      page = (HtmlPage) webClient.getPage( url );
      // assertNotNull("We can find the division of the given comment",
      // page.getDocumentElement().getHtmlElementById(testerId));
   }
}