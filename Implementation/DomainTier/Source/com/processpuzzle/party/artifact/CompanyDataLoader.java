package com.processpuzzle.party.artifact;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

import com.processpuzzle.address.domain.Country;
import com.processpuzzle.address.domain.CountryFactory;
import com.processpuzzle.address.domain.CountryRepository;
import com.processpuzzle.address.domain.Settlement;
import com.processpuzzle.address.domain.SettlementFactory;
import com.processpuzzle.address.domain.SettlementRepository;
import com.processpuzzle.address.domain.ZipCode;
import com.processpuzzle.address.domain.ZipCodeFactory;
import com.processpuzzle.address.domain.ZipCodeRepository;
import com.processpuzzle.application.resource.domain.XmlDataLoader;
import com.processpuzzle.party.domain.AddressFactory;
import com.processpuzzle.party.domain.Company;
import com.processpuzzle.party.domain.EmailAddress;
import com.processpuzzle.party.domain.GeographicAddress;
import com.processpuzzle.party.domain.TelecomAddress;
import com.processpuzzle.party.domain.WebPageAddress;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;

public class CompanyDataLoader extends XmlDataLoader {

   private static final String BUILDING_NUMBER_POSTFIX = "_BuildingNumber";
   private static final String STREET_POSTFIX = "_Street";
   private static final String SETTLEMENT_POSTFIX = "_Settlement";
   private static final String ZIP_CODE_POSTFIX = "_ZipCode";
   private static final String LAND_POSTFIX = "_Land";
   private static final String USED_FOR_POSTFIX = "_UsedFor";
   private static final String GEOG_ADDRESS_ELEMENT_PREFIX = "address";
   private static final String NUMBER_POSTFIX = "_Number";
   private static final String AREA_CODE_POSTFIX = "_AreaCode";
   private static final String COUNTRY_CODE_POSTFIX = "_CountryCode";
   private static final String PHONE_ELEMENT_PREFIX = "phone";
   private static final String EMAIL_ADDRESS_ELEMENT_PREFIX = "emailAddress";
   private static final String ROOT_ELEMENT = "company";
   private static final String SHORT_NAME_ELEMENT = "shortName";
   private static final String LONG_NAME_ELEMENT = "longName";
   private static final String WEB_ADDRESS_ELEMENT = "webAddress";
   private CompanyDataSheetFactory companyDataSheetFactory;
   private AddressFactory addressFactory;
   private CountryFactory countryFactory;
   private SettlementFactory settlementFactory;
   private ZipCodeFactory zipCodeFactory;
   private CompanyDataSheetRepository artifactRepository;
   private List<CompanyDataSheet> savedCompanies = new ArrayList<CompanyDataSheet>();
   
   public CompanyDataLoader(String sourcePath, String schemePath ) {
      super(sourcePath, schemePath);
      
      resultInPersistentObjects = true;
   }
   
   public @Override @SuppressWarnings("unchecked") void loadData() {
      super.loadData();

      companyDataSheetFactory = applicationContext.getEntityFactory( CompanyDataSheetFactory.class );
      addressFactory = applicationContext.getEntityFactory( AddressFactory.class );
      countryFactory = applicationContext.getEntityFactory( CountryFactory.class );
      settlementFactory = applicationContext.getEntityFactory( SettlementFactory.class );
      zipCodeFactory = applicationContext.getEntityFactory( ZipCodeFactory.class );
      artifactRepository = applicationContext.getRepository(CompanyDataSheetRepository.class);

      Document doc = getDocument();
      Element root = doc.getRootElement();
      
      for (Iterator<Element> i = root.elementIterator(ROOT_ELEMENT); i.hasNext();) {
         DefaultUnitOfWork work = new DefaultUnitOfWork(true);
         Element companyElement = (Element) i.next();
            
         Element shortNameElement = companyElement.element( SHORT_NAME_ELEMENT );
         String shortName = shortNameElement.getText();
   
         try {        
            Element longNameElement = companyElement.element( LONG_NAME_ELEMENT );
            String longName = longNameElement.getText();
            
            CompanyDataSheet companyDataSheet = companyDataSheetFactory.create( longName );
            
            int idx = 1;
            try {
               
               while(true) {
                  // create geographic address
                  createGeographicAddress( work, companyElement, companyDataSheet, idx );

                  // create phone address
                  createPhoneAddress( companyElement, companyDataSheet, idx );
                                                      
                  // create email address
                  createEmailAddress( companyElement, companyDataSheet, idx );
                  
                  idx++;
               
               } //while
               
                
            } catch (NullPointerException e) {
               // nothing serious just while(true) loop ends
            }
            
            //create webAddress
            createWebAddress( companyElement, companyDataSheet);

            if ( companyDataSheet != null ) {
               saveCompanyDataSheet( work, companyDataSheet );
               logSuccess( companyDataSheet.getCompany() );
   
               savedCompanies.add( companyDataSheet );
            }
         } catch( Exception e) {
            logError( e, shortName);
         } finally {
            work.finish();
         }
         
      } //end for
   }

