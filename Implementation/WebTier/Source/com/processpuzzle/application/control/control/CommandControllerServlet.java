package com.processpuzzle.application.control.control;

import java.io.IOException;
import java.security.InvalidParameterException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.processpuzzle.application.security.control.AuthorizationException;
import com.processpuzzle.artifact_management.control.ArtifactCommandException;
import com.processpuzzle.artifact_management.control.ListQueryException;

/**
 * @pattern Command (role=servletClass)
 * @generatedBy CodePro at 2005.11.13. 6:28
 * @author zsolt.zsuffa
 * @version $Revision$
 */
public class CommandControllerServlet extends HttpServlet {
   public static final String ERROR_HELPER_ATTRIBUTE = "error";
   public static final String ERROR_PAGE = "/Commons/FrontController/FrontControllerError.jsp";
   private static final long serialVersionUID = -5470896039364205824L;

   protected ServletContext servletContext = null;

   public void init( ServletConfig arg0 ) throws ServletException {
      super.init( arg0 );
      this.servletContext = arg0.getServletContext();
   }

   public CommandControllerServlet() {
      super();
   }

   public void service( ServletRequest request, ServletResponse response ) throws ServletException, IOException {
      super.service( request, response );
   }

   protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
      processRequest( request, response );
   }

   protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
      processRequest( request, response );
   }

   private void processRequest( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
      CommandDispatcher dispatcher = null;
      CommandControllerErrorHelper errorHelper = null;
      String next = null;
      try{
         dispatcher = new CommandDispatcher( request, response, servletContext );
         next = dispatcher.executeCommand();
         if( next == null ){
            String description = "The requested response page: " + request.getRequestURI() + " is invalid.";
            errorHelper = new CommandControllerErrorHelper( "InvalidResponsePage", description );
            errorHelper.setRequestAttributes( dispatcher.getProperties() );
            request.setAttribute( ERROR_HELPER_ATTRIBUTE, errorHelper );
            next = ERROR_PAGE;
         }
      }catch( AuthorizationException autex ){
         dispatch( request, response, "/Commons/FrontController/AuthorizationError.jsp" );
      }catch( InvalidParameterException ex ){
         dispatchToFrontControllerError( request, response, dispatcher, ex );
      }catch( ArtifactCommandException ex ){
         dispatchToFrontControllerError( request, response, dispatcher, ex );
      }catch( ListQueryException ex ){
         dispatchToFrontControllerError( request, response, dispatcher, ex );
      }catch( Exception ex ){
         dispatchToFrontControllerError( request, response, dispatcher, ex );
      }

      if( next != "" ){
         try{
            dispatch( request, response, next );
         }catch( Exception e ){
            handleDispatchException( request, response, next, e );
         }
      }
   }

   private void handleDispatchException( HttpServletRequest request, HttpServletResponse response, String next, Throwable throwable ) throws IOException {
      String servletName = (String) request.getAttribute( "javax.servlet.error.servlet_name" );
      if( servletName == null ) servletName = "Unknown";

      String requestUri = (String) request.getAttribute( "javax.servlet.error.request_uri" );
      if( requestUri == null ) requestUri = "Unknown";

      response.setContentType( "text/html" );
      java.io.PrintWriter out = response.getWriter();
      out.println( "<html>" );
      out.println( "<head>" );
      out.println( "<title>Error page</title>" );
      out.println( "</head>" );
      out.println( "<body>" );

      if( throwable == null ){
         out.println( "<h2>The error information is not available</h2>" );
         out.println( "Please return to the <a href=\"" + response.encodeURL( "http://localhost:8080/home" ) + "\">home page</a>." );
      }else{
         out.println( "<h2>Here is the error information</h2>" );
         out.println( "The servlet name associated with throwing the exception: " + servletName + "<br><br>" );
         out.println( "The type of exception: " + throwable.getClass().getName() + "<br><br>" );
         out.println( "The request URI: " + requestUri + "<br><br>" );
         out.println( "The exception message: " + throwable.getMessage() );
      }
      out.println( "</body>" );
      out.println( "</html>" );
   }

   private void dispatchToFrontControllerError( 
      HttpServletRequest request, HttpServletResponse response, CommandDispatcher dispatcher, Exception ex ) throws ServletException, IOException {
      CommandControllerErrorHelper errorHelper;
      errorHelper = new CommandControllerErrorHelper( "Exception", ex.getMessage(), ex );
      errorHelper.setRequestAttributes( dispatcher.getProperties() );
      request.setAttribute( ERROR_HELPER_ATTRIBUTE, errorHelper );
      dispatch( request, response, ERROR_PAGE );
   }

   private void dispatch( HttpServletRequest request, HttpServletResponse response, String page ) throws ServletException, IOException {
      RequestDispatcher dispatcher = getServletContext().getRequestDispatcher( page );
      dispatcher.forward( request, response );
   }
}