package com.processpuzzle.party.artifact;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.processpuzzle.fundamental_types.domain.TimePoint;
import com.processpuzzle.party.domain.AddressFactory;
import com.processpuzzle.party.domain.EmailAddress;
import com.processpuzzle.party.domain.GeographicAddress;
import com.processpuzzle.party.domain.Person;
import com.processpuzzle.party.domain.TelecomAddress;
import com.processpuzzle.party.domain.WebPageAddress;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;

public class PersonDataLoader extends XmlDataLoader {

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
   private static final String WEB_ADDRESS_ELEMENT = "webAddress";
   private static final String BIRTHDAY_ELEMENT = "birthday";
   private static final String GIVEN_NAME_ELEMENT = "givenName";
   private static final String NAME_PREFIX_ELEMENT = "prefix";
   private static final String FAMILY_NAME_ELEMENT = "familyName";
   private static final String ROOT_ELEMENT = "person";
   private static final String DATE_FORMAT_PATTERN = "yyyy.mm.dd.";
   private PersonDataSheetFactory personDataSheetFactory;
   private AddressFactory addressFactory;
   private CountryFactory countryFactory;
   private SettlementFactory settlementFactory;
   private ZipCodeFactory zipCodeFactory;
   private PersonDataSheetRepository artifactRepository;
   private SimpleDateFormat formatter;
   private List<PersonDataSheet> savedPersons = new ArrayList<PersonDataSheet>();

   public PersonDataLoader( String sourcePath, String schemePath ) {
      super( sourcePath, schemePath );

      resultInPersistentObjects = true;

      formatter = (SimpleDateFormat) DateFormat.getDateInstance();
      formatter.applyPattern( DATE_FORMAT_PATTERN );
   }

   @SuppressWarnings( "unchecked" )
   @Override
   public void loadData() {
      super.loadData();

      personDataSheetFactory = applicationContext.getEntityFactory( PersonDataSheetFactory.class );
      addressFactory = applicationContext.getEntityFactory( AddressFactory.class );
      countryFactory = applicationContext.getEntityFactory( CountryFactory.class );
      settlementFactory = applicationContext.getEntityFactory( SettlementFactory.class );
      zipCodeFactory = applicationContext.getEntityFactory( ZipCodeFactory.class );
      artifactRepository = applicationContext.getRepository( PersonDataSheetRepository.class );

      Document doc = getDocument();
      Element root = doc.getRootElement();

      for( Iterator<Element> i = root.elementIterator( ROOT_ELEMENT ); i.hasNext(); ){
         DefaultUnitOfWork work = new DefaultUnitOfWork( true );
         Element personElement = (Element) i.next();

         Element familyNameElement = personElement.element( FAMILY_NAME_ELEMENT );
         String familyName = familyNameElement.getText();

         try{
            Element prefixElement = personElement.element( NAME_PREFIX_ELEMENT );
            String prefix = !prefixElement.getText().equals( "" ) ? prefixElement.getText() : null;
            
            Element givenNameElement = personElement.element( GIVEN_NAME_ELEMENT );
            String givenName = givenNameElement.getText();

            Element birthdayElement = personElement.element( BIRTHDAY_ELEMENT );
            String birthdayStr = birthdayElement.getText();

            TimePoint birthDateTP = null;
            if( birthdayStr != null ){
               Date birthDate = formatter.parse( birthdayStr );
               birthDateTP = new TimePoint( birthDate );
            }
            PersonDataSheet personDataSheet = personDataSheetFactory.create( familyName, givenName, birthDateTP );
            personDataSheet.setPrefix( prefix );

            int idx = 1;
            try{

               while( true ){
                  // create geographic address
                  createGeographicAddress( work, personElement, personDataSheet, idx );

                  // create phone address
                  createPhoneAddress( personElement, personDataSheet, idx );

                  // create email address
                  createEmailAddress( personElement, personDataSheet, idx );

                  idx++;

               }
            }catch( NullPointerException e ){
               // nothing serious just while(true) loop ends
            }
            // create webAddress
            createWebAddress( personElement, personDataSheet );

            savePersonDataSheet( work, personDataSheet );
            logSuccess( personDataSheet.getPerson() );
            savedPersons.add( personDataSheet );

         }catch( Exception e ){
            logError( e, familyName );
         } finally {
            work.finish();
         }

      }
   }

