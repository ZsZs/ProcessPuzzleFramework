package com.processpuzzle.party.artifact;

import java.util.Map;

import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.artifact.domain.PropertyView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;
import com.processpuzzle.util.domain.GeneralService;

public class OrganizationUnitDataSheet_PropertyView extends PropertyView {

   public OrganizationUnitDataSheet_PropertyView() {super(null, null, null);}
    
    public OrganizationUnitDataSheet_PropertyView(Artifact<?> artifact, String name, ArtifactViewType type) {
        super(artifact, name, type);
    }
    
    public String getOrganizationUnitName() {
       return ((OrganizationUnitDataSheet)parentArtifact).getOrganizationUnit().getPartyName().getName();
    }
    
    public String getSettlement() {
       if(((OrganizationUnitDataSheet)parentArtifact).getOrganizationUnit().getDefaultGeographicAddress() != null)
          return (String)GeneralService.checkIsNotNull(((OrganizationUnitDataSheet)parentArtifact).getOrganizationUnit().getDefaultGeographicAddress().getSettlement().getName(), getPreferredLanguage());
       return GeneralService.getUndefinedMessage(getPreferredLanguage());
    }

    public String getZipCode() {
       if(((OrganizationUnitDataSheet)parentArtifact).getOrganizationUnit().getDefaultGeographicAddress() != null)
          return (String)GeneralService.checkIsNotNull(((OrganizationUnitDataSheet)parentArtifact).getOrganizationUnit().getDefaultGeographicAddress().getZipCode().getZipCode().toString(), getPreferredLanguage());
       return GeneralService.getUndefinedMessage(getPreferredLanguage());
    }
    
    public String getGeographicAddress() {
       if(((OrganizationUnitDataSheet)parentArtifact).getOrganizationUnit().getDefaultGeographicAddress() != null) 
          return (String)GeneralService.checkIsNotNull(((OrganizationUnitDataSheet)parentArtifact).getOrganizationUnit().getDefaultGeographicAddress().toString(), getPreferredLanguage());
       return GeneralService.getUndefinedMessage(getPreferredLanguage());
    }
   
    public String getStreet() {
       if(((OrganizationUnitDataSheet)parentArtifact).getOrganizationUnit().getDefaultGeographicAddress() != null)
          return (String)GeneralService.checkIsNotNull(((OrganizationUnitDataSheet)parentArtifact).getOrganizationUnit().getDefaultGeographicAddress().getStreet(), getPreferredLanguage());
      return GeneralService.getUndefinedMessage(getPreferredLanguage());
    }
    
    public String getBuildingNumber() {
       if(((OrganizationUnitDataSheet)parentArtifact).getOrganizationUnit().getDefaultGeographicAddress() != null)
          return (String)GeneralService.checkIsNotNull(((OrganizationUnitDataSheet)parentArtifact).getOrganizationUnit().getDefaultGeographicAddress().getBuildingNumber(), getPreferredLanguage());
      return GeneralService.getUndefinedMessage(getPreferredLanguage());
    }

    public String getData(String method, Map parameters) {
       return null;
    }
     
}
