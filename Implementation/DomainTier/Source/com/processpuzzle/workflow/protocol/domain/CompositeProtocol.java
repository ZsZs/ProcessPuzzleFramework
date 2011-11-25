/*
Name: 
    - CompositeProtocol 

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

import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

/**
 * @author zsolt.zsuffa
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 * @uml.annotations derived_abstraction="platform:/resource/ObjectPuzzle%20Pre-Implementation%20Models/Enterprise%20IT%20Design%20Model.emx#_Ud52gD5fEdq3NJ6sg2oavA"
 */
public abstract class CompositeProtocol extends Protocol {
   protected Set<ProtocolCallAction> steps = new HashSet<ProtocolCallAction>();

   public CompositeProtocol(String name) {
      super(name);
   }

   public CompositeProtocol() {
      super();
   }

   public void addStep(Protocol theProtocol) {
      ProtocolCallAction theProtocolReference = new ProtocolCallAction(theProtocol, this);
      steps.add(theProtocolReference);
      theProtocol.getCompositeProtocolsIncludedIn().add(theProtocolReference);
   }

   //Properties
   public Set<ProtocolCallAction> getSteps() { return steps; }
   public void setSteps(Set<ProtocolCallAction> steps) { this.steps = steps; }

   public Protocol getStep(String theName) {
      Protocol p = null;
      for (Iterator<ProtocolCallAction> iter = steps.iterator(); iter.hasNext();) {
         ProtocolCallAction c = iter.next();
         if (c.getReferedProtocol().getName().equals(theName)) {
            p = c.getReferedProtocol();
            break;
         }
      }
      return p;
   }

   public Set<Protocol> getProtocols() {
      Set<Protocol> protocols = new HashSet<Protocol>();
      for (Iterator<ProtocolCallAction> iter = steps.iterator(); iter.hasNext();) {
         ProtocolCallAction pRef = iter.next();
         protocols.add(pRef.getReferedProtocol());
      }
      return protocols;
   }

   public void setProtocols(Set<?> protocols) {
      Set<ProtocolCallAction> protocolRefs = new HashSet<ProtocolCallAction>();
      for (Iterator<?> iter = protocols.iterator(); iter.hasNext();) {
         Protocol theProtocol = (Protocol) iter.next();
         ProtocolCallAction theProtocolReference = new ProtocolCallAction(theProtocol, this);
         protocolRefs.add(theProtocolReference);
      }
      steps.addAll(protocolRefs);
   }

   public boolean hasOneStep() {
      return (getProtocols().size() > 0);
   }

   public Protocol getStepByName(String name) {
      Protocol match = null;
      if (hasOneStep()) {
         CompositeProtocol temp = this;
         for (Iterator<Protocol> iter = temp.getProtocols().iterator(); iter.hasNext();) {
            Protocol protocol = iter.next();
            if (protocol.getName().equals(name)) {
               match = protocol;
               break;
            } else {
               if (protocol instanceof CompositeProtocol) {
                  CompositeProtocol composite = (CompositeProtocol) protocol;
                  temp = composite;
                  match = temp.getStepByName(name);
                  if (match != null)
                     break;
               }
            }
         }

      }
      return match;
   }
}