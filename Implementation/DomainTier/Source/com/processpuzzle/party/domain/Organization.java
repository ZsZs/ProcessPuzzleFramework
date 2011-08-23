/*
 * Created on 2005.07.19.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.party.domain;

import java.util.Set;

import com.processpuzzle.party.partytype.domain.PartyType;
import com.processpuzzle.persistence.query.domain.DefaultQueryContext;

public class Organization extends Party<Organization> {
   private Set<Organization> affiliations;
   private String organizationType;

   Organization( OrganizationName organizationName, PartyType type ) {
      super( organizationName, type );
      this.partyName = organizationName;
   }

   protected Organization() {}

   public Set<Organization> getAffiliations() {
      return affiliations;
   }

   public void setAffiliations( Set<Organization> affiliations ) {
      this.affiliations = affiliations;
   }

   public String getOrganizationType() {
      return organizationType;
   }

   public void setOrganizationType( String organizationType ) {
      this.organizationType = organizationType;
   }

   @Override @SuppressWarnings("unchecked")
   public OrganizationIdentity getDefaultIdentity() {
      DefaultQueryContext context = new DefaultQueryContext();
      context.addTextValueFor("partyNameValue", partyName.getName() );
      OrganizationIdentity identity = new OrganizationIdentity( context );
      return identity;
   }

   protected void defineIdentityExpressions() {
   //      defaultIdentity = getDefaultIdentity();
   //      identities.add( defaultIdentity );
      }
}
