package com.processpuzzle.fitnesse.persistence;

import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

public class RepositoryTesterException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = 1493417348479037335L;

   public RepositoryTesterException( String message, Throwable cause ) {
      super( message, cause );
   }

}
