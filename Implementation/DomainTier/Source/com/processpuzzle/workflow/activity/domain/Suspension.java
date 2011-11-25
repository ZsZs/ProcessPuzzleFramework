/*
Name: 
    - Suspension 

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

package com.processpuzzle.workflow.activity.domain;

import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.fundamental_types.domain.TimePeriod;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

public class Suspension extends GenericEntity<Suspension>{
   private ActionStatus originalState;
   private TimePeriod period;

   public Suspension( ActionStatus originalState, TimePeriod period ) {
      this.originalState = originalState;
      this.period = period;
   }

   public Suspension() {}

   public TimePeriod getPeriod() {
      return period;
   }

   public void setPeriod( TimePeriod period ) {
      this.period = period;
   }

   public ActionStatus getOriginalState() {
      return originalState;
   }

   public void setOriginalState( ActionStatus originalState ) {
      this.originalState = originalState;
   }

   @Override
   public <I extends DefaultIdentityExpression<Suspension>> I getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   protected void defineIdentityExpressions() {
      // TODO Auto-generated method stub
      
   }

}
