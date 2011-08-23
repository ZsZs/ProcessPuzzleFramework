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
