/*
Name: 
    - PartyRoleTypeRepository 

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

/*
 * Created on Jun 24, 2006
 */
package com.processpuzzle.party.partyrelationshiptype.domain;


import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.commons.persistence.PersistenceStrategy;
import com.processpuzzle.commons.persistence.RepositoryResultSet;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.domain.GenericRepository;
import com.processpuzzle.persistence.query.domain.ComparisonOperators;
import com.processpuzzle.persistence.query.domain.DefaultQuery;
import com.processpuzzle.persistence.query.domain.TextAttributeCondition;

/**
 * @author zsolt.zsuffa
 */
public class PartyRoleTypeRepository extends GenericRepository<PartyRoleType> {

   public PartyRoleTypeRepository( PersistenceStrategy strategy, ProcessPuzzleContext applicationContext) {
      super(strategy, applicationContext);
   }

   // PartyRoleType
   public Integer addPartyRoleType( DefaultUnitOfWork work, PartyRoleType thePartyRoleType ) {
      return add(work, thePartyRoleType);
   }

   public PartyRoleType findPartyRoleTypeById(DefaultUnitOfWork work,String id) {
      return findById(work, new Integer(id));
   }

   public RepositoryResultSet<PartyRoleType> findAllPartyRoleType(DefaultUnitOfWork work) {
      return findAll(work);
   }

   public PartyRoleType findPartyRoleTypeByName(DefaultUnitOfWork work,String name) {
//      HashMap<String, String> map = new HashMap<String, String>();
//      map.put(GenericRepository.WHERE, "p.name = '" + name + "'");
//      Collection c = find("from PartyRoleType p", map);
//      if ((c != null) && (!c.isEmpty())) {
//         return (PartyRoleType) c.iterator().next();
//      }
//      return null;
      PartyRoleTypeIdentity identity = new PartyRoleTypeIdentity();
      identity.getQueryContext().addTextValueFor("name", name );
      return findByIdentityExpression( work, identity );
   }
   
   public PartyRoleType findByName( String name ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      PartyRoleType partyRoleType = findByName( work, name );
      work.finish();
      return partyRoleType;      
   }

   public PartyRoleType findByName ( DefaultUnitOfWork work, String name ) {
      DefaultQuery query = new DefaultQuery( PartyRoleType.class );
      query.getQueryCondition().addAttributeCondition( new TextAttributeCondition("name", name, ComparisonOperators.EQUAL_TO ));
      RepositoryResultSet<PartyRoleType> results = super.findByQuery( work, query );
      if( results.size() == 1 ) return results.getEntityAt( 0 );
      else return null;
   }
   
   public void updatePartyRoleType(DefaultUnitOfWork work,PartyRoleType PartyRoleType) {
      update(work, PartyRoleType);
   }

   public void deletePartyRoleType(DefaultUnitOfWork work,String pid) {
      PartyRoleType p = findPartyRoleTypeById(work,pid);
      if (p != null) {
         deletePartyRoleType(work, p);
      }
   }

   public void deletePartyRoleType(DefaultUnitOfWork work,PartyRoleType partyRoleType) {
      delete(work, partyRoleType);
   }

   protected Object findByIdentityExpression(String identityExpression) {
      // TODO Auto-generated method stub
      return null;
   }
   
}
