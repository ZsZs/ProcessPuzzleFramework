package com.processpuzzle.application.security.control;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorizationFilter implements Filter {
   private FilterConfig config;

   public void init(FilterConfig arg0) throws ServletException {
      config = arg0;
   }

   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
      HttpServletRequest httpRequest = (HttpServletRequest) request;
      HttpServletResponse httpResponse = (HttpServletResponse) response;

      if( checkUriBasedAuthorization( httpRequest )) {
         chain.doFilter(httpRequest, httpResponse);
      } else {
         httpRequest.getRequestDispatcher("/FrontController/AuthorizationError.jsp").forward(httpRequest, httpResponse);
      }
   }

   public void destroy() {
      config = null;
   }

   public FilterConfig getConfig() {
      return config;
   }

   private boolean checkUriBasedAuthorization(HttpServletRequest httpRequest) {
      return true;
   }
}
