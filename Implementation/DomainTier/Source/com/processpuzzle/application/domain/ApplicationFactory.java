/*
Name: 
    - ApplicationFactory

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

package com.processpuzzle.application.domain;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.springframework.core.io.ResourceLoader;

public class ApplicationFactory {

   public static <A extends Application> A create( Class<A> applicationClass, String configurationDescriptorPath ) {
      Class<?>[] argumentClasses = new Class[] { String.class };
      Object[] arguments = new Object[] { configurationDescriptorPath };
      Constructor<?> applicationConstructor = null;
      A newApplication = null;
      newApplication = instantiateApplication( applicationClass, configurationDescriptorPath, argumentClasses, arguments, applicationConstructor, newApplication );
      return newApplication;
   }

   public static <A extends Application> A create( Class<A> applicationClass, String configurationDescriptorPath, ResourceLoader resourceLoader ) {
      Class<?>[] argumentClasses = new Class[] { String.class, ResourceLoader.class };
      Object[] arguments = new Object[] { configurationDescriptorPath, resourceLoader };
      Constructor<?> applicationConstructor = null;
      A newApplication = null;
      newApplication = instantiateApplication( applicationClass, configurationDescriptorPath, argumentClasses, arguments, applicationConstructor,
            newApplication );
      return newApplication;
   }

   @SuppressWarnings( "unchecked" )
   protected static <A extends Application> A instantiateApplication( Class<A> applicationClass, String configurationDescriptorPath, Class[] argumentClasses,
         Object[] arguments, Constructor applicationConstructor, A newApplication ) {
      try{
         applicationConstructor = applicationClass.getConstructor( argumentClasses );
         newApplication = (A) applicationConstructor.newInstance( arguments );
      }catch( SecurityException e ){
         throw new ApplicationInstantiationException( applicationClass, e );
      }catch( NoSuchMethodException e ){
         throw new ApplicationInstantiationException( applicationClass, e );
      }catch( IllegalArgumentException e ){
         throw new ApplicationInstantiationException( applicationClass, e );
      }catch( InstantiationException e ){
         throw new ApplicationInstantiationException( applicationClass, e );
      }catch( IllegalAccessException e ){
         throw new ApplicationInstantiationException( applicationClass, e );
      }catch( InvocationTargetException e ){
         throw new ApplicationInstantiationException( applicationClass, e );
      }
      return newApplication;
   }
}
