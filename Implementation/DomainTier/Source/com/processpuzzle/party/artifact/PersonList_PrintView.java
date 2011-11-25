/*
Name: 
    - PersonList_PrintView

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

import java.util.Iterator;
import java.util.List;

import com.processpuzzle.artifact.domain.PrintView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;

public class PersonList_PrintView extends PrintView<PersonList> {

   public PersonList_PrintView( PersonList artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );
   }

   public String buildXml() {
      PersonList_ListView listView = parentArtifact.findViewByClass( PersonList_ListView.class );
      listView.query();
      List<?> propertyViews = listView.getListedArtifactsPropertyViews();
      StringBuffer outputXml = new StringBuffer();
      outputXml.append( "<propertyviews>" );
      for( Iterator<?> iterator = propertyViews.iterator(); iterator.hasNext(); ){
         PersonDataSheet_PropertyView propertyView = (PersonDataSheet_PropertyView) iterator.next();
         outputXml.append( "<propertyview>" );
         outputXml.append( "<personName>" + propertyView.getPersonName() + "</personName>" );
         outputXml.append( "<address>" + propertyView.getGeographicAddress() + "</address>" );
         outputXml.append( "<telecom>" + propertyView.getTelecomAddress() + "</telecom>" );
         outputXml.append( "<email>" + propertyView.getEmailAddress() + "</email>" );
         outputXml.append( "</propertyview>" );
      }
      outputXml.append( "</propertyviews>" );
      return outputXml.toString();
   }

   public void initializeView() {}

}
