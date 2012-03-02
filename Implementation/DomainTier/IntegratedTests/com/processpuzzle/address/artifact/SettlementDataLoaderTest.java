package com.processpuzzle.address.artifact;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import hu.itkodex.litest.template.DataLoaderTestTemplate;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.address.artifact.SettlementDataLoader;
import com.processpuzzle.address.domain.Country;
import com.processpuzzle.address.domain.District;
import com.processpuzzle.address.domain.Settlement;
import com.processpuzzle.address.domain.SettlementRepository;
import com.processpuzzle.address.domain.ZipCode;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class SettlementDataLoaderTest extends DataLoaderTestTemplate<SettlementDataLoader> {
   private static final String settlementXMLSourcePath = "classpath:com/itcodex/objectpuzzle/address/settlement/artifact/TestSettlement.xml";
   private static final String settlementXMLSchemePath = "classpath:com/itcodex/objectpuzzle/address/settlement/artifact/Settlement.xsd";
   private static SettlementRepository settlementRepository;
   private static List<Settlement> savedSettlement = new ArrayList<Settlement>();

   public SettlementDataLoaderTest() {
      super( DomainTierTestConfiguration.APPLICATION_CONFIGURATION_DESCRIPTOR_PATH );

      settlementRepository = applicationContext.getRepository( SettlementRepository.class );

      dataLoader = new SettlementDataLoader( settlementXMLSourcePath, settlementXMLSchemePath );
      dataLoader.loadData();
   }

   @Ignore
   @Test
   public void loadData() throws Exception {
      savedSettlement = dataLoader.getSavedSettlements();

      for( Settlement savedSettl : savedSettlement ){

         DefaultUnitOfWork work = new DefaultUnitOfWork( true );
         Country country = savedSettl.getCountry();
         assertThat( databaseSpy.retrieveColumnFromRow( "T_SETTLEMENT", savedSettl.getId(), Integer.class, "COUNTRY_ID" ), equalTo( country.getId() ) );
         savedSettl = settlementRepository.findSettlementById( work, savedSettl.getId() );
         for( ZipCode zip : savedSettl.getZipCodes() ){
            assertThat( databaseSpy.retrieveColumnFromRow( "T_ZIP_CODE", zip.getId(), Integer.class, "SETTLEMENT_ID" ), equalTo( savedSettl.getId() ) );
         }
         for( District district : savedSettl.getDistricts() ){
            assertThat( databaseSpy.retrieveColumnFromRow( "T_DISTRICT", district.getId(), Integer.class, "SETTLEMENT_ID" ), equalTo( savedSettl.getId() ) );
            for( ZipCode zip : district.getZipCodes() ){
               assertThat( databaseSpy.retrieveColumnFromRow( "T_ZIP_CODE", zip.getId(), Integer.class, "DISTRICT_ID" ), equalTo( district.getId() ) );
            }
         }
         work.finish();
      }
   }
}
