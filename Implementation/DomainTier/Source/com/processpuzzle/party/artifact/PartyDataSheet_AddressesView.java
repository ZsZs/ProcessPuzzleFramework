package com.processpuzzle.party.artifact;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import com.processpuzzle.address.artifact.SettlementDataSheet;
import com.processpuzzle.address.artifact.SettlementDataSheetRepository;
import com.processpuzzle.address.domain.Settlement;
import com.processpuzzle.address.domain.ZipCode;
import com.processpuzzle.address.domain.ZipCodeRepository;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.artifact.domain.CustomFormView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;
import com.processpuzzle.party.domain.Address;
import com.processpuzzle.party.domain.AddressFactory;
import com.processpuzzle.party.domain.EmailAddress;
import com.processpuzzle.party.domain.GeographicAddress;
import com.processpuzzle.party.domain.Party;
import com.processpuzzle.party.domain.PartyRepository;
import com.processpuzzle.party.domain.WebPageAddress;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.util.domain.GeneralService;

public class PartyDataSheet_AddressesView extends CustomFormView<PartyDataSheet<?, ?>> {
   private String emailAddress;
   private String selectedValue;
   private String webPageAddress;
   private String settlementDataSheetId;
   private String buildingNumber;
   private String street;
   private String zipCodeId;
   private String currentGeoAddressId;
   private GeographicAddress targetGeographicAddress;
   private PartyRepository partyRepository;
   private AddressFactory addressFactory;

   public PartyDataSheet_AddressesView( PartyDataSheet<?, ?> partyDataSheet, String dataSheetName, ArtifactViewType viewType ) {
      super( partyDataSheet, dataSheetName, viewType );
      partyRepository = applicationContext.getRepository( PartyRepository.class );
      addressFactory = applicationContext.getEntityFactory( AddressFactory.class );
   }

   public void setGeographidAddressById( String id ) {
      for( Iterator<GeographicAddress> iter = parentArtifact.getParty().getGeographicAddresses().iterator(); iter.hasNext(); ){
         GeographicAddress geographicAddress = (GeographicAddress) iter.next();
         if( geographicAddress.getId().toString().equals( id ) )
            this.targetGeographicAddress = geographicAddress;
      }
   }

   public void setSelectedValue( String value ) {
      this.selectedValue = value;
   }

   public Collection<EmailAddress> getEmailAddresses() {
      return parentArtifact.getParty().getEmailAddresses();
   }

   public Collection<WebPageAddress> getWebPages() {
      return parentArtifact.getParty().getWebPages();
   }

   public void setEmailAddress( String emailAddress ) {
      this.emailAddress = emailAddress;
   }

   public void setWebPageAddress( String webPage ) {
      this.webPageAddress = webPage;
   }

   public Collection<GeographicAddress> getGeographicAddresses() {
      return parentArtifact.getGeographicAddresses();
   }
   
   public void setCurrentGeoAddress( String id ) {
      this.currentGeoAddressId = id;
   }

   public String getCurrentGeoAddressName() {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      Party party = partyRepository.findPartyByAddressId( work, new Integer( currentGeoAddressId ) );
      String addressId = party.getAddressById( new Integer( currentGeoAddressId ) ).toString();
      work.finish();
      return addressId;
   }

   public SettlementDataSheet getSettlementDataSheet() {
      return parentArtifact.getSettlementDataSheet();
   }

   public void setSettlementDataSheet( String settlementDataSheetId ) {
      parentArtifact.setSettlementDataSheet( settlementDataSheetId );
   }

   public Collection<ZipCode> getZipCodes() {
      return parentArtifact.getDefaultSettlementsZipCodes();
   }

   public ZipCode getZipCode() {
      return parentArtifact.getZipCode();
   }

   public void setZipCode( String zipCodeId ) {
      parentArtifact.setZipCode( zipCodeId );
   }

   public String getBuildingNumber() {
      return parentArtifact.getBuildingNumber();
   }

   public void setBuildingNumber( String buildingNumber ) {
      parentArtifact.setBuildingNumber( buildingNumber );
   }

   public String getStreet() {
      return parentArtifact.getStreet();
   }

   public void setStreet( String street ) {
      parentArtifact.setStreet( street );
   }

