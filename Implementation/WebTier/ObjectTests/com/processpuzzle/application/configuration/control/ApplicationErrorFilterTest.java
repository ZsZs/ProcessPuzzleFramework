package com.processpuzzle.application.configuration.control;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.processpuzzle.application.control.control.CommandControllerErrorHelper;
import com.processpuzzle.application.domain.ApplicationException;
import com.processpuzzle.litest.template.FilterTestTemplate;
import com.processpuzzle.sharedfixtures.webtier.WebTierTestConfiguration;

public class ApplicationErrorFilterTest extends FilterTestTemplate<ApplicationErrorFilter, ApplicationErrorFilterTestFixture>{

   public ApplicationErrorFilterTest() {
      super( WebTierTestConfiguration.FIXTURE_CONTAINER_DEFINITION_PATH );
   }

   @Test public void doFilter_ShouldForwardOnApplicationStartupFailure() {
      //SETUP:
      ApplicationException applicationException = new ApplicationException( "Install", null );
      setServletContextAttribute( ApplicationLifecycleListener.STARTUP_FAILURE_ATTRIBUTE, applicationException );
      
      //EXCERCISE:
      doFilter();
      
      //VERIFY:
      CommandControllerErrorHelper errorHelper = ((CommandControllerErrorHelper) getRequestAttribute( ApplicationErrorFilter.EXCEPTION_ATTRIBUTE ));
      assertThat( errorHelper, instanceOf( CommandControllerErrorHelper.class ) );
      assertThat( errorHelper.getCause(), equalTo( (Throwable) applicationException ));

      //TEARDOWN:
   }
   
   @Test public void doFilter_ShouldRedirectIfUnhandledExceptionOccured() {      
   }
   
}
