/*
Name: 
    - PlanDataSheet_PrintView 

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
 * Created on Oct 19, 2006
 */
package com.processpuzzle.workflow.activity.artifact;

import com.processpuzzle.artifact.domain.PrintView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;

/**
 * @author zsolt.zsuffa
 */
public class PlanDataSheet_PrintView extends PrintView<PlanDataSheet> {

   public PlanDataSheet_PrintView( PlanDataSheet artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );
   }

   @SuppressWarnings("unchecked")
   public String buildXml() {
      ActionDataSheet planDataSheet = (PlanDataSheet) parentArtifact;
      StringBuffer outputXml = new StringBuffer();
      PlanDataSheet_PropertyView propertyView = (PlanDataSheet_PropertyView) planDataSheet.getPropertyView();
      outputXml.append( "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" );
      outputXml.append( "<!DOCTYPE chars SYSTEM \"" + getCharacterEntitiesDtd() + "\">" );
      outputXml.append( "<propertyview>" );
      outputXml.append( "<actionName>" + propertyView.getActionName() + "</actionName>" );
      outputXml.append( "<description>" + propertyView.getDescription() + "</description>" );
      outputXml.append( "<priority>" + propertyView.getPriority() + "</priority>" );
      outputXml.append( "<projectedBegin>" + propertyView.getProjectedBegin() + "</projectedBegin>" );
      outputXml.append( "<projectedEnd>" + propertyView.getProjectedEnd() + "</projectedEnd>" );
      outputXml.append( "</propertyview>" );
      return outputXml.toString();
   }

   public void initializeView() {

   }
}
