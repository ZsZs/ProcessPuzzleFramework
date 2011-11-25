/*
Name: 
    - ActionDataSheet_PropertyView 

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
 * Created on Sep 14, 2006
 */
package com.processpuzzle.workflow.activity.artifact;

import java.util.Collection;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.artifact.domain.PropertyView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;
import com.processpuzzle.workflow.activity.domain.ResourceAllocation;

/**
 * @author zsolt.zsuffa
 */
public class ActionDataSheet_PropertyView extends PropertyView<ActionDataSheet<?>> {

   public ActionDataSheet_PropertyView( ActionDataSheet<?> artifact, String name, ArtifactViewType viewType ) {
      super( artifact, name, viewType );
   }

   public String getProtocolName() {
      return ProcessPuzzleContext.getInstance().getText(
            "ui.pantokrator.protocol." + parentArtifact.getAction().getProtocol().getName(), getPreferredLanguage() );
   }

   public String getActionName() {
      return parentArtifact.getAction().getName();
   }

   public String getSubject() {
      return parentArtifact.getAction().getProcessPlan().getSubject();
   }

   public String getCaseLabel() {
      return parentArtifact.getAction().getProcessPlan().getName();
   }

   public String getDesignation() {
      return parentArtifact.getAction().getDesignation();
   }

   public String getDescription() { 
      return parentArtifact.getDescription(); 
   }

   public Boolean getExecutable() {
      return parentArtifact.getExecutable();
   }

   public String getStatus() {
      return parentArtifact.getStatus();
   }

   public String getProtocol() {
      return parentArtifact.getProtocol();
   }

   public String getPriority() {
      return parentArtifact.getPriority();
   }

   public String getProjectedBegin() {
      return parentArtifact.getProjectedBegin();
   }

   public String getProjectedEnd() {
      return parentArtifact.getProjectedEnd();
   }

   public String getRealBegin() {
      return parentArtifact.getRealBegin();
   }

   public String getRealEnd() {
      return parentArtifact.getRealEnd();
   }

   public String getOwnerName() {
      return parentArtifact.getOwnerName();
   }

   public Collection<ResourceAllocation> getBookedResources() {
      return parentArtifact.getBookedResources();
   }

   public Collection<ResourceAllocation> getUsedResources() {
      return parentArtifact.getUsedResources();
   }
}
