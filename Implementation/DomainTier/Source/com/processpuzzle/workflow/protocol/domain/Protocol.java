/*
Name: 
    - Protocol 

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

import hu.itkodex.commons.persistence.AggregateRoot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;

import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

/**
 * @author zsolt.zsuffa
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 * @generated "UML to Java
 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
 * @uml.annotations derived_abstraction="platform:/resource/ObjectPuzzle%20Pre-Implementation%20Models/Enterprise%20IT%20Design%20Model.emx#_UbnXgD5fEdq3NJ6sg2oavA"
 */
public abstract class Protocol extends GenericEntity<Protocol> implements AggregateRoot{
   protected @XmlID @XmlIDREF @XmlAttribute(name = "name") String name;
   protected Set<ProtocolCallAction> compositeProtocolsIncludedIn = new HashSet<ProtocolCallAction>();
   protected ArtifactType inputArtifactType;
   protected ArtifactType outputArtifactType;
   protected boolean mandatory = true;
   
   //only for jaxb
   protected CompositeProtocol currentParent;

   protected Set<SimpleProtocolDependency> simpleDependentProtocols = new HashSet<SimpleProtocolDependency>();
   protected Set<SimpleProtocolDependency> simpleConsequentProtocols = new HashSet<SimpleProtocolDependency>();

   public Protocol() {}

   public Protocol(String name) {
      this.name = name;
   }

   public void addSimpleDependentProtocol(Protocol p) {
      SimpleProtocolDependency pDep = new SimpleProtocolDependency(p, this, p.isMandatory());
      p.getSimpleConsequentProtocols().add(pDep);
      simpleDependentProtocols.add(pDep);
   }

   public Set<Protocol> simpledependentProtocols() {
      Set<Protocol> depProtocols = new HashSet<Protocol>();
      for (Iterator<SimpleProtocolDependency> iter = simpleDependentProtocols.iterator(); iter.hasNext();) {
         SimpleProtocolDependency sDep = iter.next();
         depProtocols.add(sDep.getDependentProtocol());
      }
      return depProtocols;
   }

   public Set<Protocol> simpleconsequentProtocols() {
      Set<Protocol> conProtocols = new HashSet<Protocol>();
      for (Iterator<SimpleProtocolDependency> iter = simpleConsequentProtocols.iterator(); iter.hasNext();) {
         SimpleProtocolDependency sDep = iter.next();
         conProtocols.add(sDep.getConsequentProtocol());
      }
      return conProtocols;
   }

   public void addDependentProtocol(Protocol dependentProtocol, CompositeProtocol composite) {
      ProtocolCallAction conRef = null;
      ProtocolCallAction depRef = null;

      if (dependentProtocol.equals(this))
         throw new IllegalDependencyException("dependent and consequent are identical objects");
      try {
         conRef = findProtocolReference(composite, this);
         depRef = findProtocolReference(composite, dependentProtocol);
      } catch (IllegalDependencyException ex) {
         throw ex;
      }
      conRef.addDependentProtocol(depRef);
   }
   
   @XmlAttribute(name = "parent")
   @XmlIDREF
   public void setParent(CompositeProtocol parent) {
	  addCompositeProtocol(parent);
	  this.currentParent = parent;
   }
   
   @XmlAttribute(name = "dependent")
   @XmlIDREF
   public void setDependentProtocols(Collection<?> dependentProtocols) {
	   for (Iterator<?> iter = dependentProtocols.iterator(); iter.hasNext();) {
		Protocol protocol = (Protocol) iter.next();
		addDependentProtocol(protocol, currentParent);
	}
   }
   
   public Collection<?> getDependentProtocols() {
	   return new ArrayList<Object>();
   }

   public Set<?> getDependentProtocols(CompositeProtocol composite) {
      ProtocolCallAction pRef = findProtocolReference(composite, this);
      return pRef.dependentProtocols();
   }

   public void addConsequentProtocol(Protocol consequentProtocol, CompositeProtocol composite) {
      ProtocolCallAction conRef = null;
      ProtocolCallAction depRef = null;

      if (consequentProtocol.equals(this))
         throw new IllegalDependencyException("dependent and consequent are identical objects");

      try {
         conRef = findProtocolReference(composite, consequentProtocol);
         depRef = findProtocolReference(composite, this);
      } catch (IllegalDependencyException ex) {
         throw ex;
      }
      depRef.addConsequentProtocol(conRef);
   }

