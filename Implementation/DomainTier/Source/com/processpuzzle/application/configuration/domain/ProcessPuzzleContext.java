package com.processpuzzle.application.configuration.domain;

import hu.itkodex.commons.persistence.Entity;
import hu.itkodex.commons.persistence.PersistenceStrategy;
import hu.itkodex.commons.persistence.Repository;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.google.common.collect.Lists;
import com.processpuzzle.application.domain.Application;
import com.processpuzzle.application.security.domain.SystemInstallerUser;
import com.processpuzzle.fundamental_types.domain.AssertionException;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleEnumeration;
import com.processpuzzle.internalization.domain.ProcessPuzzleLocale;
import com.processpuzzle.persistence.domain.EntityFactory;
import com.processpuzzle.user_session.domain.DefaultUserSession;
import com.processpuzzle.user_session.domain.StaticUserSessionHolder;
import com.processpuzzle.user_session.domain.UserRequestManager;
import com.processpuzzle.user_session.domain.UserSession;
import com.processpuzzle.user_session.domain.UserSessionHolder;
import com.processpuzzle.user_session.domain.UserSessionManager;

public class ProcessPuzzleContext extends PersistentApplicationContext implements ApplicationContext {
   public static final String UPLOADED_FILES_FOLDER = "uploaded.files.folder";
   protected static ResourceLoader resourceLoader = null;
   protected static ProcessPuzzleContext soleInstance = null;
   private static PropertyContext propertyContext = null;
   private static PersistenceContext persistenceContext = null;
   private static MeasurementContext measurementContext = null;
   private static BeanContainer beanContainer = null;
   private static UserSessionManager userSessionManager = null;
   private static InternalizationContext i18Context = null;
   private String configurationDescriptorPath = null;
   private static Logger log = LoggerFactory.getLogger( ProcessPuzzleContext.class );
   private PropertyContextOverrides propertyContextOverrides = null;

   public ProcessPuzzleContext( Application application, String descriptorPath ) {
      super( application );
      this.configurationDescriptorPath = descriptorPath;
   }

   public void createSystemInstallerRequestContext( Application application ) {
      UserRequestManager requestManager = UserRequestManager.getInstance();
      UserSessionHolder sessionHolder = new StaticUserSessionHolder();
      SystemInstallerUser user = new SystemInstallerUser();
      UserSession userSession = new DefaultUserSession( user, application );
      sessionHolder.setSession( userSession );
      requestManager.createSession( sessionHolder, user, application );
      requestManager.createRequestContext( sessionHolder );
   }

   public Object getBean( String beanName ) { return beanContainer.getBean(beanName); }

   public BeanContainer getBeanContainer() { return beanContainer; }

   public String getConfigurationDescriptorPath() { return configurationDescriptorPath; }

   //Properties
   public String getConfigurationFileName() { return configurationDescriptorPath;}

   public ProcessPuzzleLocale getDefaultLocale() {
      String localeSpecifier = getProperty( PropertyKeys.INTERNALIZATION_DEFAULT_LOCALE.getDefaultKey() );
      return i18Context.findLocaleBySpecifier( localeSpecifier );
   }

   public Properties getEmailProperties() {
      Properties mailSettings = new Properties();
      Map<?, ?> properties = getProperties( PropertyKeys.EMAIL_PROPERTIES.getDefaultKey() );
      for (Iterator<?> iter = properties.keySet().iterator(); iter.hasNext();) {
         String element = (String) iter.next();
         if (element.startsWith("mail."))
            mailSettings.put(element, soleInstance.getProperty(element));
      }
      return mailSettings;
   }
   
   public String getDefaultVendorName() {
      String defaultVendorName = getProperty( PropertyKeys.DEFAULT_VENDOR.getDefaultKey() );
      return defaultVendorName;
     
   }

   public <F extends EntityFactory<?>> F getEntityFactory( Class<F> factoryClass ) { return beanContainer.getEntityFactory( factoryClass ); }
   
   public EntityFactory<?> getEntityFactoryByEntityClass( Class<? extends Entity> entityClass ) { 
      List<String> targetPackageNames = propertyContext.getPropertyList( PropertyKeys.PERSISTENCE_FACTORY_PACKAGESS.getDefaultKey() );
      if( targetPackageNames.size() == 0 ){
         targetPackageNames = Lists.newArrayList();
         targetPackageNames.add( "com.processpuzzle" );
         targetPackageNames.add( "com.itkodex" );
      }
      return beanContainer.getEntityFactoryByEntityClass( targetPackageNames, entityClass ); 
   }

   public static ProcessPuzzleContext getInstance() {
      return UserRequestManager.getInstance().getApplicationContext();
   }
   
public InternalizationContext getInternalizationContext() { return i18Context; }

   public MeasurementContext getMeasurementContext() { return measurementContext; }

   //Properties
   public String getProperty( String propertyName ) {
      return (String) propertyContext.getProperty( propertyName );
   }

