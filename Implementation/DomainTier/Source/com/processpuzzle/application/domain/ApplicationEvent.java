/*
Name: 
    - ApplicationEvent

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

package com.processpuzzle.application.domain;


import java.util.Date;

import com.processpuzzle.commons.persistence.AggregateRoot;
import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.fundamental_types.domain.TimePoint;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

public abstract class ApplicationEvent extends GenericEntity<ApplicationEvent> implements AggregateRoot {
   protected static Application.Events eventName = null;
   protected String eventDescription = "";
   protected TimePoint occuredOn;
   
   ApplicationEvent(String desc) {
      occuredOn = new TimePoint( new Date( System.currentTimeMillis() ));
      eventDescription=desc;
   }
   protected ApplicationEvent(){}
   
   public @Override @SuppressWarnings("unchecked") DefaultIdentityExpression getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }

//Public accessors
   public String toString() { return eventDescription; }

//Properties
   public Application.Events getName() { return eventName; }
   public TimePoint getOccuredOn() { return occuredOn; }
   
//Protected, private helper methods
   protected @Override void defineIdentityExpressions() {
      // TODO Auto-generated method stub      
   }
}
