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
