/*
Name: 
    - ActionDataSheet_PrintView 

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
 * Created on Oct 12, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.workflow.activity.artifact;

import com.processpuzzle.artifact.domain.PrintView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;

/**
 * @author zsolt.zsuffa
 */
public class ActionDataSheet_PrintView extends PrintView<ActionDataSheet<?>> {

   public ActionDataSheet_PrintView( ActionDataSheet<?> artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );
   }

   public String buildXml() {
      StringBuffer outputXml = new StringBuffer();
      outputXml.append( "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" );
      outputXml.append( "<!DOCTYPE chars SYSTEM \"" + getCharacterEntitiesDtd() + "\">" );
      outputXml.append( "<propertyview>" );
      outputXml.append( "<actionName>" + parentArtifact.getActionName() + "</actionName>" );
      outputXml.append( "<description>" + parentArtifact.getDescription() + "</description>" );
      outputXml.append( "<priority>" + parentArtifact.getPriority() + "</priority>" );
      outputXml.append( "<projectedBegin>" + parentArtifact.getProjectedBegin() + "</projectedBegin>" );
      outputXml.append( "<projectedEnd>" + parentArtifact.getProjectedEnd() + "</projectedEnd>" );
      outputXml.append( "<realBegin>" + parentArtifact.getRealBegin() + "</realBegin>" );
      outputXml.append( "<realEnd>" + parentArtifact.getRealEnd() + "</realEnd>" );
      outputXml.append( "</propertyview>" );
      return outputXml.toString();
   }

   public void initializeView() {

   }
}
