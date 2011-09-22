/*
 * Created on May 25, 2006
 */
package com.processpuzzle.artifact_management.control;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.processpuzzle.application.control.control.CommandDispatcher;

/**
 * @author zsolt.zsuffa
 */
public abstract class ArtifactActionCommand extends ArtifactCommand {
   protected XmlActionResponse actionResponse;

   public String getName() {
      return this.getClass().getName();
   }

   public void init(CommandDispatcher dispatcher) {
      super.init(dispatcher);
      actionResponse = new XmlActionResponse();
   }

   public String execute(CommandDispatcher dispatcher) throws Exception {
      doAction();
      buildResponse(dispatcher);
      return "";
   }

   protected abstract void doAction();

   protected void retrieveRequestParameters(CommandDispatcher dispatcher) {}

   protected void retrieveResponseDocument() {}

   protected void buildResponse(CommandDispatcher dispatcher) throws IOException {
      HttpServletResponse response = dispatcher.getResponse();
		response.setContentType("text/xml");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(actionResponse.getAsString());

		System.out.println(actionResponse.getAsString());
   }
}