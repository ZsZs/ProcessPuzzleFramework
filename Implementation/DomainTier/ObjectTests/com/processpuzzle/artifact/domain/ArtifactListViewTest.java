package com.processpuzzle.artifact.domain;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.application.security.domain.UserFactory;
import com.processpuzzle.application.security.domain.UserRepository;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.artifact.domain.ArtifactListView;
import com.processpuzzle.artifact.domain.PropertyView;
import com.processpuzzle.artifact_type.domain.ArtifactTypeTestFixture;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;
import com.processpuzzle.sharedfixtures.domaintier.ProcessPuzzleContextFixture;

public class ArtifactListViewTest {
   private ProcessPuzzleContextFixture applicationContextFixture;
   private ArtifactTypeTestFixture typeFixture = null;
   private static String FIRST_ARTIFACT_NAME = "First artifact";
   private static String SECOND_ARTIFACT_NAME = "Second artifact";
   private static String BULK_ARTIFACT_NAME_PREFIX = "Bulk_artifact_";
   private ArtifactSubClass parentArtifact = null;
   private ArtifactListView<?> listView = null;
   private ProcessPuzzleContext applicationContext;
   private UserFactory userFactory;
   private static ArtifactSubClassRepository repository = null;
   private ArtifactSubClass firstArtifact = null;
   private ArtifactSubClass secondArtifact = null;
   private static UserRepository userRepository = null;
   private User user;

   @Before
   public void setUp() throws Exception {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      applicationContextFixture = ProcessPuzzleContextFixture.getInstance( DomainTierTestConfiguration.APPLICATION_CONFIGURATION_DESCRIPTOR_PATH );
      applicationContextFixture.setUp();
      
      applicationContext = applicationContextFixture.getApplicationContext();

      typeFixture = ArtifactTypeTestFixture.getInstance( applicationContext );
      typeFixture.setUp();

      userFactory = applicationContext.getEntityFactory( UserFactory.class );

      repository = applicationContext.getRepository( ArtifactSubClassRepository.class );
      userRepository = applicationContext.getRepository( UserRepository.class );

      user = userFactory.createUser( "P", "B" );
      userRepository.addUser( work, user );
      parentArtifact = new ArtifactSubClass( "anArtifact", typeFixture.getArtifactSubClassType(), user );
      createSubjectArtifacts();
//      listView = new ArtifactListViewSubClass( parentArtifact, "aListView", null );
      listView = new ArtifactListViewSubClass( null, "aListView", null );
      listView.query();
      work.finish();
   }

   @After
   public void tearDown() throws Exception {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      parentArtifact = null;
      listView = null;
      deleteSubjectArtifacts();
      userRepository.deleteUser( work, user );
      work.finish();
      typeFixture.tearDown();
   }

   @Ignore
   @Test
   public void getListedArtifactsPropertyView_ForDefaultSettings() {
      // assertEquals("", 53, repository.findAllArtifactSubClass().size());
      List<?> artifacts = listView.getListedArtifactsPropertyViews();
      assertEquals( "By default the ArtifactListView searches for the first 50 artifacts", 50, artifacts.size() );
      assertEquals( "By default the ArtifactListView starts with the", FIRST_ARTIFACT_NAME, ((PropertyView<?>) artifacts.get( 0 ))
            .getArtifactName() );
      assertEquals( "By default the artifact returned in the order they are created. So the second artifact is:", SECOND_ARTIFACT_NAME,
            ((PropertyView<?>) artifacts.get( 1 )).getArtifactName() );
   }

   @Ignore
   @Test
   public void query_ForSizeSpecification() {
      listView.setMaxReturnSize( new Integer( 20 ) );
      listView.query();
      assertEquals( "The number of object we requested is:", 20, listView.getListedArtifactsPropertyViews().size() );
   }

   @Ignore
   @Test
   public final void query_ForStart() {
      listView.setStartQueryFrom( new Integer( 5 ) );
      listView.query();
      List<?> artifacts = listView.getListedArtifactsPropertyViews();
      assertEquals( "Now the third object form the bulk artifacts is expected to return first", BULK_ARTIFACT_NAME_PREFIX + 3,
            ((PropertyView<?>) artifacts.get( 0 )).getArtifactName() );
   }

   @Ignore
   @Test
   public final void query_ForStatement() {
      listView.setQueryStatement( "" );
   }

   private void createSubjectArtifacts() {
      // Create named artifacts
      firstArtifact = new ArtifactSubClass( FIRST_ARTIFACT_NAME, typeFixture.getArtifactSubClassType(), user );
      repository.addArtifactSubClass( firstArtifact );

      secondArtifact = new ArtifactSubClass( SECOND_ARTIFACT_NAME, typeFixture.getArtifactSubClassType(), user );
      repository.addArtifactSubClass( secondArtifact );

      // Create numbered artifacts
      for( int i = 0; i < 51; i++ ){
         ArtifactSubClass anArtifact = new ArtifactSubClass( BULK_ARTIFACT_NAME_PREFIX + i, typeFixture.getArtifactSubClassType(), user );
         repository.addArtifactSubClass( anArtifact );
      }
   }

   private void deleteSubjectArtifacts() {
      // delete named artifacts
      repository.deletedArtifactSubClass( firstArtifact );
      repository.deletedArtifactSubClass( secondArtifact );

      // delete numbered artifacts
      for( int i = 0; i < 51; i++ ){
         ArtifactSubClass bulkArtifact = repository.findArtifactSubClassByName( BULK_ARTIFACT_NAME_PREFIX + i );
         if( bulkArtifact != null ){
            repository.deleteArtifactSubClass( bulkArtifact );
         }
      }
   }
}