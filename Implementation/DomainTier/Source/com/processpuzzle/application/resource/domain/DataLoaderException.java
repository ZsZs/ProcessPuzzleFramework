/*
 * Created on Feb 14, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.application.resource.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

public class DataLoaderException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = 6631093747323781217L;
   private static String defaultMessagePattern = "DataLoader: ''{0}'' recognized an unexpected error. ''{1}''";

   public DataLoaderException( DataLoader loader, String problem ) {
      this( loader, problem , null );
   }

   public DataLoaderException( DataLoader loader, String problem, Throwable cause ) {
      super( ExceptionHelper.defineMessage(
            DataLoaderException.class, new Object[] {loader, problem},
            defaultMessagePattern ),
            cause);
   }

   public DataLoaderException(Object[] objects, Throwable cause) {
      super( objects, cause );
   }

   public DataLoaderException(ExceptionHelper helper, Throwable cause) {
      super( helper, cause );
   }

   protected static Object[] defineMessage( DataLoader loader,  String problem ) {
      formatPattern = "DataLoader: ''{0}'' recognized an unexpected error. ''{1}''";
      Object[] arguments = {loader.getClass().getSimpleName(), problem };
      return arguments;
   }
}