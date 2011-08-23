/*
 * Created on Dec 12, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.application.control.control;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import hu.itkodex.litest.template.FrontCommandTestTemplate;



import org.junit.Test;

import com.processpuzzle.sharedfixtures.webtier.WebTierTestConfiguration;

public class BuildXmlCommandTest extends FrontCommandTestTemplate<BuildXmlCommand, BuildXmlCommandFixture> {
   
   public BuildXmlCommandTest() {
      super( WebTierTestConfiguration.FIXTURE_CONTAINER_DEFINITION_PATH );
   }

   @Test public final void execute_ConfiguresResponse() throws Exception {
      command.execute( commandDispatcher );
      assertThat( response.getContentType(), equalTo( "text/xml" ));
      assertThat( response.getCharacterEncoding(), equalTo( "UTF-8" ));
      assertThat( response.containsHeader( BuildXmlCommand.CACHE_CONTROL_NAME ), is( true ));
   }
}
