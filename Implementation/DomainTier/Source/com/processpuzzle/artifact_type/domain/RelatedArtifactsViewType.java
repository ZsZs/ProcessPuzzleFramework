package com.processpuzzle.artifact_type.domain;

import javax.xml.bind.annotation.XmlRootElement;

import com.processpuzzle.artifact.domain.RelatedArtifactsListView;

@XmlRootElement(name="relatedArtifactsViewType")
public class RelatedArtifactsViewType extends ArtifactViewType {

   public RelatedArtifactsViewType(String name, String presentationUri) {
      super(name, presentationUri);
      
   }

   public RelatedArtifactsViewType(String name) {
      super(name);
      
   }

   @Override
   public String getViewClassName() {
      if( viewClassName == null ) viewClassName = RelatedArtifactsListView.class.getName();
      return viewClassName;
   }   

   protected RelatedArtifactsViewType() {
      super();
   }
}
