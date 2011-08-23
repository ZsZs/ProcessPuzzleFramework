/*
 * Created on 2006.05.12.
 */
package ProcessInstantiation.ProjectAdministration;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;
import ConstantDefinitions.StyleSheetConstants;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlLink;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import com.gargoylesoftware.htmlunit.html.HtmlTableDataCell;
import com.processpuzzle.internalization.domain.NoneExistingResourceKeyException;
import com.processpuzzle.internalization.domain.ProcessPuzzleLocale;
import com.processpuzzle.internalization.domain.XMLResourceBundle;

/**
 * @author peter.krima
 */

public class Artifact_AccessRightsTest extends TestCase {

   private static final String BASE_URL = "http://localhost:8080/ProcessPuzzle/";

   private static XMLResourceBundle bundleHU;
   private static XMLResourceBundle bundleEN;
   private WebClient webClient = new WebClient();
   private URL url;
   private HtmlPage accessrightsPage;

   static {
      String bundleName = "WebContent/WEB-INF/ResourceBundle";
      String localeNameHU = "hu";
      String localeNameEN = "en";

      bundleHU = new XMLResourceBundle(bundleName);
      bundleEN = new XMLResourceBundle(bundleName);

      try {
         bundleHU.loadResources( new ProcessPuzzleLocale(localeNameHU) );
         bundleEN.loadResources( new ProcessPuzzleLocale(localeNameEN) );
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   protected void setUp() throws Exception {
      super.setUp();
      url = new URL(BASE_URL + "CommandControllerServlet?action=ShowArtifactViewMock&viewName=Artifact_AccessRights");
      accessrightsPage = (HtmlPage) webClient.getPage(url);
   }

   protected void tearDown() throws Exception {
      super.tearDown();
      url = null;
      accessrightsPage = null;
   }

   public void testTitle() throws FailingHttpStatusCodeException, IOException {
      assertEquals("The aritfact list page's title should be:", "Artifact_AccessRights", accessrightsPage.getTitleText());
   }

   public void testStyleSheetReferences() {
      List links = accessrightsPage.getDocumentElement().getHtmlElementsByTagName(HtmlLink.TAG_NAME);
      for (Iterator iterator = links.iterator(); iterator.hasNext();) {
         HtmlLink link = (HtmlLink) iterator.next();
         if (link.getRelAttribute().equalsIgnoreCase("stylesheet")) {
            String styleReference = link.getHrefAttribute();
            assertTrue("The page should reference:", styleReference.matches(StyleSheetConstants.BASE_STYLE_REF + "|"
                  + StyleSheetConstants.CONTENT_STYLE_REF + "|" + StyleSheetConstants.FORM_STYLE_REF));
         }
      }
   }

   public void testField_User() throws NoneExistingResourceKeyException {
      String NAME_USER_HU = bundleHU.getText("ui.label.user");
      String NAME_USER_EN = bundleEN.getText("ui.label.user");

      //Check field label
      HtmlSpan label = (HtmlSpan) accessrightsPage.getHtmlElementById("userText");
      assertEquals("Check label's formatting style:", StyleSheetConstants.FIELD_LABEL_STYLE, label.getAttribute( "class" ) );
      assertTrue("Check label text", label.getFirstChild().asText().trim().matches(NAME_USER_HU + "|" + NAME_USER_EN));
   }

   @SuppressWarnings("deprecation")
   public void testForm() {
      HtmlForm propertiesForm = accessrightsPage.getFormByName("Artifact_AccessRightsForm");
      assertNotNull("The page should contain a properties form.", propertiesForm);

      //Note! The first child element of a HtmlElement is it's text node.
      HtmlDivision containerDiv = (HtmlDivision) propertiesForm.getFirstChild().getNextSibling();
      assertEquals("The fields are displayed within a 'readOnlyContainer' formatted division.", "readOnlyContainer", containerDiv.getAttribute( "class" ) );
   }

   public void testTableDataCell_UserCaption() throws NoneExistingResourceKeyException {
      String NAME_USERCAPTION_HU = bundleHU.getText("ui.label.userName");
      String NAME_USERCAPTION_EN = bundleEN.getText("ui.label.userName");

      //Check table's captions
      HtmlTableDataCell userCaption = (HtmlTableDataCell) accessrightsPage.getHtmlElementById("userName");
      assertTrue("Check 'user' caption", userCaption.getFirstChild().asText().matches(NAME_USERCAPTION_HU + "|" + NAME_USERCAPTION_EN));
   }

   public void testTableDataCell_ReadCaption() throws NoneExistingResourceKeyException {
      String NAME_READCAPTION_HU = bundleHU.getText("ui.Artifact_AccessRights.read");
      String NAME_READCAPTION_EN = bundleEN.getText("ui.Artifact_AccessRights.read");

      //Check table's captions
      HtmlTableDataCell readCaption = (HtmlTableDataCell) accessrightsPage.getHtmlElementById("read");
      assertTrue("Check 'read' caption", readCaption.getFirstChild().asText().matches(NAME_READCAPTION_HU + "|" + NAME_READCAPTION_EN));
   }

   public void testTableDataCell_WriteCaption() throws NoneExistingResourceKeyException {
      String NAME_WRITECAPTION_HU = bundleHU.getText("ui.Artifact_AccessRights.write");
      String NAME_WRITECAPTION_EN = bundleEN.getText("ui.Artifact_AccessRights.write");

      //Check table's captions
      HtmlTableDataCell writeCaption = (HtmlTableDataCell) accessrightsPage.getHtmlElementById("write");
      assertTrue("Check 'write' caption", writeCaption.getFirstChild().asText().matches(NAME_WRITECAPTION_HU + "|" + NAME_WRITECAPTION_EN));
   }

   public void testTableDataCell_DeleteCaption() throws NoneExistingResourceKeyException {
      String NAME_DELETECAPTION_HU = bundleHU.getText("ui.Artifact_AccessRights.delete");
      String NAME_DELETECAPTION_EN = bundleEN.getText("ui.Artifact_AccessRights.delete");

      //Check table's captions
      HtmlTableDataCell deleteCaption = (HtmlTableDataCell) accessrightsPage.getHtmlElementById("delete");
      assertTrue("Check 'del' caption", deleteCaption.getFirstChild().asText().matches(NAME_DELETECAPTION_HU + "|" + NAME_DELETECAPTION_EN));
   }

   //---
   //-Drop-down boxes

   public void testSelect_User() {

      boolean exist = true;
      try {
         /* HtmlSelect input = (HtmlSelect) */accessrightsPage.getHtmlElementById("selectUserDropDown");
      } catch (ElementNotFoundException e) {
         exist = false;
      }
      if (!exist)
         fail("The 'selectUser' input doesn't exists.");
   }

   public void testTableRow() {

      boolean rowexist = true;
      try {
         accessrightsPage.getHtmlElementById("tableRow");
      } catch (ElementNotFoundException e) {
         rowexist = false;
      }

      if (rowexist == true) {

         boolean readCBexist = false;
         try {
            assertNotNull("If the table has got any row, then it must have contain a readCheckBox", accessrightsPage.getHtmlElementById("readCB"));
         } catch (ElementNotFoundException e) {
            readCBexist = true;
         }
         if (!readCBexist)
            fail("The 'readCB' input doesn't exists.");

         boolean writeCBexist = false;
         try {
            assertNotNull("If the table has got any row, then it must have contain a writeCheckBox",accessrightsPage.getHtmlElementById("writeCB"));
         } catch (ElementNotFoundException e) {
            writeCBexist = true;
         }
         if (!writeCBexist)
            fail("The 'writeCB' input doesn't exists.");

         boolean delCBexist = false;
         try {
            assertNotNull("If the table has got any row, then it must have contain a delCheckBox",accessrightsPage.getHtmlElementById("delCB"));
         } catch (ElementNotFoundException e) {
            delCBexist = true;
         }
         if (!delCBexist)
            fail("The 'delCB' input doesn't exists.");

         boolean createCBexist = false;
         try {
            assertNotNull("",accessrightsPage.getHtmlElementById("createCB"));
         } catch (ElementNotFoundException e) {
            createCBexist = true;
         }
         if (!createCBexist)
            fail("The 'createCB' input doesn't exists.");

      }

   }

}