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

