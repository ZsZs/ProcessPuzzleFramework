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

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.processpuzzle.artifact_type_group.domain.ArtifactTypeGroup;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRelationshipType;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRoleType;
import com.processpuzzle.party.partytype.domain.PartyType;
import com.processpuzzle.workflow.protocol.domain.ProtocolDefinition;

@XmlAccessorType( XmlAccessType.FIELD ) 
@XmlRootElement( name = "businessDefinition" )
public class BusinessDefinition {
   private static Logger log = LoggerFactory.getLogger( BusinessDefinition.class );
   @XmlElement(name="protocols") public ProtocolDefinition protocols = new ProtocolDefinition();
   
   @XmlElementWrapper(name="partyTypes") @XmlElement(name="partyType")
   public Set<PartyType> partyTypes = new HashSet<PartyType>();

   @XmlElementWrapper(name="artifactTypes") @XmlElement(name="artifactTypeGroup")
   public Set<ArtifactTypeGroup> artifactTypeGroups = new HashSet<ArtifactTypeGroup>();

   @XmlElementWrapper(name="partyRelationshipTypes") @XmlElement(name="partyRelationshipType")
   public Set<PartyRelationshipType> partyRelationshipTypes = new HashSet<PartyRelationshipType>();

   @XmlElementWrapper(name="partyRoleTypes") @XmlElement(name="partyRoleType")
   public Set<PartyRoleType> partyRoleTypes = new HashSet<PartyRoleType>();

   public BusinessDefinition() {
      log.debug( BusinessDefinition.class.getSimpleName() + " was instantiated." );
   }

   public void clearCollections() {
      partyTypes.clear();
      artifactTypeGroups.clear();
      partyRelationshipTypes.clear();
      partyRoleTypes.clear();
      protocols.clearCollections();
   }
}
