/*
Name: 
    - ArtifactTypeGroup

Description: 
    -  

Requires:
    - 
    
Provides:
    - 

Part of: ProcessPuzzle Framework, Domain and Business Model Ready Architecture. Provides content, workflow and social networking functionality. 
http://www.processpuzzle.com

ProcessPuzzle - Content and Workflow Management Integration Business Platform

Author(s): 
    - Zsolt Zsuffa

Copyright: (C) 2011 This program is free software: you can redistribute it and/or modify it under the terms of the 
GNU General Public License as published by the Free Software Foundation, either version 3 of the License, 
or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

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
