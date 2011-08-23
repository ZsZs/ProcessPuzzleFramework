package com.processpuzzle.party.artifact;

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
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;

public class CompanyDataSheet_BaseDataView extends CustomFormView<CompanyDataSheet> {
   private String settlementDataSheetId;
   private String zipCodeId;
   private String street = "";
   private String buildingNumber = "";
   private AddressFactory addressFactory;

   // Constructors
   protected CompanyDataSheet_BaseDataView() {
      super( null, null, null );
      addressFactory = applicationContext.getEntityFactory( AddressFactory.class );
      
   }

   public CompanyDataSheet_BaseDataView( CompanyDataSheet artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );
   }

   public String getAdministrator() {
      return parentArtifact.getAdministrator();
   }

   public String getAdminPhone() {
      return parentArtifact.getAdminPhone();
   }

   public String getBuildingNumber() {
      return parentArtifact.getBuildingNumber();
   }

   public String getCompanyName() {
      return parentArtifact.getCompanyName();
   }

   public String getCompanyShortName() {
      return parentArtifact.getCompanyShortName();
   }

   public String getCountry() { return parentArtifact.getCountry(); }

   public Collection<ZipCode> getDefaultSettlementsZipCodes() { 
      return parentArtifact.getDefaultSettlementsZipCodes(); 
   }

   public SettlementDataSheet getSettlementDataSheet() { 
      return parentArtifact.getSettlementDataSheet();
   }

   public String getStreet() {
      return parentArtifact.getStreet();
   }

   public String getTelecomAddress() { return parentArtifact.getTelecomAddress(); }

   public String getTradeRegisterNumber() {
      return parentArtifact.getTradeRegisterNumber();
   }

   public String getTaxNumber() {
      return parentArtifact.getTaxNumber();
   }

   public String getWebPageAddress() { return parentArtifact.getWebPageAddress(); }

   public ZipCode getZipCode() {
      return parentArtifact.getZipCode();
   }

   @Override
   public void initializeView() {
      // TODO Auto-generated method stub
   }

   public void performAction() {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      if( ((CompanyDataSheet) parentArtifact).getCompany().getDefaultGeographicAddress() == null ){
         SettlementDataSheetRepository settlementDataSheetRepository = applicationContext.getRepository( SettlementDataSheetRepository.class );
         Settlement settlement = null;
         if( this.settlementDataSheetId != null ){
            settlement = settlementDataSheetRepository.findById( work, new Integer( this.settlementDataSheetId ) ).getSettlement();
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
            ((CompanyDataSheet) parentArtifact).getCompany().addAddress( geo );
         }
      }
      work.finish();
   }

   public void setAdministrator( String administrator ) {
      parentArtifact.getCompany().setAdministrator( administrator );
   }

   public void setAdminPhone( String adminPhone ) {
      parentArtifact.getCompany().setAdminPhone( adminPhone );
   }

   public void setBuildingNumber( String buildingNumber ) { parentArtifact.setBuildingNumber( buildingNumber ); }

   public void setCompanyName( String name ) {
      parentArtifact.renameName( name );
   }

   public void setCompanyShortName( String name ) {
      parentArtifact.getCompany().setShortName( name );
   }

   public void setCountry( String country ) { parentArtifact.setCountry( country ); }

   public void setSettlementDataSheet( String settlementDataSheetId ) {
      parentArtifact.setSettlementDataSheet( settlementDataSheetId );
   }

   public void setStreet( String street ) { parentArtifact.setStreet( street ); }

   public void setTelecomAddress( String telecomAddress ) { parentArtifact.setTelecomAddress( telecomAddress ); }

   public void setTradeRegisterNumber( String tradeRegisterNumber ) {
      parentArtifact.getCompany().setTradeRegisterNumber( tradeRegisterNumber );
   }

   public void setTaxNumber( String taxNumber ) {
      parentArtifact.getCompany().setTaxNumber( taxNumber );
   }

   public void setZipCode( String zipCodeId ) {
      parentArtifact.setZipCode( zipCodeId );
   }

   public void setWebPageAddress( String webPageAddress ) { parentArtifact.setWebPageAddress( webPageAddress ); }
}
