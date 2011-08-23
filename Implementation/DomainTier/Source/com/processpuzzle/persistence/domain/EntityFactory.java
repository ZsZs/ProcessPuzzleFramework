package com.processpuzzle.persistence.domain;

import hu.itkodex.commons.persistence.Entity;

public interface EntityFactory<E extends Entity> {
   public Class<? extends Entity> getTargetEntityClass();
}
