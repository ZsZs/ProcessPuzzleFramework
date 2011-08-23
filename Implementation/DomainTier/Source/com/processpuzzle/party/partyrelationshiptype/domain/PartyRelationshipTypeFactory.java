/*
 * Created on Jun 25, 2006
 */
package com.processpuzzle.party.partyrelationshiptype.domain;

import com.processpuzzle.party.domain.RuleSet;
import com.processpuzzle.persistence.domain.GenericFactory;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

/**
 * @author zsolt.zsuffa
 */
public class PartyRelationshipTypeFactory extends GenericFactory<PartyRelationshipType> {
   
   public static PartyRelationshipType create( String name, PartyRoleType clientRoleType, PartyRoleType supplierRoleType ) {
      return PartyRelationshipTypeFactory.create( name, clientRoleType, supplierRoleType, null );
   }
   
   public static PartyRelationshipType create( String name, PartyRoleType clientRoleType, PartyRoleType supplierRoleType, RuleSet ruleSet ) {
      if( name == "" || clientRoleType == null || supplierRoleType == null ) return null;

      PartyRelationshipConstraint constraint = new PartyRelationshipConstraint( clientRoleType, supplierRoleType );
      PartyRelationshipType relationshipType = new PartyRelationshipType( name, constraint, ruleSet );
      
      DefaultIdentityExpression<PartyRelationshipType> identityExpression = relationshipType.getDefaultIdentity();
      checkEntityIdentityCollition( identityExpression );
      
//      PartyRelationshipTypeRepository repository = ProcessPuzzleContext.getInstance().getRepository( PartyRelationshipTypeRepository.class );
//      UnitOfWork work = new UnitOfWork( true );
//      if( repository.findByName( work, name ) == null ){
//         work.finish();
//         relationshipType = new PartyRelationshipType( name );
//         relationshipType.getValidRolePairs().add( constraint );
//      }else{
//         work.finish();
//         throw new EntityIdentityCollitionException( PartyRelationshipType.class.getName(), name, PartyRelationshipTypeFactory.class
//               .getSimpleName() );
//      }

      return relationshipType;
   }
}
