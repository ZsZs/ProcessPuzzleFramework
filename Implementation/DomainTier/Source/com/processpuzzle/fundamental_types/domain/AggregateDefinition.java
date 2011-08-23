package com.processpuzzle.fundamental_types.domain;

import java.util.ArrayList;
import java.util.List;

public abstract class AggregateDefinition {
   protected Class<?> factoryClass = null;
   protected Class<?> repositoryClass = null;
   protected Class<?> rootClass = null;
   protected List<Class<?>> managedEntities = new ArrayList<Class<?>>();
   protected List<Class<?>> managedValueObjects=new ArrayList<Class<?>>();
   protected List<Class<?>> referencedNeighbourRoots = new ArrayList<Class<?>>();
   protected List<Class<?>> anyOtherRelatedClasses = new ArrayList<Class<?>>();

   protected AggregateDefinition() {
      defineFactoryClass();
      defineRepositoryClass();
      defineRootClass();
      defineManagedEntities();
      defineReferencedNeighbourRoots();
      defineManagedValueObjects();
      defineAnyOtherRelatedClasses();
   }
//Mutators
   protected abstract void defineFactoryClass();
   protected abstract void defineRepositoryClass();
   protected abstract void defineRootClass();
   protected abstract void defineManagedEntities();
   protected abstract void defineReferencedNeighbourRoots();
   protected abstract void defineManagedValueObjects();
   protected abstract void defineAnyOtherRelatedClasses();
   
//Properties
   public Class<?> getFactoryClass(){ return factoryClass;}
   public Class<?> getRepositoryClass(){return repositoryClass;}
   public Class<?> getRootClass() { return rootClass; }
   public List<Class<?>> getManagedEntities() { return managedEntities; }
   public List<Class<?>> getManagedValueObjects(){return managedValueObjects;}
   public List<Class<?>> getReferencedNeighbourRoots() { return referencedNeighbourRoots; }
   public List<Class<?>> getAnyOtherRelatedClasses() { return anyOtherRelatedClasses; }
}
