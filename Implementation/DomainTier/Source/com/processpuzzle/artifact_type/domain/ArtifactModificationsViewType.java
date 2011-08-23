package com.processpuzzle.artifact_type.domain;

import javax.xml.bind.annotation.XmlRootElement;

import com.processpuzzle.artifact.domain.ArtifactModificationsView;

@XmlRootElement( name = "artifactModificationsViewType" )
public class ArtifactModificationsViewType extends ArtifactViewType {

   public ArtifactModificationsViewType( String name, String presentationUri ) {
      super( name, presentationUri );

   }

   public ArtifactModificationsViewType( String name ) {
      super( name );

   }

   @Override
   public String getViewClassName() {
      if( viewClassName == null ) viewClassName = ArtifactModificationsView.class.getName();
      return viewClassName;
   }
   
   protected ArtifactModificationsViewType() {
      super();
   }
}
