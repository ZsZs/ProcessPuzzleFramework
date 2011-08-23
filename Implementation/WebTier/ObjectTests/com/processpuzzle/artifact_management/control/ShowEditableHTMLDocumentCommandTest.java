package com.processpuzzle.artifact_management.control;

import java.net.URL;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import junit.framework.TestCase;

public class ShowEditableHTMLDocumentCommandTest extends TestCase {

	   private WebClient webClient;
	   private URL url;
	   private HtmlPage page;
	   private static final String BASE_URL = "http://localhost:8001/ObjectPuzzleFrameworkWebTier/"; 

	   protected void setUp() throws Exception {
	      super.setUp();
	      webClient = new WebClient();
	   }

	   protected void tearDown() throws Exception {
	      super.tearDown();
	   }

	   public final void testShowEditableHTMLDocumentCommandForMissingParameter () throws Exception {
	      url = new URL(BASE_URL + "CommandControllerServlet?action=ShowEditableHTMLDocument");
	      page = (HtmlPage) webClient.getPage(url);
	      assertEquals("Invoking ShowEditableHTMLDocumentCommand command returns:", "Front Controller Error", page.getTitleText());
	   } 
}