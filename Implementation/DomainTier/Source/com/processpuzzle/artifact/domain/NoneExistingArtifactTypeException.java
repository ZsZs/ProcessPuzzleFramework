package com.processpuzzle.artifact.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

public class NoneExistingArtifactTypeException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = -7660652032256788739L;
   private static String defaultMessagePattern = "Can't find ArtifactType: '''{0}''' for instantiating Artifact: '''{1}'''.";
   private String artifactTypeName;
   private String artifactName;
   
   public NoneExistingArtifactTypeException( String artifactTypeName, String artifactName ) {
      super( ExceptionHelper.defineMessage( 
                                            NoneExistingArtifactTypeException.class, 
                                            new Object[] { artifactTypeName, artifactName}, 
                                            defaultMessagePattern ));
      this.artifactTypeName = artifactTypeName;
      this.artifactName = artifactName;
   }

   public String getArtifactTypeName() {
      return artifactTypeName;
   }

   public String getArtifactName() {
      return artifactName;
   }

}
