/*
Name: 
    - DefaultUnitOfWork 

Description: 
    -  

Requires:
    - 
    
Provides:
    - 

Part of: ProcessPuzzle Framework, Domain and Business Model Ready Architecture. Provides content, workflow and social networking functionality. 
http://www.processpuzzle.com

ProcessPuzzle - Content and Workflow Management Integration Business Platform

Author(s): 
    - Zsolt Zsuffa

Copyright: (C) 2011 This program is free software: you can redistribute it and/or modify it under the terms of the 
GNU General Public License as published by the Free Software Foundation, either version 3 of the License, 
or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

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
