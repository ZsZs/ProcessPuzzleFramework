/*
Name: 
    - ProtocolRepository 

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
 */
package com.processpuzzle.workflow.protocol.domain;


import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.commons.persistence.PersistenceStrategy;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.domain.GenericRepository;

public class ProtocolRepository extends GenericRepository<Protocol> {
   public ProtocolRepository( PersistenceStrategy strategy, ProcessPuzzleContext applicationContext ) {
      super( strategy, applicationContext );
   }

   public void addLifecycle( DefaultUnitOfWork work, LifecycleProtocol cycle ) {
      add( work, LifecycleProtocol.class, cycle );
   }

   public void deleteLifecycle( LifecycleProtocol lifecycle ) {}

   public Protocol findById( DefaultUnitOfWork work, String id ) {
      return (Protocol) findById( work, new Integer( id ) );
   }

   public Protocol findByName( DefaultUnitOfWork work, String protocolName ) {
      ProtocolIdentity identity = new ProtocolIdentity();
      identity.getQueryContext().addTextValueFor( "name", protocolName );
      return (Protocol) findByIdentityExpression( work, identity );
   }

   public LifecycleProtocol findLifecycleById( DefaultUnitOfWork work, String id ) {
      return (LifecycleProtocol) findById( work, LifecycleProtocol.class, new Integer( id ) );
   }

   public LifecycleProtocol findLifecycleByName( DefaultUnitOfWork work, String name ) {
      // return (LifecycleProtocol) get("from LifecycleProtocol p where p.name = ${name}", new Object[] { "name", name, });
      ProtocolIdentity identity = new ProtocolIdentity();
      identity.getQueryContext().addTextValueFor( "name", name );
      return (LifecycleProtocol) findByIdentityExpression( work, identity );
   }

   public void updateLifecycle( DefaultUnitOfWork work, LifecycleProtocol orderManagement ) {
      update( work, LifecycleProtocol.class, orderManagement );

   }

   protected Object findByIdentityExpression( String identityExpression ) {
      // TODO Auto-generated method stub
      return null;
   }
}
