package com.processpuzzle.artifact_management.control;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.BufferedReader;
import java.io.IOException;

import com.mockrunner.mock.web.MockHttpServletResponse;
import com.mockrunner.servlet.BasicServletTestCaseAdapter;
import com.processpuzzle.address.artifact.SettlementDataSheet;
import com.processpuzzle.address.artifact.SettlementDataSheetFactory;
import com.processpuzzle.address.domain.Country;
import com.processpuzzle.address.domain.CountryFactory;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.control.control.CommandControllerServlet;
import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.application.security.domain.UserFactory;
import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.artifact.domain.ArtifactFolder;
import com.processpuzzle.artifact.domain.ArtifactFolderFactory;
import com.processpuzzle.artifact_management.control.DeleteArtifactCommand;
import com.processpuzzle.configuration.webtier.ConfigurationFixture;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;

public class DeleteArtifactCommandTest extends BasicServletTestCaseAdapter {
   public static final String ARTIFACT_TYPE_NAME = "SettlementDataSheet";
   public static final String ARTIFACT_TYPE_PROPERTY_SETTLEMENT_NAME = "settlementName";
   public static final String ARTIFACT_TYPE_PROPERTY_SETTLEMENT_VALUE = "Budapest";
   private static final String ARTIFACT_NAME_VALUE = "aSettlement";
   private static final String ARTIFACT_FOLDER_NAME_VALUE = "tesztEmptyFolder";
   private ProcessPuzzleContext applicationContext;
   private ArtifactFolderFactory artifactFolderFactory;
   private SettlementDataSheetFactory settlementDataSheetFactory;
   private CountryFactory countryFactory;
   private UserFactory userFactory;
   private User gipszJakab;
   private Artifact<?> artifact = null;
   private Country country = null;
   private ArtifactFolder folder;
   private ArtifactFolder subFolder;

   public void setUp() throws Exception {
      super.setUp();
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      ConfigurationFixture.getInstance().setUp();

      applicationContext = ConfigurationFixture.getConfig();

      artifactFolderFactory = applicationContext.getEntityFactory( ArtifactFolderFactory.class );
      countryFactory = applicationContext.getEntityFactory( CountryFactory.class );

      country = countryFactory.createCountry( "Hungary" );
      ConfigurationFixture.getCountryRepository().addCountry( work, country );

      settlementDataSheetFactory = applicationContext.getEntityFactory( SettlementDataSheetFactory.class );

      gipszJakab = userFactory.createUser( "Gipsz Jakab", "psw" );
      ConfigurationFixture.getUserRepository().addUser( work, gipszJakab );
      artifact = settlementDataSheetFactory.create( "Budapest" );
      folder = artifactFolderFactory.create( null, "folder" );
      subFolder = artifactFolderFactory.create( folder, "subFolder" );
      subFolder.addChildArtifact( artifact );
      ConfigurationFixture.getArtifactRepository().add( work, folder );

      createServlet( CommandControllerServlet.class );
      executeCommand( "folder.subFolder." + ARTIFACT_NAME_VALUE );
      work.finish();
   }

   public void tearDown() throws Exception {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      folder = ConfigurationFixture.getArtifactFolderRepository().findByPath( work, "folder" );
      subFolder = ConfigurationFixture.getArtifactFolderRepository().findByPath( work, "folder.subFolder" );
      ConfigurationFixture.getArtifactRepository().delete( work, folder );
      folder.setResponsible( null );
      subFolder.setResponsible( null );
      // ConfigurationFixture.getArtifactRepository().updateArtifact(folder);
      ((SettlementDataSheet) artifact).getSettlement().setCountry( null );
      //country.setSettlements( null );
      ConfigurationFixture.getCountryRepository().updateCountry( work, country );
      ConfigurationFixture.getCountryRepository().deleteCountry( work, country );
      ConfigurationFixture.getUserRepository().deleteUser( work, gipszJakab );
      ConfigurationFixture.getInstance().tearDown();
      work.finish();
      super.tearDown();
   }

   private final void executeCommand( String artifactName ) {
      addRequestParameter( "action", DeleteArtifactCommand.COMMAND_NAME );
      addRequestParameter( "subjectArtifactName", artifactName );

      // UserSession userSession = new UserSession();
      // userSession.setId(gipszJakab.getId().toString());
      // userSession.setFullName(gipszJakab.getPartyName().getName());
      // userSession.setUserName(gipszJakab.getSystemUser().getUserName());
      // userSession.setPassword(gipszJakab.getSystemUser().getPassword());
      //
      // setSessionAttribute("userSession", userSession);

      doPost();
   }

   public void testDeleteArtifact_ContentType() {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      assertNull( "", ConfigurationFixture.getArtifactRepository().findByFullName( work, "folder.subFolder." + ARTIFACT_NAME_VALUE ) );
      assertNull( "", ConfigurationFixture.getSettlementRepository().findSettlementByNameAndCountryName( work, "Budapest", "Hungary" ) );
      MockHttpServletResponse mockResponse = getWebMockObjectFactory().getMockResponse();
      assertEquals( "The content type should be:", "text/xml", mockResponse.getContentType() );
      BufferedReader reader = getOutputAsBufferedReader();
      try{
         String s;
         while( (s = reader.readLine()) != null )
            System.out.println( s );
      }catch( IOException e ){
         e.printStackTrace();
      }
      work.finish();
   }

   public void testDeleteArtifact_whenNotExist() {
      executeCommand( ARTIFACT_NAME_VALUE );
      MockHttpServletResponse mockResponse = getWebMockObjectFactory().getMockResponse();
      assertEquals( "The content type should be:", "text/xml", mockResponse.getContentType() );
      BufferedReader reader = getOutputAsBufferedReader();
      try{
         String s;
         while( (s = reader.readLine()) != null )
            System.out.println( s );
      }catch( IOException e ){
         e.printStackTrace();
      }
   }

   public void testDeleteArtifactFolder_whenEmpty() {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      ArtifactFolder folder = artifactFolderFactory.create( null, ARTIFACT_FOLDER_NAME_VALUE );
      ConfigurationFixture.getArtifactRepository().add( work, folder );
      executeCommand( ARTIFACT_FOLDER_NAME_VALUE );
      MockHttpServletResponse mockResponse = getWebMockObjectFactory().getMockResponse();
      assertEquals( "The content type should be:", "text/xml", mockResponse.getContentType() );
      BufferedReader reader = getOutputAsBufferedReader();
      try{
         String s;
         while( (s = reader.readLine()) != null )
            System.out.println( s );
      }catch( IOException e ){
         e.printStackTrace();
      }
      work.finish();
   }

   public void testDeleteArtifactFolder_whenNotEmpty() {
      executeCommand( "SystemAdministration" );
      MockHttpServletResponse mockResponse = getWebMockObjectFactory().getMockResponse();
      assertEquals( "The content type should be:", "text/xml", mockResponse.getContentType() );
      BufferedReader reader = getOutputAsBufferedReader();
      try{
         String s;
         while( (s = reader.readLine()) != null )
            System.out.println( s );
      }catch( IOException e ){
         e.printStackTrace();
      }
   }

}
