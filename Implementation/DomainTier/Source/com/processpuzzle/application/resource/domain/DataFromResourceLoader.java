/*
Name: 
    - DataFromResourceLoader

Description: 
    -  

Requires:
    - 
    
Provides:
    - 

Part of: ProcessPuzzle Framework, Domain and Business Model Ready Architecture. Provides content, workflow and social networking functionality. 
http://www.processpuzzle.com

ProcessPuzzle - Content and Workflow Management Integration Business Platform

Author(s): 
    - Zsolt Zsuffa

Copyright: (C) 2011 This program is free software: you can redistribute it and/or modify it under the terms of the 
GNU General Public License as published by the Free Software Foundation, either version 3 of the License, 
or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.processpuzzle.application.resource.domain;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.StringTokenizer;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public abstract class DataFromResourceLoader extends DataLoader {
   public String PATH_DELIMITERS = ";,";
   protected String resourcePath = null;
   protected Resource resource = null;

   // Constructors
   public DataFromResourceLoader( ResourceLoader loader, String path ) {
      super( );
      this.resourceLoader = loader;
      parsePath(path);
   }

   public DataFromResourceLoader( String path ) {
      this( new DefaultResourceLoader(), path );
   }
   
   DataFromResourceLoader() {
      super();
   }

   // Public mutators
   public void loadData() {
      try { loadResource(); } 
      catch (IOException e) { throw new ResourceNotFoundException( resourcePath, e ); };
      super.loadData();
   }

// Properties
   public String getResourcePath() { return resourcePath; }
   public void setResourcePath(String path) { parsePath( path ); }
   public Resource getResource() { return resource; }

   // Private methods
   private void parsePath(String path) {
      StringTokenizer tokenizer = new StringTokenizer(path, this.PATH_DELIMITERS);
      resourcePath = tokenizer.nextToken();

      while (tokenizer.hasMoreTokens()) {
         String token = tokenizer.nextToken();
         token = token.trim();
         try {
            Constructor<? extends DataFromResourceLoader> constructor = this.getClass().getConstructor(new Class[] { String.class });
            next = constructor.newInstance(new Object[] { token });
            ((DataFromResourceLoader) next).setResourcePath(token);
         } catch (IllegalAccessException e) {
            throw new DataLoaderException(this, e.getLocalizedMessage(), e);
         } catch (InstantiationException e) {
            throw new DataLoaderException(this, e.getLocalizedMessage(), e);
         } catch (SecurityException e) {
            throw new DataLoaderException(this, e.getLocalizedMessage(), e);
         } catch (NoSuchMethodException e) {
            throw new DataLoaderException(this, e.getLocalizedMessage(), e);
         } catch (IllegalArgumentException e) {
            throw new DataLoaderException(this, e.getLocalizedMessage(), e);
         } catch (InvocationTargetException e) {
            throw new DataLoaderException(this, e.getLocalizedMessage(), e);
         }
      }
   }

   private void loadResource() throws IOException {
      if( resourceLoader == null) resourceLoader = (ResourceLoader) new DefaultResourceLoader();
      resource = resourceLoader.getResource( resourcePath );
      if( resource.getInputStream().available() <= 0 ) throw new IOException( "Resource: " + resourcePath + "doesn't exists." );
   }
}