   public Set<?> getConsequentProtocols(CompositeProtocol composite) {
      ProtocolCallAction pRef = findProtocolReference(composite, this);
      return pRef.consequentProtocols();
   }

   public boolean isDependentOn(Protocol protocol, CompositeProtocol composite) {
      boolean isDepOn = false;
      for (Iterator<?> iter = getDependentProtocols(composite).iterator(); iter.hasNext();) {
         Protocol p = (Protocol) iter.next();
         if (protocol.getName().equals(p.getName())) {
            isDepOn = true;
         }
      }
      return isDepOn;
   }

   public void addCompositeProtocol(CompositeProtocol cp) {
      ProtocolCallAction reference = new ProtocolCallAction(this, cp);
      compositeProtocolsIncludedIn.add(reference);
      cp.getSteps().add(reference);
   }

   public Set<CompositeProtocol> getCompositeProtocols() {
      Set<CompositeProtocol> cps = new HashSet<CompositeProtocol>();
      for (Iterator<ProtocolCallAction> iter = compositeProtocolsIncludedIn.iterator(); iter.hasNext();) {
         ProtocolCallAction pRef = iter.next();
         cps.add(pRef.getParentProtocol());
      }
      return cps;
   }

   public void setCompositeProtocols(Set<?> cps) {
      for (Iterator<?> iter = cps.iterator(); iter.hasNext();) {
         CompositeProtocol cp = (CompositeProtocol) iter.next();
         ProtocolCallAction reference = new ProtocolCallAction(this, cp);
         compositeProtocolsIncludedIn.add(reference);
         cp.getSteps().add(reference);
      }
   }

   public String getName() { return name; }

   public void setName(String name) {
      this.name = name;
   }

   public boolean isMandatory() {
      return mandatory;
   }

   public void setMandatory(boolean mandatory) {
      this.mandatory = mandatory;
   }

   public Set<ProtocolCallAction> getCompositeProtocolsIncludedIn() {
      return compositeProtocolsIncludedIn;
   }

   public void setCompositeProtocolsIncludedIn(Set<ProtocolCallAction> compositeProtocolsIncludedIn) {
      this.compositeProtocolsIncludedIn = compositeProtocolsIncludedIn;
   }

   public ArtifactType getInputArtifactType() {
      return inputArtifactType;
   }

   public void setInputArtifact(ArtifactType inputArtifact) {
      this.inputArtifactType = inputArtifact;
   }

   public ArtifactType getOutputArtifactType() {
      return outputArtifactType;
   }

   public void setOutputArtifact(ArtifactType outputArtifact) {
      this.outputArtifactType = outputArtifact;
   }

   public Set<SimpleProtocolDependency> getSimpleConsequentProtocols() {
      return simpleConsequentProtocols;
   }

   public void setSimpleConsequentProtocols(Set<SimpleProtocolDependency> simpleConsequentProtocols) {
      this.simpleConsequentProtocols = simpleConsequentProtocols;
   }

   public Set<SimpleProtocolDependency> getSimpleDependentProtocols() {
      return simpleDependentProtocols;
   }

   public void setSimpleDependentProtocols(
			Set<SimpleProtocolDependency> simpleDependentProtocols) {
		this.simpleDependentProtocols = simpleDependentProtocols;
	}

	protected void defineIdentityExpressions() {
		defaultIdentity = new ProtocolIdentity();
		getIdentities().add(defaultIdentity);

	}
    
    private ProtocolCallAction findProtocolReference(CompositeProtocol cp, Protocol protocol) {
       ProtocolCallAction match = null;
       for (Iterator<ProtocolCallAction> iter = protocol.getCompositeProtocolsIncludedIn().iterator(); iter.hasNext();) {
          ProtocolCallAction pRef = iter.next();
          if (pRef.getParentProtocol().getName().equals(cp.getName())) {
             match = pRef;
             break;
          }
       }
       if (match == null)
          throw new IllegalDependencyException("dependent and consequent must share the same composite");
       else
          return match;
    }

   @Override
   public DefaultIdentityExpression getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }
}
