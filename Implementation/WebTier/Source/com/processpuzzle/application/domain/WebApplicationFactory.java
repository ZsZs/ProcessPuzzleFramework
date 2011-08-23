package com.processpuzzle.application.domain;

import java.lang.reflect.Constructor;

import javax.servlet.ServletContext;

import org.springframework.core.io.ResourceLoader;
import org.springframework.web.context.support.ServletContextResourceLoader;

public class WebApplicationFactory extends ApplicationFactory {

   public static <A extends Application> A create( Class<A> applicationClass, String configurationDescriptorPath, ServletContext servletContext ) {
      ServletContextResourceLoader resourceLoader = new ServletContextResourceLoader( servletContext );
      
      Class[] argumentClasses = new Class[] { String.class, ResourceLoader.class };
      Object[] arguments = new Object[] { configurationDescriptorPath, resourceLoader };
      Constructor<?> applicationConstructor = null;
      A newApplication = null;
      newApplication = instantiateApplication( applicationClass, configurationDescriptorPath, argumentClasses, arguments, applicationConstructor, newApplication );
      return newApplication;
   }
}
