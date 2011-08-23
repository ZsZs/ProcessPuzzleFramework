package com.processpuzzle.persistence.domain;

import java.util.Collection;

public interface RepositoryServices {
   public Object add( Object object );

   public void update( Object object );

   public void delete( Object object );

   public Object findById( Integer id );

   public Collection<?> findAll();

   public Collection<?> findByQuery( String query, Object param[] );
}
