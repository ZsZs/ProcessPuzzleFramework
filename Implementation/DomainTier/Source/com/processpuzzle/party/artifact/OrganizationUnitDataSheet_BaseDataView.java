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
