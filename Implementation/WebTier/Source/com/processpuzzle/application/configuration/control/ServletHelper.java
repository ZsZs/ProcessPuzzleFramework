package com.processpuzzle.application.configuration.control;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

public class ServletHelper {

   public ServletHelper() {
      super();
   }

   public Properties extractProperties( HttpServletRequest request ) {
      Properties properties = new Properties();
      Map<String, String[]> propertyValues = new HashMap<String, String[]>();
      Enumeration<?> enumeration = request.getParameterNames();
      
      while( enumeration.hasMoreElements() ){
         String name = (String) enumeration.nextElement();
         if(( request.getParameterValues( name )).length > 1 ){
            propertyValues.put( name, request.getParameterValues( name ) );
         }else
            properties.put( name, request.getParameterValues( name )[0] );
      }
      return properties;
   }
}
