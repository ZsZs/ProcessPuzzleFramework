/*
 * Created on Jun 25, 2006
 */
package com.processpuzzle.party.partyrelationshiptype.domain;

import com.processpuzzle.party.domain.RuleSet;
import com.processpuzzle.party.partytype.domain.PartyType;
import com.processpuzzle.persistence.domain.GenericFactory;

/**
 * @author zsolt.zsuffa
 */
public class PartyRoleTypeFactory extends GenericFactory<PartyRoleType> {
   public static PartyRoleType create( String name ) {
      return PartyRoleTypeFactory.create( name, null, null );
   }
   
   public static PartyRoleType create( String name, String description ) {
      return PartyRoleTypeFactory.create( name, description, null );
   }

   public static PartyRoleType create( String name, String description, PartyType partyCanPlay ) {
      return PartyRoleTypeFactory.create( name, description, partyCanPlay, null );
   }
   
   public static PartyRoleType create( String name, String description, PartyType partyCanPlay, RuleSet ruleSet ) {
	      PartyRoleType roleType = new PartyRoleType( name, description );
          if( partyCanPlay != null ) roleType.addPlayerPartyType( partyCanPlay );

          PartyRoleTypeIdentity identity = (PartyRoleTypeIdentity) roleType.getDefaultIdentity();
          checkEntityIdentityCollition( identity );
	      return roleType;
   }
}
