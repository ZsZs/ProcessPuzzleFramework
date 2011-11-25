/*
Name: 
    - LifecycleProtocol 

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
