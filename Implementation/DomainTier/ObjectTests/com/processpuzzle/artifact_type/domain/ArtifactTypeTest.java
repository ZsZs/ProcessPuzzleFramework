/*
 * Created on Apr 17, 2006
 */
package com.processpuzzle.artifact_type.domain;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.artifact.artifact.ArtifactList_ListView;
import com.processpuzzle.artifact.domain.AccessRightsView;
import com.processpuzzle.artifact.domain.ArtifactCommentsView;
import com.processpuzzle.artifact.domain.ArtifactModificationsView;
import com.processpuzzle.artifact.domain.ArtifactSubClass;
import com.processpuzzle.artifact.domain.PropertyView;
import com.processpuzzle.artifact.domain.TestEntityDataSheet_PrintView;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;
import com.processpuzzle.sharedfixtures.domaintier.ProcessPuzzleContextFixture;

/**
 * @author zsolt.zsuffa
 */
public class ArtifactTypeTest {
   private static ProcessPuzzleContextFixture applicationContextFixture;
   private static ArtifactTypeFactory artifactTypeFactory;
   private static ProcessPuzzleContext applicationContext;
   private static ArtifactTypeTestFixture artifactTypeTestFixture;
   private static User testUser;
   private ArtifactTypeTestFixture fixture;
   private ArtifactType artifactType;
   private ArtifactType artifactListType;

   @BeforeClass
   public static void beforeAllTests() {
      applicationContextFixture = ProcessPuzzleContextFixture.getInstance( DomainTierTestConfiguration.APPLICATION_CONFIGURATION_DESCRIPTOR_PATH );
      applicationContextFixture.setUp();
      
      applicationContext = applicationContextFixture.getApplicationContext();
      artifactTypeFactory = applicationContext.getEntityFactory( ArtifactTypeFactory.class );
      
      testUser = applicationContextFixture.getUser();
   }

   @Before
   public void setUp() throws Exception {
      artifactTypeTestFixture = ArtifactTypeTestFixture.getInstance( applicationContext );
      artifactTypeTestFixture.setUp();
      
      artifactType = artifactTypeTestFixture.getArtifactType();
      artifactListType = artifactTypeTestFixture.getArtifactListType();
   }

   @Test
   public final void testInstantiationConstraints() {
      assertEquals( ArtifactTypeTestFixture.ARTIFACT_TYPE_NAME, artifactType.getName() );
      assertEquals( ArtifactTypeTestFixture.TYPE_GROUP_NAME, artifactType.getGroup().getName() );
      assertThat( artifactType.getArtifactClassName(), equalTo( ArtifactTypeTestFixture.ARTIFACT_CLASS ));
   }

   @Test
   public final void testViewTypes() {
      ArtifactPropertyViewType propertyViewType = (ArtifactPropertyViewType) artifactType.getViewType( ArtifactTypeTestFixture.PROPERTY_VIEW_TYPE_NAME );
      assertEquals( "/ProcessInstantiation/ProjectAdministration/Artifact_Properties.jsp", propertyViewType.getPresentationUri() );
      assertEquals( PropertyView.class.getName(), propertyViewType.getViewClassName() );

      ArtifactPrintViewType printViewType = (ArtifactPrintViewType) artifactType.getViewType( ArtifactTypeTestFixture.PRINT_VIEW_TYPE_NAME );
      assertEquals( "", printViewType.getPresentationUri() );
      assertEquals( TestEntityDataSheet_PrintView.class.getName(), printViewType.getViewClassName() );
      assertEquals( ArtifactTypeTestFixture.PRINT_VIEW_XSLT_PATH, printViewType.getXmlToFoXsltPath() );
      assertEquals( TestEntityDataSheet_PrintView.class.getName(), printViewType.getViewClassName() );

      ArtifactAccessRightsViewType rightsViewType = (ArtifactAccessRightsViewType) artifactType.getViewType( ArtifactTypeTestFixture.ACCESSRIGHTS_VIEW_TYPE_NAME );
      assertEquals( "/ProcessInstantiation/ProjectAdministration/Artifact_AccessRights.jsp", rightsViewType.getPresentationUri() );
      assertEquals( AccessRightsView.class.getName(), rightsViewType.getViewClassName() );

      ArtifactListViewType listViewType = (ArtifactListViewType) artifactListType.getViewType( ArtifactTypeTestFixture.ARTIFACTLIST_VIEW_TYPE_NAME );
      assertEquals( "/ProcessInstantiation/ProjectAdministration/ArtifactList_ListedArtifacts.jsp", listViewType.getPresentationUri() );
      assertEquals( ArtifactList_ListView.class.getName(), listViewType.getViewClassName() );

      ArtifactModificationsViewType modificationsViewType = (ArtifactModificationsViewType) artifactType.getViewType( ArtifactTypeTestFixture.MODIFICATIONS_VIEW_TYPE_NAME );
      assertEquals( "/ProcessInstantiation/ProjectAdministration/Artifact_Versions.jsp", modificationsViewType.getPresentationUri() );
      assertEquals( ArtifactModificationsView.class.getName(), modificationsViewType.getViewClassName() );

      ArtifactCommentsViewType commentsViewType = (ArtifactCommentsViewType) artifactType.getViewType( ArtifactTypeTestFixture.COMMENTS_VIEW_TYPE_NAME );
      assertEquals( "/ProcessInstantiation/ProjectAdministration/Artifact_Comments.jsp", commentsViewType.getPresentationUri() );
      assertEquals( ArtifactCommentsView.class.getName(), commentsViewType.getViewClassName() );
   }
   
   @Test public void instantiateViewsFor_ShouldInstantiateArtifactsViews() {
      ArtifactSubClass artifactSubClass = new ArtifactSubClass( "TestArtifact", artifactType, testUser );
      assertThat( artifactSubClass.getPropertyView(), instanceOf( PropertyView.class ));
   }

   @After
   public void tearDown() throws Exception {
      artifactTypeTestFixture.tearDown();
   }

   @AfterClass
   public static void afterAllTests() {
      applicationContextFixture.tearDown();
   }
}