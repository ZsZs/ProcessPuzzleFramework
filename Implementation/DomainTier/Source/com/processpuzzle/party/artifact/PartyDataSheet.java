/*
Name: 
    - PartyDataSheet

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

package com.processpuzzle.party.artifact;

import hu.itkodex.commons.persistence.RepositoryResultSet;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.processpuzzle.address.artifact.SettlementDataSheet;
import com.processpuzzle.address.artifact.SettlementDataSheetRepository;
import com.processpuzzle.address.domain.ZipCode;
import com.processpuzzle.address.domain.ZipCodeRepository;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.artifact.domain.DefaultArtifactRepository;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.party.domain.GeographicAddress;
import com.processpuzzle.party.domain.Party;
import com.processpuzzle.party.domain.PartyName;
import com.processpuzzle.party.domain.PartyRepository;
import com.processpuzzle.party.domain.PartyRole;
import com.processpuzzle.party.domain.TelecomAddress;
import com.processpuzzle.party.domain.WebPageAddress;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.user_session.domain.UserRequestManager;

public class PartyDataSheet<A extends PartyDataSheet<?,?>, P extends Party<?>> extends Artifact<A> {
   protected ProcessPuzzleContext applicationContext;
   protected DefaultArtifactRepository artifactRepository;
   protected SettlementDataSheetRepository settlementDataSheetRepository;
   protected P party;
   protected PartyRepository partyRepository;
   private SettlementDataSheet defaultAddressesSettlementDataSheet;
   
   protected PartyDataSheet() {
      applicationContext = UserRequestManager.getInstance().getApplicationContext();
      artifactRepository = applicationContext.getRepository( DefaultArtifactRepository.class );
      partyRepository = applicationContext.getRepository( PartyRepository.class );
      settlementDataSheetRepository = applicationContext.getRepository( SettlementDataSheetRepository.class );
   }

   public PartyDataSheet( P party ) {
      super();
      this.party = party;
   }

   PartyDataSheet( String name, ArtifactType type, User responsible, P party ) {
      super( name, type, responsible );
      this.party = party;
   }
   
   //Public accessors
   @SuppressWarnings("unchecked")
   public @Override String getAsXml() {
      Class<A> partyDataSheetClass = (Class<A>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
      return super.getAsXml( partyDataSheetClass, party ); 
   }
   
   public String getBuildingNumber() {
      if( party.getDefaultGeographicAddress() != null )
         return party.getDefaultGeographicAddress().getBuildingNumber();
      return notAvailableResponse();
   }

   public String getCountry() {      
      if( party.getDefaultGeographicAddress() != null )
         return party.getDefaultGeographicAddress().getSettlement().getCountry().getName();
      return notAvailableResponse();
   }
   
   public String getEmailAddress() {
      if( party.getDefaultEmailAddress() != null )
         return party.getDefaultEmailAddress().getEmailAddress(); 
      else return notAvailableResponse();
   }

   public String getGeographicAddress() {
      if( party.getDefaultGeographicAddress() != null ) {
         return party.getDefaultGeographicAddress().toString();
      }else return notAvailableResponse();
   }

   public Collection<GeographicAddress> getGeographicAddresses() {      
      return party.getGeographicAddresses();
   }

   public P getParty() { return party; }

   public List<PartyRole> getPartyRoles() {
      List<PartyRole> thePartyRoles = new ArrayList<PartyRole>();
      Set<PartyRole> partyRoles = party.getRoles();
      for( Iterator<PartyRole> iter = partyRoles.iterator(); iter.hasNext(); ){
         PartyRole partyRole = (PartyRole) iter.next();
         thePartyRoles.add( partyRole );
      }
      return thePartyRoles;
   }
   
   public String getSettlement() {
      if( party.getDefaultGeographicAddress() != null )
         return super.formaInternationalizedText( party.getDefaultGeographicAddress().getSettlement().getName() );
      else return super.notAvailableResponse();
   }

   SettlementDataSheet getSettlementDataSheet() {
      if( defaultAddressesSettlementDataSheet == null && party.getDefaultGeographicAddress() != null ) {
         DefaultUnitOfWork work = new DefaultUnitOfWork( true );
         String settlementName = party.getDefaultGeographicAddress().getSettlement().getName();
         defaultAddressesSettlementDataSheet = settlementDataSheetRepository.findByName( work, settlementName );
         work.finish();                  
      }

      return defaultAddressesSettlementDataSheet;
   }
   
   public RepositoryResultSet<SettlementDataSheet> getSettlementDataSheets() {
      SettlementDataSheetRepository repository = applicationContext.getRepository( SettlementDataSheetRepository.class );
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      RepositoryResultSet<SettlementDataSheet> dataSheets = repository.findAll( work );
      work.finish();
      return dataSheets;
   }

   public String getStreet() {
      if( party.getDefaultGeographicAddress() != null )
         return party.getDefaultGeographicAddress().getStreet();
      else return notAvailableResponse();
   }

   public String getTelecomAddress() {
      if( party.getDefaultTelecomAddress() != null )
         return party.getDefaultTelecomAddress().toString();
      return notAvailableResponse();
   }

   public ZipCode getZipCode() {
      if( party.getDefaultGeographicAddress() != null )
         return party.getDefaultGeographicAddress().getZipCode();
      else return null;
   }

   public String getZipCodeAsText() {
      if( party.getDefaultGeographicAddress() != null )
         return super.formaInternationalizedText( party.getDefaultGeographicAddress().getZipCode().asString() );
      else return super.notAvailableResponse();
   }

   public Collection<ZipCode> getDefaultSettlementsZipCodes() {
      if( party.getDefaultGeographicAddress() != null )
         return party.getDefaultGeographicAddress().getSettlement().getZipCodes();
      else return null;
   }

   public String getWebPageAddress() {
      if( party.getDefaultWebPageAddress() != null )
         return party.getDefaultWebPageAddress().getUrl();
      return null;
   }

   //Public mutators
   public void rename( PartyName partyName ) {
      renameName( partyName.getName() );
   }
   
   public boolean setBuildingNumber( String buildingNumber ) {
      if( party.getDefaultGeographicAddress() != null ){
         party.getDefaultGeographicAddress().setBuildingNumber( buildingNumber );
         return true;
      }else return false;
   }

   public void setCountry( String country ) {
      if( party.getDefaultGeographicAddress() != null ){
         party.getDefaultGeographicAddress().getSettlement().getCountry().setName( country );
      }
   }

   public boolean setSettlementDataSheet( String settlementDataSheetId ) {
      if( party.getDefaultGeographicAddress() != null ){
         DefaultUnitOfWork work = new DefaultUnitOfWork( true );
         SettlementDataSheet s = (SettlementDataSheet) artifactRepository.findById( work, new Integer( settlementDataSheetId ) );
         party.getDefaultGeographicAddress().setSettlement( s.getSettlement() );
         work.finish();
         return true;
      }else return false;
   }
   
   public boolean setStreet( String street ) {
      if( party.getDefaultGeographicAddress() != null ){
         party.getDefaultGeographicAddress().setStreet( street );
         return true;
      }else return false;
   }
   
   public void setTelecomAddress( String telecomAddress ) {
      if( party.getDefaultTelecomAddress() != null ){
         party.getDefaultTelecomAddress().setNumber( telecomAddress );
      }else
         party.getAddresses().add( new TelecomAddress( telecomAddress, true ) );
   }

   public void setWebPageAddress( String webPageAddress ) {
      if( party.getDefaultWebPageAddress() != null ){
         party.getDefaultWebPageAddress().setUrl( webPageAddress );
      }else
         party.getAddresses().add( new WebPageAddress( webPageAddress, true ) );
   }

   public boolean setZipCode( String zipCodeId ) {
      if( party.getDefaultGeographicAddress() != null ){
         DefaultUnitOfWork work = new DefaultUnitOfWork( true );
         ZipCodeRepository zipCodeRepository = applicationContext.getRepository( ZipCodeRepository.class );
         party.getDefaultGeographicAddress().setZipCode( zipCodeRepository.findZipCodeById( work, new Integer( zipCodeId ) ) );
         work.finish();
         return true;
      }else return false;
   }
}
