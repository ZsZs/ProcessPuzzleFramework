/*
 * Created on Jul 13, 2006
 */
package com.processpuzzle.artifact.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.persistence.domain.TestEntity;

/**
 * @author zsolt.zsuffa
 */
public class CustomFormViewTest {
   private static String NEW_TEXT_VALUE = "New text value.";
   private ArtifactTestFixture fixture = null;
   private TestEntityDataSheet dataSheet = null;
   private TestEntity testEntity = null;
   private TestEntityFormView formView = null;

   @Before
   public void setUp() throws Exception {
      fixture = ArtifactTestFixture.getInstance();
      fixture.setUp();
      dataSheet = fixture.getDataSheet();
      formView = (TestEntityFormView) dataSheet.getView(TestEntityFormView.class.getSimpleName());
      testEntity = dataSheet.getTestEntity();
   }

   @After
   public void tearDown() throws Exception {
      fixture.tearDown();
   }

   @Ignore
   @Test
   public void testFormView_ForExistence () {
      assertNotNull(formView);
   }
   
   @Ignore
   @Test
   public void testFormView_ForGetters () {
      assertEquals(ArtifactTestFixture.TEXT_VALUE, formView.getTextAttribute());
      assertEquals(ArtifactTestFixture.NUMBER_VALUE, formView.getNumberAttribute());
      assertEquals(ArtifactTestFixture.DATE_VALUE, formView.getDateAttribute());
   }
   
   @Ignore
   @Test
   public void testFormView_ForSetters () {
      formView.setTextAttribute(NEW_TEXT_VALUE);
      assertEquals(NEW_TEXT_VALUE, testEntity.getTextAttribute());
   }
}
