package com.processpuzzle.artifact.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

public class NoneExistingEntityForCreationArtifactException extends ProcessPuzzleProgrammingException {

   /**
    * 
    */
   private static final long serialVersionUID = 7392943901289061813L;
   private static String defaultMessagePattern = "Can't find Entity: '''{0}''' for instantiating Artifact: '''{1}'''.";
   
   public NoneExistingEntityForCreationArtifactException( String entityClassName, String artifactName ) {
      super( ExceptionHelper.defineMessage( 
                                            NoneExistingArtifactTypeException.class, 
                                            new Object[] { entityClassName, artifactName}, 
                                            defaultMessagePattern ));
   }

}
