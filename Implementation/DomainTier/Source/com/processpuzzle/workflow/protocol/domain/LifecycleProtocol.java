/*
 * Created on 2005.07.19.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.workflow.protocol.domain;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.xml.bind.annotation.XmlAttribute;

public class LifecycleProtocol extends CompositeProtocol {
   private @XmlAttribute String prefix;

   // Constructors
   LifecycleProtocol( String name ) {
      super( name );
   }

   protected LifecycleProtocol() {}

   // Public accessors
   public Set<LifecyclePhaseProtocol> getLifecyclePhases() {
      Set<LifecyclePhaseProtocol> phases = new HashSet<LifecyclePhaseProtocol>();
      for( Iterator<?> iter = getProtocols().iterator(); iter.hasNext(); ){
         Protocol pr = (Protocol) iter.next();
         if( pr instanceof LifecyclePhaseProtocol ){
            LifecyclePhaseProtocol lp = (LifecyclePhaseProtocol) pr;
            phases.add( lp );
         }
      }
      return phases;
   }
   
   // Public mutators
   public void addPhase( LifecyclePhaseProtocol newPhase ) {
      
   }

   // Properties
   public void setLifecyclePhases( Set<LifecyclePhaseProtocol> phases ) {
      for( LifecyclePhaseProtocol protocol : phases ){
         addStep( protocol );
      }
   }

   public String getPrefix() {
      return prefix;
   }

   public void setPrefix( String prefix ) {
      this.prefix = prefix;
   }

}
