package com.processpuzzle.address.domain;

import hu.itkodex.commons.persistence.PersistenceStrategy;
import hu.itkodex.commons.persistence.RepositoryResultSet;
import hu.itkodex.commons.persistence.query.BooleanOperator;
import hu.itkodex.commons.persistence.query.BooleanOperators;

import java.util.Set;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.domain.GenericRepository;
import com.processpuzzle.persistence.query.domain.ComparisonOperators;
import com.processpuzzle.persistence.query.domain.DefaultQuery;
import com.processpuzzle.persistence.query.domain.TextAttributeCondition;

public class SettlementRepository extends GenericRepository<Settlement> {

   public SettlementRepository( PersistenceStrategy strategy, ProcessPuzzleContext applicationContext ) {
      super( strategy, applicationContext );
   }

   public RepositoryResultSet<Settlement> findAllSettlement( DefaultUnitOfWork work ) {
      return findAll( work );
   }

   public Settlement findSettlementById( DefaultUnitOfWork work, Integer id ) {
      return findById( work, id );
   }

   public Settlement findSettlementByNameAndCountryName( String name, String countryName ) {
      Settlement settlement = null;
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      settlement = findSettlementByNameAndCountryName( work, name, countryName );
      work.finish();
      return settlement;
   }

   public Settlement findSettlementByNameAndCountryName( DefaultUnitOfWork work, String name, String countryName ) {
      // return (Settlement) get("from Settlement c where c.name = '"+name+"' and c.country.name = '"+countryName+"'");
      DefaultQuery query = new DefaultQuery( Settlement.class );
      query.getQueryCondition().addAttributeCondition( new TextAttributeCondition( "name", name, ComparisonOperators.EQUAL_TO ) );
      query.getQueryCondition().addAttributeCondition( new TextAttributeCondition( "country.name", countryName, ComparisonOperators.EQUAL_TO ) );
      query.getQueryCondition().addBooleanOperator( new BooleanOperator( BooleanOperators.AND ) );
      RepositoryResultSet<Settlement> results = findByQuery( work, query );
      if( results.size() == 1 ){
         return results.getEntityAt( 0 );
      }else{
         return null;
      }

   }

   public Settlement findSettlementByNameAndZipcode( DefaultUnitOfWork work, String name, Integer zipCode ) {
      DefaultQuery query = new DefaultQuery( Settlement.class );
      query.getQueryCondition().addAttributeCondition( new TextAttributeCondition( "name", name, ComparisonOperators.EQUAL_TO ) );
      RepositoryResultSet<Settlement> results = findByQuery( work, query );
      if( results.size() > 0 ){
         for( Settlement settlement : results ){
            Set<ZipCode> zipCodes = settlement.getZipCodes();
            for( ZipCode settlementZipCode : zipCodes ){
               if( settlementZipCode.getZipCode().intValue() == zipCode.intValue() ){
                  return settlement;
               }
            }
            // if not found try within the districts
            Set<District> districts = settlement.getDistricts();
            for( District district : districts ){
               Set<ZipCode> zipCodesOfDistrict = district.getZipCodes();
               for( ZipCode districtZipCode : zipCodesOfDistrict ){
                  if( districtZipCode.getZipCode().intValue() == zipCode.intValue() ){
                     return settlement;
                  }
               }
            }
         }
      }

      return null;

   }

   public void addSettlement( DefaultUnitOfWork work, Settlement settlement ) {
      add( work, settlement );
   }

   public void updateSettlement( DefaultUnitOfWork work, Settlement settlement ) {
      update( work, settlement );
   }

   public void deleteSettlement( DefaultUnitOfWork work, Settlement settlement ) {
      delete( work, settlement );
   }

   protected Object findByIdentityExpression( String identityExpression ) {
      return null;
   }

}