   public void initializeView() {}

   public String getData( String method, Map<String, String> parameters ) {
      StringBuffer responseXml = new StringBuffer();
      String selectedValue = (String) parameters.get( "par0" );
      if( !method.equals( "" ) ){
         if( method.equals( "getGeographicAddress" ) ){
            GeographicAddress geographicAddress = null;
            for( Iterator<GeographicAddress> iter = parentArtifact.getParty().getGeographicAddresses().iterator(); iter.hasNext(); ){
               GeographicAddress geo = iter.next();
               if( geo.getId().toString().equals( selectedValue ) )
                  geographicAddress = geo;
            }
            responseXml.append( "<geographicAddress id=\"" + geographicAddress.getId().toString() + "\">" );
            DefaultUnitOfWork work = new DefaultUnitOfWork( true );
            responseXml.append( "<settlement id=\""
                  + artifactRepository.findById( work, geographicAddress.getSettlement().getId() ).getId() + "\"" );
            work.finish();
            responseXml.append( " current=\"true\"" );
            responseXml.append( ">" + geographicAddress.getSettlement().getName() + "</settlement>" );
            responseXml.append( "<zipCodes>" );
            for( Iterator<ZipCode> iter = geographicAddress.getSettlement().getZipCodes().iterator(); iter.hasNext(); ){
               ZipCode zipCode = (ZipCode) iter.next();
               responseXml.append( "<zipCode id=\"" + zipCode.getId().toString() + "\"" );
               if( geographicAddress.getZipCode() != null && zipCode.getId().equals( geographicAddress.getZipCode().getId() ) )
                  responseXml.append( " current=\"true\"" );
               responseXml.append( ">" + zipCode.getZipCode().toString() + "</zipCode>" );
            }
            responseXml.append( "</zipCodes>" );
            responseXml.append( "<buildingNumber>" + geographicAddress.getBuildingNumber() + "</buildingNumber>" );
            responseXml.append( "<street>" + geographicAddress.getStreet() + "</street>" );
            responseXml.append( "</geographicAddress>" );
            return responseXml.toString();
         }else{
            return responseXml.append( "<failure></failure>" ).toString();
         }
      }
      return null;
   }

   public void performAction() {
      if( method != null && !method.equals( "" ) ){

         if( method.equals( "newEmailAddress" ) ){
            if( this.emailAddress != null ){
               parentArtifact.getParty().getAddresses().add( addressFactory.createEmailAddress( emailAddress ) );
            }
         }else if( method.equals( "newWebPageAddress" ) ){
            if( this.webPageAddress != null ){
               parentArtifact.getParty().getAddresses().add( addressFactory.createWebPageAddress( webPageAddress ) );
            }
         }else if( method.equals( "newGeographicAddress" ) ){
            DefaultUnitOfWork work = new DefaultUnitOfWork( true );
            Settlement settlement = null;
            if( this.settlementDataSheetId != null ){
               SettlementDataSheetRepository settlementDataSheetRepository = applicationContext.getRepository( SettlementDataSheetRepository.class );
               settlement = settlementDataSheetRepository.findById( work, new Integer( this.settlementDataSheetId )).getSettlement();
            }
            ZipCode zipCode = null;
            if( this.zipCodeId != null ){
               ZipCodeRepository zipCodeRepository = (ZipCodeRepository) ProcessPuzzleContext.getInstance().getRepository(
                     ZipCodeRepository.class );
               zipCode = zipCodeRepository.findZipCodeById( work, new Integer( this.zipCodeId ) );
            }
            work.finish();
            GeographicAddress geo = null;
            if( settlement != null && zipCode != null ){
               geo = addressFactory.createGeographicAddress( this.street, this.buildingNumber, zipCode, settlement );
               geo.setIsDefault( false );
               parentArtifact.getParty().addAddress( geo );
            }
         }else if( method.equals( "delAddress" ) ){
            if( this.selectedValue != null ){
               Address address = null;
               address = (Address) GeneralService.findCollectionItemByFieldName( parentArtifact.getParty().getAddresses(), "id", new Integer( selectedValue ) );
               parentArtifact.getParty().getAddresses().remove( address );
            }
         }
      }
   }
}
