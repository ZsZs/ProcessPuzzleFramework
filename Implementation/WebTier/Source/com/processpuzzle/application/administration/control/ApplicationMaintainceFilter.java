package com.processpuzzle.application.administration.control;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ApplicationMaintainceFilter implements Filter {
   public static final String HALT_APPLICATION = "haltApplication";
   private final Log log = LogFactory.getLog( ApplicationMaintainceFilter.class );
   @SuppressWarnings("unused")
   private ServletContext servletContext = null;

   public void init(FilterConfig arg0) throws ServletException {
      this.servletContext = arg0.getServletContext();
   }

   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {      
      log.info("Application maintainance started.");
      
//      String value = (String) servletContext.getAttribute(HALT_APPLICATION);
//      DefaultUserRequestContextFactory requestContextFactory = new DefaultUserRequestContextFactory();
//      UserRequestContext requestContext = requestContextFactory.getRequestContext();
//      UserSession userSession =  requestContext.getUserSession();
//
//      boolean l = true;
//      if( ( ( userSession != null ) &&
//    	    ( servletContext.getAttribute("maintainerUserId") != null ) &&
//    	    ( userSession.getId().equals((String)servletContext.getAttribute("maintainerUserId")) ) )  || 
//    	    ( ((HttpServletRequest)request).getParameter("action") != null ) &&
//    	    ( ((HttpServletRequest)request).getParameter("action").equals("ShowUndoMaintenance") ) )
//      {
//    	  l = false;
//      }
//      if(value != null && value.equals("true") && l) {
//         httpResponse.sendRedirect("\\ApplicationAdministration\\MaintainceMessage.jsp");
//      }
//      else 
         chain.doFilter(request, response);

   }

   public void destroy() {
   // TODO Auto-generated method stub

   }

}
