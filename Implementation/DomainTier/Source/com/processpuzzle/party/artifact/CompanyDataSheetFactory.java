package com.processpuzzle.party.artifact;

import com.processpuzzle.artifact.domain.ArtifactFactory;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.party.domain.Company;
import com.processpuzzle.party.domain.CompanyFactory;
import com.processpuzzle.persistence.domain.EntityIdentityCollitionException;

public class CompanyDataSheetFactory extends ArtifactFactory<CompanyDataSheet> {

   public CompanyDataSheetFactory() {
      super();
   }

   public CompanyDataSheet create( String organizationName ) throws EntityIdentityCollitionException {
      // create Company
      CompanyFactory companyFactory = applicationContext.getEntityFactory( CompanyFactory.class );
      Company company = companyFactory.create( organizationName );
      
      // create CompanyDatasheet
      ArtifactType companyDataSheetType = findTypeFor( CompanyDataSheet.class );
      return super.create( organizationName, companyDataSheetType, company );
   }
}
