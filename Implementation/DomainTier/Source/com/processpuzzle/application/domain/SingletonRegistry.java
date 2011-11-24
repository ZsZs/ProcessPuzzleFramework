/*
Name: 
    - SingletonRegistry

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

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SingletonRegistry {
   public static SingletonRegistry REGISTRY = new SingletonRegistry();

   private static HashMap<Class<?>, Object> map = new HashMap<Class<?>, Object>();
   private static Logger logger = LoggerFactory.getLogger( SingletonRegistry.class );

   protected SingletonRegistry() {
      // Exists to defeat instantiation
   }
   
   @SuppressWarnings("unchecked")
   public static <S> S getInstance( Class <S> classToInstantiate ) {
      S singleton = (S) map.get( classToInstantiate );

      synchronized( map ) {
         if( singleton != null ) { return singleton; }
         
         try {
            singleton = classToInstantiate.newInstance();
            logger.info("created singleton: " + singleton);
         }

         catch(InstantiationException ie) {
            logger.error("Couldn't instantiate an object of type " + classToInstantiate );    
         }
         catch(IllegalAccessException ia) {
            logger.error("Couldn't access class " + classToInstantiate );    
         }
         map.put( classToInstantiate, singleton );
      }
      return singleton;
   }
   
   public static Application getApplicationInstance( String applicationName ) {
      // TODO Auto-generated method stub
      return null;
   }
}
