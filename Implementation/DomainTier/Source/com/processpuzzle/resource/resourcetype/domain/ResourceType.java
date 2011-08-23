/*
 * Created on 2005.07.19.
 * 
 * TODO To change the template for this generated file go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.resource.resourcetype.domain;

import hu.itkodex.commons.persistence.AggregateRoot;

public interface ResourceType extends AggregateRoot, Comparable<ResourceType> {
   public String getName();

   public String getDescription();

   public void setDescription( String description );
}
