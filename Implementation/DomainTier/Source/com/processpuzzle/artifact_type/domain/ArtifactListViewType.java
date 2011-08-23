package com.processpuzzle.artifact_type.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.processpuzzle.artifact.artifact.ArtifactList_ListView;

@XmlRootElement( name = "artifactListViewType" )
public class ArtifactListViewType extends ArtifactViewType {

   @XmlAttribute
   protected String listedArtifactType;

   @XmlAttribute
   protected String orderBy;

   @XmlAttribute( name = "orderBy" )
   protected String ord;

   ArtifactListViewType( String name, String presentationUri ) {
      super( name, presentationUri );
   }

   ArtifactListViewType( String name ) {
      super( name );
   }

   protected ArtifactListViewType() {
      super();
   }

   public String getOrd() {
      return ord;
   }

   public void setOrd( String order ) {
      this.ord = order;
   }

   public String getOrderBy() {
      return orderBy;
   }

   public void setOrderBy( String orderBy ) {
      this.orderBy = orderBy;
   }

   public String getListedArtifactType() {
      return listedArtifactType;
   }

   public void setListedArtifactType( String listedArtifactType ) {
      this.listedArtifactType = listedArtifactType;
   }

   @Override
   public String getViewClassName() {
      if( viewClassName == null ) viewClassName = ArtifactList_ListView.class.getName();
      return viewClassName;
   }
}
