package com.processpuzzle.artifact.domain;

import java.util.Date;
import java.util.GregorianCalendar;

import com.processpuzzle.artifact_type.domain.ArtifactTypeTestFixture;
import com.processpuzzle.litest.template.ArtifactTestEnvironment;
import com.processpuzzle.litest.template.ArtifactTestFixture;
import com.processpuzzle.persistence.domain.TestEntity;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;
import com.processpuzzle.sharedfixtures.domaintier.DomainTier_ConfigurationFixture;
import com.processpuzzle.sharedfixtures.domaintier.ProcessPuzzleContextFixture;

public abstract class GenericArtifactTestFixture<S extends Artifact<S>> extends ArtifactTestFixture<S>{
   public static String TEXT_VALUE = "Some text";
   public static Integer NUMBER_VALUE = new Integer(555);
   public static Date DATE_VALUE = new Date(new GregorianCalendar(1999, 6, 11).getTimeInMillis());
   private static DomainTier_ConfigurationFixture configFixture = null;
   private static ArtifactTypeTestFixture typeFixture = null;
   private static ProcessPuzzleContextFixture applicationContextFixture;
   private TestEntityDataSheet dataSheet = null;
   private TestEntity testEntity = null;

   //Constructors
   protected GenericArtifactTestFixture( ArtifactTestEnvironment<S, ?> testEnvironment ) {
      super( testEnvironment );
   }

   //Protected, private helper methods
   public void setUp() {
      configFixture = DomainTier_ConfigurationFixture.getInstance();
      configFixture.setUp();

      applicationContextFixture = ProcessPuzzleContextFixture.getInstance( DomainTierTestConfiguration.APPLICATION_CONFIGURATION_DESCRIPTOR_PATH );
      applicationContextFixture.setUp();
      
      typeFixture = new ArtifactTypeTestFixture( null );
      typeFixture.setUp();

      dataSheet = TestEntityDataSheet.create("Test_Entity");
      testEntity = dataSheet.getTestEntity();
      testEntity.setTextAttribute(TEXT_VALUE);
      testEntity.setNumberAttribute(NUMBER_VALUE);
      testEntity.setDateAttribute(DATE_VALUE);
      dataSheet.update();
   }

   public void tearDown() {
      dataSheet.delete();
      typeFixture.tearDown();
   }

   public TestEntityDataSheet getDataSheet() {
      return dataSheet;
   }

   public DomainTier_ConfigurationFixture getConfigurationFixture() {
      return configFixture;
   }
}