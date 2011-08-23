/*
 * Created on Jul 12, 2006
 */
package com.processpuzzle.artifact_management.control;

import java.util.Date;

import com.processpuzzle.artifact.domain.TestEntityDataSheet;
import com.processpuzzle.persistence.domain.TestEntity;
import com.processpuzzle.user_session.domain.UserRequestManager;

/**
 * @author zsolt.zsuffa
 */
public class ArtifactCommandsTestFixture {
   public static String TEXT_VALUE = "Some text";
   public static Integer NUMBER_VALUE = new Integer(555);
   public static Date DATE_VALUE = new Date(0);
   protected static ArtifactCommandsTestFixture fixtureInstance = null;
   private TestEntityDataSheet dataSheet = null;
   private TestEntity testEntity = null;

   public static ArtifactCommandsTestFixture getInstance() {
      if (fixtureInstance == null)
         return new ArtifactCommandsTestFixture();
      return fixtureInstance;
   }

   protected void setUp() {
      UserRequestManager.getInstance().getApplicationContext();
      dataSheet = TestEntityDataSheet.create("Test_Entity");
      testEntity = dataSheet.getTestEntity();
      testEntity.setTextAttribute(TEXT_VALUE);
      testEntity.setNumberAttribute(NUMBER_VALUE);
      testEntity.setDateAttribute(DATE_VALUE);
      dataSheet.update();
   }

   protected void tearDown() {
   // TODO Implement tearDown().
   }
}