   private void createWebAddress( Element personElement, PersonDataSheet personDataSheet ) {
      Element webAddressElement = personElement.element( WEB_ADDRESS_ELEMENT );
      String webAddressStr = webAddressElement.getText();
      String webPageAddress = !"".equals( webAddressStr ) ? webAddressStr : null;

      if( webPageAddress != null ){
         WebPageAddress webAddr = addressFactory.createWebPageAddress( webPageAddress );
         if( webAddr != null ){
            personDataSheet.addAddress( webAddr );
         }
      }
   }

   private void createEmailAddress( Element personElement, PersonDataSheet personDataSheet, int idx ) {
      Element emailAddressElement = personElement.element( EMAIL_ADDRESS_ELEMENT_PREFIX + idx );
      if( emailAddressElement != null ){
         String emailStr = emailAddressElement.getText();
         String emailAddress = !"".equals( emailStr ) ? emailStr : null;
         if( emailAddress != null ){
            EmailAddress email = addressFactory.createEmailAddress( emailAddress );
            if( email != null ){
               if( idx == 1 ){
                  email.setIsDefault( true );
               }
               personDataSheet.addAddress( email );
            }
         }
      }
   }

   private void createPhoneAddress( Element personElement, PersonDataSheet personDataSheet, int idx ) {
      String countryCode = null;
      String areaCode = null;
      String number = null;
      Element countryCodeElement = personElement.element( PHONE_ELEMENT_PREFIX + idx + COUNTRY_CODE_POSTFIX );
      if( countryCodeElement != null ){
         String countryCodeStr = countryCodeElement.getText();
         countryCode = !"".equals( countryCodeStr ) ? countryCodeStr : null;
      }

      Element areaCodeElement = personElement.element( PHONE_ELEMENT_PREFIX + idx + AREA_CODE_POSTFIX );
      if( areaCodeElement != null ){
         String areaCodeStr = areaCodeElement.getText();
         areaCode = !"".equals( areaCodeStr ) ? areaCodeStr : null;
      }

      Element numberElement = personElement.element( PHONE_ELEMENT_PREFIX + idx + NUMBER_POSTFIX );
      if( numberElement != null ){
         String phoneNumberStr = numberElement.getText();
         number = !"".equals( phoneNumberStr ) ? phoneNumberStr : null;
      }

      // create phone by factory
      if( !(countryCode == null && areaCode == null && number == null) ){
         TelecomAddress phone = addressFactory.createTelecomAddress( countryCode, areaCode, number );
         if( phone != null ){
            if( idx == 1 ){
               phone.setIsDefault( true );
            }
            personDataSheet.addAddress( phone );
         }
      }
   }

