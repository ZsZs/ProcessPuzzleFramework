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
