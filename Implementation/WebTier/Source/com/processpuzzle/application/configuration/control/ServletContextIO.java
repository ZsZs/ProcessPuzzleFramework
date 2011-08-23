/*
 * Created on Feb 13, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.application.configuration.control;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletContext;

import com.processpuzzle.application.resource.domain.IOEnvironment;

public class ServletContextIO extends IOEnvironment {
	
   private ServletContext context = null;
   
   public ServletContextIO (ServletContext context) {
      this.context = context;
   }

   public InputStream inputStream(String fileName) {
      return context.getResourceAsStream(fileName);
   }

	public URL getResource(String path) throws MalformedURLException {
		return context.getResource(path);
	}

	public String getRealPath(String path) {
		return context.getRealPath(path);
	}
    
}
