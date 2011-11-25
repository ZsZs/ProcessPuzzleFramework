/*
Name: 
    - PlanList_PrintView 

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

/*
 * Created on Sep 7, 2006
 */
package com.processpuzzle.workflow.activity.artifact;

import java.util.Iterator;
import java.util.List;

import com.processpuzzle.artifact.artifact.ArtifactList;
import com.processpuzzle.artifact.domain.PrintView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;

/**
 * @author zsolt.zsuffa
 */
public class PlanList_PrintView extends PrintView<PlanList> {

   public PlanList_PrintView( PlanList artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );
   }

   public String buildXml() {
      ArtifactList<?> artifactList = (ArtifactList<?>) parentArtifact;
      PlanList_ListView listView = (PlanList_ListView) artifactList.getListView();
      listView.query();
      List<?> propertyViews = listView.getListedArtifactsPropertyViews();
      StringBuffer outputXml = new StringBuffer();
      outputXml.append( "<propertyviews>" );
      for( Iterator<?> iter = propertyViews.iterator(); iter.hasNext(); ){
         PlanDataSheet_PropertyView pView = (PlanDataSheet_PropertyView) iter.next();
         outputXml.append( "<propertyview>" );
         outputXml.append( "<actionName>" + pView.getActionName() + "</actionName>" );
         outputXml.append( "<status>" + pView.getStatus() + "</status>" );
         outputXml.append( "<priority>" + pView.getPriority() + "</priority>" );
         outputXml.append( "<owner>" + pView.getOwnerName() + "</owner>" );
         outputXml.append( "<projectedBegin>" + pView.getProjectedBegin() + "</projectedBegin>" );
         outputXml.append( "<projectedEnd>" + pView.getProjectedEnd() + "</projectedEnd>" );
         outputXml.append( "<realBegin>" + pView.getRealBegin() + "</realBegin>" );
         outputXml.append( "<realEnd>" + pView.getRealEnd() + "</realEnd>" );
         outputXml.append( "</propertyview>" );
      }
      outputXml.append( "</propertyviews>" );
      log.info( outputXml.toString() );
      return outputXml.toString();
   }

   public void initializeView() {

   }
}
