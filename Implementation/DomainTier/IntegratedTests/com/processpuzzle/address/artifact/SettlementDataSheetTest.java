/*
 * Created on Sep 10, 2006
 */
package com.processpuzzle.address.artifact;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.address.artifact.SettlementDataSheet;

/**
 * @author zsolt.zsuffa
 */
public class SettlementDataSheetTest {
   private SettlementDataSheetTestFixture fixture = null;
   private SettlementDataSheet budapestSheet = null;
   
   @Before
   public void setUp() throws Exception {
      fixture = SettlementDataSheetTestFixture.getInstance();
      fixture.setUp();
      budapestSheet = fixture.getBudapest();
   }

   @After
   public void tearDown() throws Exception {
   }
 
   @Test
   public void create() {
      assertNotNull("The create() method also creates a Settlement object.", budapestSheet.getSettlement());
      assertEquals("The SettlementDataSheet inherits the settlement's name.", budapestSheet.getName(), budapestSheet.getSettlement().getName());
   }
}
