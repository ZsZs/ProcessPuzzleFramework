/*
 * Created on May 27, 2006
 */
package ProcessInstantiation.ProjectAdministration;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author zsolt.zsuffa
 */
public class ProjectAdministrationTestSuite {

   public static Test suite() {
      TestSuite suite = new TestSuite("Test for ProcessInstantiation.ProjectAdministration");
      //$JUnit-BEGIN$
      suite.addTestSuite(Artifact_RelatedArtifactsTest.class);
      suite.addTestSuite(Artifact_AccessRightsTest.class);
      suite.addTestSuite(ArtifactList_PropertiesTest.class);
      suite.addTestSuite(ArtifactList_ListedArtifactsTest.class);
      suite.addTestSuite(Artifact_VersionsTest.class);
      //$JUnit-END$
      return suite;
   }
}
