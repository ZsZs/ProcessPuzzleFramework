/*
Name: 
    - FindArtifactResponse

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

package com.processpuzzle.artifact.service;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.artifact.domain.ArtifactView;

@XmlAccessorType( XmlAccessType.FIELD )
@XmlRootElement( name="findArtifactViewResponse", namespace="http://www.processpuzzle.com/" )
public class FindArtifactResponse {
   private @XmlTransient Artifact<?> artifact;
   private @XmlElement( required=true, name="artifactName", namespace="http://www.processpuzzle.com/" ) String artifactName;
   private @XmlTransient ArtifactView<?> artifactView;
   private List<ArtifactView<?>> artifactViews;
   
   protected FindArtifactResponse() {} //Needed for JAXB
   
   public FindArtifactResponse( ArtifactView<?> artifactView ) {
      this.artifactView = artifactView;
      this.artifact = artifactView.getParentArtifact();
      updateMessageFields();
   }
   
   //Public mutators
   public void updateMessageFields() {
      artifactName = artifact.getName();
   }
   
   //Properties
   public Artifact<?> getArtifact() { return artifact; }
   public ArtifactView<?> getArtfifactView() { return artifactView; }
   public String getName() { return artifactName; }
   public Integer getNumberOfArtifact() { return artifactViews.size() ; }
}

