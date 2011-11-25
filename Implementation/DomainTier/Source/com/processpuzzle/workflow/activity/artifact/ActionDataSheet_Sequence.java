/*
Name: 
    - ActionDataSheet_Sequence 

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

import java.util.List;
import java.util.Map;

import com.processpuzzle.artifact.domain.CustomFormView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;
import com.processpuzzle.workflow.activity.domain.Action;

public class ActionDataSheet_Sequence extends CustomFormView<ActionDataSheet<?>> {

   public ActionDataSheet_Sequence( ActionDataSheet<?> artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );
   }

   public Map<String, List<Action<?>>> getPreviousActions() {
      return parentArtifact.getPreviousActions();
   }
   
   public Map<String, List<Action<?>>> getNextActions() {
      return parentArtifact.getNextActions();
   }

   @Override
   public void initializeView() {
   // TODO Auto-generated method stub

   }

}