   public void addProperty( String nodeSelector, String name, String value ) {
      propertyContext.addProperty( nodeSelector, name, value );
   }

   public Properties getProperties( String key ) { return propertyContext.getProperties( key ); }

   public static PersistenceStrategy getStrategy(String strategyName) { return persistenceContext.getStrategy(strategyName);}

   public String getText( String key ) {
      if( i18Context != null ) return i18Context.getText( key );
      else return null;
   }

   public String getText( String key, String localeSpecifier ) {
      ProcessPuzzleLocale locale = i18Context.findLocaleBySpecifier(localeSpecifier);
      return getText( key, locale );
   }

   public String getText( String key, ProcessPuzzleLocale locale) {
      if( locale == null ) throw new AssertionException( "Configuration.getText() can't be called by null locale." );
 
      if( i18Context != null ) return i18Context.getText( key, locale );
      else return null;
   }

   public <R extends Repository<?>> R getRepository( Class<R> repositoryClass ) { return persistenceContext.getRepositoryInstance(repositoryClass); }
   public Repository<?> getRepositoryByEntityClass( Class<? extends Entity> entityClass ) { return persistenceContext.getRepositoryByAggregateRootClass( entityClass ); }
   public ResourceLoader getResourceLoader() { return resourceLoader; }
   public PropertyContext getPropertyContext() { return propertyContext; }
   public PersistenceContext getPersistenceContext() { return persistenceContext; }
   public UserSessionManager getUserSessionManager() { return userSessionManager; }
   public void setConfigurationDescriptorPath( String descriptorPath ) { configurationDescriptorPath = descriptorPath; }
   public void setPropertyContextOverrides( PropertyContextOverrides overrides ) { this.propertyContextOverrides = overrides; } 
   
   //Protected mutator methods
   @Override protected void setUpTransientComponents( ) throws ConfigurationSetUpException {
      if( resourceLoader == null ) resourceLoader = new DefaultResourceLoader();
      
      createSystemInstallerRequestContext( application );
      
      try {
         setUpPropertyContext( configurationDescriptorPath );
         if( propertyContextOverrides != null) overrideProperties();
         setUpMeasurementContext();
         setUpInternalizationResources();
         setUpBeanContainer();
//         setUpUserSessionManager();
      } catch( Exception e ) {
         log.error( "ProcessPuzzleContext.setUp() - failed", e );
            throw new ConfigurationSetUpException( configurationDescriptorPath, e );
      }
   }
   
   @Override protected void setUpPersistentComponents() {
      setUpPersistenceContext();      
   }

   @Override protected void tearDownTransientComponents() {
      if( propertyContext != null ) propertyContext.tearDown( applicationAction );
      if( i18Context != null ) i18Context.tearDown( applicationAction );
      if( measurementContext != null ) measurementContext.tearDown( applicationAction );
      if( beanContainer != null ) beanContainer.tearDown( applicationAction );
      
      configurationDescriptorPath = null;
      resourceLoader = null;
      propertyContext = null;
      i18Context = null;
      propertyContextOverrides = null;
   }
   
   @Override protected void tearDownPersistentComponents() {
      if( persistenceContext != null ) persistenceContext.tearDown( applicationAction );
      persistenceContext = null;
      
      soleInstance = null;
   }

//Private mutators
   private void setUpPropertyContext( String  descriptorPath ) {
      propertyContext = new PropertyContext( application, resourceLoader, descriptorPath );
      propertyContext.setUp( applicationAction );
      configurationDescriptorPath = descriptorPath;
   }
   
   private void setUpPersistenceContext() {
      persistenceContext = new PersistenceContext( application );
      persistenceContext.setUp( applicationAction );
   }
   
   private void setUpMeasurementContext() {
      measurementContext = new MeasurementContext( application );
      measurementContext.setUp( applicationAction );
   }

   private void setUpInternalizationResources() {
      i18Context = new InternalizationContext( application );
      i18Context.setUp( applicationAction );      
   }
   
   private void setUpBeanContainer() {
      String beanContainerConfigurationPath = propertyContext.getProperty( PropertyKeys.BEAN_CONTAINER_DEFINITION_PATH.getDefaultKey() );
      if( beanContainerConfigurationPath == null ) throw new UndefinedBeanContainerPathException( configurationDescriptorPath );
         
      Resource beanDefinitionResource = resourceLoader.getResource( beanContainerConfigurationPath );
      beanContainer = new BeanContainer( application, beanDefinitionResource );
      beanContainer.setUp( applicationAction );
   }
   
   @SuppressWarnings("unchecked")
   private void overrideProperties() {
      for (Iterator iter = propertyContextOverrides.overridesEntrySetIterator(); iter.hasNext();) {
         Map.Entry<PropertyKeys, ProcessPuzzleEnumeration> propertyEntry = (Entry<PropertyKeys, ProcessPuzzleEnumeration>) iter.next();
         propertyContext.overrideProperty( propertyEntry.getKey(), propertyEntry.getValue() );
      }
   }
}