package com.processpuzzle.artifact_type.domain;

import javax.xml.bind.annotation.XmlRootElement;

import com.processpuzzle.artifact.domain.ArtifactCommentsView;

@XmlRootElement(name="artifactCommentsViewType")
public class ArtifactCommentsViewType extends ArtifactViewType {

   protected ArtifactCommentsViewType() {
      super();    
   }

   public ArtifactCommentsViewType(String name, String presentationUri) {
      super(name, presentationUri);     
   }

   public ArtifactCommentsViewType(String name) {
      super(name);      
   }
   
   @Override
   public String getViewClassName() {
      if( viewClassName == null ) viewClassName = ArtifactCommentsView.class.getName();
      return viewClassName;
   }   
}
