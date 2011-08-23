package com.processpuzzle.sharedfixtures.webtier;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletContext;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

public class MockServletContextFixtureTest {
   @Test public void resourceExists() throws IOException {
      MockServletContextFixture fixture = new MockServletContextFixture();
      fixture.setUp();
      
      String resourcePath = WebTierTestConfiguration.BEAN_CONTAINER_DEFINITION_PATH;
      Resource resource = fixture.getResourceLoader().getResource( resourcePath );
      
      File file = new File( fixture.getServletContext().getRealPath( resourcePath ) );
      assertThat( file.exists(), is( true ));
      
      file = new File( getRealPath( fixture.getServletContext(), resourcePath ) );
      assertThat( file.exists(), is( true ));
      assertThat( resource.getFile().exists(), is( true ));
      assertThat( resource.exists(), is( true ));
   }
   
    public static String getRealPath(ServletContext servletContext, String path) throws FileNotFoundException {
        Assert.notNull(servletContext, "ServletContext must not be null");
        // Interpret location as relative to the web application root directory.
        if (!path.startsWith("/")) {
            path = "/" + path;
        }
        String realPath = servletContext.getRealPath(path);
        if (realPath == null) {
            throw new FileNotFoundException(
                    "ServletContext resource [" + path + "] cannot be resolved to absolute file path - " +
                    "web application archive not expanded?");
        }
        return realPath;
    }
}
