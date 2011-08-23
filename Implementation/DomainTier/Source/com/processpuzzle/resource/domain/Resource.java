package com.processpuzzle.resource.domain;

import hu.itkodex.commons.persistence.AggregateRoot;

import com.processpuzzle.resource.resourcetype.domain.ResourceType;

public interface Resource extends AggregateRoot {
   public String getName();
   public void renameName( String name );
   public ResourceType getType();
}
