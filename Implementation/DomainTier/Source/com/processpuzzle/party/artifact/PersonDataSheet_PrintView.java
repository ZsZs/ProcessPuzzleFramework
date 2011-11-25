/*
Name: 
    - PersonDataSheet_PrintView

Description: 
    -  

Requires:
    - 
    
Provides:
    - 

Part of: ProcessPuzzle Framework, Domain and Business Model Ready Architecture. Provides content, workflow and social networking functionality. 
http://www.processpuzzle.com

ProcessPuzzle - Content and Workflow Management Integration Business Platform

Author(s): 
    - Zsolt Zsuffa

Copyright: (C) 2011 This program is free software: you can redistribute it and/or modify it under the terms of the 
GNU General Public License as published by the Free Software Foundation, either version 3 of the License, 
or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

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
