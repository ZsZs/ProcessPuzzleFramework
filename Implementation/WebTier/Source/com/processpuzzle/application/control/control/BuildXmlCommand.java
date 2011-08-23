/*
 * Created on Dec 12, 2005
 * 
 * TODO To change the template for this generated file go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.application.control.control;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

public class BuildXmlCommand implements CommandInterface {
   public static final String CACHE_CONTROL_NAME = "Cache-Control";
   public static final String CACHE_CONTROL_VALUE = "no-cache";
   public static final String CHARACTER_ENCODING = "UTF-8";
   public static final String CONTENT_TYPE = "text/xml";
   public static final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>";

   public void init( CommandDispatcher dispatcher ) {}

   public String getName() {
      return this.getClass().getName();
   }

   public String execute( CommandDispatcher dispatcher ) throws Exception {

      HttpServletResponse response = dispatcher.getResponse();

      response.setContentType( CONTENT_TYPE );
      response.setCharacterEncoding( CHARACTER_ENCODING );
      response.setHeader( CACHE_CONTROL_NAME, CACHE_CONTROL_VALUE );

      PrintWriter responseWriter = response.getWriter();
      responseWriter.println( XML_HEADER );

      return "http://www.processpuzzle.com";
   }
}