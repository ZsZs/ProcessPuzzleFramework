package com.processpuzzle.persistence.domain;

import hu.itkodex.commons.persistence.AggregateRoot;
import hu.itkodex.commons.persistence.Entity;
import hu.itkodex.commons.persistence.ValueObject;

import java.util.ArrayList;
import java.util.List;


public abstract class PersistentClassList {
//   protected List<Class<? extends AggregateRoot>> aggregateRoots = new ArrayList<Class<? extends AggregateRoot>>();
//   protected List<Class<? extends Entity>> entities = new ArrayList<Class<? extends Entity>>();
//   protected List<Class<? extends ValueObject>> valueObjects = new ArrayList<Class<? extends ValueObject>>();
   protected List<Class<? extends AggregateRoot>> aggregateRoots = new ArrayList<Class<? extends AggregateRoot>>();
   protected List<Class<? extends Entity>> entities = new ArrayList<Class<? extends Entity>>();
   protected List<Class<? extends ValueObject>> valueObjects = new ArrayList<Class<? extends ValueObject>>();

   public PersistentClassList() {
      defineAggregateRoots();
      defineEntities();
      defineValueObjects();
   }

//   public List<Class<? extends AggregateRoot>> getAggregateRoots() { return aggregateRoots; }
//   public List<Class<? extends Entity>> getEntities() { return entities; }
//   public List<Class<? extends ValueObject>> getValueObjects() { return valueObjects; }
   public List<Class<? extends AggregateRoot>> getAggregateRoots() { return aggregateRoots; }
   public List<Class<? extends Entity>> getEntities() { return entities; }
   public List<Class<? extends ValueObject>> getValueObjects() { return valueObjects; }

   protected abstract void defineAggregateRoots();
   protected abstract void defineEntities();
   protected abstract void defineValueObjects();
}
