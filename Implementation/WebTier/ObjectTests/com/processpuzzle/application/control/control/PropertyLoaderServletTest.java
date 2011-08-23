/*
 * Created on Jan 22, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.application.control.control;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * @author zsolt.zsuffa
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PropertyLoaderServletTest {
   private WebClient webClient;
   private URL url;
   private HtmlPage page;
   private static final String BASE_URL = "http://localhost:8001/ObjectPuzzleFrameworkWebTier/"; 

   @Before
   protected void setUp() throws Exception {
      webClient = new WebClient();
   }

   @After
   protected void tearDown() throws Exception {
   }

   @Test
   public final void testPropertyLoaderServle () {
      try {
         url = new URL(BASE_URL + "PropertyLoaderServlet");
         page = (HtmlPage) webClient.getPage(url);
         assertEquals("If properties are successfuly loaded, the returned is:", "Properties.html", page.getTitleText());
      }
      catch ( MalformedURLException malformedURLException) { malformedURLException.printStackTrace();}
      catch ( FailingHttpStatusCodeException failingHttpStatusCodeException) {failingHttpStatusCodeException.printStackTrace();}
      catch ( IOException ioException) {ioException.printStackTrace();}      
   }
}
