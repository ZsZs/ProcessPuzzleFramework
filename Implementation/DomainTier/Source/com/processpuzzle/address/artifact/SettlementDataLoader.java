/*
Name: 
    - SettlementDataLoader

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

package com.processpuzzle.address.artifact;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;

import com.processpuzzle.address.domain.Country;
import com.processpuzzle.address.domain.CountryFactory;
import com.processpuzzle.address.domain.CountryRepository;
import com.processpuzzle.address.domain.District;
import com.processpuzzle.address.domain.Settlement;
import com.processpuzzle.address.domain.SettlementFactory;
import com.processpuzzle.address.domain.ZipCode;
import com.processpuzzle.address.domain.ZipCodeFactory;
import com.processpuzzle.application.resource.domain.XmlDataLoader;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;

public class SettlementDataLoader extends XmlDataLoader {
   private static final String DEFAULT_COUNTRY_NAME = "Magyarország";
   private static final String ROOT_ELEMENT = "country";
   private static final String COUNTRY_NAME_ATTRIBUTE = "name";
   private static final String SETTLEMENT_ELEMENT = "settlement";
   private static final String SETTLEMENT_NAME_ATTRIBUTE = "name";
   private static final String DISTRICT_ELEMENT = "district";
   private static final String DISTRICT_NAME_ATTRIBUTE = "name";
   private static final String ZIPCODE_ELEMENT = "zipcode";
   private static final String CODE_ATTRIBUTE = "code";
   private SettlementDataSheetRepository artifactRepository;
   private static CountryFactory countryFactory;
   private static SettlementFactory settlementFactory;
   private static SettlementDataSheetFactory settlementDataSheetFactory;
   private static ZipCodeFactory zipCodeFactory;
   private static SettlementDataSheetRepository settlementDataSheetRepository;
   private static CountryRepository countryRepository;
   private List<Settlement> savedSettlements = new ArrayList<Settlement>();

   public SettlementDataLoader( String sourcePath, String schemePath ) {
      super( sourcePath, schemePath );

      resultInPersistentObjects = true;

   }

   public List<Settlement> getSavedSettlements() {
      return savedSettlements;
   }

   @SuppressWarnings("unchecked")
   @Override
   public void loadData() {
      super.loadData();

      countryFactory = applicationContext.getEntityFactory( CountryFactory.class );
      countryRepository = applicationContext.getRepository( CountryRepository.class );
      settlementFactory = applicationContext.getEntityFactory( SettlementFactory.class );
      settlementDataSheetFactory = applicationContext.getEntityFactory( SettlementDataSheetFactory.class );
      settlementDataSheetRepository = applicationContext.getRepository( SettlementDataSheetRepository.class );
      zipCodeFactory = applicationContext.getEntityFactory( ZipCodeFactory.class );

      Document doc = getDocument();
      Element root = doc.getRootElement();

      for( Iterator<Element> i = root.elementIterator( ROOT_ELEMENT ); i.hasNext(); ){
         Element countryElement = i.next();

         Attribute countryNameAttribute = countryElement.attribute( COUNTRY_NAME_ATTRIBUTE );
         String countryName = countryNameAttribute.getValue();

         if( countryName != null && !"".equals( countryName ) ){

            Country country = createAndSaveCountry( countryName );

            for( Iterator<Element> j = countryElement.elementIterator( SETTLEMENT_ELEMENT ); j.hasNext(); ){
               Element settlementElement = j.next();

               String settlementName = settlementElement.attribute( SETTLEMENT_NAME_ATTRIBUTE ).getValue();
               if( settlementName != null && !"".equals( settlementName ) ){
                  DefaultUnitOfWork work = new DefaultUnitOfWork( true );
                  try{

                     SettlementDataSheet settlementDataSheet = createAndSaveSettlement( work, settlementName, country );
                     savedSettlements.add( settlementDataSheet.getSettlement() );

                     for( Iterator<Element> k = settlementElement.elementIterator( DISTRICT_ELEMENT ); k.hasNext(); ){
                        Element districtElement = k.next();

                        String districtName = districtElement.attribute( DISTRICT_NAME_ATTRIBUTE ).getValue();
                        District district = settlementFactory.createDistrict( districtName, settlementDataSheet.getSettlement() );

                        // process ZipCodes
                        for( Iterator<Element> l = districtElement.elementIterator( ZIPCODE_ELEMENT ); l.hasNext(); ){
                           Element zipCodeElement = l.next();
                           String code = zipCodeElement.attribute( CODE_ATTRIBUTE ).getValue();
                           ZipCode zipCode = zipCodeFactory.createZipCode( new Integer( code ), district );
                        }
                     }

                     // process ZipCodes
                     for( Iterator<Element> l = settlementElement.elementIterator( ZIPCODE_ELEMENT ); l.hasNext(); ){
                        Element zipCodeElement = l.next();
                        String code = zipCodeElement.attribute( CODE_ATTRIBUTE ).getValue();
                        ZipCode zipCode = zipCodeFactory.createZipCode( new Integer( code ), settlementDataSheet.getSettlement() );
                     }

                  }catch( Exception e ){
                     logError( e, settlementName );
                  }finally{
                     work.finish();
                  }
               }
            }
         }
      }
   }

   private SettlementDataSheet createAndSaveSettlement( DefaultUnitOfWork work, String settlementName, Country country ) {
      SettlementDataSheet settlementDataSheet = settlementDataSheetFactory.create( settlementName, country.getName() );
      settlementDataSheetRepository.add( work, settlementDataSheet );
      return settlementDataSheet;

   }

   private Country createAndSaveCountry( String countryName ) {
      Country country = countryFactory.createCountry( DEFAULT_COUNTRY_NAME );
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      countryRepository.add( work, country );
      work.finish();
      return country;

   }

   private void logSuccess( Settlement settlement ) {
      log.debug( "SettlementDataLoader - load success: " + settlement.getName() + " id: " + settlement.getId() );
   }

   private void logError( Exception e, String name ) {
      log.error( "SettlementDataLoader - FAILED to load: " + name );
      log.error( "SettlementDataLoader - Error: " + e.getMessage() );
   }

   private void processZipCode( Element element, Settlement settlement ) {
      for( Iterator<Element> l = element.elementIterator( ZIPCODE_ELEMENT ); l.hasNext(); ){

         Element zipCodeElement = l.next();
         String code = zipCodeElement.attribute( CODE_ATTRIBUTE ).getValue();

         ZipCode zipCode = zipCodeFactory.createZipCode( new Integer( code ), settlement );

      }
   }

   private void saveSettlementDataSheet( DefaultUnitOfWork work, SettlementDataSheet settlementDataSheet ) {
      artifactRepository.add( work, settlementDataSheet );
   }
}
