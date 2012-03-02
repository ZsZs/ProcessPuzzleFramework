/*
 * Created on Jul 9, 2006
 */
package com.processpuzzle.artifact_type.domain;

import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.artifact.domain.ArtifactFolder;
import com.processpuzzle.artifact.domain.ArtifactSubClass;
import com.processpuzzle.artifact.domain.TestEntityDataSheet;
import com.processpuzzle.artifact.domain.TestEntityDataSheet_PrintView;
import com.processpuzzle.artifact.domain.TestEntityDataSheet_QueryView;
import com.processpuzzle.artifact.domain.TestEntityFormView;
import com.processpuzzle.artifact.domain.TestEntityList;
import com.processpuzzle.artifact_type.domain.ArtifactAccessRightsViewType;
import com.processpuzzle.artifact_type.domain.ArtifactCommentsViewType;
import com.processpuzzle.artifact_type.domain.ArtifactCustomFormViewType;
import com.processpuzzle.artifact_type.domain.ArtifactListViewType;
import com.processpuzzle.artifact_type.domain.ArtifactModificationsViewType;
import com.processpuzzle.artifact_type.domain.ArtifactPrintViewType;
import com.processpuzzle.artifact_type.domain.ArtifactPropertyViewType;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.artifact_type.domain.ArtifactTypeFactory;
import com.processpuzzle.artifact_type.domain.ArtifactTypeRepository;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;
import com.processpuzzle.artifact_type.domain.ListQueryViewType;
import com.processpuzzle.artifact_type.domain.RelatedArtifactsViewType;
import com.processpuzzle.artifact_type_group.domain.ArtifactTypeGroup;
import com.processpuzzle.artifact_type_group.domain.ArtifactTypeGroupFactory;
import com.processpuzzle.artifact_type_group.domain.ArtifactTypeGroupRepository;

/**
 * @author zsolt.zsuffa
 */
public class ArtifactTypeTestFixture {
   public static final String ARTIFACT_CLASS = ArtifactSubClass.class.getName();
   public static final String ARTIFACT_DESCRIPTION = "This is a test artifact type";
   public static final String ARTIFACT_SUBCLASS_TYPE_NAME = "Test_ArtifactSubClassType";
   public static final String ARTIFACT_TYPE_NAME = "Test_Artifact";
   public static final String ARTIFACT_LIST_TYPE_NAME = "Test_Artifact_List";
   public static final String TYPE_GROUP_NAME = "Test_ArtifactTypeGroup";
   public static final String FOLDER_TYPE_NAME = ArtifactFolder.class.getSimpleName();
   public static final String BASE_URI = "ProcessInstantiation/SystemAdministration/";

   public static final String PROPERTY_VIEW_TYPE_NAME = "ArtifactPropertyViewType";
   public static final String PRINT_VIEW_TYPE_NAME = "PrintViewType";
   public static final String PRINT_VIEW_XSLT_PATH = "A_path";
   public static final String ARTIFACTLIST_VIEW_TYPE_NAME = "ArtifactListViewType";
   public static final String MODIFICATIONS_VIEW_TYPE_NAME = "ModificationsViewType";
   public static final String COMMENTS_VIEW_TYPE_NAME = "CommentsViewType";
   public static final String ACCESSRIGHTS_VIEW_TYPE_NAME = "AccessRightsViewType";
   public static final String RELATEDARTIFACTS_VIEW_TYPE_NAME = "RelatedArtifactsViewType";

   public static final String QUERY_NAME = "Test_query";
   public static final String QUERY_DESCRIPTION = "This query is only for testing puposes.";
   public static final String QUERY_STATEMENT = "select * fro artifact";

   protected static ArtifactTypeTestFixture fixtureInstance;
   protected static ProcessPuzzleContext applicationContext;
   private static ArtifactTypeGroupFactory groupFactory;
   private static ArtifactTypeGroupRepository groupRepository;
   private static ArtifactTypeRepository typeRepository;
   private ArtifactType folderType;
   private ArtifactType artifactSubClassType;
   private ArtifactTypeGroup typeGroup;
   private ArtifactType artifactType;
   private ArtifactType artifactListType;
   private ArtifactType dataSheetType;
   private ArtifactTypeFactory artifactTypeFactory;

   public ArtifactType getArtifactType() {
      return artifactType;
   }
   
   public ArtifactType getArtifactListType() {
      return artifactListType;
   }

   public ArtifactTypeGroupRepository getArtifactTypeGroupRepository() {
      return groupRepository;
   }

