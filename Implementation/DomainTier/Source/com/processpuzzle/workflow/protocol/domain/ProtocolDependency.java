package com.processpuzzle.workflow.protocol.domain;

import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

public class ProtocolDependency extends GenericEntity<ProtocolDependency> {
   private ProtocolCallAction dependentProtocol;
   private ProtocolCallAction consequentProtocol;
   private boolean strict;

   public ProtocolDependency(ProtocolCallAction dependent, ProtocolCallAction consequent, boolean strict) {
      this.dependentProtocol = dependent;
      this.consequentProtocol = consequent;
      this.strict = strict;
   }

   public ProtocolDependency(ProtocolCallAction dependent, ProtocolCallAction consequent) {
      this.dependentProtocol = dependent;
      this.consequentProtocol = consequent;
   }

   public ProtocolDependency() {}

   public ProtocolCallAction getConsequentProtocol() {
      return consequentProtocol;
   }

   public void setConsequentProtocol(ProtocolCallAction consequentProtocol) {
      this.consequentProtocol = consequentProtocol;
   }

   public ProtocolCallAction getDependentProtocol() {
      return dependentProtocol;
   }

   public void setDependentProtocol(ProtocolCallAction dependentProtocol) {
      this.dependentProtocol = dependentProtocol;
   }

   public boolean isStrict() {
      return strict;
   }

   public void setStrict(boolean strict) {
      this.strict = strict;
   }

   @Override
   protected void defineIdentityExpressions() {
      // TODO Auto-generated method stub
      
   }

   @Override
   public <I extends DefaultIdentityExpression<ProtocolDependency>> I getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }
}
