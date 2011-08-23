package com.processpuzzle.party.artifact;

import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.party.domain.Organization;
import com.processpuzzle.party.domain.OrganizationName;


public class OrganizationDataSheet<O extends OrganizationDataSheet<?,?>, P extends Organization> extends PartyDataSheet<O,P> {
   
   protected OrganizationDataSheet() {
   }
   
   OrganizationDataSheet( String name, ArtifactType type, User creator, P organization ) {
      super( name, type, creator, organization );
   }

   //Mutators
   void rename( OrganizationName name ) {
      super.rename( name );
      party.rename( name );
   }
   
   //Properties
   OrganizationName getOrganizationName() { return (OrganizationName) party.getPartyName(); }
}
