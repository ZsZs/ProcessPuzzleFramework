/*
 * Created on Jun 24, 2006
 */
package com.processpuzzle.party.partyrelationshiptype.domain;

import hu.itkodex.commons.persistence.PersistenceStrategy;
import hu.itkodex.commons.persistence.RepositoryResultSet;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
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
