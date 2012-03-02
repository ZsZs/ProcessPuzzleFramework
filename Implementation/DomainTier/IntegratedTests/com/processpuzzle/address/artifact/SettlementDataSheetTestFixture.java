/*
 * Created on Sep 10, 2006
 */
package com.processpuzzle.address.artifact;

import com.processpuzzle.address.artifact.SettlementDataSheet;
import com.processpuzzle.address.artifact.SettlementDataSheetFactory;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.application.security.domain.UserFactory;
import com.processpuzzle.application.security.domain.UserRepository;
import com.processpuzzle.artifact.domain.DefaultArtifactRepository;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.sharedfixtures.domaintier.DomainTier_ConfigurationFixture;

public class SettlementDataSheetTestFixture {
   private static DomainTier_ConfigurationFixture configFixture = null;
   private static SettlementDataSheetTestFixture fixtureInstance = null;
   private ProcessPuzzleContext applicationContext;
   private DefaultArtifactRepository artifactRepository;
   private SettlementDataSheetFactory settlementDataSheetFactory;
   private UserFactory userFactory;
   private UserRepository userRepository = null;
   private User creator = null;
   private SettlementDataSheet budapest = null;
   private SettlementDataSheet godollo = null;

   public static SettlementDataSheetTestFixture getInstance() {
      if( fixtureInstance == null )
         return new SettlementDataSheetTestFixture();
      return fixtureInstance;
   }

   protected void setUp() {
      configFixture = DomainTier_ConfigurationFixture.getInstance();
      configFixture.setUp();
      applicationContext = DomainTier_ConfigurationFixture.getConfig();

      settlementDataSheetFactory = applicationContext.getEntityFactory( SettlementDataSheetFactory.class );

      DefaultUnitOfWork work = new DefaultUnitOfWork( true );

      userFactory = applicationContext.getEntityFactory( UserFactory.class );
      userRepository = DomainTier_ConfigurationFixture.getUserRepository();
      creator = userFactory.createUser( "Bárczi Benõ", "" );
      userRepository.addUser( work, creator );

      artifactRepository = DomainTier_ConfigurationFixture.getArtifactRepository();
      budapest = settlementDataSheetFactory.create( "Budapest" );
      artifactRepository.add( work, budapest );

      godollo = settlementDataSheetFactory.create( "Gödöllõ" );
      artifactRepository.add( work, godollo );
      work.finish();
   }

   protected void tearDown() {
      configFixture.tearDown();
      budapest.delete();
      budapest = null;
      godollo.delete();
      godollo = null;
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      userRepository.deleteUser( work, creator );
      work.finish();
   }

   public DomainTier_ConfigurationFixture getConfigurationFixture() {
      return configFixture;
   }

   public SettlementDataSheet getBudapest() {
      return budapest;
   }

   public SettlementDataSheet getGodollo() {
      return godollo;
   }
}
