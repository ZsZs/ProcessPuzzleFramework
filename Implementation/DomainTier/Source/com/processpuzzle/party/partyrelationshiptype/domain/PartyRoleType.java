/*
Name: 
    - PartyRoleType 

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
 * Created on 2005.09.22.
 */
package com.processpuzzle.party.partyrelationshiptype.domain;

import hu.itkodex.commons.persistence.AggregateRoot;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlID;

import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.fundamental_types.domain.HashCodeUtil;
import com.processpuzzle.party.domain.RuleSet;
import com.processpuzzle.party.partytype.domain.PartyType;
import com.processpuzzle.persistence.query.domain.DefaultQueryContext;

public class PartyRoleType extends GenericEntity<PartyRoleType> implements Comparable<Object>, AggregateRoot {
   @XmlAttribute @XmlID public String name;
   @XmlElement private String description;
   @XmlElementWrapper(name = "partyRoleConstraints") @XmlElement(name = "partyRoleConstraint")
   private Set<PartyRoleConstraint> validPartyTypes = new HashSet<PartyRoleConstraint>();
   private RuleSet requirementsForRole;
   
   //Constructors
   protected PartyRoleType() {}

   PartyRoleType( String name ) {
      this( name, null, null );
   }

   PartyRoleType( String name, String description ) {
      this( name, description, null );
   }

   PartyRoleType( String name, String description, RuleSet ruleSet ) {
      this.name = name;
      this.description = description;
      this.requirementsForRole = ruleSet;
   }

   //Public accessors
   public boolean canPlayRole( PartyType partyType ) {
      boolean valid = false;
      for (Iterator<PartyRoleConstraint> iter = validPartyTypes.iterator(); iter.hasNext();) {
         PartyRoleConstraint partyRoleConstraint = (PartyRoleConstraint) iter.next();
         valid = partyRoleConstraint.canPlayRole( partyType );
         if (valid) break;
      }
      return valid;
   }

   public int compareTo(Object o) {
      if (!(o instanceof PartyRoleType)) return -1;
      
      PartyRoleType n = (PartyRoleType) o;
      int c;
      if ((c = id.compareTo(n.getId())) != 0) return c;
      
      return 0;
   }

   @Override public boolean equals( Object objectToCheck ) {
      if( !( objectToCheck instanceof PartyRoleType )) return false;
      PartyRoleType anotherPartyType = (PartyRoleType) objectToCheck;
      return name.equals( anotherPartyType.name ) && id.equals( anotherPartyType.id );
   }

   @Override public int hashCode() {
      int result = HashCodeUtil.SEED;
      result = HashCodeUtil.hash( result, name );
      result = HashCodeUtil.hash( result, description );
      result = HashCodeUtil.hash( result, id );

      return result;
   }

   //Public mutators
   public void addPlayerPartyType( PartyType partyType ) {
      PartyRoleConstraint roleConstraint = new PartyRoleConstraint( partyType );
      validPartyTypes.add( roleConstraint );
   }
   
   @Deprecated public void addValidTypeOfParty( PartyRoleConstraint partyRoleConstraint ) {
      this.validPartyTypes.add(partyRoleConstraint);
   }

   public void removeValidTypesOfParty( PartyRoleConstraint partyRoleConstraint ) {
      for (Iterator<PartyRoleConstraint> iter = validPartyTypes.iterator(); iter.hasNext();) {
         PartyRoleConstraint p = (PartyRoleConstraint) iter.next();
         if (p.getTypeOfParty().equals(partyRoleConstraint.getTypeOfParty()))
            validPartyTypes.remove(p);
      }
   }

   //Properties
   public Integer getId() { return id; }
   
   public String getName() { return name; }

   public String getDescription() { return description; }
   public void setDescription(String description) { this.description = description; }

   public Set<PartyRoleConstraint> getValidPartyTypes() { return validPartyTypes; }

   public RuleSet getRequirementsForRole() {
      return requirementsForRole;
   }

   public void setRequirementsForRole(RuleSet requirementsForRole) {
      this.requirementsForRole = requirementsForRole;
   }

   @SuppressWarnings("unchecked")
   @Override
   public PartyRoleTypeIdentity getDefaultIdentity() { 
      DefaultQueryContext context = new DefaultQueryContext();
      context.addTextValueFor("nameValue", name );
      PartyRoleTypeIdentity identity = new PartyRoleTypeIdentity( context );
      return identity;
   }

   protected void defineIdentityExpressions() {
      defaultIdentity = new PartyRoleTypeIdentity();
      identities.add(defaultIdentity);
   }
}
