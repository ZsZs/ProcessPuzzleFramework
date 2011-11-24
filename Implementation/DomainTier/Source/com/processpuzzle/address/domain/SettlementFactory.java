/*
Name: 
    - SettlementFactory

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
