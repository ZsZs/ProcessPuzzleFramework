package com.processpuzzle.party.artifact;

import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.party.domain.OrganizationUnit;

public class OrganizationUnitDataSheet extends OrganizationDataSheet<OrganizationUnitDataSheet, OrganizationUnit> {

   protected OrganizationUnitDataSheet( String artifactName, ArtifactType type, User creator, OrganizationUnit organizationUnit ) {
      super( artifactName, type, creator, organizationUnit );
   }

   public OrganizationUnit getOrganizationUnit() { return getParty(); }
}
