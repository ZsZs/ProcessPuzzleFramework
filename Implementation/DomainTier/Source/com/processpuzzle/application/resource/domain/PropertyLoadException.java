/*
 * Created on Feb 12, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.application.resource.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

public class PropertyLoadException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = 5962926287782501763L;
   private static String defaultMessagePattern= "Loading property file: ''{0}'' caused an error.";

   public PropertyLoadException(String fileName) {
      this(fileName, null);
   }

   public PropertyLoadException(String fileName, Throwable cause) {
      super(ExceptionHelper.defineMessage(PropertyLoadException.class, new Object[] { fileName }, defaultMessagePattern), cause);
   }

}