/*
Name: 
    - ActionList_PrintView 

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

package com.processpuzzle.workflow.activity.artifact;

import java.util.Iterator;
import java.util.List;

import com.processpuzzle.artifact.artifact.ArtifactList;
import com.processpuzzle.artifact.domain.PrintView;
import com.processpuzzle.artifact.domain.PropertyView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;

public class ActionList_PrintView extends PrintView<ActionList> {

   public ActionList_PrintView( ActionList artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );
   }

   public String buildXml() {
      ArtifactList<?> artifactList = parentArtifact;
      ActionList_ListView listView = (ActionList_ListView) artifactList.getListView();
      listView.query();
      List<PropertyView<ActionList>> propertyViews = listView.getListedArtifactsPropertyViews();
      StringBuffer outputXml = new StringBuffer();
      outputXml.append( "<propertyviews>" );
      for( Iterator iter = propertyViews.iterator(); iter.hasNext(); ){
         ActionDataSheet_PropertyView propertyView = (ActionDataSheet_PropertyView) iter.next();
         outputXml.append( "<propertyview>" );
         outputXml.append( "<actionName>" + propertyView.getActionName() + "</actionName>" );
         outputXml.append( "<status>" + propertyView.getStatus() + "</status>" );
         outputXml.append( "<priority>" + propertyView.getPriority() + "</priority>" );
         outputXml.append( "<owner>" + propertyView.getOwnerName() + "</owner>" );
         outputXml.append( "<projectedBegin>" + propertyView.getProjectedBegin() + "</projectedBegin>" );
         outputXml.append( "<projectedEnd>" + propertyView.getProjectedEnd() + "</projectedEnd>" );
         outputXml.append( "<realBegin>" + propertyView.getRealBegin() + "</realBegin>" );
         outputXml.append( "<realEnd>" + propertyView.getRealEnd() + "</realEnd>" );
         outputXml.append( "</propertyview>" );
      }
      outputXml.append( "</propertyviews>" );
      log.info( outputXml.toString() );
      return outputXml.toString();
   }

   public void initializeView() {

   }
}
