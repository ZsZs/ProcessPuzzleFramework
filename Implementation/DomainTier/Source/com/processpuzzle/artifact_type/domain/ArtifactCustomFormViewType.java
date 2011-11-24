/*
Name: 
    - ArtifactCustomFormViewType

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

package com.processpuzzle.artifact_type.domain;

import javax.xml.bind.annotation.XmlRootElement;

import com.processpuzzle.artifact.domain.CustomFormView;
import com.processpuzzle.fundamental_types.domain.AssertionException;

@XmlRootElement(name="artifactCustomFormViewType")
public class ArtifactCustomFormViewType extends ArtifactViewType {

   protected ArtifactCustomFormViewType() {
      super();
   }

   public ArtifactCustomFormViewType(String name, String presentationUri, String viewClassName) {
      super(name, presentationUri);
      if(presentationUri == null || presentationUri.equals("")) throw new AssertionException("ArtifactCustomFormViewType.presnetationUri can't be null or empty string");
      if(viewClassName == null || viewClassName.equals("")) throw new AssertionException("ArtifactCustomFormViewType.viewClassName can't be null or empty string");

      this.presentationUri = presentationUri;
      this.viewClassName = viewClassName;
   }

   @Override
   public String getViewClassName() {
      if( viewClassName == null ) viewClassName = CustomFormView.class.getName();
      return viewClassName;
   }   
}
