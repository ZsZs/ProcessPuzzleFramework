package com.processpuzzle.application.control.control;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.processpuzzle.application.resource.domain.PropertyLoader;

public class PropertyLoaderServlet extends HttpServlet implements Servlet {
   private static final long serialVersionUID = -7645002597022244827L;

   public PropertyLoaderServlet() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      processRequest(request, response);
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      processRequest(request, response);
   }

   private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      System.out.println("PropertyLoaderServlet");
      try {
         PropertyLoader loader = new PropertyLoader("classpath:com/itcodex/objectpuzzle/framework/application_control/control/commandMapping.properties");
         loader.loadData();
         final Properties properties = loader.getLoadedProperties();

         String next;
         if (properties != null) {
            next = properties.getProperty("returnPage");
            if (next == null)
               next = "/FrontController/UnknownCommandError.jsp";
         } else
            next = "/FrontController/UnknownCommandError.jsp";

         dispatch(request, response, next);
      } catch (Exception ex) {
         ex.printStackTrace();
      }
   }

   private void dispatch(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
      RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
      dispatcher.forward(request, response);
   }
}