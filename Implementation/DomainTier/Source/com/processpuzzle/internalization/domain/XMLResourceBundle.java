/*
Name: 
    - XMLResourceBundle

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

/*
 =====================================================================

 XMLResourceBundle.java
 
 Created by Claude Duguay
 Copyright (c) 2002
 
 =====================================================================
 */

package com.processpuzzle.internalization.domain;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.core.io.ResourceLoader;

/**
 * XML ResourceBundles loads a HashMap with values associated with localized keys. More specific locale information is
 * prefered, and overwritten as it's processed. Deeper XML values are more specific.
 */

public class XMLResourceBundle {
   public static String PATH_DELIMITERS = ";,";
   public static String DEFAULT_PATH_DELIMITER = ";";
   private ProcessPuzzleLocale targetLocale = null;
   protected Logger log = LoggerFactory.getLogger( XMLResourceBundle.class );
   protected XMLBundleParser parser;
   protected ResourceCache cache;
   protected String fileSetPath;
   protected ResourceLoader loader = null;
   protected Collection<String> loadedFileNames = new ArrayList<String>();

   // Constructors
   public XMLResourceBundle( ResourceLoader loader, String fileSetPath ) {
      this.loader = loader;
      if( loader != null )
         parser = new XMLBundleParser( loader );
      else
         parser = new XMLBundleParser();
      cache = new ResourceCache();
      this.fileSetPath = fileSetPath;
   }

   public XMLResourceBundle( String fileSetPath ) {
      this( null, fileSetPath );
   }

   public XMLResourceBundle() {
      this( null );
   }

   // Public mutators
   @SuppressWarnings( "static-access" )
   public void loadResources( ProcessPuzzleLocale locale ) throws InvalidResourcePathException, InternalizationException {
      StringTokenizer tokenizer = new StringTokenizer( fileSetPath, this.PATH_DELIMITERS );

      while( tokenizer.hasMoreTokens() ){
         String token = tokenizer.nextToken();
         token = token.trim();
         loadResourceFile( token, locale );
      }
   }

   // Getters and setters
   public String getText( String key ) throws NoneExistingResourceKeyException {
      String text;
      try{
         text = cache.getResource( key, "String" );
      }catch( NoneExistingResourceKeyException e ){
         throw new NoneExistingResourceKeyException( key, fileSetPath, targetLocale );
      }
      return text;
   }

   public File getFile( String key ) throws NoneExistingResourceKeyException {
      String file = cache.getResource( key, "File" );
      return new File( file );
   }

   public String getFileSetPath() {
      return fileSetPath;
   }

   public void setFilePath( String filePath ) {
      this.fileSetPath = filePath;
   }

   public Collection<String> getLoadedFileNames() {
      return loadedFileNames;
   }

   // Private mutators
   protected void loadResourceFile( String baseFilePath, ProcessPuzzleLocale locale ) throws InternalizationException,
         InvalidResourcePathException {
      targetLocale = locale;
      if( baseFilePath.contains( ".xml" ) )
         baseFilePath = baseFilePath.substring( 0, baseFilePath.indexOf( ".xml" ) );
      String[] fileList = LocaleUtil.getFileNameList( locale, baseFilePath, ".xml" );
      int numberOfLoadedFiles = 0;
      for( int i = 0; i < fileList.length; i++ ){
         String resourcePath = fileList[i];

         if( resourcePath != null ){
            try{
               parseFile( cache, resourcePath, locale );
               loadedFileNames.add( resourcePath );
               numberOfLoadedFiles++;
            }catch( ResourceBundleIOException e ){
               if( i <= fileList.length - 1 ){
                  log.info( "Can't parse resource: " + resourcePath );
               }else
                  throw new InvalidResourcePathException( fileSetPath, e );
            }
         }
      }
      if( numberOfLoadedFiles == 0 )
         throw new InvalidResourcePathException( baseFilePath );
   }

   protected void parseFile( ResourceCache cache, String absolutePath, ProcessPuzzleLocale locale ) throws InternalizationException {
      parser.parse( cache, absolutePath, locale );
   }
}
