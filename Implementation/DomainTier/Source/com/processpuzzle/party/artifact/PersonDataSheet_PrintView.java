package com.processpuzzle.party.artifact;

import com.processpuzzle.artifact.domain.PrintView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;

public class PersonDataSheet_PrintView extends PrintView<PersonDataSheet> {

   public PersonDataSheet_PrintView( PersonDataSheet artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );
   }

   @Override
   public String buildXml() {
      StringBuffer outputXml = new StringBuffer();
      PersonDataSheet_PropertyView propertyView = (PersonDataSheet_PropertyView) parentArtifact.getPropertyView();
      outputXml.append( "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" );
      outputXml.append( "<!DOCTYPE chars SYSTEM \"" + getCharacterEntitiesDtd() + "\">" );
      outputXml.append( "<propertyview>" );
      outputXml.append( "<prefix>" + propertyView.getPrefix() + "</prefix>" );
      outputXml.append( "<familyName>" + propertyView.getFamilyName() + "</familyName>" );
      outputXml.append( "<givenName>" + propertyView.getGivenName() + "</givenName>" );
      outputXml.append( "<birthDate>" + propertyView.getBirthDate() + "</birthDate>" );
      outputXml.append( "<geographicAddress>" + propertyView.getGeographicAddress() + "</geographicAddress>" );
      outputXml.append( "<telecomAddress>" + propertyView.getTelecomAddress() + "</telecomAddress>" );
      outputXml.append( "<emailAddress>" + propertyView.getEmailAddress() + "</emailAddress>" );
      outputXml.append( "</propertyview>" );
      return outputXml.toString();
   }

}
