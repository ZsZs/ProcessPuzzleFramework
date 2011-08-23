/*
 * Created on 2006.05.16.
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
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlLink;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTableDataCell;
import com.processpuzzle.internalization.domain.NoneExistingResourceKeyException;
import com.processpuzzle.internalization.domain.ProcessPuzzleLocale;
import com.processpuzzle.internalization.domain.XMLResourceBundle;

/**
 * @author peter.krima
 */
public class Artifact_VersionsTest extends TestCase {

    private static final String BASE_URL = "http://localhost:8001/ObjectPuzzleFrameworkWebTier/";
    
    private static XMLResourceBundle bundleHU;
    private static XMLResourceBundle bundleEN;
    private WebClient webClient = new WebClient();
    private URL url;
    private HtmlPage versionsPage;

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
        url = new URL(BASE_URL + "CommandControllerServlet?action=ShowArtifactViewMock&viewName=Artifact_Versions");
        versionsPage = (HtmlPage) webClient.getPage(url);
   }

    protected void tearDown() throws Exception {
        super.tearDown();
        url = null;
        versionsPage = null;
    }

    public void testTitle() throws FailingHttpStatusCodeException, IOException{
        assertEquals("The aritfact list page's title should be:", "Artifact_Versions", versionsPage.getTitleText());
    }
    
    public void testStyleSheetReferences() {
        List links = versionsPage.getDocumentElement().getHtmlElementsByTagName(HtmlLink.TAG_NAME);
        for (Iterator iterator = links.iterator(); iterator.hasNext();) {
           HtmlLink link = (HtmlLink) iterator.next();
           if (link.getRelAttribute().equalsIgnoreCase("stylesheet")) {
              String styleReference = link.getHrefAttribute();
              assertTrue("The page should reference:", styleReference.matches(StyleSheetConstants.BASE_STYLE_REF + "|"
                    + StyleSheetConstants.CONTENT_STYLE_REF + "|" + StyleSheetConstants.FORM_STYLE_REF));
           }
        }
    }

    public void testForm() {
        HtmlForm propertiesForm = versionsPage.getFormByName("Artifact_VersionsForm");
        assertNotNull("The page should contain a properties form.", propertiesForm);

        //Note! The first child element of a HtmlElement is it's text node.
        HtmlDivision containerDiv = (HtmlDivision) propertiesForm.getFirstChild().getNextSibling();
        assertEquals("The fields are displayed within a 'readOnlyContainer' formatted division.", "readOnlyContainer", containerDiv.getAttribute( "class" ) );
     }
    
    public void testTableDataCell_VersionCaption () throws NoneExistingResourceKeyException {
        String NAME_VERSIONCAPTION_HU = bundleHU.getText("ui.label.version");
        String NAME_VERSIONCAPTION_EN = bundleEN.getText("ui.label.version");
        
        //Check table's captions 
        HtmlTableDataCell versionCaption = (HtmlTableDataCell) versionsPage.getHtmlElementById("version");
        assertTrue("Check 'version' caption", versionCaption.getFirstChild().asText().matches(NAME_VERSIONCAPTION_HU+"|"+NAME_VERSIONCAPTION_EN));
    }
    
    public void testTableDataCell_BeginOfModCaption () throws NoneExistingResourceKeyException {
        String NAME_BOMCAPTION_HU = bundleHU.getText("ui.Artifact_Versions.beginOfMod");
        String NAME_BOMCAPTION_EN = bundleEN.getText("ui.Artifact_Versions.beginOfMod");
        
        //Check table's captions 
        HtmlTableDataCell bomCaption = (HtmlTableDataCell) versionsPage.getHtmlElementById("beginOfMod");
        assertTrue("Check 'beginOfMod' caption", bomCaption.getFirstChild().asText().matches(NAME_BOMCAPTION_HU+"|"+NAME_BOMCAPTION_EN));
    }
    
    public void testTableDataCell_EndOfModCaption () throws NoneExistingResourceKeyException {
        String NAME_EOMCAPTION_HU = bundleHU.getText("ui.Artifact_Versions.endOfMod");
        String NAME_EOMCAPTION_EN = bundleEN.getText("ui.Artifact_Versions.endOfMod");
        
        //Check table's captions 
        HtmlTableDataCell eomCaption = (HtmlTableDataCell) versionsPage.getHtmlElementById("endOfMod");
        assertTrue("Check 'beginOfMod' caption", eomCaption.getFirstChild().asText().matches(NAME_EOMCAPTION_HU+"|"+NAME_EOMCAPTION_EN));
    }
    
    public void testTableDataCell_ModifierCaption () throws NoneExistingResourceKeyException {
        String NAME_MODIFIERCAPTION_HU = bundleHU.getText("ui.Artifact_Versions.modifier");
        String NAME_MODIFIERCAPTION_EN = bundleEN.getText("ui.Artifact_Versions.modifier");
        
        //Check table's captions 
        HtmlTableDataCell modifierCaption = (HtmlTableDataCell) versionsPage.getHtmlElementById("modifier");
        assertTrue("Check 'beginOfMod' caption", modifierCaption.getFirstChild().asText().matches(NAME_MODIFIERCAPTION_HU+"|"+NAME_MODIFIERCAPTION_EN));
    }
    
    public void testTableDataCell_CommentCaption () throws NoneExistingResourceKeyException {
        String NAME_COMMENTCAPTION_HU = bundleHU.getText("ui.label.comment");
        String NAME_COMMENTCAPTION_EN = bundleEN.getText("ui.label.comment");
        
        //Check table's captions 
        HtmlTableDataCell commentCaption = (HtmlTableDataCell) versionsPage.getHtmlElementById("comment");
        assertTrue("Check 'comment' caption", commentCaption.getFirstChild().asText().matches(NAME_COMMENTCAPTION_HU+"|"+NAME_COMMENTCAPTION_EN));
    }
}
