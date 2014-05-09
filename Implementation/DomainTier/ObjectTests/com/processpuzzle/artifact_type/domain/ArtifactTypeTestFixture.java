package com.processpuzzle.artifact_type.domain;

import com.processpuzzle.artifact.domain.ArtifactFolder;
import com.processpuzzle.artifact.domain.ArtifactSubClass;
import com.processpuzzle.artifact.domain.TestEntityDataSheet;
import com.processpuzzle.artifact.domain.TestEntityDataSheet_PrintView;
import com.processpuzzle.artifact.domain.TestEntityDataSheet_QueryView;
import com.processpuzzle.artifact.domain.TestEntityFormView;
import com.processpuzzle.artifact.domain.TestEntityList;
import com.processpuzzle.artifact_type_group.domain.ArtifactTypeGroup;
import com.processpuzzle.artifact_type_group.domain.ArtifactTypeGroupRepository;
import com.processpuzzle.litest.template.DomainObjectTestEnvironment;
import com.processpuzzle.litest.template.DomainObjectTestFixture;

public class ArtifactTypeTestFixture extends DomainObjectTestFixture<ArtifactType>{
   public static final String ACCESSRIGHTS_VIEW_TYPE_NAME = "AccessRightsViewType";
   public static final String ARTIFACT_CLASS = ArtifactSubClass.class.getName();
   public static final String ARTIFACT_DESCRIPTION = "This is a test artifact type";
   public static final String ARTIFACT_LIST_TYPE_NAME = "Test_Artifact_List";
   public static final String ARTIFACT_SUBCLASS_TYPE_NAME = "Test_ArtifactSubClassType";
   public static final String ARTIFACT_TYPE_NAME = "Test_Artifact";
   public static final String ARTIFACTLIST_VIEW_TYPE_NAME = "ArtifactListViewType";
   public static final String BASE_URI = "ProcessInstantiation/SystemAdministration/";
   public static final String COMMENTS_VIEW_TYPE_NAME = "CommentsViewType";
   public static final String FOLDER_TYPE_NAME = ArtifactFolder.class.getSimpleName();
   public static final String MODIFICATIONS_VIEW_TYPE_NAME = "ModificationsViewType";
   public static final String PRINT_VIEW_TYPE_NAME = "PrintViewType";
   public static final String PRINT_VIEW_XSLT_PATH = "A_path";
   public static final String PROPERTY_VIEW_TYPE_NAME = "ArtifactPropertyViewType";
   public static final String QUERY_DESCRIPTION = "This query is only for testing puposes.";
   public static final String QUERY_NAME = "Test_query";
   public static final String QUERY_STATEMENT = "select * fro artifact";
   public static final String RELATEDARTIFACTS_VIEW_TYPE_NAME = "RelatedArtifactsViewType";
   public static final String TYPE_GROUP_NAME = "Test_ArtifactTypeGroup";

   private ArtifactType artifactListType;
   private ArtifactTypeGroup artifactTypeGroup;
   private ArtifactType artifactSubClassType;
   private ArtifactType artifactType;
   private ArtifactType dataSheetType;
   private ArtifactTypeGroupRepository groupRepository;
   
   //Constructors
   public ArtifactTypeTestFixture(  DomainObjectTestEnvironment<ArtifactType, ?> testEnvironment  ) {
      super( testEnvironment );
   }
   
   //Public accessors and mutators

   //Properties
   public ArtifactType getArtifactType() { return artifactType; }
   public ArtifactType getArtifactListType() { return artifactListType; }
   public ArtifactTypeGroupRepository getArtifactTypeGroupRepository() { return groupRepository; }
   public ArtifactType getArtifactSubClassType() { return artifactSubClassType; }

   //Protected, private helper methods
   @Override
   protected void configureAfterSutInstantiation() {
      setUpArtifactSubClassType();
      setUpArtifactListType();
      setUpDataSheetType();
   }
   
   @Override protected void configureBeforeSutInstantiation(){
      setUpArtifactTypeGroup();      
   }

   @Override
   protected ArtifactType instantiateSUT() {
      setUpArtifactType();
      return artifactType;
   }

