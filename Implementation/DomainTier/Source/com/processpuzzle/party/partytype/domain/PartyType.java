/*
Name: 
    - PartyType 

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

package com.processpuzzle.party.partytype.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlType;

import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.fundamental_types.domain.HashCodeUtil;
import com.processpuzzle.persistence.query.domain.DefaultQueryContext;
import com.processpuzzle.resource.resourcetype.domain.AssetType;
import com.processpuzzle.resource.resourcetype.domain.ResourceType;

@XmlType( name="PartyType", propOrder={"name", "partyClassName", "description"} )
@XmlAccessorType( XmlAccessType.NONE )
public class PartyType extends GenericEntity<PartyType> implements AssetType {
   protected @XmlAttribute @XmlID String name;
   protected @XmlAttribute String description;
   protected @XmlAttribute String partyClassName;

   public PartyType( String theName ) {
      this( theName, null );
   }
   
   public PartyType( String theName, String description ) {
      this.name = theName;
      this.description = description;
   }

   public @Override int compareTo( ResourceType o ) {
      return 0;
   }
   public boolean equals( Object objectToCheck ) {
      if( !(objectToCheck instanceof PartyType) )
         return false;
      PartyType anotherPartyType = (PartyType) objectToCheck;
      return name.equals( anotherPartyType.name ) && id.equals( anotherPartyType.id );
   }

   public @Override @SuppressWarnings("unchecked") PartyTypeIdentity getDefaultIdentity() {
      DefaultQueryContext context = new DefaultQueryContext();
      context.addTextValueFor( "nameValue", name );
      return new PartyTypeIdentity( context );
   }

   public @Override String getDescription() { return description; }
   public @Override String getName() { return name; }

   
   public String getPartyClassName() {
      return partyClassName;
   }

   public int hashCode() {
      int result = HashCodeUtil.SEED;
      result = HashCodeUtil.hash( result, name );
      result = HashCodeUtil.hash( result, description );
      result = HashCodeUtil.hash( result, id );
   
      return result;
   }

   public @Override void setDescription( String description ) { this.description = description; }

   public void setPartyClassName( String partyClassName ) { this.partyClassName = partyClassName; }

   protected PartyType() {}

   protected @Override void defineIdentityExpressions() {
      identities.add( getDefaultIdentity() );
   }
}
