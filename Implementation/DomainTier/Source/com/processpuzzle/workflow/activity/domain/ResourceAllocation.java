/*
 * Created on 2005.07.19.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.workflow.activity.domain;

import hu.itkodex.commons.persistence.Entity;

import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.fundamental_types.quantity.domain.Quantity;
import com.processpuzzle.resource.resourcetype.domain.ResourceType;

public abstract class ResourceAllocation extends GenericEntity<ResourceAllocation> implements Entity {
   private String name;
   private ResourceType type;
   private Quantity quantity;

   public ResourceAllocation(ResourceType theType, Quantity theQuantity) {
      this.type = theType;
      this.quantity = theQuantity;
   }

   public ResourceAllocation() {
      super();
   }

   public ResourceType getType() {
      return type;
   }

   public void setType(ResourceType type) {
      this.type = type;
   }

   public Quantity getQuantity() {
      return quantity;
   }

   public void setQuantity(Quantity quantity) {
      this.quantity = quantity;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }
}
