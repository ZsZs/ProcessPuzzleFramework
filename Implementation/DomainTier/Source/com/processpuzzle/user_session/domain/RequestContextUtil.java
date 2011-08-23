package com.processpuzzle.user_session.domain;

//import javax.ejb.EJBObject;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utilities used by RequestContext implementation. Adapted from ClassUtil by Wenbo Zhu
 * 
 * @author Wenbo Zhu
 * @author kkj
 * @version 1.0
 */
public final class RequestContextUtil {
   private static final Logger log = LoggerFactory.getLogger( RequestContextUtil.class );

   /**
    * Get the class names than can be used in remote reflection invocation. <p/> argTypes is not null (or empty).
    * 
    * @param argTypes
    *           The method argument classes
    * @return class names
    */
   public static String[] getNames( Class<?>[] argTypes ) {
      String[] result = new String[argTypes.length];
      for( int i = 0; i < argTypes.length; i++ ){
         result[i] = argTypes[i].getName();
      }
      return result;
   }

   /**
    * Get the classes from names. <p/> argTypes is not null.
    * 
    * @param argTypes
    *           The method argument classes' names
    * @return ClassNotFoundException if any class can not be located
    */
   public static Class<?>[] forNames( String[] argTypes ) throws ClassNotFoundException {
      Class<?>[] result = new Class[argTypes.length];
      for( int i = 0; i < argTypes.length; i++ ){
         result[i] = forName( argTypes[i] );
      }
      return result;
   }

   /**
    * Enhanced java.lang.Class.forName().
    * 
    * @param name
    *           The class name or a primitive type name
    * @return The matched class
    * @throws ClassNotFoundException
    *            if no class can be located
    */
   public static Class<?> forName( String name ) throws ClassNotFoundException {

      if( name.equals( "int" ) ){
         return int.class;
      }else if( name.equals( "boolean" ) ){
         return boolean.class;
      }else if( name.equals( "char" ) ){
         return char.class;
      }else if( name.equals( "byte" ) ){
         return byte.class;
      }else if( name.equals( "short" ) ){
         return short.class;
      }else if( name.equals( "long" ) ){
         return long.class;
      }else if( name.equals( "float" ) ){
         return float.class;
      }else if( name.equals( "double" ) ){
         return double.class;
      }else{
         return Class.forName( name );
      }
   }

   /**
    * If object implements RequestContextInterceptor, wrap the instance in a proxy for context propagation. Otherwise, return the original object unchanged.
    * 
    * @param object
    *           The EJB remote interface to wrap if possible
    * @return Potentially a wrapped remote interface for context propagation
    */
   // public static EJBObject getRemoteInterface(EJBObject object) {
   // if (object instanceof RequestContextInterceptor) {
   // if (log.isDebugEnabled()) {
   // log.debug("Wrapping remote object " + object + " in RequestContextPropagationInterceptor");
   // }
   // object = (EJBObject) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
   // filterClasses(object.getClass().getInterfaces()),
   // new RequestContextPropagationInterceptor((RequestContextInterceptor) object));
   // } else {
   // if (log.isDebugEnabled()) {
   // log.debug("Remote object " + object + " does not implement RequestContextInterceptor! Request context propagation disabled!");
   // }
   // }
   // return object;
   // }
   /**
    * Filter out classes that are not sub interfaces of EJBObject. These could include application server specific interfaces attached for internal purposes. If
    * present, such interfaces could cause class-loading issues with the proxy.
    * 
    * @param classes
    *           Interfaces to filter
    * @return Interfaces extending EJBObject
    */
   @SuppressWarnings( "unused" )
   private static Class[] filterClasses( Class[] classes ) {
      if( classes == null ){
         return classes;
      }

      List<Class> newClassList = new ArrayList<Class>();
      for( int i = 0; i < classes.length; i++ ){
         if( log.isDebugEnabled() ){
            log.debug( "Class " + i + " = " + classes[i].getName() );
         }
         // if (EJBObject.class.isAssignableFrom(classes[i])) {
         // newClassList.add(classes[i]);
         // }
         else{
            if( log.isDebugEnabled() ){
               log.debug( "Ignoring interface " + classes[i].getName() );
            }
         }
      }
      return (Class[]) newClassList.toArray( new Class[newClassList.size()] );
   }
}
