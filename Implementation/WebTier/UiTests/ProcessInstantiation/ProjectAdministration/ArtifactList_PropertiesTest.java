/*
 * Created on May 4, 2006
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
import com.processpuzzle.artifact_management.control.ShowArtifactListViewMockCommand;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;
import com.processpuzzle.internalization.domain.NoneExistingResourceKeyException;
import com.processpuzzle.internalization.domain.ProcessPuzzleLocale;
import com.processpuzzle.internalization.domain.XMLResourceBundle;

/**
 * @author zsolt.zsuffa
 */
public class ArtifactList_PropertiesTest extends TestCase {

   private static final String BASE_URL = "http://localhost:8001/ObjectPuzzleFrameworkWebTier/";

   private static XMLResourceBundle bundleHU;
   private static XMLResourceBundle bundleEN;
   private WebClient webClient = new WebClient();
   private URL url;
   private HtmlPage propertyPage;

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
      url = new URL( BASE_URL + "CommandControllerServlet?action=ShowArtifactListViewMock&viewName=PropertyView" );
      propertyPage = (HtmlPage) webClient.getPage( url );
   }

   protected void tearDown() throws Exception {
      super.tearDown();
      url = null;
      propertyPage = null;
   }

   public void testTitle() throws FailingHttpStatusCodeException, IOException {
      assertEquals( "The property page's title shold be:", "ArtifactList_Properties", propertyPage.getTitleText() );
   }

   public void testStyleReferences() {
      List links = propertyPage.getDocumentElement().getHtmlElementsByTagName( HtmlLink.TAG_NAME );
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
      HtmlForm propertiesForm = propertyPage.getFormByName( "ArtifactList_PropertiesForm" );
      assertNotNull( "The page should contain a properties form.", propertiesForm );

      // Note! The first child element of a HtmlElement is it's text node.
      HtmlDivision containerDiv = (HtmlDivision) propertiesForm.getFirstChild().getNextSibling();
      assertEquals( "The fields are displayed within a 'readOnlyContainer' formatted division.", "readOnlyContainer", containerDiv.getAttribute( "class" ) );
   }

   public void testFieldRows() {
      HtmlDivision containerDiv = (HtmlDivision) propertyPage.getFormByName( "ArtifactList_PropertiesForm" ).getFirstChild().getNextSibling();
      for( HtmlElement childElement : containerDiv.getChildElements() ){
         HtmlDivision rowDiv = (HtmlDivision) childElement;
         assertNotNull( "Each field row is surrounded with a division tag.", rowDiv );
         assertEquals( "This row division is formatting style is:", StyleSheetConstants.ROW_STYLE, rowDiv.getAttribute( "class" ) );
      }
   }

   public void testField_Name() throws NoneExistingResourceKeyException {
      String NAME_LABEL_HU = bundleHU.getText( "ui.label.name" );
      String NAME_LABEL_EN = bundleEN.getText( "ui.label.name" );

      // Check field label
      HtmlSpan label = (HtmlSpan) propertyPage.getHtmlElementById( "Name_Label" );
      assertEquals( "Check label's formatting style:", StyleSheetConstants.FIELD_LABEL_STYLE, label.getAttribute( "class" ) );
      assertTrue( "Check label text", label.getFirstChild().asText().trim().matches( NAME_LABEL_HU + "|" + NAME_LABEL_EN ) );

      // Check field values
      HtmlSpan value = (HtmlSpan) propertyPage.getHtmlElementById( "Name_Value" );
      assertEquals( "The value's formatting style should: ", StyleSheetConstants.FIELD_VALUE_STYLE, value.getAttribute( "class" ) );
      assertEquals( "Check value text", ShowArtifactListViewMockCommand.PROPERTYVIEW_ARTIFACTNAME, value.getFirstChild().asText() );
   }

   public void testField_FullName() throws NoneExistingResourceKeyException {
      String FULLNAME_LABEL_HU = bundleHU.getText( "ui.label.fullName" );
      String FULLNAME_LABEL_EN = bundleEN.getText( "ui.label.fullName" );

      // Check field label
      HtmlSpan label = (HtmlSpan) propertyPage.getHtmlElementById( "FullName_Label" );
      assertEquals( "Check label's formatting style:", StyleSheetConstants.FIELD_LABEL_STYLE, label.getAttribute( "class" ) );
      assertTrue( "Check label text", label.getFirstChild().asText().trim().matches( FULLNAME_LABEL_HU + "|" + FULLNAME_LABEL_EN ) );

      // Check field values
      HtmlSpan value = (HtmlSpan) propertyPage.getHtmlElementById( "FullName_Value" );
      assertEquals( "The value's formatting style should: ", StyleSheetConstants.FIELD_VALUE_STYLE, value.getAttribute( "class" ) );
      assertEquals( "Check value text", ShowArtifactListViewMockCommand.PROPERTYVIEW_FULLNAME, value.getFirstChild().asText() );
   }

   public void testField_Type() throws NoneExistingResourceKeyException {
      String TYPENAME_LABEL_HU = bundleHU.getText( "ui.label.type" );
      String TYPENAME_LABEL_EN = bundleEN.getText( "ui.label.type" );

      // Check field label
      HtmlSpan label = (HtmlSpan) propertyPage.getHtmlElementById( "Type_Label" );
      assertEquals( "Check label's formatting style:", StyleSheetConstants.FIELD_LABEL_STYLE, label.getAttribute( "class" ) );
      assertTrue( "Check label text", label.getFirstChild().asText().trim().matches( TYPENAME_LABEL_HU + "|" + TYPENAME_LABEL_EN ) );

      // Check field values
      HtmlSpan value = (HtmlSpan) propertyPage.getHtmlElementById( "Type_Value" );
      assertEquals( "The value's formatting style should: ", StyleSheetConstants.FIELD_VALUE_STYLE, value.getAttribute( "class" ) );
      assertEquals( "Check value text", ArtifactViewType.class.getSimpleName(), value.getFirstChild().asText() );
   }

   // private HtmlSpan findLabelElement(HtmlDivision rowDivision) {
   // HtmlSpan label = (HtmlSpan) rowDivision.getFirstChild().getNextSibling();
   // return label;
   //      
   // }
   // private HtmlSpan findValueElement(HtmlDivision rowDivision) {
   // HtmlSpan value = (HtmlSpan) findLabelElement(rowDivision).getNextSibling().getNextSibling();
   // return value;
   //      
   // }
}