   @Override
   protected void releaseResources() {
   }
   
   private void setUpArtifactListType() {
      artifactListType = new ArtifactType( ARTIFACT_LIST_TYPE_NAME, artifactTypeGroup, TestEntityList.class );

      ArtifactListViewType listViewType = new ArtifactListViewType( ARTIFACTLIST_VIEW_TYPE_NAME );
      listViewType.setPresentationUri( "/ProcessInstantiation/ProjectAdministration/ArtifactList_ListedArtifacts.jsp" );
      artifactListType.addViewType( listViewType );
   }

   private void setUpArtifactSubClassType() {
      artifactSubClassType = new ArtifactType( ARTIFACT_SUBCLASS_TYPE_NAME, artifactTypeGroup );
   }
   
   private void setUpArtifactTypeGroup() {
      artifactTypeGroup = new ArtifactTypeGroup( TYPE_GROUP_NAME );
   }

   private void setUpArtifactType() {
      artifactType = new ArtifactType( ARTIFACT_TYPE_NAME, artifactTypeGroup, ArtifactSubClass.class );
      artifactType.setDescription( ARTIFACT_DESCRIPTION );
      artifactType.setBaseUri( BASE_URI );

      ArtifactViewType propertyViewType = new ArtifactPropertyViewType( PROPERTY_VIEW_TYPE_NAME );
      propertyViewType.setPresentationUri( "/ProcessInstantiation/ProjectAdministration/Artifact_Properties.jsp" );
      artifactType.addViewType( propertyViewType );
      
      artifactType.addViewType( new ArtifactPrintViewType( PRINT_VIEW_TYPE_NAME, TestEntityDataSheet_PrintView.class.getName(), PRINT_VIEW_XSLT_PATH ) );
      
      ArtifactAccessRightsViewType accessRightsViewType = new ArtifactAccessRightsViewType( ACCESSRIGHTS_VIEW_TYPE_NAME ); 
      accessRightsViewType.setPresentationUri( "/ProcessInstantiation/ProjectAdministration/Artifact_AccessRights.jsp" );
      artifactType.addViewType( accessRightsViewType );
      
      ArtifactModificationsViewType modificationsViewType = new ArtifactModificationsViewType( MODIFICATIONS_VIEW_TYPE_NAME );
      modificationsViewType.setPresentationUri( "/ProcessInstantiation/ProjectAdministration/Artifact_Versions.jsp" );
      artifactType.addViewType( modificationsViewType );
      
      ArtifactCommentsViewType commentsViewType = new ArtifactCommentsViewType( COMMENTS_VIEW_TYPE_NAME ); 
      commentsViewType.setPresentationUri( "/ProcessInstantiation/ProjectAdministration/Artifact_Comments.jsp" );
      artifactType.addViewType( commentsViewType );
      
      artifactType.addViewType( new RelatedArtifactsViewType( RELATEDARTIFACTS_VIEW_TYPE_NAME ) );
   }

   private void setUpDataSheetType() {
      dataSheetType = new ArtifactType( TestEntityDataSheet.class.getSimpleName(), artifactTypeGroup );
      dataSheetType.setArtifactClassName( TestEntityDataSheet.class.getName() );
      dataSheetType.addViewType( new ArtifactCustomFormViewType( TestEntityFormView.class.getSimpleName(), "has no relevance in this test", TestEntityFormView.class.getName() ) );
      dataSheetType.addViewType( new ArtifactPrintViewType( TestEntityDataSheet_PrintView.class.getSimpleName(), TestEntityDataSheet_PrintView.class.getName(), "/com/itcodex/objectpuzzle/artifact/domain/TestEntityDataSheet.xsl" ) );

      ListQueryViewType queryViewType = new ListQueryViewType( TestEntityDataSheet_QueryView.class.getSimpleName(), TestEntityDataSheet_QueryView.class.getName(), "presentation.uri" );
      queryViewType.addPredefinedQuery( QUERY_NAME, QUERY_DESCRIPTION, QUERY_STATEMENT );
      dataSheetType.addViewType( queryViewType );
   }

}
