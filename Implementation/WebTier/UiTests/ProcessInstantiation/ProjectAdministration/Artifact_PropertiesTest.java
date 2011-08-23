/*
 * Created on 2006.05.19.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ProcessInstantiation.ProjectAdministration;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;
import ConstantDefinitions.StyleSheetConstants;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlLink;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import com.processpuzzle.internalization.domain.NoneExistingResourceKeyException;
import com.processpuzzle.internalization.domain.ProcessPuzzleLocale;
import com.processpuzzle.internalization.domain.XMLResourceBundle;

/**
 * @author peter.krima
 */
public class Artifact_PropertiesTest extends TestCase {

   private static final String BASE_URL = "http://localhost:8001/ObjectPuzzleFrameworkWebTier/";

   private static XMLResourceBundle bundleHU;
   private static XMLResourceBundle bundleEN;
   private WebClient webClient = new WebClient();
   private URL url;
   private HtmlPage propertiesPage;

   static{
      String bundleName = "WebContent/WEB-INF/ResourceBundle";
      String localeNameHU = "hu";
      String localeNameEN = "en";

      bundleHU = new XMLResourceBundle( bundleName );
      bundleEN = new XMLResourceBundle( bundleName );

      try{
         bundleHU.loadResources( new ProcessPuzzleLocale( localeNameHU ) );
         bundleEN.loadResources( new ProcessPuzzleLocale( localeNameEN ) );
      }catch( Exception e ){
         e.printStackTrace();
      }
   }

   protected void setUp() throws Exception {
      super.setUp();
      url = new URL( BASE_URL + "CommandControllerServlet?action=ShowArtifactViewMock&viewName=Artifact_Properties" );
      propertiesPage = (HtmlPage) webClient.getPage( url );
   }

   protected void tearDown() throws Exception {
      super.tearDown();
      url = null;
      propertiesPage = null;
   }

   public void testTitle() throws FailingHttpStatusCodeException, IOException {
      assertEquals( "The properties page's title should be:", "Artifact_Properties", propertiesPage.getTitleText() );
   }

   public void testStyleReferences() {
      List links = propertiesPage.getDocumentElement().getHtmlElementsByTagName( HtmlLink.TAG_NAME );
      for( Iterator iterator = links.iterator(); iterator.hasNext(); ){
         HtmlLink link = (HtmlLink) iterator.next();
         if( link.getRelAttribute().equalsIgnoreCase( "stylesheet" ) ){
            String styleReference = link.getHrefAttribute();
            assertTrue( "The page should reference:", styleReference.matches( StyleSheetConstants.BASE_STYLE_REF + "|"
                  + StyleSheetConstants.CONTENT_STYLE_REF + "|" + StyleSheetConstants.FORM_STYLE_REF ) );
         }
      }
   }

   public void testPropertiesForm() {
      HtmlForm propertiesForm = propertiesPage.getFormByName( "Artifact_PropertiesForm" );
      assertNotNull( "The page should contain a properties form.", propertiesForm );

      // Note! The first child element of a HtmlElement is it's text node.
      HtmlDivision containerDiv = (HtmlDivision) propertiesForm.getFirstChild().getNextSibling();
      assertEquals( "The fields are displayed within a 'readOnlyContainer' formatted division.", "readOnlyContainer", containerDiv.getAttribute( "class" ) );
   }

   public void testFieldRows() {
      HtmlDivision containerDiv = (HtmlDivision) propertiesPage.getFormByName( "Artifact_PropertiesForm" ).getFirstChild().getNextSibling();
      for( HtmlElement childElement : containerDiv.getChildElements() ){
         HtmlDivision rowDiv = (HtmlDivision) childElement;
         assertNotNull( "Each field row is surrounded with a division tag.", rowDiv );
         assertEquals( "This row division is formatting style is:", StyleSheetConstants.ROW_STYLE, rowDiv.getAttribute( "class" ) );
      }
   }

   public void testField_CreationDate() throws NoneExistingResourceKeyException {
      String NAME_CREATIONDATE_HU = bundleHU.getText( "ui.label.creationDate" );
      String NAME_CREATIONDATE_EN = bundleEN.getText( "ui.label.creationDate" );

      // Check field label
      HtmlSpan label = (HtmlSpan) propertiesPage.getHtmlElementById( "creationDate_Label" );
      assertEquals( "Check label's formatting style:", StyleSheetConstants.FIELD_LABEL_STYLE, label.getAttribute( "class" ) );
      assertTrue( "Check label text", label.getFirstChild().asText().trim().matches( NAME_CREATIONDATE_HU + "|" + NAME_CREATIONDATE_EN ) );
   }

   public void testField_LastModDate() throws NoneExistingResourceKeyException {
      String NAME_LASTMODDATE_HU = bundleHU.getText( "ui.label.lastModDate" );
      String NAME_LASTMODDATE_EN = bundleEN.getText( "ui.label.lastModDate" );

      // Check field label
      HtmlSpan label = (HtmlSpan) propertiesPage.getHtmlElementById( "lastModDate_Label" );
      assertEquals( "Check label's formatting style:", StyleSheetConstants.FIELD_LABEL_STYLE, label.getAttribute( "class" ) );
      assertTrue( "Check label text", label.getFirstChild().asText().trim().matches( NAME_LASTMODDATE_HU + "|" + NAME_LASTMODDATE_EN ) );
   }

   public void testField_LastModifier() throws NoneExistingResourceKeyException {
      String NAME_LASTMODIFIER_HU = bundleHU.getText( "ui.label.lastModifier" );
      String NAME_LASTMODIFIER_EN = bundleEN.getText( "ui.label.lastModifier" );

      // Check field label
      HtmlSpan label = (HtmlSpan) propertiesPage.getHtmlElementById( "lastModifier_Label" );
      assertEquals( "Check label's formatting style:", StyleSheetConstants.FIELD_LABEL_STYLE, label.getAttribute( "class" ) );
      assertTrue( "Check label text", label.getFirstChild().asText().trim().matches( NAME_LASTMODIFIER_HU + "|" + NAME_LASTMODIFIER_EN ) );
   }

   public void testField_Responsible() throws NoneExistingResourceKeyException {
      String NAME_RESPONSIBLE_HU = bundleHU.getText( "ui.label.responsible" );
      String NAME_RESPONSIBLE_EN = bundleEN.getText( "ui.label.responsible" );

      // Check field label
      HtmlSpan label = (HtmlSpan) propertiesPage.getHtmlElementById( "responsible_Label" );
      assertEquals( "Check label's formatting style:", StyleSheetConstants.FIELD_LABEL_STYLE, label.getAttribute( "class" ) );
      assertTrue( "Check label text", label.getFirstChild().asText().trim().matches( NAME_RESPONSIBLE_HU + "|" + NAME_RESPONSIBLE_EN ) );
   }

   public void testField_VersionControlled() throws NoneExistingResourceKeyException {
      String NAME_VC_HU = bundleHU.getText( "ui.label.versionControlled" );
      String NAME_VC_EN = bundleEN.getText( "ui.label.versionControlled" );

      // Check field label
      HtmlSpan label = (HtmlSpan) propertiesPage.getHtmlElementById( "versionControlled_Label" );
      assertEquals( "Check label's formatting style:", StyleSheetConstants.FIELD_LABEL_STYLE, label.getAttribute( "class" ) );
      assertTrue( "Check label text", label.getFirstChild().asText().trim().matches( NAME_VC_HU + "|" + NAME_VC_EN ) );
   }

}
