package com.processpuzzle.artifact_type.domain;

import javax.xml.bind.annotation.XmlRootElement;

import com.processpuzzle.artifact.domain.AccessRightsView;

@XmlRootElement( name = "accessRightsViewType" )
public class ArtifactAccessRightsViewType extends ArtifactViewType {

   protected ArtifactAccessRightsViewType() {
      super();
   }

   public ArtifactAccessRightsViewType( String name, String presentationUri ) {
      super( name, presentationUri );

   }

   public ArtifactAccessRightsViewType( String name ) {
      super( name );
   }

   @Override
   public String getViewClassName() {
      if( viewClassName == null ) viewClassName = AccessRightsView.class.getName();
      return viewClassName;
   }
   
}
