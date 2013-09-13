/*
Name: 
    - OPDomainStrings 

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

package com.processpuzzle.util.domain;

public final class OPDomainStrings {
   
   public static final String ACTION_STATUS_PROPOSED = "Proposed";
   public static final String ACTION_STATUS_WAITING_FOR_RESOURCE = "Erőforrásra vár";

   public static final String ACTION_STATUS_IMPLEMENTABLE = "Implementable";

   public static final String ACTION_STATUS_IMPLEMENTED = "Implemented";
   public static final String ACTION_STATUS_COMPLETED = "Completed";
   public static final String ACTION_STATUS_UNDEREDIT = "UnderEdit";
   
   public static final String ACTION_EVENT_IMPLEMETATION = "Implementation";
   public static final String ACTION_EVENT_COMPLETITION = "Completition";
   public static final String ACTION_EVENT_ABANDONMENT = "Abandonment";
   public static final String ACTION_EVENT_SUSPENSIONMENT = "Suspensionment";
   
   public static final String ACTION_STATUS_LIFECYCLE_PROCESSINGPHASE = "ProcessingPhase";
   public static final String ACTION_STATUS_LIFECYCLE_SERVINGPHASE = "ServingPhase";
   public static final String ACTION_STATUS_LIFECYCLE_CLOSEUPPHASE = "CloseUpPhase";
   
   public static final String ACTION_EVENT_LIFECYCLE_PROCESSINGPHASE_STATUS_CHANGE = "ProcessingPhaseStatusChange";
   public static final String ACTION_EVENT_LIFECYCLE_SERVINGPHASE_STATUS_CHANGE = "ServingPhaseStatusChange";
   public static final String ACTION_EVENT_LIFECYCLE_CLOSEUPPHASE_STATUS_CHANGE = "CloseUpPhaseStatusChange";
   
   public static final String LOGGED_USER_ATTRIBUTE_NAME_IN_SESSION = "userSession";
   public static final String BACKUP_DIR_NAME = "BackUps";
}
