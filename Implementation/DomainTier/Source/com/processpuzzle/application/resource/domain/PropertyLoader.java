/*
Name: 
    - PropertyLoader

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
import java.io.InputStream;
import java.util.Properties;

import org.springframework.core.io.ResourceLoader;

/**
 * A simple class for loading java.util.Properties backed by .properties files
 * deployed as classpath or url resources. See individual methods for details.
 */

public class PropertyLoader extends DataFromResourceLoader {
   private Properties loadedProperties = null;

//Constructors   
   public PropertyLoader( String path ) {
      this( null, path);
   }
   
   public PropertyLoader( ResourceLoader resourceLoader, String fileName) {
      super( resourceLoader, fileName );
   }

   //Public mutators
   public void loadData() {
      try { super.loadData(); }
      catch( ResourceNotFoundException e ) { throw new PropertyLoadException( e.getReourceUrl(), e ); }
      
      InputStream in = null;
      try {
         in = resource.getInputStream();
         if (in != null) {
            loadedProperties = new Properties();
            try {
               loadedProperties.load(in);               
            } catch (IOException e) {throw new PropertyLoadException( resourcePath, e );}
            catch (IllegalArgumentException e) {throw new PropertyLoadException( resourcePath, e );}
         }
         else {
            throw new PropertyLoadException( resourcePath );            
         }
      }
      catch (IOException e) {
         throw new PropertyLoadException( resourcePath, e);
      }
      finally {
         if (in != null) {
            try {
               in.close();
            } catch (Throwable ignore) {}
         }
      }
   }

//Setters and getters
   public Properties getLoadedProperties() {return loadedProperties;}
}