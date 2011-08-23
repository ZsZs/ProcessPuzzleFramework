/*
 * Created on 2005.09.22.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.party.domain;

import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRelationshipType;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

public class PartyRelationship extends GenericEntity<PartyRelationship> {
   protected PartyRelationshipType relationshipType;
   protected String name;
   protected String description;


   public PartyRelationship() {
      super();
   }

   public PartyRelationship(String name, PartyRole client, PartyRole supplier, PartyRelationshipType type) {
      if (type.canFormRelationship(client, supplier)) {
         this.name = name;
         this.relationshipType = type;
      }
   }

   public PartyRelationship(String name) {
      super();
      // TODO Auto-generated constructor stub
      this.name = name;
   }

   public String getDescription() {
      return description;
   }

   public String getName() {
      return name;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Integer getId() {
      return id;
   }

   public PartyRelationshipType getRelationshipType() {
      return relationshipType;
   }

   public void setRelationshipType(PartyRelationshipType relationshipType) {
      this.relationshipType = relationshipType;
   }
   
   public @Override <I extends DefaultIdentityExpression<PartyRelationship>> I getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   protected void defineIdentityExpressions() {
      // TODO Auto-generated method stub
      
   }

}
