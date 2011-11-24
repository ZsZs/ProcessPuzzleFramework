/*
Name: 
    - CustomFormView

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
 * Created on Jul 6, 2006
 */
package com.processpuzzle.artifact.domain;

import com.processpuzzle.artifact_type.domain.ArtifactViewType;

public abstract class CustomFormView<A extends Artifact<?>> extends ArtifactView<A> {
   protected String method;
   protected String forward;

   // Constructors
   public CustomFormView( A artifact, String viewName, ArtifactViewType type ) {
      super( artifact, viewName, type );
   }

   //Public mutators
   public void performAction() {}
   
   // Properties
   public String getForward() { return forward; }
   public void setForward( String forward ) { this.forward = forward; }

   public String getMethod() { return method; }
   public void setMethod( String method ) { this.method = method; }
}
