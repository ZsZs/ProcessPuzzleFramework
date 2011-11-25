/*
Name: 
    - PartyRelationshipConstraint

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

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlIDREF;

import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.party.domain.PartyRole;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

public class PartyRelationshipConstraint extends GenericEntity<PartyRelationshipConstraint> {
   @XmlIDREF @XmlAttribute(name = "clientRoleType") private PartyRoleType clientRoleType;
   @XmlIDREF @XmlAttribute(name = "supplierRoleType") private PartyRoleType supplierRoleType;

   public PartyRelationshipConstraint() {}

   public PartyRelationshipConstraint(PartyRoleType clientRoleType, PartyRoleType supplierRoleTye) {
      this.clientRoleType = clientRoleType;
      this.supplierRoleType = supplierRoleTye;
   }

   public boolean canFormRelationship(PartyRole clientPartyRole, PartyRole supplierPartyRole) {
      boolean canForm = false;
      if(( clientPartyRole.getName() != null) && (supplierPartyRole.getName() != null)) {
         if(( clientPartyRole.getRoleType().getName().equals(clientRoleType.getName()))
               && (supplierPartyRole.getRoleType().getName().equals(supplierRoleType.getName()))) {
            canForm = true;
         }
      }
      return canForm;
   }

   public boolean canFormRelationship( PartyRoleType client, PartyRoleType supplier ) {
      if( client.equals( this.clientRoleType ) && supplier.equals( this.supplierRoleType )) return true;
      else return false;
   }

   public PartyRoleType getClientRoleType() {
      return clientRoleType;
   }

   public void setClientRoleType(PartyRoleType clientRoleType) {
      this.clientRoleType = clientRoleType;
   }

   public PartyRoleType getSupplierRoleType() {
      return supplierRoleType;
   }

   public void setSupplierRoleType(PartyRoleType supplierRoleType) {
      this.supplierRoleType = supplierRoleType;
   }

   @Override
   public <I extends DefaultIdentityExpression<PartyRelationshipConstraint>> I getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   protected void defineIdentityExpressions() {
      // TODO Auto-generated method stub
      
   }

}
