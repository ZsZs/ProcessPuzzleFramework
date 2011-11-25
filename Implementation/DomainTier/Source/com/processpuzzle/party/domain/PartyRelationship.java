/*
Name: 
    - PartyRelationship 

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
 * Created on 2005.09.22.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.party.domain;

import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRelationshipType;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

public class PartyRelationship extends GenericEntity<PartyRelationship> {
   protected PartyRelationshipType relationshipType;
   protected String name;
   protected String description;


   public PartyRelationship() {
      super();
   }

   public PartyRelationship(String name, PartyRole client, PartyRole supplier, PartyRelationshipType type) {
      if (type.canFormRelationship(client, supplier)) {
         this.name = name;
         this.relationshipType = type;
      }
   }

   public PartyRelationship(String name) {
      super();
      // TODO Auto-generated constructor stub
      this.name = name;
   }

   public String getDescription() {
      return description;
   }

   public String getName() {
      return name;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Integer getId() {
      return id;
   }

   public PartyRelationshipType getRelationshipType() {
      return relationshipType;
   }

   public void setRelationshipType(PartyRelationshipType relationshipType) {
      this.relationshipType = relationshipType;
   }
   
   public @Override <I extends DefaultIdentityExpression<PartyRelationship>> I getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   protected void defineIdentityExpressions() {
      // TODO Auto-generated method stub
      
   }

}
