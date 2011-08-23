package com.processpuzzle.party.domain;

import com.processpuzzle.party.partytype.domain.PartyType;
import com.processpuzzle.persistence.query.domain.DefaultQueryContext;

public class Company extends Organization {
   private String tradeRegisterNumber;
   private String taxNumber;
   private String administrator;
   private String adminPhone;
   private String shortName;

   Company( OrganizationName companyName, PartyType partyType ) {
      super( companyName, partyType );
   }

   protected Company() {}

   public String getShortName() {
      return shortName;
   }

   public void setShortName( String shortName ) {
      this.shortName = shortName;
   }

   public String getTaxNumber() {
      return taxNumber;
   }

   public void setTaxNumber( String taxNumber ) {
      this.taxNumber = taxNumber;
   }

   public String getTradeRegisterNumber() {
      return tradeRegisterNumber;
   }

   public void setTradeRegisterNumber( String tradeRegisterNumber ) {
      this.tradeRegisterNumber = tradeRegisterNumber;
   }

   public String getAdministrator() {
      return administrator;
   }

   public void setAdministrator( String administrator ) {
      this.administrator = administrator;
   }

   public String getAdminPhone() {
      return adminPhone;
   }

   public void setAdminPhone( String adminPhone ) {
      this.adminPhone = adminPhone;
   }

   @Override
   protected void defineIdentityExpressions() {
      DefaultQueryContext context = new DefaultQueryContext();
      context.addTextValueFor( "nameValue", this.getName() );
//      defaultIdentity = new CompanyIdentity( context );
//      identities.add( defaultIdentity );
   }
}