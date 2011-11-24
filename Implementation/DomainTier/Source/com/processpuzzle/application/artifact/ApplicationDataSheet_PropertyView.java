/*
Name: 
    - ApplicationDataSheet_PropertyView

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

package com.processpuzzle.application.artifact;

import com.processpuzzle.application.domain.Application;
import com.processpuzzle.artifact.domain.PropertyView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;

public class ApplicationDataSheet_PropertyView extends PropertyView<ApplicationDataSheet> {

   private Application application = ((ApplicationDataSheet) parentArtifact).getApplication();

   public ApplicationDataSheet_PropertyView( ApplicationDataSheet artifact, String name, ArtifactViewType viewType ) {
      super( artifact, name, viewType );
      // TODO Auto-generated constructor stub
   }

   public String getApplicationName() {
      return application.getApplicationName();
   }

   public String getApplicationVersion() {
      return application.getApplicationVersion();
   }

   public String getApplicationStatus() {
      return application.getExecutionStatus().toString();
   }

}
