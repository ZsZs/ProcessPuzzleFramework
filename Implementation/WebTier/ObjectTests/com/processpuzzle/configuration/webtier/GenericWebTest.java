package com.processpuzzle.configuration.webtier;
import java.io.IOException;
import java.net.URL;

import org.junit.Before;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.mockrunner.servlet.BasicServletTestCaseAdapter;


public class GenericWebTest extends BasicServletTestCaseAdapter {
   protected WebClient webClient;
   protected URL url;
   protected HtmlPage page;

   @Before
   public void beforeEachTests() throws FailingHttpStatusCodeException, IOException {
      webClient = new WebClient();
      url = new URL( TestConfigurationConstants.PROCESSPUZZLE_FRAMEWORK_URL );
      page = (HtmlPage) webClient.getPage(url);      
   }
}
