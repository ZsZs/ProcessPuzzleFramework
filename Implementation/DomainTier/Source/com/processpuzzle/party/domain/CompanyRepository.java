/*
Name: 
    - CompanyRepository 

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

package com.processpuzzle.party.domain;

import hu.itkodex.commons.persistence.PersistenceStrategy;
import hu.itkodex.commons.persistence.RepositoryResultSet;
import hu.itkodex.commons.persistence.UnitOfWork;
import hu.itkodex.commons.persistence.query.OrderSpecifier;
import hu.itkodex.commons.persistence.query.OrderingDirections;

import java.util.ArrayList;
import java.util.List;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.persistence.domain.GenericRepository;
import com.processpuzzle.persistence.query.domain.ComparisonOperators;
import com.processpuzzle.persistence.query.domain.DefaultQuery;
import com.processpuzzle.persistence.query.domain.TextAttributeCondition;

public class CompanyRepository extends GenericRepository<Company> {

   public CompanyRepository( PersistenceStrategy strategy, ProcessPuzzleContext applicationContext ) {
      super( strategy, applicationContext );
   }

   public void addCompany( UnitOfWork work, Company company ) {
      add( work, Company.class, company );
   }

   public void deleteCompany( UnitOfWork work, Company company ) {
      delete( work, Company.class, findCompanyById( work, company.getId() ) );
   }

   public void deleteCompany( UnitOfWork work, String pid ) {
      Company p = findCompanyById( work, pid );
      if( p != null ){
         deleteCompany( work, p );
      }
   }

   public void deleteCompany( UnitOfWork work, Integer id ) {
      Company p = findCompanyById( work, id );
      if( p != null ){
         deleteCompany( work, p );
      }
   }

   public void updateCompany( UnitOfWork work, Company company ) {
      update( work, Company.class, company );
   }

   public RepositoryResultSet<Company> findAllCompany( UnitOfWork work ) {
      return findAll( work, Company.class );
   }

   public Company findCompanyById( UnitOfWork work, Integer id ) {
      return (Company) findById( work, Company.class, id );
   }

   public Company findCompanyById( UnitOfWork work, String id ) {
      return findCompanyById( work, new Integer( id ) );
   }

   public List<Company> findCompaniesByName( UnitOfWork work, String organizationName ) {
      DefaultQuery query = new DefaultQuery( Company.class );
      query.getQueryCondition().addAttributeCondition(
            new TextAttributeCondition( "partyName.name", organizationName, ComparisonOperators.EQUAL_TO ) );
      RepositoryResultSet<Company> results = findByQuery( work, query );
      // List<Company> companies = new ArrayList<Company>();
      // for ( Party<?> party : results ) {
      // if ( party instanceof Company ) {
      // companies.add( (Company)party );
      // }
      // }
      return (List<Company>) results.getEntitiesAt( 0, results.size() );
   }

   public List<Company> findCompaniesByShortName( UnitOfWork work, String shortName ) {
      List<Company> companies = new ArrayList<Company>();
      DefaultQuery query = new DefaultQuery( Company.class );
      query.getQueryCondition().addAttributeCondition( new TextAttributeCondition( "shortName", shortName, ComparisonOperators.EQUAL_TO ) );
      RepositoryResultSet<Company> results = findByQuery( work, query );
      for( Party<?> party : results ){
         if( party instanceof Company ){
            Company company = (Company) party; // only contains party attributes, need to fill up
            company = findCompanyById( work, party.getId() );
            companies.add( company );
         }
      }
      return companies;
   }

   public RepositoryResultSet<Company> findByPartyTypeName( UnitOfWork work, String name ) {
      DefaultQuery query = new DefaultQuery( Company.class );
      query.getQueryCondition().addAttributeCondition( new TextAttributeCondition( "type.name", name, ComparisonOperators.EQUAL_TO ) );
      return findByQuery( work, query );
   }

   public RepositoryResultSet<Company> findAllOrderedCompany( UnitOfWork work, String propertyName ) {
      // HashMap<String, String> map = new HashMap<String, String>();
      // map.put(GenericRepository.ORDER, propertyName);
      // return find("from Company", map);
      DefaultQuery q = new DefaultQuery( Company.class );
      q.getQueryOrder().addOrderSpecifier( new OrderSpecifier( propertyName, OrderingDirections.Ascending ) );
      return findByQuery( work, q );
   }

}
