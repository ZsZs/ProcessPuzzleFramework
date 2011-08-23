/*
 * Created on Feb 13, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.application.resource.domain;

import java.io.InputStream;
import java.net.URL;

public class ClassLoaderIO extends IOEnvironment {
	
   private ClassLoader loader = null;

   public ClassLoaderIO(ClassLoader loader) {
      this.loader = loader;
   }

   public InputStream inputStream(String fileName) {
      
      return loader.getResourceAsStream(fileName);
   }

   public URL getResource(String path) {
		return loader.getResource(path);
   }

   public String getRealPath(String path) {
      return null;
   }

}
