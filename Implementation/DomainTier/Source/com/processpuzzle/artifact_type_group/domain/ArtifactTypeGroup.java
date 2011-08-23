/*
 * Created on Jul 10, 2006
 */
package com.processpuzzle.artifact_type_group.domain;

import hu.itkodex.commons.persistence.AggregateRoot;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlID;

import com.processpuzzle.application.security.domain.DefaultAccessRight;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.persistence.query.domain.DefaultQueryContext;

/**
 * @author zsolt.zsuffa
 */
public class ArtifactTypeGroup extends GenericEntity<ArtifactTypeGroup> implements AggregateRoot {
   private @XmlAttribute @XmlID String name;
   @XmlElementWrapper( name = "defaultAccessRights" ) @XmlElement( name = "accessRight" )
   private List<DefaultAccessRight> defaultAccessRights = new ArrayList<DefaultAccessRight>();
   private @XmlElement( name = "artifactType" ) List<ArtifactType> artifactTypes = new ArrayList<ArtifactType>();

   ArtifactTypeGroup() {
   }

   ArtifactTypeGroup( String name ) {
      this.name = name;
   }

   public void addType( ArtifactType aType ) {
      artifactTypes.add( aType );
   }

   // Public accessors
   public DefaultAccessRight findAccessRightsFor( String partyRoleName ) {
      for( DefaultAccessRight anAccessRight : defaultAccessRights ) {
         if( anAccessRight.getUserRoleName().equals( partyRoleName )) return anAccessRight;
      }
      
      return null;
   }

   public List<ArtifactType> getArtifactTypes() { return artifactTypes; }

   public List<DefaultAccessRight> getDefaultAccessRights() { return defaultAccessRights; }

   public @Override @SuppressWarnings("unchecked") ArtifactTypeGroupIdentity getDefaultIdentity() {
      DefaultQueryContext queryContext = new DefaultQueryContext();
      queryContext.addTextValueFor( "nameValue", name );
      defaultIdentity = new ArtifactTypeGroupIdentity( queryContext );
      identities.add( defaultIdentity );
      return (ArtifactTypeGroupIdentity) defaultIdentity;
   }

   public String getLocalizedName() { return null; }

   // Properties
   public String getName() { return name; }

   public void setDefaultAccessRights( List<DefaultAccessRight> defaultAccessRights ) { this.defaultAccessRights = defaultAccessRights; }

   @Override
   protected void defineIdentityExpressions() {
      // TODO Auto-generated method stub
      
   }
}
