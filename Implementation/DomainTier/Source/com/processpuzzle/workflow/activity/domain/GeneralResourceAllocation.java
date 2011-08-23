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
