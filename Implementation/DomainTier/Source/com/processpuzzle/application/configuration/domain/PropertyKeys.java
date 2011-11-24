/*
Name: 
    - PropertyKeys 

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

import java.text.MessageFormat;

public enum PropertyKeys {
   APPLICATION( "ac:application", "ac:application" ),
   APPLICATION_NAME( "ac:application.ac:applicationName", "ac:application/ac:applicationName" ),
   APPLICATION_VERSION( "ac:application.ac:version", "ac:application/ac:version" ),
   APPLICATION_SERVER_WORKING_FOLDER( "ac:application.ac:serverWorkingFolder", "ac:application/ac:serverWorkingFolder" ),
   APPLICATION_CLIENT_WORKING_FOLDER( "ac:application.ac:clientWorkingFolder", "ac:application/ac:clientWorkingFolder" ),
   APPLICATION_DEFAULT_USER_NAME ( "ac:application.ac:defaultUserAccount.ge:userName", "ac:application/ac:defaultUserAccount/ge:userName" ),
   APPLICATION_DEFAULT_USER_PASSWORD( "ac:application.ac:defaultUserAccount.ge:password", "ac:application/ac:defaultUserAccount/ge:password" ),
   APPLICATION_DATABASE_CREATION( "ac:application.ac:persistentDataInitializationStrategy", "ac:application/ac:persistentDataInitializationStrategy" ),
   APPLICATION_DATABASE_CREATION_FOR_OVERRIDE( "ac:application.ac:databaseCreation", "ac:application/ac:databaseCreation" ),
   APPLICATION_REPOSITORY_PATH( "", "application/repository/path" ),
   BUSINESS_DEFINITION( "bd:businessDefinitions.bd:businessDefinition", "bd:businessDefinitions/bd:businessDefinition" ),
   BUSINESS_DEFINITION_SCHEMA( "bd:businessDefinitions.ge:schema", "bd:businessDefinitions/ge:schema" ),
   BUSINESS_DEFINITION_MAPPING( "bd:businessDefinitions.ge:mapping", "bd:businessDefinitions/ge:mapping" ),
   BUSINESS_IMPLEMENTATION( "bi:businessImplementations.bi:businessImplementation", "bi:businessImplementations/bi:businessImplementation" ),
   BUSINESS_IMPLEMENTATION_SCHEMA( "bi:businessImplementations.ge:schema", "bi:businessImplementations/ge:schema" ),
   BUSINESS_IMPLEMENTATION_MAPPING( "bi:businessImplementations.ge:mapping", "bi:businessImplementations/ge:mapping" ),
   CLASS_REPOSITORY_MAPPING( "pr:persistence.pr:repositoryMapping.ge:class", "pr:persistence/pr:repositoryMapping/ge:class"),
   COMMAND_MAPPING( "fc:frontController.fc:commandMapping.ge:fullClassName", "fc:frontController/fc:commandMapping/ge:fullClassName"),
   PERSISTENCE_FACTORY_PACKAGESS( "pr:persistence.pr:factoryPackages.pr:factoryPackage[@name]", "" ),
   PERSISTENCE_STRATEGY_EVENT_HANDLERS( "pr:persistence.pr:persistenceStrategies.pr:persistenceStrategy[@name=''{0}''].pr:repositoryEventHandlers.pr:repositoryEventHandler.[@name]", "pr:persistence/pr:persistenceStrategies/pr:persistenceStrategy[@name=''{0}'']/pr:repositoryEventHandlers/pr:repositoryEventHandler/@name" ),
   PERSISTENCE_STRATEGY_EVENT_HANDLER_CLASS( "pr:persistence.pr:persistenceStrategies.pr:persistenceStrategy[@name=''{0}''].pr:repositoryEventHandlers.pr:repositoryEventHandler[@name=''{1}''].pr:eventHandlerClass", "" ),
   PERSISTENCE_STRATEGY_EVENT_HANDLER_PROPERTIES( "pr:persistence.pr:persistenceStrategies.pr:persistenceStrategy[@name=''{0}''].pr:repositoryEventHandlers.pr:repositoryEventHandler[@name=''{1}'']", "pr:persistence/pr:persistenceStrategies/pr:persistenceStrategy[@name=''{0}'']/pr:repositoryEventHandlers/pr:repositoryEventHandler[@name=''{1}'']" ),
   PERSISTENCE_STRATEGY( "pr:persistence.pr:persistenceStrategies.pr:persistenceStrategy({0})", "" ),
   PERSISTENCE_STRATEGY_PROPERTIES( "pr:persistence.pr:persistenceStrategies.pr:persistenceStrategy", "pr:persistence/pr:persistenceStrategies/pr:persistenceStrategy[@name=''{0}'']" ),
   PERSISTENCE_STRATEGY_NAME( "pr:persistence.pr:persistenceStrategies.pr:persistenceStrategy[@name]", "pr:persistence/pr:persistenceStrategies/pr:persistenceStrategy[@name]/@name" ),
   PERSISTENCE_STRATEGY_CLASS( "pr:persistence.pr:persistenceStrategies.pr:persistenceStrategy.pr:strategyClass", "pr:persistence/pr:persistenceStrategies/pr:persistenceStrategy/pr:strategyClass" ),
   PERSISTENCE_PERSISTENT_CLASSES( "pr:persistence.pr:persistentClasses.pr:persistentClass[@class]", "pr:persistence/pr:persistentClasses/pr:persistentClass[@class]/@class" ),
   PERSISTENCE_PERSISTENT_CLASSLIST_CLASS( "pr:persistence.pr:persistentClasses.pr:persistentClassList[@class]", "pr:persistence/pr:persistentClasses/pr:persistentClassList[@class]/@class" ),
   PERSISTENCE_REPOSITORY_CLASS( "pr:persistence.pr:repositories.pr:repository[@class]", "pr:persistence/pr:repositories/pr:repository[@class]/@class" ),
   PERSISTENCE_REPOSITORY_CLASS_STRATEGY( "pr:persistence.pr:repositories.pr:repository[@class=''{0}''].@strategy", "pr:persistence/pr:repositories/pr:repository[@class=''{0}'']/@strategy" ),
   PERSISTENCE_REPOSITORY_LIST_CLASS( "pr:persistence.pr:repositories.pr:repositoryList[@class]", "pr:persistence/pr:repositories/pr:repositoryList[@class]/@class" ),
   PERSISTENCE_REPOSITORY_LIST_STRATEGY( "pr:persistence.pr:repositories.pr:repositoryList[@strategy]", "pr:persistence/pr:repositories/pr:repositoryList[@class=''{0}'']/@strategy" ),
   PERSISTENCE_REPOSITORY_MAPPING_CLASS( "pr:persistence.pr:persistenceStrategies.pr:repositoryMapping[@class]", "pr:persistence/pr:persistenceStrategies/pr:repositoryMapping/class" ),
   PRESENTATION_DEFALT_SKIN_NAME( "wui:webUI.wui:desktop.wui:defaultSkin[@name]", "wui:webUI/wui:desktop/wui:defaultSkin/@name" ),
   PRESENTATION_DEFALT_SKIN_PATH( "wui:webUI.wui:desktop.wui:availableSkins.wui:skin[@name=''{0}''].relativePath", "wui:webUI/wui:desktop/wui:availableSkins/wui:skin[@name=''{0}'']/@relativePath" ),
   PRRODUCT_CATALOG_SCHEMA_PATH( "", "trading/productCatalogSchema" ),
   REPOSITORY_EVENT_HANDLER( "pr:persistence.pr:persistenceStrategies.pr:persistenceStrategy({0}).pr:repositoryEventHandlers.pr:repositoryEventHandler({1})", "" ),
   RUNTIME_ENVIRONMENT( "rt:runtime.rt:environment", "rt:runtime/rt:environment" ),
   EVENT_HANDLER_CLASS ( "pr:eventHandlerClass", "pr:eventHandlerClass" ),
   EMAIL_PROPERTIES( "em:emailing", "em:emailing" ),
   TRAIDING_PROPERTIES( "traiding", "traiding" ),
   DEFAULT_VENDOR( "traiding.defaultVendor", "traiding/defaultVendor" ),
   INTERNALIZATION( "in:internationalization", "in:internationalization" ),
   INTERNALIZATION_RESOURCE_BUNDLE( "in:internationalization.in:resouceBundles.in:resourceBundle", "in:internationalization/in:resourceBundles" ),
   INTERNALIZATION_DEFAULT_LOCALE( "in:internationalization.in:defaultLocale", "in:internationalization/in:defaultLocale" ),
   INTERNALIZATION_AVAILABLE_LOCALES( "in:internationalization.in:availableLocales.in:locale", "in:internationalization/in:availableLocales/in:locale" ),
   INTERNALIZATION_LOCALE_DEFINITIONS( "in:internationalization.in:localeDefinitions", "in:internationalization/in:localeDefinitions" ),
   DATA_LOADER_CLASSES( "dl:dataLoaders.dl:dataLoader.dl:dataLoaderClass", "dl:dataLoaders/dl:dataLoader/dl:dataLoaderClass"),
   DATA_LOADER_CONFIGURATION( "dl:dataLoaders.dl:dataLoader({0})", "dl:dataLoaders/dl:dataLoader[@dataLoaderClass=''{0}'']/dl:instantiationArguments"),
   BEAN_CONTAINER_DEFINITION_PATH( "bc:beanContainer.bc:containerDefinitionPath", "bc:beanContainer/bc:containerDefinitionPath");

   PropertyKeys( String defaultKey, String xPathKey ) { 
      this.defaultKey = defaultKey; 
      this.xPathKey = xPathKey; 
   }
   
   public static String createKey( String keyFormat, Object[] parameters ) {
      MessageFormat selector = new MessageFormat( keyFormat );      
      return selector.format( parameters );
   }
   
   public String getDefaultKey() { return this.defaultKey; }
   public String getXPathKey() { return this.xPathKey; }

   private String xPathKey;
   private String defaultKey;
}
