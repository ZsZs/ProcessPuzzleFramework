/*
 * Created on Jul 14, 2006
 */
package com.processpuzzle.artifact_type.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.persistence.domain.EntityIdentityCollitionException;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.artifact_type.domain.ArtifactTypeFactory;
import com.processpuzzle.artifact_type.domain.ArtifactTypeRepository;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;
import com.processpuzzle.sharedfixtures.domaintier.ProcessPuzzleContextFixture;

/**
 * @author zsolt.zsuffa
 */
public class ArtifactTypeFactoryTest {
   private static final String TEST_TYPE_NAME = "TestArtifactTypeName";
   private ProcessPuzzleContextFixture applicationContextFixture;
   private ProcessPuzzleContext applicationContext;
   private ArtifactTypeTestFixture typeFixture = null;
   private ArtifactType type = null;
   private static ArtifactTypeFactory artifactTypeFactory;

   @Before
   public void setUp() throws Exception {
      applicationContextFixture = ProcessPuzzleContextFixture.getInstance( DomainTierTestConfiguration.APPLICATION_CONFIGURATION_DESCRIPTOR_PATH );
      applicationContextFixture.setUp();
      
      applicationContext = applicationContextFixture.getApplicationContext();
      
      typeFixture = ArtifactTypeTestFixture.getInstance( applicationContext );
      typeFixture.setUp();
      
      artifactTypeFactory = applicationContext.getEntityFactory( ArtifactTypeFactory.class );
      type = artifactTypeFactory.createArtifactType(TEST_TYPE_NAME, ArtifactTypeTestFixture.TYPE_GROUP_NAME);
   }

   @After
   public void tearDown() throws Exception {
      typeFixture.tearDown();
   }

   @Ignore
   @Test
   public final void createArtifactType_ForSuccess() {
      assertNotNull(type);
      assertNotNull(type.getGroup());
      assertEquals(ArtifactTypeTestFixture.TYPE_GROUP_NAME, type.getGroup().getName());
   }

   @Ignore
   @Test
   public final void createArtifactType_ForConstraintViolation() {
      DefaultUnitOfWork work = new DefaultUnitOfWork(true);
      ArtifactTypeRepository repository = (ArtifactTypeRepository) ProcessPuzzleContext.getInstance().getRepository(ArtifactTypeRepository.class);
      repository.addArtifactType(work, type);
      try {
         artifactTypeFactory.createArtifactType(TEST_TYPE_NAME, ArtifactTypeTestFixture.TYPE_GROUP_NAME);
         fail("We never should reach this point.");
      } catch (EntityIdentityCollitionException e) {
         assertTrue("ArtifactType.name should be unique.", e instanceof EntityIdentityCollitionException);
      }
      repository.deleteArtifactType(work, type);
      work.finish();
   }
}
