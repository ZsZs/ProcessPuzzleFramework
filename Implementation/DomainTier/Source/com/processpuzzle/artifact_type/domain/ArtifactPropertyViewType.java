package com.processpuzzle.artifact_type.domain;

import javax.xml.bind.annotation.XmlRootElement;

import com.processpuzzle.artifact.domain.PropertyView;

@XmlRootElement(name="propertyViewType")
public class ArtifactPropertyViewType extends ArtifactViewType {

   protected ArtifactPropertyViewType() {
      super();
   }

   ArtifactPropertyViewType(String name, String presentationUri) {
      this(name);

   }

   ArtifactPropertyViewType(String name) {
      super(name);
   }
   
   @Override
   public String getViewClassName() {
      if( viewClassName == null ) viewClassName = PropertyView.class.getName();
      return viewClassName;
   }   
}
