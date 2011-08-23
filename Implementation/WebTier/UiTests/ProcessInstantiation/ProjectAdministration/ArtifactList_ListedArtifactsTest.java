/*
 * Created on 2006.05.05.
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

public class ArtifactList_ListedArtifactsTest extends TestCase {

    private static final String BASE_URL = "http://localhost:8001/ObjectPuzzleFrameworkWebTier/";
    
    private static XMLResourceBundle bundleHU;
    private static XMLResourceBundle bundleEN;
    private WebClient webClient = new WebClient();
    private URL url;
    private HtmlPage listPage;

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
        url = new URL(BASE_URL + "CommandControllerServlet?action=ShowArtifactListViewMock&viewName=listView");
        listPage = (HtmlPage) webClient.getPage(url);

    }

    protected void tearDown() throws Exception {
        super.tearDown();
        url = null;
        listPage = null;

    }
    
    public void testTitle() throws FailingHttpStatusCodeException, IOException{
        assertEquals("The aritfact list page's title should be:", "ArtifactList_ListedArtifacts", listPage.getTitleText());
    }

    public void testStyleSheetReferences() {
        List links = listPage.getDocumentElement().getHtmlElementsByTagName(HtmlLink.TAG_NAME);
        for( Iterator iterator = links.iterator(); iterator.hasNext();) {
           HtmlLink link = (HtmlLink) iterator.next();
           if (link.getRelAttribute().equalsIgnoreCase("stylesheet")) {
              String styleReference = link.getHrefAttribute();
              assertTrue("The page should reference:", styleReference.matches(StyleSheetConstants.BASE_STYLE_REF + "|"
                    + StyleSheetConstants.CONTENT_STYLE_REF + "|" + StyleSheetConstants.FORM_STYLE_REF));
           }
        }
    }

    public void testPropertiesForm() {
        HtmlForm propertiesForm = listPage.getFormByName("ArtifactList_ListedArtifactsForm");
        assertNotNull("The page should contain a properties form.", propertiesForm);

        //Note! The first child element of a HtmlElement is it's text node.
        HtmlDivision containerDiv = (HtmlDivision) propertiesForm.getFirstChild().getNextSibling();
        assertEquals( "The fields are displayed within a 'readOnlyContainer' formatted division.", "readOnlyContainer", containerDiv.getAttribute( "class" ) );
     }
    
    public void testTableDataCell_artifactNameCaption () throws NoneExistingResourceKeyException {
        String NAME_ARTIFACTNAMECAPTION_HU = bundleHU.getText("ui.label.name");
        String NAME_ARTIFACTNAMECAPTION_EN = bundleEN.getText("ui.label.name");
        
        //Check table's captions 
        HtmlTableDataCell artifactNameCaption = (HtmlTableDataCell) listPage.getHtmlElementById("artifactName");
        assertTrue("Check 'artifact name' caption", artifactNameCaption.getFirstChild().asText().matches(NAME_ARTIFACTNAMECAPTION_HU+"|"+NAME_ARTIFACTNAMECAPTION_EN));

    }
 
    public void testTableDataCell_ownerCaption () throws NoneExistingResourceKeyException {
        String NAME_OWNERCAPTION_HU = bundleHU.getText("ui.label.owner");
        String NAME_OWNERCAPTION_EN = bundleEN.getText("ui.label.owner");
        
        //Check table's captions 
        HtmlTableDataCell ownerCaption = (HtmlTableDataCell) listPage.getHtmlElementById("owner");
        assertTrue("Check 'owner' caption", ownerCaption.getFirstChild().asText().matches(NAME_OWNERCAPTION_HU+"|"+NAME_OWNERCAPTION_EN));

    }

    public void testTableDataCell_lastModDateCaption () throws NoneExistingResourceKeyException {
        String NAME_LASTMODDATECAPTION_HU = bundleHU.getText("ui.label.lastModDate");
        String NAME_LASTMODDATECAPTION_EN = bundleEN.getText("ui.label.lastModDate");
        
        //Check table's captions 
        HtmlTableDataCell lastModDateCaption = (HtmlTableDataCell) listPage.getHtmlElementById("lastModDate");
        assertTrue("Check 'lastModDate' caption", lastModDateCaption.getFirstChild().asText().matches(NAME_LASTMODDATECAPTION_HU+"|"+NAME_LASTMODDATECAPTION_EN));
    }
}
    
