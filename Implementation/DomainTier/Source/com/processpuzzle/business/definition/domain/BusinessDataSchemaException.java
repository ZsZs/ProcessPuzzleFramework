package com.processpuzzle.business.definition.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

public class BusinessDataSchemaException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = -8529950050461537105L;
   private static final String defaultMessage = "An error occured in loading shema: ''{0}'' for business data. For more details see the log.";
   private String schemaPath;
   
   public BusinessDataSchemaException( String schemaPath, Throwable cause ) {
      super( ExceptionHelper.defineMessage( BusinessDataSchemaException.class, new Object[] {schemaPath}, defaultMessage ), cause );
      this.schemaPath = schemaPath;
   }

   public String getSchemaPath() { return schemaPath; }
}
