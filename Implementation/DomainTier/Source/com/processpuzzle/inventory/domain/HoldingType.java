/*
 * Created on 2005.08.08.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.inventory.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;

import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;
import com.processpuzzle.resource.resourcetype.domain.ConsumableResourceType;
import com.processpuzzle.resource.resourcetype.domain.ResourceType;

public class HoldingType extends GenericEntity<HoldingType> implements ConsumableResourceType {
   protected @XmlAttribute @XmlID String name;
   protected @XmlAttribute String description;
//	private Holding holding;
//	private TransferRule rule;
    
    public HoldingType( String name, String description) {
		this.name = name ;
		this.description = description;
	}

   public @Override int compareTo( ResourceType o ) {
      // TODO Auto-generated method stub
      return 0;
   }

   public @Override <I extends DefaultIdentityExpression<HoldingType>> I getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }

   public @Override String getDescription() { return description; }
   public @Override String getName() { return name; }

   //Public mutators
   public @Override void setDescription( String description ) { this.description = description; }

   protected HoldingType() {
    	super();
    }

   @Override
   protected void defineIdentityExpressions() {
      // TODO Auto-generated method stub
   }
}