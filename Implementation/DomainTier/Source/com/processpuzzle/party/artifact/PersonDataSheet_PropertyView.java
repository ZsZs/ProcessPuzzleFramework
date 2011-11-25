/*
Name: 
    - PersonDataSheet_PropertyView

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

import com.processpuzzle.artifact.domain.PropertyView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;

public class PersonDataSheet_PropertyView extends PropertyView<PersonDataSheet> {

   public PersonDataSheet_PropertyView() {
      super( null, null, null );
   }

   public PersonDataSheet_PropertyView( PersonDataSheet artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );
   }

   public String getBirthDate() {
      return parentArtifact.getBirthDate();
   }

   public String getEmailAddress() {
      return parentArtifact.getEmailAddress();
   }
   
   public String getFamilyName() {
      return parentArtifact.getFamilyName();
   }

   public String getGeographicAddress() {
      return parentArtifact.getGeographicAddress();
   }
   
   public String getGivenName() {
      return parentArtifact.getGivenName();
   }
   
   public String getPersonName() {
      return parentArtifact.getPersonName();
   }
   
   public String getPrefix() {
      return parentArtifact.getPrefix();
   }
   
   public String getTelecomAddress() {
      return parentArtifact.getTelecomAddress();
   }
}
