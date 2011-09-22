package com.processpuzzle.address.domain;

import com.processpuzzle.persistence.domain.EntityIdentityCollitionException;
import com.processpuzzle.persistence.domain.GenericFactory;

public class SettlementFactory extends GenericFactory<Settlement> {

   public Settlement createSettlement(String settlementName) {
      return new Settlement(settlementName);
   }

   public Settlement createSettlement(String settlementName, Country country) throws EntityIdentityCollitionException {
      Settlement settlement = new Settlement(settlementName);
      settlement.setCountry( country );
      try{
         country.addSettlement(settlement);
      }catch( AlreadyExistingSettlementInCountryException e ){
         e.printStackTrace();
      }
      return settlement;
      
/*  Settlements with the same name can exist within the country
 *     if (settlementRepository.findSettlementByNameAndCountryName( settlementName, country.getName() ) == null) {
         Settlement settlement = new Settlement(settlementName);
         settlement.setCountry( country );
         country.addSettlement(settlement);
         return settlement;
      } else {
          throw new EntityIdentityCollitionException(Settlement.class.getName(), settlementName, Settlement.class.getSimpleName());
      }*/
   }
   
   public District createDistrict( String districtName, Settlement settlement) throws EntityIdentityCollitionException  {
      District newDistrict = null;
      if ( settlement != null ) {
         for ( District district : settlement.getDistricts() ) {
            if ( district.getName().equals( districtName )) {
               throw new EntityIdentityCollitionException(District.class.getName(), districtName, District.class.getSimpleName());
            }
         }
         newDistrict = new District( districtName );
         settlement.addDistrict( newDistrict );
      }
      return newDistrict;
   }
   

   // @Override
   // public Settlement create(Object[] params) {
   // if (params.length>=2 && (params[0] instanceof String) && (params[1]
   // instanceof Country)){
   // return createSettlement((String)params[0],(Country)params[1]);
   // }else if (params.length>=1 && (params[0] instanceof String)){
   // return createSettlement((String)params[0]);
   // }else throw new
   // InvalidParameterListException(SettlementFactory.class,params,null);
   //   
   // }
}
