/*
 * Created on 2005.07.19.
 */
package com.processpuzzle.workflow.protocol.domain;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

public class ProtocolCallAction extends GenericEntity<ProtocolCallAction> {
   private Protocol referedProtocol;
   private CompositeProtocol parentProtocol;
   protected Set<ControlFlow> incommingControlFlows = new HashSet<ControlFlow>();
   protected Set<ControlFlow> outgoingControlFlows = new HashSet<ControlFlow>();
   protected Set<ObjectFlow> inputObjectFlows = new HashSet<ObjectFlow>();
   protected Set<ObjectFlow> outputObjectFlows = new HashSet<ObjectFlow>();

   protected Set<ProtocolDependency> dependentProtocols = new HashSet<ProtocolDependency>();
   protected Set<ProtocolDependency> consequentProtocols = new HashSet<ProtocolDependency>();

   // Constructors
   ProtocolCallAction(Protocol referredProtocol, CompositeProtocol parentProtocol) {
      this.referedProtocol = referredProtocol;
      this.parentProtocol = parentProtocol;
   }

   protected ProtocolCallAction() {}

   // Explicit destructor
   public void destroy() {
      referedProtocol = null;
      parentProtocol = null;
      incommingControlFlows.clear();
      outgoingControlFlows.clear();
      inputObjectFlows.clear();
      outputObjectFlows.clear();
   }

   // Getters and Setters
   public Protocol getReferedProtocol() {
      return referedProtocol;
   }

   public CompositeProtocol getParentProtocol() {
      return parentProtocol;
   }

   public Set<ControlFlow> getOutgoingControlFlows() {
      return outgoingControlFlows;
   }

   public Set<ControlFlow> getIncommingControlFlows() {
      return incommingControlFlows;
   }

   public Set<ObjectFlow> getInputObjectFlows() {
      return inputObjectFlows;
   }

   public Set<ObjectFlow> getOutputObjectFlows() {
      return outputObjectFlows;
   }

   public Set<ProtocolDependency> getConsequentProtocols() {
      return consequentProtocols;
   }

   public void setConsequentProtocols(Set<ProtocolDependency> consequentProtocols) {
      this.consequentProtocols = consequentProtocols;
   }

   public Set<ProtocolDependency> getDependentProtocols() {
      return dependentProtocols;
   }

   public void setDependentProtocols(Set<ProtocolDependency> dependentProtocols) {
      this.dependentProtocols = dependentProtocols;
   }

   public void addDependentProtocol(ProtocolCallAction pRef) {
      ProtocolDependency pDep = new ProtocolDependency(pRef, this, pRef.getReferedProtocol().isMandatory());
      dependentProtocols.add(pDep);
      pRef.getConsequentProtocols().add(pDep);
   }

   public void addConsequentProtocol(ProtocolCallAction pRef) {
      ProtocolDependency pDep = new ProtocolDependency(this, pRef, pRef.getReferedProtocol().isMandatory());
      consequentProtocols.add(pDep);
      pRef.getDependentProtocols().add(pDep);
   }

   public Set<?> dependentProtocols() {
      Set<Protocol> protocols = new HashSet<Protocol>();
      for (Iterator<ProtocolDependency> iter = dependentProtocols.iterator(); iter.hasNext();) {
         ProtocolDependency dependency = (ProtocolDependency) iter.next();
         protocols.add(dependency.getDependentProtocol().getReferedProtocol());
      }
      return protocols;
   }

   public Set<?> consequentProtocols() {
      Set<Protocol> protocols = new HashSet<Protocol>();
      for (Iterator<ProtocolDependency> iter = consequentProtocols.iterator(); iter.hasNext();) {
         ProtocolDependency dependency = (ProtocolDependency) iter.next();
         protocols.add(dependency.getConsequentProtocol().getReferedProtocol());
      }
      return protocols;
   }

   @Override
   protected void defineIdentityExpressions() {
      // TODO Auto-generated method stub
      
   }

   @Override
   public <I extends DefaultIdentityExpression<ProtocolCallAction>> I getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }

}
