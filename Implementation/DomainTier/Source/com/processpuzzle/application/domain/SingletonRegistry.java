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
