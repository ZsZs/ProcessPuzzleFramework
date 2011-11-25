/*
Name: 
    - GeneralResourceAllocation 

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
 * Created on 2005.07.19.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.workflow.activity.domain;

import com.processpuzzle.fundamental_types.quantity.domain.Quantity;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRoleType;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;
import com.processpuzzle.resource.resourcetype.domain.ResourceType;

public class GeneralResourceAllocation extends ResourceAllocation {
   private ProposedAction proposedAction;
   private PartyRoleType partyRoleType;

   public GeneralResourceAllocation(ResourceType theResourceType, Quantity theQuantity) {
      super(theResourceType, theQuantity);
   }

   public GeneralResourceAllocation(PartyRoleType partyRoleType, Quantity theQuantity) {
      super(null, theQuantity);
      this.partyRoleType = partyRoleType;
   }

   public GeneralResourceAllocation() {}

   public ProposedAction getProposedAction() {
      return proposedAction;
   }

   public void setProposedAction(ProposedAction proposedAction) {
      this.proposedAction = proposedAction;
   }

   public PartyRoleType getPartyRoleType() {
      return partyRoleType;
   }

   public void setPartyRoleType(PartyRoleType partyRoleType) {
      this.partyRoleType = partyRoleType;
   }

   @Override
   public <I extends DefaultIdentityExpression<ResourceAllocation>> I getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   protected void defineIdentityExpressions() {
      // TODO Auto-generated method stub
      
   }

}
