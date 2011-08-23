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
