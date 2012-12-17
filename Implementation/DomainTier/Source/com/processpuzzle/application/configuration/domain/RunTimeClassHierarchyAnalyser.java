/*
Name: 
    - RunTimeClassHierarchyAnalyser

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

/**
 * RTSI.java
 *
 * Created: Wed Jan 24 11:15:02 2001
 *
 */
package com.processpuzzle.application.configuration.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.access.BootstrapException;

import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.user_session.domain.UserRequestManager;

/**
 * This utility class is looking for all the classes implementing or inheriting from a given interface or class.
 * (RunTime Subclass Identification)
 * 
 * @author <a href="mailto:daniel@satlive.org">Daniel Le Berre</a>
 * @version 1.0
 */
public class RunTimeClassHierarchyAnalyser {
   private static Logger log = LoggerFactory.getLogger( RunTimeClassHierarchyAnalyser.class );
   private Set<Class<?>> subClasses = new HashSet<Class<?>>();

   public RunTimeClassHierarchyAnalyser() {
      super();
   }
   
   public boolean checkIfIsChildOf( Class<?> classToCheck, Class<?> parentClass ) {
      boolean result = false;
      
      if( classToCheck.equals( Object.class )) return false;
      
      if( classToCheck.getSuperclass().equals( parentClass )) return true;
      else if( !classToCheck.getSuperclass().equals( Object.class ))
         result = checkIfIsChildOf( classToCheck.getSuperclass(), parentClass );

      return result;
   }
   
   public Set<Class<?>> findSubClasses( List<String> targetPackageNames, String parentClassName ) throws FileNotFoundException {
      try{
         Class<?> parentClass = Class.forName( parentClassName );
         
         for( String packageName : targetPackageNames ){
            getClass().getClassLoader().getResource( packageName + ".*" );
         }
         
         Package[] packages = Package.getPackages();
         for( int i = 0; i < packages.length; i++ ){
            String packageName = packages[i].getName();
            for( String targetPackageName : targetPackageNames ){
               if( packageName.startsWith( targetPackageName )) 
                  findSubClasses( packageName, parentClass );
            }
         }
      }catch( ClassNotFoundException ex ){
         log.error( "Class " + parentClassName + " not found!", ex );
      }
      
      return subClasses;
   }

   public void findSubClasses( String packageName, String parentClassName ) throws FileNotFoundException {
      try{
         Class<?> parentClass = Class.forName( parentClassName );
         findSubClasses( packageName, parentClass );
      }catch( ClassNotFoundException ex ){
         log.error( "Class " + parentClassName + " not found!", ex );
      }
   }

   /**
    * Display all the classes inheriting or implementing a given class in a given package.
    * 
    * @param packageName
    *           the fully qualified name of the package
    * @param parentClass
    *           the Class object to inherit from
    * @throws FileNotFoundException 
    */
   public void findSubClasses( String packageName, Class<?> parentClass ) throws FileNotFoundException {
      
      String path = translatePackageNameIntoPath( packageName );
      URL url = getFileForPackage( path );
      if( url == null ) throw new FileNotFoundException( path );
      File directory = new File( url.getFile() );

      if( directory.exists() ){
         determineSubClassesFromClassFiles( packageName, parentClass, directory );
      }else{
         determineSubClassesFromJarFiles( parentClass, url );
      }
   }

   public Set<Class<?>> getSubClasses() { return subClasses; }

   private URL getFileForPackage( String path ) {
      URL url = RunTimeClassHierarchyAnalyser.class.getResource( path );
      // URL url = tosubclass.getResource(name);
      // URL url = ClassLoader.getSystemClassLoader().getResource(name);
      return url;
   }

