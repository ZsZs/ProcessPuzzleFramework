/*
 * Created on 2005.07.19.
 */
package com.processpuzzle.workflow.protocol.domain;

import hu.itkodex.commons.persistence.PersistenceStrategy;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
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
