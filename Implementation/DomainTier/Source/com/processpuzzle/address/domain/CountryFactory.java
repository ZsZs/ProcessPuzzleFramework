/*
Name: 
    - CountryFactory

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
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;

public class CountryFactory extends GenericFactory<Country> {

   public Country createCountry( String countryName ) {
      return createCountry( countryName, null );
   }

   public Country createCountry( String countryName, Settlement settlement ) {
      CountryRepository countryRepository = (CountryRepository) determineRepository( Country.class );

      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      if( countryRepository.findCountryByName( work, countryName ) == null ){
         work.finish();
         Country country = new Country( countryName );
         if( settlement != null )
            try{
               country.addSettlement( settlement );
            }catch( AlreadyExistingSettlementInCountryException e ){
               e.printStackTrace();
            }
         return country;
      }else{
         work.finish();
         throw new EntityIdentityCollitionException( Country.class.getName(), countryName, Country.class.getSimpleName() );
      }
   }
}