   public ArtifactType getArtifactSubClassType() {
      return artifactSubClassType;
   }

   public static ArtifactTypeTestFixture getInstance( ProcessPuzzleContext applicationContext ) {
      if( fixtureInstance == null ){
         return new ArtifactTypeTestFixture( applicationContext );
      }
      return fixtureInstance;
   }

   public void setUp() {
      artifactTypeFactory = applicationContext.getEntityFactory( ArtifactTypeFactory.class );
      groupFactory = applicationContext.getEntityFactory( ArtifactTypeGroupFactory.class );
      groupRepository = applicationContext.getRepository( ArtifactTypeGroupRepository.class );
      typeRepository = applicationContext.getRepository( ArtifactTypeRepository.class );
      setUpArtifactTypeGroup();
      setUpArtifactSubClassType();
      setUpArtifactType();
      setUpArtifactListType();
      setUpDataSheetType();
   }

   public void tearDown() {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      typeRepository.deleteArtifactType( work, dataSheetType );
      typeRepository.deleteArtifactType( work, artifactType );
      typeRepository.deleteArtifactType( work, artifactSubClassType );
      typeRepository.deleteArtifactType( work, folderType );
      groupRepository.deleteArtifactTypeGroup( work, typeGroup );
      applicationContext = null;
      typeRepository = null;
      work.finish();
   }

   private void setUpArtifactTypeGroup() {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      typeGroup = groupFactory.create( TYPE_GROUP_NAME );
      groupRepository.addArtifactTypeGroup( work, typeGroup );
      work.finish();
   }

   private void setUpArtifactType() {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      folderType = typeRepository.findArtifactTypeByName( work, FOLDER_TYPE_NAME );
      if( folderType == null ){
         folderType = artifactTypeFactory.createArtifactType( FOLDER_TYPE_NAME, TYPE_GROUP_NAME );
         typeRepository.addArtifactType( work, folderType );
      }

      artifactType = artifactTypeFactory.createArtifactType( ARTIFACT_TYPE_NAME, TYPE_GROUP_NAME, ArtifactSubClass.class );
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

      typeRepository.addArtifactType( work, artifactType );
      work.finish();
   }
   
   private void setUpArtifactListType() {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      artifactListType = artifactTypeFactory.createArtifactType( ARTIFACT_LIST_TYPE_NAME, TYPE_GROUP_NAME, TestEntityList.class );

      ArtifactListViewType listViewType = new ArtifactListViewType( ARTIFACTLIST_VIEW_TYPE_NAME );
      listViewType.setPresentationUri( "/ProcessInstantiation/ProjectAdministration/ArtifactList_ListedArtifacts.jsp" );
      artifactListType.addViewType( listViewType );

      typeRepository.addArtifactType( work, artifactListType );
      work.finish();
   }

   private void setUpArtifactSubClassType() {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      artifactSubClassType = artifactTypeFactory.createArtifactType( ARTIFACT_SUBCLASS_TYPE_NAME, TYPE_GROUP_NAME );
      typeRepository.addArtifactType( work, artifactSubClassType );
      work.finish();
   }

   private void setUpDataSheetType() {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      dataSheetType = artifactTypeFactory.createArtifactType( TestEntityDataSheet.class.getSimpleName(), TYPE_GROUP_NAME );
      dataSheetType.setArtifactClassName( TestEntityDataSheet.class.getName() );
      dataSheetType.addViewType( new ArtifactCustomFormViewType( TestEntityFormView.class.getSimpleName(), "has no relevance in this test", TestEntityFormView.class.getName() ) );
      dataSheetType.addViewType( new ArtifactPrintViewType( TestEntityDataSheet_PrintView.class.getSimpleName(), TestEntityDataSheet_PrintView.class.getName(), "/com/itcodex/objectpuzzle/artifact/domain/TestEntityDataSheet.xsl" ) );

      ListQueryViewType queryViewType = new ListQueryViewType( TestEntityDataSheet_QueryView.class.getSimpleName(), TestEntityDataSheet_QueryView.class.getName(), "presentation.uri" );
      queryViewType.addPredefinedQuery( QUERY_NAME, QUERY_DESCRIPTION, QUERY_STATEMENT );
      dataSheetType.addViewType( queryViewType );

      typeRepository.addArtifactType( work, dataSheetType );
      work.finish();
   }

   private ArtifactTypeTestFixture( ProcessPuzzleContext applicationContext ) {
      ArtifactTypeTestFixture.applicationContext = applicationContext;
   }
}
