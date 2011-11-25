/*
Name: 
    - SimpleProtocolDependency 

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
