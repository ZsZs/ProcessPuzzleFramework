/*
Name: 
    - PartyRoleTypeFactory 

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
