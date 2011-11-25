/*
Name: 
    - PartyRelationshipTypeFactory 

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
