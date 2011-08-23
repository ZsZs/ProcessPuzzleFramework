/*
 * Created on 2005.07.19.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.persistence.domain;

import hu.itkodex.commons.persistence.PersistenceProvider;
import hu.itkodex.commons.persistence.UnitOfWork;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class DefaultUnitOfWork implements UnitOfWork {
   private Set<PersistenceProvider> effectedPersistenceProviders = null;
   private boolean isStarted = false;

   // Constructors
   public DefaultUnitOfWork( boolean autoStart ) {
      if( autoStart )
         start();
   };

   // Public accessors
   public boolean isStarted() {
      return isStarted;
   }

   // Public mutators
   public void start() {
      if( !isStarted ){
         effectedPersistenceProviders = new HashSet<PersistenceProvider>();
         isStarted = true;
      }
   }

   public void finish() {
      if( isStarted ){
         for( Iterator<PersistenceProvider> iter = effectedPersistenceProviders.iterator(); iter.hasNext(); ){
            DefaultPersistenceProvider provider = (DefaultPersistenceProvider) iter.next();
            provider.closeSessionFor( this );
         }

         effectedPersistenceProviders.clear();
         effectedPersistenceProviders = null;
         isStarted = false;
      }
   }

   public void discard() {
      for( Iterator<PersistenceProvider> iter = effectedPersistenceProviders.iterator(); iter.hasNext(); ){
         DefaultPersistenceProvider provider = (DefaultPersistenceProvider) iter.next();
         provider.discardSessionFor( this );
      }

      effectedPersistenceProviders.clear();
      effectedPersistenceProviders = null;
      isStarted = false;
   }

   public void registerPersistenceProvicer( PersistenceProvider provider ) {
      effectedPersistenceProviders.add( provider );
   }
}
