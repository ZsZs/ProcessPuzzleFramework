package com.processpuzzle.application.security.control;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.processpuzzle.application.security.control.AuthenticationFilter;
import com.processpuzzle.litest.template.FilterTestTemplate;
import com.processpuzzle.sharedfixtures.webtier.WebTierTestConfiguration;

public class AuthenticationFilterTest extends FilterTestTemplate<AuthenticationFilter, AuthenticationFilterTestFixute> {
   
   public AuthenticationFilterTest() {
      super( WebTierTestConfiguration.FIXTURE_CONTAINER_DEFINITION_PATH );
   }

   @Test public final void doFilter_ActuallyDoesNothing() throws FailingHttpStatusCodeException, IOException {
      assertThat( "Actually does nothing.", true, is( true ));
   }
}