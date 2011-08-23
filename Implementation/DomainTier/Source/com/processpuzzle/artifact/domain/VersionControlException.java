package com.processpuzzle.artifact.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleException;

public class VersionControlException extends ProcessPuzzleException {
 
   private static final long serialVersionUID = 8451482404848670241L;
   private static String defaultMessagePattern;

   public VersionControlException(String msg) {
      super(ExceptionHelper.defineMessage(VersionControlException.class, new Object[] {msg}, defaultMessagePattern), null);
   }

}
