/*
Name: 
    - AbandonedAction 

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
package com.processpuzzle.workflow.activity.domain;

import java.util.Date;

import com.processpuzzle.fundamental_types.domain.TimePoint;

/**
 * @author zsolt.zsuffa
 */
public class AbandonedAction extends GenericAction {
   private Date effective;
   private String cause;
   private ProposedAction proposedAction;
   private ImplementedAction implementedAction;
   
   public AbandonedAction(Activity subAction) {
      super(subAction);      
   }
   
   protected AbandonedAction() {
      super();      
   }
   
   public String getCause() {return cause;}
   public void setCause(String cause) {this.cause = cause;}

   public ImplementedAction getImplementedAction() {return implementedAction;}
   public void setImplementedAction(ImplementedAction action) {implementedAction = action;}

   public ProposedAction getProposedAction() {return proposedAction;}
   public void setProposedAction(ProposedAction action) {proposedAction = action;}

   public Date getEffective() {return effective;}
   public void setEffective(Date effective) {this.effective = effective;}

   public TimePoint getProjectedBegin() { return proposedAction.getProjectedBegin();}
   public void setProjectedBegin(TimePoint projectedBegin) { proposedAction.setProjectedBegin(projectedBegin); }

   public TimePoint getProjectedEnd() { return proposedAction.getProjectedEnd(); }
   public void setProjectedEnd(TimePoint projectedEnd) { proposedAction.setProjectedEnd( projectedEnd ); }

   public TimePoint getRealBegin() { return implementedAction.getRealBegin(); }
   public void setRealBegin(TimePoint realBegin) { implementedAction.setRealBegin(realBegin); }

   public TimePoint getRealEnd() { return implementedAction.getRealEnd(); }
   public void setRealEnd(TimePoint realEnd) { implementedAction.setRealEnd( realEnd ); }
}
