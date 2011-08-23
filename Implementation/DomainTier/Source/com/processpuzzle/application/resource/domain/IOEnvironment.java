/*
 * Created on Feb 13, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.application.resource.domain;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class IOEnvironment {
  
   public abstract InputStream inputStream (String fileName);
 
   public abstract URL getResource(String path) throws MalformedURLException;
   
   public abstract String getRealPath(String path);
}
