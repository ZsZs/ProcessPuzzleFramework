package com.processpuzzle.party.artifact;

import com.processpuzzle.artifact.domain.ArtifactFactory;
import com.processpuzzle.persistence.domain.EntityIdentityCollitionException;

public class OrganizationUnitDataSheetFactory extends ArtifactFactory<OrganizationUnitDataSheet> {

   public OrganizationUnitDataSheetFactory() {
      super();
   }
   
   public OrganizationUnitDataSheet create( String organizationName ) throws EntityIdentityCollitionException {
      return super.create( organizationName );
   }
}
