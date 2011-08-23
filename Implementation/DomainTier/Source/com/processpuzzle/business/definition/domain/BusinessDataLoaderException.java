package com.processpuzzle.business.definition.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

public class BusinessDataLoaderException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = 5151286187896141786L;
   private static final String defaultMessage = "An error occured when loading business data: ''{0}''. For more details see the log.";
   private String businessDefinitionPath;

   public BusinessDataLoaderException( String businessDefinitionPath, Throwable cause ) {
      super( ExceptionHelper.defineMessage( BusinessDataLoaderException.class, new Object[] {businessDefinitionPath}, defaultMessage ), cause );
      this.businessDefinitionPath = businessDefinitionPath;
   }

   public String getBusinessDefinitionPath() { return businessDefinitionPath; }
}
