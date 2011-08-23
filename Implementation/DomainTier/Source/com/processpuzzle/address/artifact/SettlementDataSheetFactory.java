package com.processpuzzle.address.artifact;

import com.processpuzzle.address.domain.Country;
import com.processpuzzle.address.domain.CountryRepository;
import com.processpuzzle.address.domain.Settlement;
import com.processpuzzle.address.domain.SettlementFactory;
import com.processpuzzle.artifact.domain.ArtifactFactory;

public class SettlementDataSheetFactory extends ArtifactFactory<SettlementDataSheet> {

   public SettlementDataSheetFactory() {
      super();
   }

/*   public SettlementDataSheet create( String settlementName ) throws EntityIdentityCollitionException {
      System.out.println( UserRequestManager.getInstance().currentUser().getUserName() );
      return this.create( settlementName, null );
   }*/

   public SettlementDataSheet create( String settlementName, String countryName ) {
      // create settlement
      Settlement settlement = null;
      
      SettlementFactory settlementFactory = applicationContext.getEntityFactory( SettlementFactory.class ); 
      if( countryName == null ) {
         settlement = settlementFactory.createSettlement( settlementName );         
      }else {
         CountryRepository countryRepository = applicationContext.getRepository( CountryRepository.class );
         Country country = countryRepository.findCountryByName( countryName );         
         settlement = settlementFactory.createSettlement( settlementName, country );
      }
      
      // create settlementdatasheet
      //ArtifactType dataSheetType = findTypeFor( SettlementDataSheet.class );
      SettlementDataSheet dataSheet = super.create( settlementName, settlement );
      return dataSheet;
   }
}
