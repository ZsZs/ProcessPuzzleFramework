package com.processpuzzle.party.domain;

import java.util.Date;

import com.processpuzzle.fundamental_types.domain.TimePeriod;
import com.processpuzzle.fundamental_types.domain.TimePoint;
import com.processpuzzle.party.partytype.domain.PartyType;

public class PersonFactory extends PartyFactory<Person> {
   
   public PersonFactory() {
      super();
   }
   
   public Person create( PersonName name, TimePoint birthDate ) {
      PartyType personType = findTypeFor( Person.class );
      Person person = new Person( name, personType, birthDate );
      person.setValid( new TimePeriod( new TimePoint( new Date( System.currentTimeMillis() ) ), null ) );
      return person;
   }
   
   public Person create( String familyName, String givenName ) {
      return create( familyName, givenName, null );
   }
   
   public Person create( String familyName, String givenName, TimePoint birthDate ) {
      return create( new PersonName( givenName, familyName ), birthDate );
   }

   public Person create( String familyName, String givenName, String userName, String password ) {
      PartyType personType = findTypeFor( Person.class );
      Person person = new Person( new PersonName( givenName, familyName ), personType, userName, password );
      person.setValid( new TimePeriod( new TimePoint( new Date( System.currentTimeMillis() ) ), null ) );
      return person;
   }
}
