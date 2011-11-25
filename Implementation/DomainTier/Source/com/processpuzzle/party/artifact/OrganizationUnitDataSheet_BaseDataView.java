/*
Name: 
    - OrganizationUnitDataSheet_BaseDataView

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

import java.util.Collection;

import com.processpuzzle.address.artifact.SettlementDataSheet;
import com.processpuzzle.address.artifact.SettlementDataSheetRepository;
import com.processpuzzle.address.domain.Settlement;
import com.processpuzzle.address.domain.ZipCode;
import com.processpuzzle.address.domain.ZipCodeRepository;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.artifact.domain.CustomFormView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;
import com.processpuzzle.party.domain.AddressFactory;
import com.processpuzzle.party.domain.GeographicAddress;
import com.processpuzzle.party.domain.OrganizationName;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;

public class OrganizationUnitDataSheet_BaseDataView extends CustomFormView<OrganizationUnitDataSheet> {
   private String settlementDataSheetId;
   private String zipCodeId;
   private String street = "";
   private String buildingNumber = "";
   private AddressFactory addressFactory;

   public OrganizationUnitDataSheet_BaseDataView( OrganizationUnitDataSheet artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );
      addressFactory = applicationContext.getEntityFactory( AddressFactory.class );
   }

   //Public mutators
   public @Override void initializeView() {
      // TODO Auto-generated method stub
   }

   //Properties
   public String getBuildingNumber() { return parentArtifact.getBuildingNumber(); }

   public void setBuildingNumber( String buildingNumber ) { 
      if( !parentArtifact.setBuildingNumber( buildingNumber )) this.buildingNumber = buildingNumber; 
   }

   public String getOrganizationUnitName() { return parentArtifact.getOrganizationName().getName(); }

   public void setOrganizationUnitName( String name ) { parentArtifact.rename( new OrganizationName( name )); }

   public SettlementDataSheet getSettlementDataSheet() { return parentArtifact.getSettlementDataSheet(); }

   public void setSettlementDataSheet( String settlementDataSheetId ) { 
      if( !parentArtifact.setSettlementDataSheet( settlementDataSheetId )) this.settlementDataSheetId = settlementDataSheetId;; 
   }

   public RepositoryResultSet<SettlementDataSheet> getSettlementDataSheets() { return parentArtifact.getSettlementDataSheets(); }

   public String getStreet() { return parentArtifact.getStreet(); }

   public void setStreet( String street ) { 
      if( !parentArtifact.setStreet( street )) this.street = street; 
   }

   public ZipCode getZipCode() { return parentArtifact.getZipCode(); }
   
   public void setZipCode( String zipCodeId ) { 
      if( !parentArtifact.setZipCode( zipCodeId )) this.zipCodeId = zipCodeId;
   }

   public Collection<ZipCode> getZipCodes() { return parentArtifact.getDefaultSettlementsZipCodes(); }

   public void performAction() {
      SettlementDataSheetRepository dataSheetRepository = applicationContext.getRepository( SettlementDataSheetRepository.class );
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      if( parentArtifact.getOrganizationUnit().getDefaultGeographicAddress() == null ){

         Settlement settlement = null;
         if( this.settlementDataSheetId != null ){
            settlement = dataSheetRepository.findById( work, new Integer( this.settlementDataSheetId ) ).getSettlement();
         }
         ZipCode zipCode = null;
         if( this.zipCodeId != null ){
            ZipCodeRepository zipCodeRepository = (ZipCodeRepository) ProcessPuzzleContext.getInstance().getRepository(
                  ZipCodeRepository.class );
            zipCode = zipCodeRepository.findZipCodeById( work, new Integer( this.zipCodeId ) );
         }
         GeographicAddress geo = null;
         if( settlement != null && zipCode != null ){
            geo = addressFactory.createGeographicAddress( this.street, this.buildingNumber, zipCode, settlement );
            geo.setIsDefault( true );
            ((OrganizationUnitDataSheet) parentArtifact).getOrganizationUnit().addAddress( geo );
         }
         work.finish();

      }
   }
}
