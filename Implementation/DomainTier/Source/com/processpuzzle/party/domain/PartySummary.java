package com.processpuzzle.party.domain;

import hu.itkodex.commons.persistence.Entity;

import java.util.HashSet;
import java.util.Set;

import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.fundamental_types.uniqueidentifier.domain.InvalidUniqueIdentifier;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

public class PartySummary extends GenericEntity<PartySummary> implements Entity {
   private Set<PartyIdentifier> identifiers = new HashSet<PartyIdentifier>();
   private String name;
   private String address;
   private String telephoneNumber;
   private String faxNumber;
   private String emailAddress;
   private String webAddress;

   protected PartySummary() {}

   protected PartySummary( Party<?> party ) {
      fillInPartyProperties( party );
   }

   public String getAddress() {
      return address;
   }

   public String getEmailAddress() {
      return emailAddress;
   }

   public Set<PartyIdentifier> getIdentifiers() {
      return identifiers;
   }

   public void addIdentifier( PartyIdentifier identifier ) {
      identifiers.add( identifier );
   }

   public String getName() {
      return name;
   }

   public String getTelephoneNumber() {
      return telephoneNumber;
   }

   public String getFaxNumber() {
      return faxNumber;
   }

   public String getWebAddress() {
      return webAddress;
   }

   public @Override
   <I extends DefaultIdentityExpression<PartySummary>> I getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }

   public void setAddress( String address ) {
      this.address = address;
   }

   public void setEmailAddress( String emailAddress ) {
      this.emailAddress = emailAddress;
   }

   public void setFaxNumber( String faxNumber ) {
      this.faxNumber = faxNumber;
   }

   public void setName( String name ) {
      this.name = name;
   }

   public void setTelephoneNumber( String telephoneNumber ) {
      this.telephoneNumber = telephoneNumber;
   }

   public void setWebAddress( String webAddress ) {
      this.webAddress = webAddress;
   }

   public @Override
   String toString() {
      String text = name;
      text += "\n" + address;
      text += "\n" + telephoneNumber;
      text += "\n" + emailAddress;
      return text;
   }

   @Override
   protected void defineIdentityExpressions() {
   // TODO Auto-generated method stub

   }

   // Private helper methods
   private void fillInPartyProperties( Party<?> party ) {
      if ( party.getId() != null ) {
         try {
         this.addIdentifier( new PartyIdentifier( party.getId().toString()) );
         } catch( InvalidUniqueIdentifier e) {
            // can not occur
         }
      }
      this.name = party.getPartyName().getName();
      this.address = party.getDefaultGeographicAddress() != null ? party.getDefaultGeographicAddress().toString() : "";
      this.emailAddress = party.getDefaultEmailAddress() != null ? party.getDefaultEmailAddress().getEmailAddress() : "";
      this.faxNumber = party.getFaxNumber() != null ? party.getFaxNumber() : "";
      this.telephoneNumber = party.getDefaultTelecomAddress() != null ? party.getDefaultTelecomAddress().toString() : "";
      // TODO complete
   }
}
