package com.processpuzzle.artifact_management.control;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.control.control.CommandDispatcher;
import com.processpuzzle.application.control.control.CommandInterface;
import com.processpuzzle.application.security.domain.UserFactory;
import com.processpuzzle.artifact.artifact.ArtifactList;
import com.processpuzzle.artifact.artifact.ArtifactList_ListView;
import com.processpuzzle.artifact.artifact.ArtifactList_PropertyView;
import com.processpuzzle.artifact.domain.ArtifactListFactory;
import com.processpuzzle.artifact.domain.ArtifactVersion;
import com.processpuzzle.artifact.domain.Modification;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.artifact_type.domain.ArtifactTypeFactory;
import com.processpuzzle.user_session.domain.UserRequestManager;

public class ShowArtifactListViewMockCommand implements CommandInterface {
   public static final String PROPERTYVIEW_ARTIFACTNAME = "Proba Lista";
   public static final String PROPERTYVIEW_FULLNAME = "Proba Lista Teljes Neve";
   private ProcessPuzzleContext applicationContext;
   private ArtifactTypeFactory artifactTypeFactory;
   private UserFactory userFactory;
   private ArtifactListFactory<ArtifactList<?>> artifactListFactory;
   private String targetPage;

   public void init( CommandDispatcher dispatcher ) {
      applicationContext = UserRequestManager.getInstance().getApplicationContext();
      userFactory = applicationContext.getEntityFactory( UserFactory.class );
      artifactListFactory = applicationContext.getEntityFactory( ArtifactListFactory.class );
      artifactTypeFactory = applicationContext.getEntityFactory( ArtifactTypeFactory.class );

      ArtifactType artifactListType = artifactTypeFactory.createArtifactType( "ArtifactList", "ArtifactGroup" );
      ArtifactList<?> artifactList = artifactListFactory.create( PROPERTYVIEW_ARTIFACTNAME, artifactListType );

      String viewName = dispatcher.getProperties().getProperty( "viewName" );
      if( viewName.equals( "PropertyView" ) ){
         ArtifactList_PropertyView<ArtifactList<?>> propertyView = new ArtifactList_PropertyView<ArtifactList<?>>( artifactList, "PropertyView", null );
         propertyView.setFullName( PROPERTYVIEW_FULLNAME );
         dispatcher.getRequest().setAttribute( "subjectArtifactView", propertyView );
         targetPage = propertyView.getType().getPresentationUri();
      }else if( viewName.equals( "listView" ) ){
         ArtifactList_ListView listView = new ArtifactList_ListView( null, "Proba Test Lista", null );

         // SortedSet properties = new TreeSet();
         // PropertyView propertyView;
         // propertyView = new
         // DocumentPropertyView(artifactList,"PropertyView1");
         // properties.add(propertyView);
         // propertyView = new
         // DocumentPropertyView(artifactList,"PropertyView2");
         // properties.add(propertyView);
         // propertyView = new
         // DocumentPropertyView(artifactList,"PropertyView3");
         // properties.add(propertyView);
         // listView.setPropertyViews(properties);

         dispatcher.getRequest().setAttribute( "listView", listView );
         targetPage = listView.getType().getPresentationUri();
      }else if( viewName.equals( "artifactVersionsView" ) ){
         // setUp fixture
         // Artifact anArtifact = new Document ("anArtifact", new DocumentType(""), null);
         ArtifactVersion anArtifactVersion = new ArtifactVersion( "anArtifact", userFactory.createUser( "Gipsz Jakab", "" ) );
         /* Modification aModification = */
         new Modification( anArtifactVersion, userFactory.createUser( "Bárczi Benõ", "psw" ), "Mindent kijavítok!" );

         // ArtifactModificationsView view = new ArtifactModificationsView(anArtifact, "modificationsView");
         // List modifications = view.getModifications();
         // modifications.put(anArtifactVersion.getVersionNumber(), aModification);

         // dispatcher.getRequest().setAttribute("artifactVersionsView", view);
      }
   }

   public String getName() {
      return "ShowArtifactListViewMock";
   }

   public String execute( CommandDispatcher dispatcher ) throws Exception {
      return targetPage;
   }
}