/*
 * Created on Dec 12, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.application.configuration.control;

import com.processpuzzle.application.control.control.CommandDispatcher;
import com.processpuzzle.application.control.control.CommandInterface;

public class LifecycleListCommand implements CommandInterface {
   private static final String NAME = "content.htm";

   //Public accessors and mutators
   public String execute( CommandDispatcher dispatcher ) throws Exception {
      return "Hello";
   }
   
   public void init( CommandDispatcher dispatcher ) {}

   //Propterties
   public String getName() { return NAME; }

}