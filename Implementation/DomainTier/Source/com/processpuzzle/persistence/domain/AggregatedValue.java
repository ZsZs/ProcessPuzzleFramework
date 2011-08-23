package com.processpuzzle.persistence.domain;

import hu.itkodex.commons.persistence.PersistentObject;
import hu.itkodex.commons.persistence.query.AggregateFunction;

public class AggregatedValue implements PersistentObject {
   private Double value = null;
   private AggregateFunction function = null;
   
   AggregatedValue( Double value, AggregateFunction function ) {
      this.value = value;
      this.function = function;
   }
   
   public Double getValue() { return value; }
   public AggregateFunction getFunction() { return function; }

   public Integer getId() { return null; }
}
