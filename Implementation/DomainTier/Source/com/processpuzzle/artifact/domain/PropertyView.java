/*
 * Created on Apr 12, 2006
 */
package com.processpuzzle.artifact.domain;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;
import com.processpuzzle.util.domain.GeneralService;

/**
 * @author zsolt.zsuffa
 */
@XmlRootElement( name = "propertyView" )
@XmlAccessorType( XmlAccessType.PUBLIC_MEMBER )
public class PropertyView<A extends Artifact<?>> extends ArtifactView<A> {
   protected Map<String, String> properties = new HashMap<String, String>();

   public PropertyView( A artifact, String name, ArtifactViewType viewType) {
      super(artifact, name, viewType);
   }
   
   public PropertyView() {} //Used by JAXB2
   
   public void initializeView() {
      // TODO Auto-generated method stub      
   }

   public String getId() {
      return this.getParentArtifact().getId().toString();
   }

   public String getArtifactName() {
      return this.getParentArtifact().getName();
   }
   
   public @XmlElement String getArtifactTypeName() { return parentArtifact.getType().getName(); }

   public @XmlElement String getCreationDate() {
      return GeneralService.dateToString(this.getParentArtifact().getCreation());
   }

   public boolean getIsFile() {
      return parentArtifact instanceof FileStorage;
   }

   public @XmlElement boolean getIsVersionControlled() {
       return parentArtifact.isVersionControlled();
   }

   public @XmlElement String getLastModificationDate() {
      return GeneralService.dateToString(this.getParentArtifact().getLastModification());
   }

   public @XmlElement String getLastModifierName() {
      User modifier = getParentArtifact().getLastModifier();
      if (modifier!=null) return modifier.getUserName();
      else return "N/A";
   }

   public @XmlTransient Map<String, String> getProperties() { return properties; }

   public @XmlElement String getResponsibleName() {
      if(this.parentArtifact.getResponsible() != null)
         return this.getParentArtifact().getResponsible().getUserName();
      else 
         return "N/A";
   }

   public @XmlElement boolean getIsReserved() {
	   return this.parentArtifact.isReserved();
   }
   
   public @XmlElement String getUsedBy() {
	   return "N/A";
   }
   
   public void setProperties(Map<String, String> properties) {
      this.properties = properties;
   }

}