   private void createGeographicAddress( DefaultUnitOfWork work, Element personElement, PersonDataSheet personDataSheet, int idx ) {
      Element addressUsedForElement = personElement.element( GEOG_ADDRESS_ELEMENT_PREFIX + idx + USED_FOR_POSTFIX );
      String addressUsedFor = addressUsedForElement.getText();

      Element addressLandElement = personElement.element( GEOG_ADDRESS_ELEMENT_PREFIX + idx + LAND_POSTFIX );
      String land = addressLandElement.getText();
      String addressLand = !"".equals( land ) ? land : null;

      if( addressLand != null ){

         String addressZipCode = null;
         Element addressZipCodeElement = personElement.element( GEOG_ADDRESS_ELEMENT_PREFIX + idx + ZIP_CODE_POSTFIX );
         if( addressZipCodeElement != null ){
            String zipCodeStr = addressZipCodeElement.getText();
            addressZipCode = !"".equals( zipCodeStr ) ? zipCodeStr : null;
         }

         String addressSettlement = null;
         Element addressSettlementElement = personElement.element( GEOG_ADDRESS_ELEMENT_PREFIX + idx + SETTLEMENT_POSTFIX );
         if( addressSettlementElement != null ){
            String settlementName = addressSettlementElement.getText();
            addressSettlement = !"".equals( settlementName ) ? settlementName : null;
         }

         Settlement settlement = null;
         if( addressSettlement != null ){
            settlement = findOrCreateSettlement( work, addressSettlement, addressLand );
         }

         ZipCode zipCode = null;
         if( addressZipCode != null && !"".equals( addressZipCode ) && settlement != null ){
            Integer zipCodeInteger = new Integer( addressZipCode );
            zipCode = findOrCreateZipcode( work, zipCodeInteger, settlement );
         }

         String addressStreet = null;
         Element addressStreetElement = personElement.element( GEOG_ADDRESS_ELEMENT_PREFIX + idx + STREET_POSTFIX );
         if( addressStreetElement != null ){
            String street = addressStreetElement.getText();
            addressStreet = !"".equals( street ) ? street : null;
         }

         String addressBuildingNumber = null;
         Element addressBuildingNumberElement = personElement.element( GEOG_ADDRESS_ELEMENT_PREFIX + idx + BUILDING_NUMBER_POSTFIX );
         if( addressBuildingNumberElement != null ){
            String buildingNumber = addressBuildingNumberElement.getText();
            addressBuildingNumber = !"".equals( buildingNumber ) ? buildingNumber : null;
         }

         if( !(addressStreet == null && addressBuildingNumber == null && zipCode == null && settlement == null) ){
            GeographicAddress address = addressFactory.createGeographicAddress( addressStreet, addressBuildingNumber, zipCode, settlement );
            if( address != null ){
               if( idx == 1 ){
                  address.setIsDefault( true );
               }
               if( addressUsedFor != null ){
                  address.setUsedFor( addressUsedFor );
               }

               personDataSheet.addAddress( address );
            }
         }

      }
   }

   public List<PersonDataSheet> getSavedPeople() {
      return savedPersons;
   }

   private ZipCode findOrCreateZipcode( DefaultUnitOfWork work, Integer zipCodeInteger, Settlement settlement ) {
      ZipCodeRepository zipCodeRepository = applicationContext.getRepository( ZipCodeRepository.class );

      ZipCode zipCode = zipCodeRepository.findByIdentityExpression( work, zipCodeInteger, settlement.getName() );
      if( zipCode == null ){
         zipCode = zipCodeFactory.createZipCode( zipCodeInteger, settlement );
      }
      return zipCode;
   }

   private Settlement findOrCreateSettlement( DefaultUnitOfWork work, String settlementName, String countryName ) {
      Settlement settlement = null;
      CountryRepository countryRepository = applicationContext.getRepository( CountryRepository.class );
      SettlementRepository settlementRepository = applicationContext.getRepository( SettlementRepository.class );

      Country country = findOrCreateCountry( work, countryName, countryRepository );

      settlement = settlementRepository.findSettlementByNameAndCountryName( work, settlementName, countryName );

      if( settlement == null ){
         settlement = settlementFactory.createSettlement( settlementName, country );
         settlementRepository.add( work, settlement );
      }
      return settlement;
   }

   private Country findOrCreateCountry( DefaultUnitOfWork work, String countryName, CountryRepository countryRepository ) {
      Country country = countryRepository.findCountryByName( work, countryName );
      if( country == null ){
         country = countryFactory.createCountry( countryName );
         countryRepository.add( work, country );
      }
      return country;
   }

   private void savePersonDataSheet( DefaultUnitOfWork work, PersonDataSheet personDataSheet ) {
      artifactRepository.add( work, personDataSheet );
      log.trace( "PersonDataSheet: '" + personDataSheet.getName() + "' saved." );
   }

   private void logSuccess( Person person ) {
      log.debug( "PersonDataLoader - load success: " + person.getPersonName().getName() + " id: " + person.getId() );
   }

   private void logError( Exception e, String name ) {
      log.error( "PersonDataLoader - FAILED to load: " + name, e );
      log.error( "PersonDataLoader - Error: " + e.getMessage(), e );
   }
}
