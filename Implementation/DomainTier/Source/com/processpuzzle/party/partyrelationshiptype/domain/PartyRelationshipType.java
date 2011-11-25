/*
Name: 
    - PartyRelationshipType 

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
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.party.partyrelationshiptype.domain;

import hu.itkodex.commons.persistence.AggregateRoot;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.party.domain.PartyRole;
import com.processpuzzle.party.domain.RuleSet;
import com.processpuzzle.persistence.query.domain.DefaultQueryContext;

public class PartyRelationshipType extends GenericEntity<PartyRelationshipType> implements AggregateRoot {
   @XmlAttribute private String name;
   @XmlAttribute private String description;
   @XmlElementWrapper(name = "partyRelationshipConstraints") @XmlElement(name = "partyRelationshipConstraint")
   private Set<PartyRelationshipConstraint> validRolePairs = new HashSet<PartyRelationshipConstraint>();

   private RuleSet requirementsForRelationship = new RuleSet();

   protected PartyRelationshipType() {}

   PartyRelationshipType( String name, PartyRelationshipConstraint partyRelationshipConstraint ) {
      this( name, partyRelationshipConstraint, null );
   }

   PartyRelationshipType( String name, PartyRelationshipConstraint partyRelationshipConstraint, RuleSet ruleSet ) {
      this.name = name;
      this.requirementsForRelationship = ruleSet;
      this.addValidRolePair(partyRelationshipConstraint);
   }

   public PartyRelationshipType(String name, Set<PartyRelationshipConstraint> partyRelationshipConstraints, RuleSet ruleSet) {
      this.name = name;
      this.requirementsForRelationship = ruleSet;
      this.validRolePairs = partyRelationshipConstraints;
   }

   public boolean canFormRelationship( PartyRoleType client, PartyRoleType supplier ) {
      for (Iterator<PartyRelationshipConstraint> iter = validRolePairs.iterator(); iter.hasNext();) {
         PartyRelationshipConstraint relationshipConstraint = iter.next();
         if( relationshipConstraint.canFormRelationship( client, supplier )) return true;
      }
      
      return false;
   }
      
   public boolean canFormRelationship( PartyRole client, PartyRole supplier ) {
      boolean canForm = false;
      for (Iterator<PartyRelationshipConstraint> iter = validRolePairs.iterator(); iter.hasNext();) {
         PartyRelationshipConstraint relationshipConstraint = iter.next();
         if(relationshipConstraint.canFormRelationship( client, supplier )) {
            canForm = true;
            break;
         }
      }
      return canForm;
   }

   public void addValidRolePair(PartyRelationshipConstraint partyRelationshipConstraint) {
      this.getValidRolePairs().add(partyRelationshipConstraint);
   }

   public void removeValidRolePair(PartyRelationshipConstraint partyRoleConstraint) {
      Iterator<PartyRelationshipConstraint> it = this.getValidRolePairs().iterator();
      boolean l = false;
      PartyRelationshipConstraint p = null;

      while ((it.hasNext()) && (!(l))) {
         p = it.next();
         if (p.getId().equals(partyRoleConstraint.getId())) {
            l = true;
         }
      }
      if (l) {
         this.validRolePairs.remove(p);
      }
   }

   public String getName() {
      return name;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public Set<PartyRelationshipConstraint> getValidRolePairs() {
      return validRolePairs;
   }

   public PartyRelationshipConstraint getSingleValidRolePair() {
      if (validRolePairs.iterator().hasNext())
         return validRolePairs.iterator().next();
      return null;
   }

   //Properties
   public RuleSet getRequirementsForRelationship() {
      return requirementsForRelationship;
   }

   public void setRequirementsForRelationship(RuleSet requirementsForRelationship) {
      this.requirementsForRelationship = requirementsForRelationship;
   }

   @SuppressWarnings("unchecked")
   @Override public PartyRelationshipTypeIdentity getDefaultIdentity() { 
      DefaultQueryContext context = new DefaultQueryContext();
      context.addTextValueFor("nameValue", name );
      PartyRelationshipTypeIdentity identity = new PartyRelationshipTypeIdentity( context );
      return identity;
   }

   protected void defineIdentityExpressions() {
      defaultIdentity = new PartyRelationshipTypeIdentity();
      identities.add(defaultIdentity);

   }

}
