package com.processpuzzle.application.configuration.control;

import java.io.FileNotFoundException;

import javax.servlet.http.HttpSession;

import static org.hamcrest.core.IsNull.*;
import static org.hamcrest.core.IsEqual.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.mockito.Mockito.*;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.configuration.domain.PropertyContext;
import com.processpuzzle.application.configuration.domain.PropertyKeys;
import com.processpuzzle.litest.template.MockServletRunner;

public class SkinSelectorFilterTest {
   private static MockServletRunner servletRunner;

   @BeforeClass
   public static void beforeAllTests() throws FileNotFoundException {
      servletRunner = new MockServletRunner();
      servletRunner.setUp();
      createMockPropertyContext();
   }

   @Before
   public void beforeEachTests() {
      servletRunner.setFilter( SkinSelectorFilter.class );
      servletRunner.doFilter();
   }

   @Test
   public void doFilter_ShouldPlaceSkinDescriptorInSessionContext() {
      // Implicit SETUP and EXCERCISE.

      // VERIFY:
      HttpSession session = servletRunner.getSession();
      SkinDescriptor skinDescriptor = (SkinDescriptor) session.getAttribute( SkinSelectorFilter.SKIN_ATTRIBUTE_NAME );
      assertThat( skinDescriptor, notNullValue() );
      assertThat( skinDescriptor.getSkinName(), equalTo( MockServletRunner.DEFAULT_SKIN_NAME ) );
      assertThat( skinDescriptor.getStylesPath(), equalTo( SkinDescriptor.BASE_PATH + "/" + MockServletRunner.DEFAULT_SKIN_PATH + SkinDescriptor.STYLES_FOLDER ) );
   }

   @After
   public void afterEachTests() {}

   @AfterClass
   public static void afterAllTests() {
      servletRunner.tearDown();
   }

   private static void createMockPropertyContext() {
      PropertyContext mockPropertyContext = mock( PropertyContext.class );
      when( mockPropertyContext.getProperty( PropertyKeys.PRESENTATION_DEFALT_SKIN_NAME.getDefaultKey() )).thenReturn( MockServletRunner.DEFAULT_SKIN_NAME );
      when( mockPropertyContext.getProperty( PropertyKeys.PRESENTATION_DEFALT_SKIN_PATH.getDefaultKey() )).thenReturn( MockServletRunner.DEFAULT_SKIN_PATH );

      ProcessPuzzleContext applicationContext = servletRunner.getApplicationContext();
      when( applicationContext.getPropertyContext() ).thenReturn( mockPropertyContext );
   }
}
