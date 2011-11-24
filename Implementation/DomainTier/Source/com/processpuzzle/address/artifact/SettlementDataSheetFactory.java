/*
Name: 
    - SettlementDataSheetFactory

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