   private void createWebAddress( Element element, CompanyDataSheet companyDataSheet ) {
      Element webAddressElement = element.element( WEB_ADDRESS_ELEMENT );
      if ( webAddressElement != null ) {
         String webAddressStr = webAddressElement.getText();
         String webPageAddress = !"".equals( webAddressStr ) ? webAddressStr : null;
         
         if ( webPageAddress != null ) {
            WebPageAddress webAddr = addressFactory.createWebPageAddress( webPageAddress );
            if (webAddr != null) {
               companyDataSheet.addAddress( webAddr );
            }
         }
      }
   }

   private void createEmailAddress( Element element, CompanyDataSheet companyDataSheet, int idx ) {
      Element emailAddressElement = element.element( EMAIL_ADDRESS_ELEMENT_PREFIX + idx );
      if ( emailAddressElement != null ) {
         String emailStr = emailAddressElement.getText();
         String emailAddress = !"".equals( emailStr ) ? emailStr : null;
         if ( emailAddress != null ) {
            EmailAddress email = addressFactory.createEmailAddress( emailAddress );
            if (email != null) {
               if ( idx == 1 ) {
                  email.setIsDefault( true );
               }
               companyDataSheet.addAddress( email );
            }
         }
      }
   }

   private void createPhoneAddress( Element element, CompanyDataSheet companyDataSheet, int idx ) {
      String countryCode = null;
      String areaCode = null;
      String number = null;
      Element countryCodeElement = element.element( PHONE_ELEMENT_PREFIX + idx +COUNTRY_CODE_POSTFIX );
      if ( countryCodeElement != null ) {
         String countryCodeStr = countryCodeElement.getText();
         countryCode = !"".equals( countryCodeStr ) ? countryCodeStr : null;
      }
      
      Element areaCodeElement = element.element( PHONE_ELEMENT_PREFIX + idx +AREA_CODE_POSTFIX );
      if ( areaCodeElement != null ) {
         String areaCodeStr = areaCodeElement.getText();
         areaCode = !"".equals( areaCodeStr ) ? areaCodeStr : null;
      }
      
      Element numberElement = element.element( PHONE_ELEMENT_PREFIX + idx +NUMBER_POSTFIX );
      if ( numberElement != null ) {
         String phoneNumberStr = numberElement.getText();
         number = !"".equals( phoneNumberStr ) ? phoneNumberStr : null;
      }
      
      // create phone by factory
      if ( ! (countryCode == null && areaCode == null && number == null) ) {
         TelecomAddress phone = addressFactory.createTelecomAddress( countryCode, areaCode, number );
         if ( phone != null ) {
            if ( idx == 1 ) {
               phone.setIsDefault( true );
            }
            companyDataSheet.addAddress( phone );
         }
      }
   }

