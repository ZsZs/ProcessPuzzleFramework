package com.processpuzzle.application.configuration.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.MockitoAnnotations.Mock;

import com.processpuzzle.application.domain.Application;
import com.processpuzzle.fundamental_types.quantity.domain.Unit;

public class MeasurementContextTest extends ContextTest {
   @Mock private Application application;
   private MeasurementContext measurementContext;
   
   @Before
   public void beforeEachTest() {
      MockitoAnnotations.initMocks( MeasurementContextTest.class );
      measurementContext = new MeasurementContext( application );
      measurementContext.setUp( Application.Action.start );
   }

   @Test
   public void findUnitBySymbol_ForExistingSymbols(){
      assertNotNull("HUF exists",measurementContext.findUnitBySymbol("HUF"));
      assertNotNull("msec exists",measurementContext.findUnitBySymbol("msec"));
      assertNotNull("pc exists",measurementContext.findUnitBySymbol("pc"));
      assertNotNull("mm exists",measurementContext.findUnitBySymbol("mm"));
   }
   
   @Test
   public void findUnitBySymbol_ForNonExistingSymbol(){
      assertNull("xy doesn't exists",measurementContext.findUnitBySymbol("xy"));
   }
   
   @Test
   public void findAllUnits_ForNotEmpty(){
      Collection<Unit> c=measurementContext.findAllUnits();
      assertFalse("Unit list is not empty", c.isEmpty());
   }
   
   @After
   public void afterEachTest() {
      measurementContext.tearDown( Application.Action.stop );
      measurementContext = null;
   }
   
}
