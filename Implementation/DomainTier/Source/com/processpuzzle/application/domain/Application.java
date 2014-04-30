/*
Name: 
    - Application

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
 * Created on Feb 12, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.application.domain;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.HierarchicalConfiguration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import com.processpuzzle.application.configuration.domain.PersistentDataInitializationStrategies;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleApplicationComponent;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.configuration.domain.PropertyContext;
import com.processpuzzle.application.configuration.domain.PropertyContextOverrides;
import com.processpuzzle.application.configuration.domain.PropertyKeys;
import com.processpuzzle.application.resource.domain.DataLoader;
import com.processpuzzle.application.resource.domain.HardCodedDataLoader;
import com.processpuzzle.application.resource.domain.XmlDataLoader;
import com.processpuzzle.application.security.domain.PredefinedUsersLoader;
import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.application.security.domain.UserRepository;
import com.processpuzzle.commons.persistence.AggregateRoot;
import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.party.artifact.PredefinedUserDataSheetsCreator;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.query.domain.DefaultQueryContext;
import com.processpuzzle.user_session.domain.UserRequestManager;

public abstract class Application extends GenericEntity<Application> implements AggregateRoot {
   private static final String PARAMETER_TYPE_ELEMENT_NAME = "type";
   private static final String INSTANTIATION_ARGUMENTS_SELECTOR = "dl:instantiationArguments.dl:argument";
   private static Logger log = LoggerFactory.getLogger( Application.class );
   protected ResourceLoader resourceLoader;
   protected String applicationName;
   protected String applicationVersion;
   protected String applicationDescription;
   protected ExecutionStatus executionStatus = ExecutionStatus.stopped;
   protected InstallationStatus installationStatus = InstallationStatus.notInstalled;
   protected ProcessPuzzleContext applicationContext;
   protected String configurationDescriptorPath = "classpath:com/itcodex/objectpuzzle/framework/configuration/domaintier/configuration_descriptor.xml";
   protected Map<Application.Events, List<ApplicationEvent>> history = new LinkedHashMap<Events, List<ApplicationEvent>>();
   protected List<DataLoader> dataLoaders = new ArrayList<DataLoader>();

   // Constructors
   protected Application( String configurationDescriptorPath ) {
      this( configurationDescriptorPath, new DefaultResourceLoader() );
   }

   protected Application( String configurationDescriptorPath, ResourceLoader resourceLoader ) {
      this.configurationDescriptorPath = configurationDescriptorPath;
      this.resourceLoader = resourceLoader;
      defineApplicationDescription();
      determineInstallationConfiguration();
      determineInstallationStatus();
   }

   public @Override
   @SuppressWarnings( "unchecked" )
   ApplicationIdentity getDefaultIdentity() {
      DefaultQueryContext context = new DefaultQueryContext();
      context.addTextValueFor( "applicationNameValue", applicationName );
      context.addTextValueFor( "applicationVersionValue", applicationVersion );
      return new ApplicationIdentity( context );
   }

   // Public mutators
   public void install() throws ApplicationException {
      log.info( "Installation of application: " + applicationName + " begins." );

      if( installationStatus.equals( InstallationStatus.installed ) )
         throw new ReinstallationException( this );

      PropertyContextOverrides overrides = defineInstallationOverrides();
      try{
         setUpApplicationContext( Action.install, overrides );
         applicationContext.createSystemInstallerRequestContext( this );
         persistSystemInstallerUser();
         dataLoaders = determineDataLoaders();
         executeDataLoaders( Action.install );

         executionStatus = ExecutionStatus.running;
         installationStatus = InstallationStatus.installed;
         addEventToHistory( Events.installed );
      }catch( Exception e ){
         throw new ApplicationInstallationException( this, e );
      }

      log.info( "Installation of application: " + applicationName + " finished. \n" );
   }

   public void unInstall() {
      if( executionStatus == ExecutionStatus.running ) stop();
      
      log.info( "Uninstallation of application: " + applicationName + " begins." );

      executionStatus = ExecutionStatus.stopped;
      installationStatus = InstallationStatus.notInstalled;

      log.info( "Uninstallation of application: " + applicationName + " finished. \n" );
   }

   public void start() throws ApplicationException {
      start( null );
   }

   public void start( PropertyContextOverrides overrides ) throws ApplicationException {
      if( executionStatus == ExecutionStatus.running )
         return;
      log.info( "Starting of application: " + applicationName + " begins." );

      setUpApplicationContext( Action.start, overrides );
      executionStatus = ExecutionStatus.running;
      addEventToHistory( Events.started );

      log.info( "Starting of application: " + applicationName + " ended." );
   }

   public void stop() {
      if( executionStatus == ExecutionStatus.stopped )
         return;
      applicationContext.tearDown( Action.stop );
      applicationContext = null;
      executionStatus = ExecutionStatus.stopped;
      log.info( "Application stopped." );
   };

   public User authenticateUser( String userName, String password ) {
      UserRepository repository = (UserRepository) applicationContext.getRepository( UserRepository.class );
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      User userInQuestion = repository.findUserByName( work, userName );
      if( userInQuestion != null && userInQuestion.getPassword().equals( password ) )
         return userInQuestion;
      else
         return null;
   }

   public boolean loginUser( String userName, String password ) {
      User user = authenticateUser( userName, password );
      if( user != null )
         return true;
      else
         return false;
   }

   // Public accessors
   public List<ApplicationEvent> findApplicationEventsByType( Events searchedEvent ) {
      return history.get( searchedEvent );
   }

   @Override
   public boolean equals( Object objectToCheck ) {
      if( !(objectToCheck instanceof Application) )
         return false;
      Application anotherApplication = (Application) objectToCheck;

      boolean returnValue = applicationName.equals( anotherApplication.applicationName )
            && applicationVersion.equals( anotherApplication.applicationVersion )
            && executionStatus.equals( anotherApplication.executionStatus )
            && installationStatus.equals( anotherApplication.installationStatus )
            && configurationDescriptorPath.equals( anotherApplication.configurationDescriptorPath )
            && applicationDescription.equals( anotherApplication.applicationDescription )
            && dataLoaders.equals( anotherApplication.dataLoaders );

      return returnValue;
   }

   // Collection facades
   public int getHistorySize() {
      return history.size();
   }

   // Properties
   public String getApplicationName() { return applicationName; }
   public String getApplicationVersion() { return applicationVersion; }
   public String getConfigurationDescriptorPath() { return configurationDescriptorPath; }
   public ProcessPuzzleContext getContext() { return applicationContext; }
   public List<DataLoader> getDataLoaders() { return dataLoaders; }
   public String getDescription() { return applicationDescription; }
   public ExecutionStatus getExecutionStatus() { return executionStatus; }
   public InstallationStatus getInstallationStatus() { return installationStatus; }
   
   // Embeded enumerations
   public enum InstallationStatus { installed, notInstalled }
   public enum ExecutionStatus { stopped, running, suspended }
   public enum Events { started, stopped, installed, unistalled, dataload, backup, restore }
   public enum Action { install, uninstall, start, stop }

   // Protedted, private helper methods
   protected abstract void defineApplicationDescription();

   protected void runDataLoader( String property ) {
      String loaderClassName = (String) applicationContext.getProperty( property );
      if( loaderClassName != null ){
         try{
            DataLoader loader = (DataLoader) Class.forName( loaderClassName ).newInstance();
            loader.loadData();
         }catch( Exception e ){
            e.printStackTrace();
            throw new Error( "The data loader: " + loaderClassName + " doesn't exist!" );
         }
      }
   }

   protected void addEventToHistory( Events eventName ) {
      ApplicationEvent event = null;
      List<ApplicationEvent> eventList = history.get( eventName );
      if( eventList == null ) eventList = new ArrayList<ApplicationEvent>();
      
      switch( eventName ) {
         case started:
            event = new ApplicationStartedEvent();
            break;
         case stopped:
            event = new ApplicationStopped();
            break;
         case installed:
            event = new ApplicationInstallationEvent();
            break;
         case dataload:
            event = new DataLoadEvent();
            break;
         case backup:
            event = new BackupEvent();
            break;
         case restore:
            event = new RestoreEvent();
            break;
         default:
            break;
      }
      
      eventList.add( event );
      history.put( eventName, eventList );
   }

   protected @Override
   void defineIdentityExpressions() {
      identities.add( getDefaultIdentity() );
   }

   private void addBusinessDefintionLoader( List<DataLoader> dataLoaders ) {
      String path = null;
      try{
         path = determineBusinessDefintionsPaths();
         XmlDataLoader businessDefinitionLoader = (XmlDataLoader) applicationContext.getBean( ProcessPuzzleApplicationComponent.BUSINESS_DEFINITION_LOADER.getComponentId() );
         businessDefinitionLoader.setResourcePath( path );
         dataLoaders.add( businessDefinitionLoader );
      }catch( NoSuchBeanDefinitionException e ){
         if( path != null && path != "" )
            throw e;
      }
   }

   private void addPredefinedUsersDataSheetLoader( List<DataLoader> dataLoaders ) {
      PredefinedUserDataSheetsCreator userDataSheetCreator = new PredefinedUserDataSheetsCreator( applicationContext );
      dataLoaders.add( userDataSheetCreator );
   }

   private void addPredefinedUsersLoader( List<DataLoader> dataLoaders ) {
      PredefinedUsersLoader usersLoader = (PredefinedUsersLoader) applicationContext.getBean( ProcessPuzzleApplicationComponent.PREDEFINED_USERS_LOADER.getComponentId() );
      usersLoader.setApplicationContext( applicationContext );
      dataLoaders.add( usersLoader );
   }

   private void addSystemArtifactsLoader( List<DataLoader> dataLoaders ) {
      String componentName = ProcessPuzzleApplicationComponent.SYSTEM_ARTIFACTS_LOADER.getComponentId();
      try{
         HardCodedDataLoader systemArtifactsLoader = (HardCodedDataLoader) applicationContext.getBean( componentName );
         dataLoaders.add( systemArtifactsLoader );
      }catch( NoSuchBeanDefinitionException e ){
         log.warn( "SystemArtifactsLoder not found." );
         throw e;
      }
   }

   private void determineInstallationConfiguration() {}

   private void determineInstallationStatus() {}

   private PropertyContextOverrides defineInstallationOverrides() {
      PropertyContextOverrides overrides = new PropertyContextOverrides();
      overrides.addProperty( PropertyKeys.APPLICATION_DATABASE_CREATION, PersistentDataInitializationStrategies.dropAndCreate );
      return overrides;
   }

   private List<DataLoader> determineDataLoaders() throws ApplicationException {
      List<DataLoader> dataLoaders = new ArrayList<DataLoader>();

      determinePredefinedDataLoaders( dataLoaders );
      determineCustomDataLoaders( dataLoaders );

      return dataLoaders;
   }

   private void determinePredefinedDataLoaders( List<DataLoader> dataLoaders ) {
      addPredefinedUsersLoader( dataLoaders );
      addBusinessDefintionLoader( dataLoaders );
      addSystemArtifactsLoader( dataLoaders );
      addPredefinedUsersDataSheetLoader( dataLoaders );
   }

   private void determineCustomDataLoaders( List<DataLoader> dataLoaders ) throws ApplicationException {
      List<String> dataLoaderClassNames = applicationContext.getPropertyContext().getPropertyList( PropertyKeys.DATA_LOADER_CLASSES.getDefaultKey() );
      Integer dataLoaderIndex = 0;
      for( Iterator<String> iter = dataLoaderClassNames.iterator(); iter.hasNext(); ){
         String className = (String) iter.next();
         
         String selector = MessageFormat.format( PropertyKeys.DATA_LOADER_CONFIGURATION.getDefaultKey(), new Object[] { dataLoaderIndex.toString() } );
         HierarchicalConfiguration dataLoaderConfig = applicationContext.getPropertyContext().getConfigurationAt( selector );
         DataLoader dataLoader = instantitateDataLoader( className, dataLoaderConfig );
         dataLoaders.add( dataLoader );
         dataLoaderIndex++;
      }
   }

   private String determineBusinessDefintionsPaths() {
      PropertyContext propertyContext = applicationContext.getPropertyContext();
      List<String> businessDefinitionPaths = propertyContext.getPropertyList( PropertyKeys.BUSINESS_DEFINITION.getDefaultKey() );
      if( businessDefinitionPaths.size() == 0 ) {
         throw new MissingRequiredConfigurationPropertyException( 
               PropertyKeys.BUSINESS_DEFINITION.getDefaultKey(), configurationDescriptorPath );
      }
      
      String combinedPath = "";
      for( String path : businessDefinitionPaths ){
         combinedPath += path + ";";
      }
      
      return combinedPath;
   }

   private void executeDataLoaders( Action action ) throws ApplicationException {
      for( Iterator<DataLoader> iter = dataLoaders.iterator(); iter.hasNext(); ){
         DataLoader dataLoader = iter.next();
         if( (action == Action.install && dataLoader.isResultInPersistentObjects())
               || (action == Action.start && !dataLoader.isResultInPersistentObjects()) ){
            dataLoader.setResourceLoader( applicationContext.getResourceLoader() );
            try{
               dataLoader.loadData();               
            }catch( Exception e ){
               throw new ApplicationInstallationException( applicationName, e );
            }
            addEventToHistory( Events.dataload );
         }
      }
   }

   @SuppressWarnings( "unchecked" )
   private DataLoader instantitateDataLoader( String className, HierarchicalConfiguration dataLoaderConfig ) throws ApplicationException {
      DataLoader dataLoader = null;
      Constructor<DataLoader> dataLoaderConstructor;

      List<HierarchicalConfiguration> arguments = dataLoaderConfig.configurationsAt( INSTANTIATION_ARGUMENTS_SELECTOR );
      Class<?>[] parameterTypes = new Class[arguments.size()];
      Object[] parameterValues = new Object[arguments.size()];
      
      try{
         for( int j = 0; j < arguments.size(); j++ ){
            String selectorBase = INSTANTIATION_ARGUMENTS_SELECTOR + PropertyContext.PROPERTY_ARRAY_BEGIN + String.valueOf( j ) + PropertyContext.PROPERTY_ARRAY_END; 
            String typeSelector = selectorBase + PropertyContext.ATTRIBUTE_BEGIN + PropertyContext.ATTRIBUTE_SIGNER + PARAMETER_TYPE_ELEMENT_NAME + PropertyContext.ATTRIBUTE_END;
            String paremeterType = dataLoaderConfig.getString( typeSelector );
            parameterTypes[j] = Class.forName( paremeterType );
            
            //String nameSelector = selectorBase + PropertyContext.ATTRIBUTE_BEGIN + PropertyContext.ATTRIBUTE_SIGNER + PARAMETER_NAME_ELEMENT_NAME + PropertyContext.ATTRIBUTE_END;
            
            String parameterValue = dataLoaderConfig.getString( selectorBase );
            parameterValues[j] = parameterValue;
         }

         Class<DataLoader> dataLoaderClass = (Class<DataLoader>) Class.forName( className );
         dataLoaderConstructor = dataLoaderClass.getConstructor( parameterTypes );
         dataLoader = (DataLoader) dataLoaderConstructor.newInstance( parameterValues );
      }catch( SecurityException e ){
         throw new ApplicationStartException( this, e );
      }catch( NoSuchMethodException e ){
         throw new ApplicationStartException( this, e );
      }catch( IllegalArgumentException e ){
         throw new ApplicationStartException( this, e );
      }catch( InstantiationException e ){
         throw new ApplicationStartException( this, e );
      }catch( IllegalAccessException e ){
         throw new ApplicationStartException( this, e );
      }catch( InvocationTargetException e ){
         throw new ApplicationStartException( this, e );
      }catch( ClassNotFoundException e ){
         throw new ApplicationStartException( this, e );
      }

      return dataLoader;
   }

   private void persistSystemInstallerUser() {
      User systemInstaller = UserRequestManager.getInstance().currentUser();
      UserRepository userRepository = applicationContext.getRepository( UserRepository.class );
      userRepository.add( systemInstaller );
   }
   
   private void setUpApplicationContext( Action applicationAction, PropertyContextOverrides overrides ) throws ApplicationException {
      try{
         if( applicationContext == null )
            applicationContext = new ProcessPuzzleContext( this, configurationDescriptorPath );

         if( !applicationContext.isConfigured() ){
            if( overrides != null )
               applicationContext.setPropertyContextOverrides( overrides );

            applicationContext.setUp( applicationAction );
         }

         // applicationName = applicationContext.getProperty( PropertyKeys.APPLICATION_NAME.getXPathKey() );

      }catch( Exception e ){
         // applicationName = applicationContext.getProperty( PropertyKeys.APPLICATION_NAME.getXPathKey() );
         String message = "Starting " + applicationName + "application failed.";
         log.error( message, e );
         applicationContext = null;
         throw new ApplicationStartException( this, e );
      }
   }

}
