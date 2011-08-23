package com.processpuzzle.application.configuration.control;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CharacterEncoderFilter implements Filter {

   private FilterConfig config;

   public void init(FilterConfig arg0) throws ServletException {
      this.config = arg0;
   }

   public void destroy() {
      this.config = null;
   }

   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
      HttpServletRequest httpRequest = (HttpServletRequest) request;
      HttpServletResponse httpResponse = (HttpServletResponse) response;

      // response.setContentType("text/html; charset=UTF-8");
      request.setCharacterEncoding("UTF8");
      chain.doFilter(httpRequest, httpResponse);
      // response.setContentType("text/html; charset=UTF-8");

      request.setCharacterEncoding("UTF8");

   }

   public FilterConfig getConfig() {
      return config;
   }

}
