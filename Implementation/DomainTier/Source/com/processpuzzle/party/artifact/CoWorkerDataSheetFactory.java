package com.processpuzzle.party.artifact;

import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.artifact.domain.ArtifactFactory;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.party.domain.Person;
import com.processpuzzle.party.domain.PersonFactory;
import com.processpuzzle.persistence.domain.EntityIdentityCollitionException;
import com.processpuzzle.user_session.domain.UserRequestManager;

public class CoWorkerDataSheetFactory extends ArtifactFactory<CoWorkerDataSheet> {

   public CoWorkerDataSheetFactory() {
      super();
   }
   
   public CoWorkerDataSheet create( String artifactName, String familyName, String givenName, String userName, String password ) throws EntityIdentityCollitionException {
      ArtifactType type = findTypeFor( CoWorkerDataSheet.class );
      PersonFactory personFactory = applicationContext.getEntityFactory( PersonFactory.class );
      Person coWorker = personFactory.create( familyName, givenName, userName, password );
      User creator = UserRequestManager.getInstance().currentUser();
      return new CoWorkerDataSheet( creator, artifactName, type, coWorker );
  }  
}
