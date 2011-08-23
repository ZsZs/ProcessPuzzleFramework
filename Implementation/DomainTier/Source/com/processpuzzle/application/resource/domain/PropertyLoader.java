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