   private void createGeographicAddress( DefaultUnitOfWork work, Element element, CompanyDataSheet companyDataSheet, int idx ) {
      Element addressUsedForElement = element.element( GEOG_ADDRESS_ELEMENT_PREFIX + idx + USED_FOR_POSTFIX );
      String addressUsedFor = addressUsedForElement.getText();

      Element addressLandElement = element.element( GEOG_ADDRESS_ELEMENT_PREFIX + idx + LAND_POSTFIX );
      String land = addressLandElement.getText();
      String addressLand = !"".equals( land ) ? land : null;

      if( addressLand != null ){

         String addressZipCode = null;
         Element addressZipCodeElement = element.element( GEOG_ADDRESS_ELEMENT_PREFIX + idx +ZIP_CODE_POSTFIX );
         if ( addressZipCodeElement != null ) {
            String zipCodeStr = addressZipCodeElement.getText();
            addressZipCode = !"".equals( zipCodeStr ) ? zipCodeStr : null;
         }
         
         String addressSettlement = null;
         Element addressSettlementElement = element.element( GEOG_ADDRESS_ELEMENT_PREFIX + idx +SETTLEMENT_POSTFIX );
         if ( addressSettlementElement != null ) { 
            String settlementName = addressSettlementElement.getText();
            addressSettlement = !"".equals( settlementName ) ? settlementName : null;
         }
  
         Settlement settlement = null;
         if ( addressSettlement != null ) {
            settlement = findOrCreateSettlement( work, addressSettlement, addressLand);
         }

         ZipCode zipCode = null;
         if( addressZipCode != null && !"".equals( addressZipCode ) && settlement != null ){
            Integer zipCodeInteger = new Integer( addressZipCode );
            zipCode = findOrCreateZipcode( work, zipCodeInteger, settlement );
         }

         String addressStreet = null;
         Element addressStreetElement = element.element( GEOG_ADDRESS_ELEMENT_PREFIX + idx +STREET_POSTFIX );
         if ( addressStreetElement != null ) {
            String street = addressStreetElement.getText();
            addressStreet = !"".equals( street ) ? street : null;
         }
  
         String addressBuildingNumber = null;
         Element addressBuildingNumberElement = element.element( GEOG_ADDRESS_ELEMENT_PREFIX + idx +BUILDING_NUMBER_POSTFIX );
         if ( addressBuildingNumberElement != null ) {
            String buildingNumber = addressBuildingNumberElement.getText();
            addressBuildingNumber = !"".equals( buildingNumber ) ? buildingNumber : null;
         }
  
         if (! ( addressStreet == null && addressBuildingNumber == null &&
                 zipCode == null && settlement == null ) ) {
            GeographicAddress address = addressFactory.createGeographicAddress( addressStreet, addressBuildingNumber, zipCode,
                  settlement );
            if( address != null ){
               if ( idx == 1 ) {
                  address.setIsDefault( true );
               }
              if( addressUsedFor != null ){
                  address.setUsedFor( addressUsedFor );
               }
  
               companyDataSheet.addAddress( address );
            }
         }

      }
   }

   private ZipCode findOrCreateZipcode( DefaultUnitOfWork work, Integer zipCodeInteger, Settlement settlement ) {
      ZipCodeRepository zipCodeRepository = applicationContext.getRepository(ZipCodeRepository.class);
     
      ZipCode zipCode = zipCodeRepository.findByIdentityExpression( work, zipCodeInteger, settlement.getName());
      if ( zipCode == null ) {
         zipCode = zipCodeFactory.createZipCode( zipCodeInteger, settlement );
      } 
      return zipCode;
   }
   private Settlement findOrCreateSettlement( DefaultUnitOfWork work, String settlementName, String countryName ) {
      Settlement settlement = null;
      CountryRepository countryRepository = applicationContext.getRepository(CountryRepository.class);
      SettlementRepository settlementRepository = applicationContext.getRepository(SettlementRepository.class);
      
      Country country = countryRepository.findCountryByName( work, countryName );
      if ( country == null ) {
         country  = countryFactory.createCountry( countryName );
         countryRepository.add( work, country );
      }
      
      settlement = settlementRepository.findSettlementByNameAndCountryName( work, settlementName, countryName );
      
      if ( settlement == null ) {
         settlement = settlementFactory.createSettlement( settlementName, country );
         settlementRepository.add( work, settlement );
      }
      return settlement;
   }
   private void saveCompanyDataSheet( DefaultUnitOfWork work, CompanyDataSheet companyDataSheet ) {
//      log.debug("CampoanyDataLoader - companyDataSheet.getName(): " + companyDataSheet.getName());
      artifactRepository.add( work, companyDataSheet );
      
   }

   public List<CompanyDataSheet> getSavedCompanies() {
      return savedCompanies;
   }

   private void logSuccess( Company company ) {
//      log.debug("CompanyDataLoader - load success: " + company.getName() + " id: " + company.getId());
      
   }

   private void logError( Exception e, String name ) {
      log.error("CompanyDataLoader - FAILED to load: " + name);
      log.error("CompanyDataLoader - Error: " + e.getMessage());
      e.printStackTrace();
      
   }
   
}
