package com.processpuzzle.party.artifact;

import com.processpuzzle.artifact.domain.ArtifactFactory;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.fundamental_types.domain.TimePoint;
import com.processpuzzle.party.domain.Person;
import com.processpuzzle.party.domain.PersonFactory;

public class PersonDataSheetFactory extends ArtifactFactory<PersonDataSheet> {
   
   public PersonDataSheetFactory() {
      super();
   }
   
   public PersonDataSheet create( String givenName, String familyName ) {
      return create( givenName, familyName, null );
   }
   
   public PersonDataSheet create( String givenName, String familyName, TimePoint birthDate ) {
      // create person
      PersonFactory personFactory = applicationContext.getEntityFactory( PersonFactory.class );
      Person person = personFactory.create( givenName, familyName, birthDate );
      
      // create PersonDataSheet
      ArtifactType dataSheetType = findTypeFor( PersonDataSheet.class );
      return super.create( person.getPersonName().getName(), dataSheetType, person );
   }

   public PersonDataSheet create( String familyName, String givenName, String userName, String password ) {
      // create person
      PersonFactory personFactory = applicationContext.getEntityFactory( PersonFactory.class );
      Person person = personFactory.create( givenName, familyName, userName, password );
      
      // create PersonDataSheet
      ArtifactType dataSheetType = findTypeFor( PersonDataSheet.class );
      return super.create( person.getPersonName().getName(), dataSheetType, person );
      
   }
}
