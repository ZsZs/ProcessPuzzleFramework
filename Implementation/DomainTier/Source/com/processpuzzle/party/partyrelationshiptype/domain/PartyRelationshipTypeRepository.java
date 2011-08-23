package com.processpuzzle.party.partyrelationshiptype.domain;

import hu.itkodex.commons.persistence.PersistenceStrategy;
import hu.itkodex.commons.persistence.RepositoryResultSet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.party.domain.Party;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.domain.GenericRepository;

public class PartyRelationshipTypeRepository extends GenericRepository<PartyRelationshipType> {

   public PartyRelationshipTypeRepository( PersistenceStrategy strategy, ProcessPuzzleContext applicationContext ) {
      super( strategy, applicationContext );
   }

   // PartyRelationshipType
   public Integer add( DefaultUnitOfWork work, PartyRelationshipType thePartyRelationshipType ) {
      return super.add( work, thePartyRelationshipType );
   }

   public PartyRelationshipType findById( DefaultUnitOfWork work, String id ) {
      return super.findById( work, new Integer( id ) );
   }

   public RepositoryResultSet<PartyRelationshipType> findAllPartyRelationshipType( DefaultUnitOfWork work ) {
      return findAll( work );
   }

   public PartyRelationshipType findByName( String name ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      PartyRelationshipType relationshipType = findByName( work, name );
      work.finish();
      return relationshipType;
   }

   public PartyRelationshipType findByName( DefaultUnitOfWork work, String name ) {
      PartyRelationshipTypeIdentity identity = new PartyRelationshipTypeIdentity();
      identity.getQueryContext().addTextValueFor( "nameValue", name );
      return findByIdentityExpression( work, identity );
   }

   public List<PartyRelationshipType> findPartyRelationshipTypeByValidTypeOfParty( DefaultUnitOfWork work, Party<?> ownParty, Party<?> party ) {
      List<PartyRelationshipType> validTypes = new ArrayList<PartyRelationshipType>();
      // List allTypes = (List) super.findByQuery("from PartyRelationshipType");
      RepositoryResultSet<PartyRelationshipType> allTypes = findAll( work );
      for( Iterator<PartyRelationshipType> iter = allTypes.iterator(); iter.hasNext(); ){
         PartyRelationshipType type = iter.next();
         Set<PartyRelationshipConstraint> constraints = type.getValidRolePairs();
         for( Iterator<PartyRelationshipConstraint> iterator = constraints.iterator(); iterator.hasNext(); ){
            PartyRelationshipConstraint constraint = (PartyRelationshipConstraint) iterator.next();
            PartyRoleType clientRoleType = constraint.getClientRoleType();
            PartyRoleType supplierRoleType = constraint.getSupplierRoleType();
            if( clientRoleType.canPlayRole( ownParty.getType() ) && supplierRoleType.canPlayRole( party.getType() )
                  || clientRoleType.canPlayRole( party.getType() ) && supplierRoleType.canPlayRole( ownParty.getType() ) ){
               validTypes.add( type );
            }
         }
      }
      return validTypes;
   }

   public void updatePartyRelationshipType( DefaultUnitOfWork work, PartyRelationshipType PartyRelationshipType ) {
      update( work, PartyRelationshipType );
   }

   public void deletePartyRelationshipType( DefaultUnitOfWork work, String pid ) {
      PartyRelationshipType p = findById( work, pid );
      if( p != null ){
         delete( work, p );
      }
   }

   public void deletePartyRelationshipType( DefaultUnitOfWork work, PartyRelationshipType relationshipType ) {
      delete( work, relationshipType );
   }

   protected Object findByIdentityExpression( String identityExpression ) {
      // TODO Auto-generated method stub
      return null;
   }

   // @Override
   protected void setSupportedAggregateRootClass() {
      supportedAggregateRootClass = PartyRelationshipType.class;
   }
}