   private void determineSubClassesFromClassFiles( String packageName, Class<?> parentClass, File directory ) {
      log.debug( "Looking for subclasses of:'" + parentClass.getName() + "' in package:'" + packageName + "'." );
      
      String[] files = directory.list();
      for( int i = 0; i < files.length; i++ ){
   
         // we are only interested in .class files
         if( files[i].endsWith( ".class" ) ){
            // removes the .class extension
            String className = files[i].substring( 0, files[i].length() - 6 );
            try{
               log.debug( "Investigating class: '" + className + "'." );
               String fullClassName = packageName + "." + className;
               
               User currentUser = UserRequestManager.getInstance().currentUser();
            
               Class<?> anyClass = Class.forName( fullClassName );
               
               if( !UserRequestManager.getInstance().currentUser().equals( currentUser )) {
                  System.out.println("Investigating class: '" + fullClassName + "' changed current user!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!.");
                  throw new Exception();
               }
               if( isInstanceOf( anyClass, parentClass ))
                  subClasses.add( anyClass );
            }catch( ClassNotFoundException cnfex ){
               log.error( "Class: " + className + " not found.", cnfex );
            }catch( ExceptionInInitializerError e ) {
               // The class can't be initialized
            }catch( BootstrapException e ) {
               // The class can't be loaded
            }catch( NullPointerException e ) {
               // The class can't be loaded
               log.debug( "Strange to catch here NullPointerException for: " + packageName + "." + className, e );
            }catch( Exception e ) {
               throw new SubClassIdentificationException( parentClass, e );
            }catch( Throwable e ) {
               throw new SubClassIdentificationException( parentClass, e );
            }
         }
      }
   }

   private void determineSubClassesFromJarFiles( Class<?> parentClass, URL url ) {
      log.debug( "Looking for subclasses of:'" + parentClass.getName() + "' in url:'" + url + "'." );
      try{
         // It does not work with the filesystem: we must be in the case of a package contained in a jar file.
         JarURLConnection conn = (JarURLConnection) url.openConnection();
         String starts = conn.getEntryName();
         JarFile jfile = conn.getJarFile();
         Enumeration<JarEntry> e = jfile.entries();
         while( e.hasMoreElements() ){
            ZipEntry entry = (ZipEntry) e.nextElement();
            String entryname = entry.getName();
            if( entryname.startsWith( starts ) && (entryname.lastIndexOf( '/' ) <= starts.length()) && entryname.endsWith( ".class" ) ){
               String className = entryname.substring( 0, entryname.length() - 6 );
               if( className.startsWith( "/" ) )
                  className = className.substring( 1 );
               className = className.replace( '/', '.' );
               try{
                  log.debug( "Investigating class: '" + className + "'." );
                  Class<?> anyClass = Class.forName( className );
                  if( isInstanceOf( anyClass, parentClass ))
                     subClasses.add( anyClass );
               }catch( ClassNotFoundException cnfex ){
                  log.error( "Class: " + className + " not found." );
//               }catch( InstantiationException iex ){
//                  // We try to instanciate an interface or an object that does not have a default constructor
//               }catch( IllegalAccessException iaex ){
//                  // The class is not public
               }catch( Exception ex ) {
                  throw new SubClassIdentificationException( parentClass, ex );
               }catch( Throwable ex ) {
                  throw new SubClassIdentificationException( parentClass, ex );
               }
            }
         }
      }catch( IOException ioex ){
         log.error( "This error text should be defined!", ioex );
      }
   }

   private String translatePackageNameIntoPath( String packageName ) {
      String path = new String( packageName );
      if( !path.startsWith( "/" ) ){
         path = "/" + path;
      }
      path = path.replace( '.', '/' );
      return path;
   }
   
   private boolean isInstanceOf( Class<?> classToCheck, Class<?> parentClass ) {
      Class<?> superClass = null;
      try{
         superClass = classToCheck.getSuperclass();         
      }catch( Exception e ){
         System.out.println( RunTimeClassHierarchyAnalyser.class.getName() + ": Unexpected exception occured.");
      }
      if( superClass.equals( parentClass )) return true;
      else if( !( superClass.equals( Object.class )))
         return isInstanceOf( superClass, parentClass );
      else return false;
   }
}
