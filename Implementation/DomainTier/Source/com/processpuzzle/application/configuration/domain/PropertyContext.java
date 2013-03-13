/*
Name: 
    - PropertyContext 

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

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.DefaultConfigurationBuilder;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.tree.DefaultExpressionEngine;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.processpuzzle.application.domain.Application;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleEnumeration;

public class PropertyContext extends TransientApplicationContext implements ApplicationContext {
   private String configurationDescriptorUrl;
   private org.apache.commons.configuration.CombinedConfiguration configuration;
   private ResourceLoader loader;
   public static final String PROPERTY_ARRAY_BEGIN = "(";
   public static final String PROPERTY_ARRAY_END = ")";
   public static final String ATTRIBUTE_SIGNER = "@";
   public static final String ATTRIBUTE_END = "]";
   public static final String ATTRIBUTE_BEGIN = "[";
   public static final String SELECTOR_DELIMITER = ".";

   public PropertyContext( Application application, ResourceLoader loader, String descriptorUrl ) {
      super( application );
      log.debug( "Application: " + application.getApplicationName() + " is creating configuration: " + descriptorUrl );
      this.loader = loader;
      this.configurationDescriptorUrl = descriptorUrl;
      
      if( loader == null ) this.loader = new DefaultResourceLoader();
   }
   
   public PropertyContext( Application application, String descriptorUrl ) {
      this( application, null, descriptorUrl );
   }
   
   //Public accessors and mutators
   public void addProperty( String nodeSelector, String propertyName, Object value ) {
      configuration.addProperty( nodeSelector + " " + propertyName, value ); 
   }

   public void overrideProperty(PropertyKeys key, String value ) {
      configuration.setProperty( key.getDefaultKey(), value );
   }

   public void overrideProperty(PropertyKeys key, ProcessPuzzleEnumeration value ) {
      overrideProperty(key, value.asString() );
   }

   //Properties
   public String getApplicationName() {
      return configuration.getString( PropertyKeys.APPLICATION_NAME.getDefaultKey() );
   }

   public String getApplicationVersion() {
      return configuration.getString( PropertyKeys.APPLICATION_VERSION.getDefaultKey() );
   }

   public String getClientWorkingFolder() {
      return configuration.getString( PropertyKeys.APPLICATION_CLIENT_WORKING_FOLDER.getDefaultKey() );
   }

   public org.apache.commons.configuration.Configuration getConfiguration() { return configuration; }

   public HierarchicalConfiguration getConfigurationAt( String  key ) { return configuration.configurationAt( key ); }
   
   public HierarchicalConfiguration getConfigurationAtByParameter( String selector, Object[] parameters ){
      ParametrizedConfigurationPropertyHandler parametrizedPropertyHandler = new ParametrizedConfigurationPropertyHandler( configuration );
      return parametrizedPropertyHandler.configurationAt( selector, parameters );
   }

   public String getConfigurationDescriptorUrl() { return configurationDescriptorUrl; }

   public String getDefaultUserName() {
      return configuration.getString( PropertyKeys.APPLICATION_DEFAULT_USER_NAME.getDefaultKey() );
   }

   public String getDefaultUserPassword() {
      return configuration.getString( PropertyKeys.APPLICATION_DEFAULT_USER_PASSWORD.getDefaultKey() );
   }
   
   public String getPropertyByParameter( String selector, Object[] parameters ){
      ParametrizedConfigurationPropertyHandler parametrizedPropertyHandler = new ParametrizedConfigurationPropertyHandler( configuration );
      return parametrizedPropertyHandler.getProperty( selector, parameters );
   }
   
   public List<String> getPropertyListByParameter( String selector, Object[] parameters ){
      ParametrizedConfigurationPropertyHandler parametrizedPropertyHandler = new ParametrizedConfigurationPropertyHandler( configuration );
      return parametrizedPropertyHandler.getPropertyList( selector, parameters );
   }
   
   public Properties getProperties( String key ) { 
      Configuration subConfiguration = configuration.configurationAt( key );
      Properties properties = new Properties();
      for (Iterator<?> iter = subConfiguration.getKeys(); iter.hasNext();) {
         String propertyKey = (String) iter.next();
         String propertyValue = subConfiguration.getString( propertyKey );
         properties.put( propertyKey.replace( '/',  '.' ), propertyValue );
      }
      return properties;
   }

   public String getProperty( String key ) {
      if( configuration != null ) return configuration.getString( key );
      else return null;
   }

   public List<String> getPropertyList(String pathKey) { return Lists.transform( configuration.getList( pathKey ), new Function<Object, String>(){
         @Override public String apply( Object configurationElement ) {
      if( configurationElement != null ) return configurationElement.toString();
      else return "null";
   }});}
   public ResourceLoader getResourceLoader() { return loader; }
   public String getServerWorkingFolder() {
      return configuration.getString( PropertyKeys.APPLICATION_SERVER_WORKING_FOLDER.getDefaultKey() );
   }

   //Protected, private helper methods
   @Override protected void setUpTransientComponents() {
      File resourceFile = null;
      try {
         log.debug( "Trying to load resource: " + configurationDescriptorUrl );
         Resource resource = loader.getResource( configurationDescriptorUrl );
         if( resource != null ) resourceFile = resource.getFile();
         else throw new IOException( "Configuration descriptor: " + configurationDescriptorUrl + " can't be load." );
         
         DefaultConfigurationBuilder configurationBuilder = new DefaultConfigurationBuilder( resourceFile );
         configuration = configurationBuilder.getConfiguration( true );
         configuration.setExpressionEngine( new DefaultExpressionEngine() );
      } catch( ConfigurationException e ) {
    	 log.debug( e.getMessage() );
         throw new ConfigurationSetUpException( configurationDescriptorUrl, e );
      } catch( IOException e ) {
         throw new UndefinedPropertyDescriptorException( configurationDescriptorUrl, e );
      }      
   }

   @Override protected void tearDownTransientComponents() {
      if( configuration != null ) configuration.clear();
   }
}
