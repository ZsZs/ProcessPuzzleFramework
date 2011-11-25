/*
Name: 
    - CompletedAction 

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

import com.processpuzzle.fundamental_types.domain.TimePeriod;
import com.processpuzzle.fundamental_types.domain.TimePoint;
import com.processpuzzle.util.domain.OPDomainStrings;

public class CompletedAction extends GenericAction {

   private ImplementedAction implementedVersion;

   public CompletedAction(Activity parentSubAction) {
      super(parentSubAction);
      statusKeyName = OPDomainStrings.ACTION_STATUS_COMPLETED;
   }

   public CompletedAction() {
      super();
   }

   public void transferState(ImplementedAction from) {
      from.setCompletedAction(this);
      this.implementedVersion = from;
   }

   public ImplementedAction getImplementedVersion() {
      return implementedVersion;
   }

   public void setImplementedVersion(ImplementedAction implementedVersion) {
      this.implementedVersion = implementedVersion;
   }

   public TimePeriod getTimePeriod() {
      return implementedVersion.getTimePeriod();
   }

   public TimePoint getRealBegin() {
      return implementedVersion.getRealBegin();
   }

   public void setRealBegin(TimePoint realBegin) {
      implementedVersion.setRealBegin(realBegin);
   }

   public TimePoint getRealEnd() {
      return implementedVersion.getRealEnd();
   }

   public void setRealEnd(TimePoint realEnd) {
      implementedVersion.setRealEnd(realEnd);
   }

   public TimePoint getProjectedBegin() {
      return implementedVersion.getProjectedBegin();
   }

   public TimePoint getProjectedEnd() {
      return implementedVersion.getProjectedEnd();
   }

   public void setProjectedBegin(TimePoint projectedBegin) {
      implementedVersion.setProjectedBegin(projectedBegin);
   }

   public void setProjectedEnd(TimePoint projectedEnd) {
      implementedVersion.setProjectedEnd(projectedEnd);
   }
}
