/*
 * Created on Jul 13, 2006
 */
package com.processpuzzle.artifact_type.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

/**
 * @author zsolt.zsuffa
 */
public class ArtifactTypeNotFoundException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = 3163364542722746501L;
   private String typeName = "";
   private static String defaultMessagePattern = "Undefined ArtifactType: ''{0}''";

   public ArtifactTypeNotFoundException( String typeName ) {
      super( ExceptionHelper.defineMessage( ArtifactTypeNotFoundException.class, new Object[] { typeName }, defaultMessagePattern ) );
      this.typeName = typeName;
   }

   public String getTypeName() { return typeName; }
}