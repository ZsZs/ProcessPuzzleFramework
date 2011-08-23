package com.processpuzzle.workflow.protocol.domain;

import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

public class SimpleProtocolDependency extends GenericEntity<SimpleProtocolDependency> {
   private Protocol dependentProtocol;
   private Protocol consequentProtocol;
   private boolean strict;

   public SimpleProtocolDependency(Protocol dependent, Protocol consequent, boolean strict) {
      this.dependentProtocol = dependent;
      this.consequentProtocol = consequent;
      this.strict = strict;
   }

   public SimpleProtocolDependency(Protocol dependent, Protocol consequent) {
      this.dependentProtocol = dependent;
      this.consequentProtocol = consequent;
   }

   public SimpleProtocolDependency() {}

   public Protocol getConsequentProtocol() {
      return consequentProtocol;
   }

   public void setConsequentProtocol(Protocol consequentProtocol) {
      this.consequentProtocol = consequentProtocol;
   }

   public Protocol getDependentProtocol() {
      return dependentProtocol;
   }

   public void setDependentProtocol(Protocol dependentProtocol) {
      this.dependentProtocol = dependentProtocol;
   }

   public boolean isStrict() {
      return strict;
   }

   public void setStrict(boolean strict) {
      this.strict = strict;
   }

   public Integer getId() {
      return id;
   }

   @Override
   protected void defineIdentityExpressions() {
      // TODO Auto-generated method stub
      
   }

   @Override
   public <I extends DefaultIdentityExpression<SimpleProtocolDependency>> I getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }

}
