/*
Name: 
    - BusinessDefinition

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

package com.processpuzzle.business.definition.domain;

import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Sets;
import com.processpuzzle.artifact_type_group.domain.ArtifactTypeGroup;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRelationshipType;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRoleType;
import com.processpuzzle.party.partytype.domain.PartyType;
import com.processpuzzle.workflow.protocol.domain.ProtocolDefinition;

@XmlAccessorType( XmlAccessType.FIELD ) 
@XmlRootElement( name = "businessDefinition", namespace = "http://www.processpuzzle.com/BusinessDefinitionsConfiguration" )
@XmlType( propOrder = { "partyTypes", "partyRoleTypes", "partyRelationshipTypes", "artifactTypeGroups", "protocols" })
public class BusinessDefinition {
   private static Logger log = LoggerFactory.getLogger( BusinessDefinition.class );
   @XmlElementWrapper(name="artifactTypes") @XmlElement(name="artifactTypeGroup", namespace="http://www.processpuzzle.com/ArtifactTypeDefinition" ) public Set<ArtifactTypeGroup> artifactTypeGroups = Sets.newHashSet();
   @XmlElementWrapper(name="partyRelationshipTypes") @XmlElement( name="partyRelationshipType", namespace="http://www.processpuzzle.com/PartyRelationshipTypeDefinition" ) public Set<PartyRelationshipType> partyRelationshipTypes = Sets.newHashSet();
   @XmlElementWrapper(name="partyRoleTypes") @XmlElement(name="partyRoleType", namespace="http://www.processpuzzle.com/PartyRelationshipTypeDefinition" ) public Set<PartyRoleType> partyRoleTypes = Sets.newHashSet();
   @XmlElementWrapper(name="partyTypes") @XmlElement( name="partyType", namespace="http://www.processpuzzle.com/PartyTypeDefinition" ) public Set<PartyType> partyTypes = Sets.newHashSet();
   @XmlElement(name="protocols") public ProtocolDefinition protocols = new ProtocolDefinition();

   //Constructors
   public BusinessDefinition() {
      log.debug( BusinessDefinition.class.getSimpleName() + " was instantiated." );
   }

   //Public accessors and mutators
   public void addArtifactTypeGroup( ArtifactTypeGroup artifactType ) {
      artifactTypeGroups.add( artifactType );
   }

   public void addPartyType( PartyType partyType ) {
      partyTypes.add( partyType );
   }
   
   public void clearCollections() {
      partyTypes.clear();
      artifactTypeGroups.clear();
      partyRelationshipTypes.clear();
      partyRoleTypes.clear();
      protocols.clearCollections();
   }

}
