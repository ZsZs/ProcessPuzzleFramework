/*
 * Created on Dec 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.application.control.control;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.processpuzzle.artifact_management.control.ShowArtifactCommand;
import com.processpuzzle.litest.template.MockServletRunner;

@RunWith( PowerMockRunner.class )
@PrepareForTest( CommandFactory.class )
public class CommandControllerServletTest {
   private static final String COMMAND_NAME = "ShowArtifact";
   private static final String UNKNOWN_COMMAND_NAME = "UnknownFrontCommand";
   private MockServletRunner servletRunner;
   
   @Before public void beforeEachTests() throws Exception {
      servletRunner = new MockServletRunner();
      servletRunner.setUp();
      servletRunner.setServlet( CommandControllerServlet.class );
      
      PowerMockito.mockStatic( CommandFactory.class );
   }

   @After public void afterEachTests() {
      servletRunner.tearDown();
   }

   @Test public final void processRequest_WhenCommandIsSpecified_InstantiatesAndExecutes () throws Exception {
      ShowArtifactCommand showArtifactCommand = mock( ShowArtifactCommand.class );
      when( CommandFactory.getCommand( COMMAND_NAME )).thenReturn( showArtifactCommand );
      servletRunner.addRequestParameter( CommandDispatcher.ACTION_PARAMETER_NAME, COMMAND_NAME );
      
      servletRunner.doPost();
      
      verify( showArtifactCommand ).init( (CommandDispatcher) any() );
      verify( showArtifactCommand ).execute( (CommandDispatcher) any() );
   }
 
   @Test public final void processRequest_WhenCommandParameterIsMissing_UnknownFrontCommandExecutes () throws Exception {
      UnknownFrontCommand unknownFrontCommand = mock( UnknownFrontCommand.class );
      when( CommandFactory.getCommand( UNKNOWN_COMMAND_NAME )).thenReturn( unknownFrontCommand );
      
      servletRunner.doPost();
      
      verify( unknownFrontCommand ).init( (CommandDispatcher) any() );
      verify( unknownFrontCommand ).execute( (CommandDispatcher) any() );
   }
   
   @Test public final void processRequest_InternalExceptionsOccures_DispatchesToErrorPage () throws Exception {
      ShowArtifactCommand showArtifactCommand = mock( ShowArtifactCommand.class );
      when( showArtifactCommand.execute( (CommandDispatcher) any() ) ).thenThrow( new RuntimeException() );
      when( CommandFactory.getCommand( COMMAND_NAME )).thenReturn( showArtifactCommand );
      servletRunner.addRequestParameter( CommandDispatcher.ACTION_PARAMETER_NAME, COMMAND_NAME );
      
      servletRunner.doPost();
      
      assertThat( servletRunner.getRequest().getAttribute( CommandControllerServlet.ERROR_HELPER_ATTRIBUTE ), instanceOf( CommandControllerErrorHelper.class ));
   }
}
