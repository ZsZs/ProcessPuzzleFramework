package com.processpuzzle.persistence.domain;

import hu.itkodex.commons.persistence.PersistentObject;
import hu.itkodex.commons.persistence.RepositoryResultSet;

import java.util.Iterator;
import java.util.List;


public class SimpleResultSet<T extends PersistentObject> implements RepositoryResultSet<T> {
   private List<T> results = null;

   public SimpleResultSet( List<T> results ) {
      this.results = results;
   }

   public List<T> getEntitiesAt( int start, int maxCount ) {
      // TODO Auto-generated method stub
      return null;
   }

   public T getEntityAt( int index ) {
      return results.get( index );
   }

   public Iterator<T> iterator() {
      return results.iterator();
   }

   public boolean isEmpty() {
      return results.isEmpty();
   }

   @SuppressWarnings("unchecked")
   @Override
   public T[] toArray() {
      return (T[]) results.toArray();
   }

   public int size() {
      return results.size();
   }
}
