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
