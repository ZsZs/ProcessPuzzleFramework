/*
 * Created on 2006.07.20.
 */
package com.processpuzzle.artifact.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.persistence.domain.TestEntity;
import com.processpuzzle.persistence.query.domain.DefaultQuery;
import com.processpuzzle.artifact_type.domain.ArtifactTypeTestFixture;

/**
 * @author zsolt.zsuffa
 */
public class ListQueryViewTest {
   private ArtifactTestFixture artifactFixture = null;
   private TestEntityDataSheet dataSheet = null;
   private TestEntity testEntity = null;
   private TestEntityDataSheet_QueryView queryView = null;

   @Before
   public void setUp() throws Exception {
      artifactFixture = ArtifactTestFixture.getInstance();
      artifactFixture.setUp();
      dataSheet = artifactFixture.getDataSheet();
      testEntity = dataSheet.getTestEntity();
      queryView = (TestEntityDataSheet_QueryView) dataSheet.getView(TestEntityDataSheet_QueryView.class.getSimpleName());
   }

   @After
   public void tearDown() throws Exception {
      artifactFixture.tearDown();
   }

   @Ignore
   @Test
   public void testQueryView_ForExistence() {
      assertNotNull(queryView);
   }

   @Ignore
   @Test
   public void testGetPreDefinedQueries() {
      DefaultQuery query = (DefaultQuery) queryView.getPreDefinedQueries().get(ArtifactTypeTestFixture.QUERY_NAME); 
      assertNotNull(query);
      assertEquals(ArtifactTypeTestFixture.QUERY_DESCRIPTION, query.getDescription());
//      assertEquals(ArtifactTypeTestFixture.QUERY_STATEMENT, query.getStatement());
   }
   
   @Ignore
   @Test
   public void testAdHocProperties () {
      assertEquals("Values returned by view getter should be the same as:", testEntity.getName(), queryView.getArtifactName());
      assertEquals("Values returned by view getter should be the same as:", testEntity.getTextAttribute(), queryView.getTextAttribute());
      assertEquals("Values returned by view getter should be the same as:", testEntity.getNumberAttribute(), queryView.getNumberAttribute());
      assertEquals("Values returned by view getter should be the same as:", testEntity.getDateAttribute(), queryView.getDateAttribute());
   }
}
