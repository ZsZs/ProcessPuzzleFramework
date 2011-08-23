/*
 * Created on Jul 20, 2006
 */
package com.processpuzzle.business.definition.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

/**
 * @author zsolt.zsuffa
 */
public class SystemArtifactInstantiationException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = -6649082226297084920L;
   private static String defaultMessagePattern = "Serious error occured during instantiation of system artifacts.";

   SystemArtifactInstantiationException(String artifactName, String typeName) {
      super(ExceptionHelper.defineMessage(SystemArtifactInstantiationException.class, new Object[] { artifactName, typeName },
            defaultMessagePattern));
   }
}
