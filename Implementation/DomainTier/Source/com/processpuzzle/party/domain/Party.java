/*
Name: 
    - Party 

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

package com.processpuzzle.party.domain;


import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.commons.persistence.AggregateRoot;
import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.fundamental_types.domain.TimePeriod;
import com.processpuzzle.party.partytype.domain.PartyType;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.resource.domain.Asset;
import com.processpuzzle.resource.resourcetype.domain.AssetType;
import com.processpuzzle.util.domain.GeneralService;

public abstract class Party<P extends Party<?>> extends GenericEntity<P> implements Asset, AggregateRoot {
   private String description;
   private Set<Address> addresses = new HashSet<Address>();
   private Set<PartyRole> partyRoles = new HashSet<PartyRole>();
   private TimePeriod valid;
   protected PartyType type;
   protected PartyName partyName;
   protected Set<PartyName> otherNames;

   // Constructors
   protected Party() {}

   Party( PartyName name, PartyType type ) {
      this.partyName = name;
      this.type = type;
   }

   // Public accesors
   public EmailAddress findEmailAddress( String emailAddress ) {
      for( Address address : addresses ){
         if( (address instanceof EmailAddress) && (address.getName().equals( emailAddress )) )
            return (EmailAddress) address;
      }
      return null;
   }

   public Address getAddressById( Integer id ) {
      for( Iterator<Address> i = addresses.iterator(); i.hasNext(); ){
         Address address = (Address) i.next();
         if( id == address.getId() )
            return address;
      }
      return null;
   }

   public EmailAddress getDefaultEmailAddress() {
      return findDefaultAddressOf( EmailAddress.class );
   }

   public GeographicAddress getDefaultGeographicAddress() {
      return findDefaultAddressOf( GeographicAddress.class );
   }

   public TelecomAddress getDefaultTelecomAddress() {
      return findDefaultAddressOf( TelecomAddress.class );
   }

   public WebPageAddress getDefaultWebPageAddress() {
      return findDefaultAddressOf( WebPageAddress.class );
   }

   public Collection<EmailAddress> getEmailAddresses() {
      return collectAddressesByKind( EmailAddress.class );
   }

   public Collection<GeographicAddress> getGeographicAddresses() {
      return collectAddressesByKind( GeographicAddress.class );
   }

   public Collection<Party<?>> getPartiesByRelationshipType( String partyRelationshipTypeName ) {
      PartyRepository partyRep = (PartyRepository) ProcessPuzzleContext.getInstance().getRepository( PartyRepository.class );
      Set<Party<?>> parties = new HashSet<Party<?>>();
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      for( Iterator<PartyRole> iter = partyRoles.iterator(); iter.hasNext(); ){
         PartyRole ownRole = (PartyRole) iter.next();

         if( ownRole.getPartyRelationship() != null
               && ownRole.getPartyRelationship().getRelationshipType().getName().equals( partyRelationshipTypeName ) ){
            Party<?> party = partyRep.findOtherPartyByPartyRole( work, ownRole.getPartyRelationship().getId().toString(), ownRole.getId()
                  .toString() );
            parties.add( party );
         }

      }
      work.finish();
      return parties;
   }

   public static PartyRole getRoleFromPartyById( Party<?> party, Integer id ) {
      Set<PartyRole> roles = party.getRoles();
      for( Iterator<PartyRole> role = roles.iterator(); role.hasNext(); ){
         PartyRole nextRole = (PartyRole) role.next();
         if( nextRole.getId() == id ){
            return nextRole;
         }
      }
      return null;
   }

   public Collection<WebPageAddress> getWebPages() {
      return collectAddressesByKind( WebPageAddress.class );
   }

   // Public mutators
   public void addAddress( Address address ) {
      addresses.add( address );
   }

   public void addPartyRole( PartyRole role ) {
      partyRoles.add( role );
      role.setParty( this );
   }

   public void rename( PartyName partyName ) {
      this.partyName = partyName;
   }

   public void removePartyRole( PartyRole partyRole ) throws Exception {
      PartyRole role = (PartyRole) GeneralService.findCollectionItemByFieldName( partyRoles, "id", partyRole.getId() );
      role.setParty( null );
      partyRoles.remove( role );
   }

   public void removeRole( PartyRole role ) {
      partyRoles.remove( role );
   }

   // Properties
   public Set<Address> getAddresses() {
      return addresses;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription( String description ) {
      this.description = description;
   }

   public Set<PartyName> getOtherNames() {
      return otherNames;
   }

   public PartyName getPartyName() {
      return partyName;
   }

   public String getName() {
      return partyName.getName();
   }

   public void renameName( String name ) {
      partyName.rename( name );
   }

   public Set<PartyRole> getRoles() {
      return partyRoles;
   }

   public PartyType getType() {
      return type;
   }

   public void setType( AssetType type ) {
      this.type = (PartyType) type;
   }

   public TimePeriod getValid() {
      return valid;
   }

   public void setValid( TimePeriod valid ) {
      this.valid = valid;
   }

   @SuppressWarnings( "unchecked" )
   private <A extends Address> Collection<A> collectAddressesByKind( Class<A> addressKind ) {
      Collection<A> mailAddresses = new HashSet<A>();
      Iterator<Address> addressIterator = getAddresses().iterator();

      while( addressIterator.hasNext() ){
         Address address = addressIterator.next();
         if( address.getClass().equals( addressKind ) ){
            mailAddresses.add( (A) address );
         }
      }
      return mailAddresses;
   }

   @SuppressWarnings( "unchecked" )
   private <A extends Address> A findDefaultAddressOf( Class<A> addressKind ) {
      Iterator<A> addressIterator = collectAddressesByKind( addressKind ).iterator();
      while( addressIterator.hasNext() ){
         Address address = (Address) addressIterator.next();
         if( address.getIsDefault() )
            return (A) address;
      }
      return null;
   }

   public Address getAddressUsedFor( String usedFor ) {
      for( Iterator<Address> iterator = addresses.iterator(); iterator.hasNext(); ){
         Address address = (Address) iterator.next();
         if(address != null && address.getUsedFor() != null && address.getUsedFor().equalsIgnoreCase( usedFor ))
            return address;
      }
      return null;
   }
   
   public String getFaxNumber() {
      Address address = getAddressUsedFor( TelecomAddress.USED_FOR_FAX );
      if(address != null && address instanceof TelecomAddress)
         return ((TelecomAddress) address).toString();
      return null;
   }

   // Public accessors
   // public Address getAddress( String addressTypeName ) {
   // Address address = null;
   // Iterator<Address> addressIterator = getAddresses().iterator();
   // while( (addressIterator.hasNext())
   // && ((address = (Address) addressIterator.next()).getClass().getName().indexOf( addressTypeName ) == -1) ){}
   // if( (address != null) && (address.getClass().getName().indexOf( addressTypeName ) != -1) ){
   // return address;
   // }else
   // return null;
   // }
   // 
}