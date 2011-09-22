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
