/*
 * Created on 2006.05.16.
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
import com.gargoylesoftware.htmlunit.html.HtmlTableDataCell;
import com.processpuzzle.internalization.domain.NoneExistingResourceKeyException;
import com.processpuzzle.internalization.domain.ProcessPuzzleLocale;
import com.processpuzzle.internalization.domain.XMLResourceBundle;

/**
 * @author peter.krima
 */
public class Artifact_RelatedArtifactsTest extends TestCase {

    private static final String BASE_URL = "http://localhost:8001/ObjectPuzzleFrameworkWebTier/";

    private static XMLResourceBundle bundleHU;
    private static XMLResourceBundle bundleEN;
    private WebClient webClient = new WebClient();
    private URL url;
    private HtmlPage relatedartifactsPage;

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
        url = new URL(BASE_URL + "CommandControllerServlet?action=ShowArtifactViewMock&viewName=Artifact_RelatedArtifacts");
        relatedartifactsPage = (HtmlPage) webClient.getPage(url);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        url = null;
        relatedartifactsPage = null;
    }

    public void testTitle() throws FailingHttpStatusCodeException, IOException{
        assertEquals("The aritfact list page's title should be:", "Artifact_RelatedArtifacts", relatedartifactsPage.getTitleText());
    }

    public void testStyleReferences() {
        List links = relatedartifactsPage.getDocumentElement().getHtmlElementsByTagName(HtmlLink.TAG_NAME);
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
        HtmlForm propertiesForm = relatedartifactsPage.getFormByName("ArtifactList_ListedArtifactsForm");
        assertNotNull("The page should contain a form.", propertiesForm);

        //Note! The first child element of a HtmlElement is it's text node.
        HtmlDivision containerDiv = (HtmlDivision) propertiesForm.getFirstChild().getNextSibling();
        assertEquals("The fields are displayed within a 'readOnlyContainer' formatted division.", "readOnlyContainer", containerDiv.getAttribute( "class" ) );
     }

    public void testTableDataCell_CreationDateCaption () throws NoneExistingResourceKeyException {
        String NAME_CREATIONDATE_HU = bundleHU.getText("ui.label.creationDate");
        String NAME_CREATIONDATE_EN = bundleEN.getText("ui.label.creationDate");

        //Check table's captions 
        HtmlTableDataCell creationDateCaption = (HtmlTableDataCell) relatedartifactsPage.getHtmlElementById("creationDate");
        assertTrue("Check 'creationDate' caption", creationDateCaption.getFirstChild().asText().matches(NAME_CREATIONDATE_HU+"|"+NAME_CREATIONDATE_EN));
    }

    public void testTableDataCell_lastModDateCaption () throws NoneExistingResourceKeyException {
        String NAME_LASTMODDATE_HU = bundleHU.getText("ui.label.lastModDate");
        String NAME_LASTMODDATE_EN = bundleEN.getText("ui.label.lastModDate");

        //Check table's captions 
        HtmlTableDataCell lastModDateCaption = (HtmlTableDataCell) relatedartifactsPage.getHtmlElementById("lastModDate");
        assertTrue("Check 'lastModDate' caption", lastModDateCaption.getFirstChild().asText().matches(NAME_LASTMODDATE_HU+"|"+NAME_LASTMODDATE_EN));
    }

    public void testTableDataCell_lastModifierCaption () throws NoneExistingResourceKeyException {
        String NAME_LASTMODIFIER_HU = bundleHU.getText("ui.label.lastModifier");
        String NAME_LASTMODIFIER_EN = bundleEN.getText("ui.label.lastModifier");

        //Check table's captions 
        HtmlTableDataCell lastModifierCaption = (HtmlTableDataCell) relatedartifactsPage.getHtmlElementById("lastModifier");
        assertTrue("Check 'lastModifier' caption", lastModifierCaption.getFirstChild().asText().matches(NAME_LASTMODIFIER_HU+"|"+NAME_LASTMODIFIER_EN));
    }

    public void testTableDataCell_responsibleCaption () throws NoneExistingResourceKeyException {
        String NAME_RESPONSIBLE_HU = bundleHU.getText("ui.label.responsible");
        String NAME_RESPONSIBLE_EN = bundleEN.getText("ui.label.responsible");
        
        //Check table's captions 
        HtmlTableDataCell responsibleCaption = (HtmlTableDataCell) relatedartifactsPage.getHtmlElementById("responsible");
        assertTrue("Check 'responsible' caption", responsibleCaption.getFirstChild().asText().matches(NAME_RESPONSIBLE_HU+"|"+NAME_RESPONSIBLE_EN));
    }

    public void testTableDataCell_versionCaption () throws NoneExistingResourceKeyException {
        String NAME_VERSION_HU = bundleHU.getText("ui.label.versionControlled");
        String NAME_VERSION_EN = bundleEN.getText("ui.label.versionControlled");

        //Check table's captions 
        HtmlTableDataCell versionCaption = (HtmlTableDataCell) relatedartifactsPage.getHtmlElementById("versionControlled");
        assertTrue("Check 'versionControlled' caption", versionCaption.getFirstChild().asText().matches(NAME_VERSION_HU+"|"+NAME_VERSION_EN));
    }

    public void testTableDataCell_useByCaption () throws NoneExistingResourceKeyException {
        String NAME_USEBY_HU = bundleHU.getText("ui.generic.artifact.reservestatus.reserve");
        String NAME_USEBY_EN = bundleEN.getText("ui.generic.artifact.reservestatus.reserve");

        //Check table's captions 
        HtmlTableDataCell useByCaption = (HtmlTableDataCell) relatedartifactsPage.getHtmlElementById("useBy");
        assertTrue("Check 'useBy' caption", useByCaption.getFirstChild().asText().matches(NAME_USEBY_HU+"|"+NAME_USEBY_EN));
    }

    public void testButton_NewRelationButton () {

        boolean exist=true;
        try {
            /* HtmlButtonInput input = (HtmlButtonInput) */ relatedartifactsPage.getHtmlElementById("newRelationButton");
        }
        catch (ElementNotFoundException e) {
            exist=false;
        }
        if (!exist) fail("The 'newRelationButton' input doesn't exists.");
    }

    public void testButton_DeleteRelationButton () {

        boolean exist=true;
        try {
            /* HtmlButtonInput input = (HtmlButtonInput) */ relatedartifactsPage.getHtmlElementById("deleteRelationButton");
        }
        catch (ElementNotFoundException e) {
            exist=false;
        }
        if (!exist) fail("The 'newRelationButton' input doesn't exists.");
    }

    public void testButton_NewRelatedFileButton () {

        boolean exist=true;
        try {
            /* HtmlButtonInput input = (HtmlButtonInput) */ relatedartifactsPage.getHtmlElementById("newRelationButton");
        }
        catch (ElementNotFoundException e) {
            exist=false;
        }
        if (!exist) fail("The 'newRelatedFileButton' input doesn't exists.");
    }
}