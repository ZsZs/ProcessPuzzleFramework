/*
 * Created on Jul 13, 2006
 */
package com.processpuzzle.business.definition.domain;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.application.security.domain.UserRepository;
import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.artifact.domain.ArtifactFolder;
import com.processpuzzle.artifact.domain.ArtifactFolderRepository;
import com.processpuzzle.artifact.domain.DefaultArtifactRepository;
import com.processpuzzle.artifact.domain.RootArtifactFolder;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.artifact_type.domain.ArtifactTypeRepository;
import com.processpuzzle.party.artifact.PersonDataSheet;
import com.processpuzzle.party.artifact.PersonList;
import com.processpuzzle.sharedfixtures.domaintier.BusinessDefinitionFixture;
import com.processpuzzle.user_session.domain.UserRequestManager;

/**
 * @author zsolt.zsuffa
 */
public class SystemArtifactsLoaderTest {
   private static ProcessPuzzleContext applicationContext;
   private static BusinessDefinitionFixture businessDefinitionFixture;
   private static SystemArtifactsLoader systemArtifactsLoader;
   private static ArtifactTypeRepository artifactTypeRepository;
   private static DefaultArtifactRepository artifactRepository;
   private static ArtifactFolderRepository artifactFolderRepository;
   private Artifact<?> personList;
   private Artifact<?> companyList;
   
   @BeforeClass public static void beforeAllTests() throws Exception {
      businessDefinitionFixture = BusinessDefinitionFixture.getInstance();
      businessDefinitionFixture.setUp();
      applicationContext = businessDefinitionFixture.getApplicationContext();
      artifactTypeRepository = applicationContext.getRepository( ArtifactTypeRepository.class );
      artifactFolderRepository = applicationContext.getRepository( ArtifactFolderRepository.class );
      artifactRepository = applicationContext.getRepository( DefaultArtifactRepository.class );
      
      saveCurrentUser();
      
      systemArtifactsLoader = new SystemArtifactsLoader();
      systemArtifactsLoader.loadData();
   }

   @Test public void verifyRootArtifactFolder()  {
      RootArtifactFolder rootFolder = artifactFolderRepository.findRootFolder();
      assertThat( "BusinessImplementationLoader should instantiate one root folder.", rootFolder, notNullValue() );
   }
   
   @Test public void verifySystemFolderInstantiations() {
      ArtifactFolder workflowsFolder = artifactFolderRepository.findByPath( SystemArtifact.WORKFLOWS_FOLDER.getPath() );
      assertThat( "BusinessImplementationLoader should instantiate one workflows folder", workflowsFolder, notNullValue() );
      
      ArtifactFolder artifactsFolder = artifactFolderRepository.findByPath( SystemArtifact.ARTIFACTS_FOLDER.getPath() );
      assertThat( "BusinessImplementationLoader should instantiate one artifacts folder", artifactsFolder, notNullValue() );
      
      ArtifactFolder rolesFolder = artifactFolderRepository.findByPath( SystemArtifact.ROLES_FOLDER.getPath() );
      assertThat( "BusinessImplementationLoader should instantiate one 'roles' folder", rolesFolder, notNullValue() );
      
      ArtifactFolder contentFolder = artifactFolderRepository.findByPath( SystemArtifact.CONTENT_FOLDER.getPath() );
      assertThat( "BusinessImplementationLoader should instantiate one 'content' folder", contentFolder, notNullValue() );      
   }
   
   
   public @Test void verifySystemArtifacts() {
      personList = artifactRepository.findByPath( SystemArtifact.PERSON_LIST_NAME.getPath() );
      assertThat( personList, notNullValue() );
      
      companyList = artifactRepository.findByPath( SystemArtifact.COMPANY_LIST_NAME.getPath() );
      assertThat( companyList, notNullValue() );
      
      Artifact<?> messageWall = artifactRepository.findByPath( SystemArtifact.MESSAGE_WALL.getPath() );
      assertThat( messageWall, notNullValue() );
   }   
   
   public @Test void verifyArtifactInstancesFolder() {
      //SETUP:
      ArtifactType personDataSheetType = artifactTypeRepository.findByName( PersonDataSheet.class.getSimpleName() );
      ArtifactType personListType = artifactTypeRepository.findByName( PersonList.class.getSimpleName() );
      
      //EXCERCISE:
      ArtifactFolder personsFolder = artifactFolderRepository.findByPath( personDataSheetType.getInstanceFolderPath() );
      ArtifactFolder personListFolder = artifactFolderRepository.findByPath( personListType.getInstanceFolderPath() );
      
      //VERIFY:
      assertThat( "Creates a folder for each artifact type's instances.", personsFolder, notNullValue() );
      assertThat( "Dosn't create folder for singletons.", personListFolder, nullValue() );
   }
   
   @AfterClass public static void afterAllTests() throws Exception {
      businessDefinitionFixture.tearDown();
      deleteCurrentUser();
   }
   
   private static void deleteCurrentUser() {
      
   }

   private static void saveCurrentUser() {
      User currentUser = UserRequestManager.getInstance().currentUser();
      UserRepository userRepository = applicationContext.getRepository( UserRepository.class );
      userRepository.add( currentUser );
   }
}
