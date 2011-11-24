/*
Name: 
    - ProcessPuzzleApplicationComponent 

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

package com.processpuzzle.application.configuration.domain;

public enum ProcessPuzzleApplicationComponent {
   BUSINESS_DEFINITION_LOADER( "BusinessDefinitionLoader", "businessDefinitionLoader" ),
   BUSINESS_IMPLEMENTATION_LOADER( "BusinessImplementationLoader", "businessImplementationLoader" ),
   SYSTEM_ARTIFACTS_LOADER( "SystemArtifactsLoader", "systemArtifactsLoader" ),
   PREDEFINED_USERS_LOADER( "PredefinedUsersLoader", "predefinedUsersLoader"),
   USER_REQUEST_FACTORY( "UserRequestContextFactory", "userRequestContextFactory" ),
   USER_SESSION_MANAGER( "UserSessionManager", "userSessionManager"),
   ORDER_IDENTIFIER_FACTORY( "OrderIdentiferFactory", "orderIdentiferFactory"),
   ORDER_LINE_IDENTIFIER_FACTORY( "OrderLineIdentiferFactory", "orderLineIdentiferFactory");
   
   ProcessPuzzleApplicationComponent( String componentName, String componentId ) {
      this.componentName = componentName;
      this.componentId = componentId;
   }
   
   public String getComponentName() { return componentName; }
   public String getComponentId() { return componentId; }
   
   private String componentName;
   private String componentId;
}
