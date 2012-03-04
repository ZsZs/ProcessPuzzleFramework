/*
Name: 
    - InternalizationContext

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ResourceLoader;

import com.processpuzzle.application.domain.Application;
import com.processpuzzle.application.resource.domain.ResourceNotFoundException;
import com.processpuzzle.fundamental_types.domain.OpAssertion;
import com.processpuzzle.internalization.domain.InvalidResourcePathException;
import com.processpuzzle.internalization.domain.LocaleDefinitionNotFoundException;
import com.processpuzzle.internalization.domain.LocaleLoader;
import com.processpuzzle.internalization.domain.LocaleParseException;
import com.processpuzzle.internalization.domain.NoneExistingResourceKeyException;
import com.processpuzzle.internalization.domain.ProcessPuzzleLocale;
import com.processpuzzle.internalization.domain.UnsupportedLocaleException;
import com.processpuzzle.internalization.domain.XMLResourceBundle;

public class InternalizationContext extends TransientApplicationContext implements ApplicationContext {
   protected static Map<ProcessPuzzleLocale, XMLResourceBundle> resourceBundles = new HashMap<ProcessPuzzleLocale, XMLResourceBundle>();
   private static Log log = LogFactory.getLog( InternalizationContext.class );
   private ResourceLoader loader = null;
   private PropertyContext propertyContext = null;
   private Set<String> sourceUrls = new HashSet<String>();
   private ProcessPuzzleLocale defaultLocale = null;
   private Set<ProcessPuzzleLocale> supportedLocales = new HashSet<ProcessPuzzleLocale>();
   private MeasurementContext measurementContext;

   // Constructos
   public InternalizationContext( Application application ) {
      this( application, null );
   }

   InternalizationContext( Application application, ResourceLoader loader ) {
      super( application );
      this.propertyContext = application.getContext().getPropertyContext();
      this.measurementContext = application.getContext().getMeasurementContext();

      if( loader != null )
         this.loader = loader;
      else
         this.loader = propertyContext.getResourceLoader();
   }

   // Package mutators
   @Override
   protected void setUpTransientComponents() {
      determineSourceUrls();
      determineDefaultLocale();
      determineSupportedLocales();
      loadSupportedLocales();
      String fileSetPath = concatenateSourceUrls();
      loadResourceBundle( fileSetPath );
   }

   @Override
   protected void tearDownTransientComponents() {
      defaultLocale = null;
      supportedLocales.clear();
      resourceBundles.clear();
   }

   // Public accessors
   public String getText( String key, ProcessPuzzleLocale locale ) {
      if( !supportedLocales.contains( locale ) )
         throw new UnsupportedLocaleException( locale.getLanguage(), locale.getCountry(), locale.getVariant() );

      String text = null;
      for( Iterator<ProcessPuzzleLocale> iter = resourceBundles.keySet().iterator(); iter.hasNext(); ){
         ProcessPuzzleLocale localeKey = iter.next();
         if( localeKey.equals( locale ) ){
            XMLResourceBundle defaultBundle = resourceBundles.get( localeKey );
            try{
               text = defaultBundle.getText( key );
            }catch( NoneExistingResourceKeyException e ){
               // Log the problem and return the key
               log.debug( "Resource with key: " + key + " was not found.", e );
               text = null;
            }
         }
      }
      if( text != null )
         return text;
      else
         return null;
   }

   public String getText( String key ) {
      return getText( key, getDefaultLocale() );
   }

   // Public mutators

   // Properties
   public Set<String> getSourceUrls() {
      return sourceUrls;
   }

   public Map<ProcessPuzzleLocale, XMLResourceBundle> getResourceBundles() {
      return resourceBundles;
   }

   public ProcessPuzzleLocale getDefaultLocale() {
      return defaultLocale;
   }

   public Set<ProcessPuzzleLocale> getSupportedLocales() {
      return supportedLocales;
   }

   public List<String> getSupportedLocalesList() {
      List<String> localesList = new ArrayList<String>();
      for( ProcessPuzzleLocale locale : getSupportedLocales() ) {
         localesList.add( locale.toString() );
      }
      return localesList;
   }

   public ProcessPuzzleLocale findLocaleByLanguage( String language ) {
      for( Iterator<ProcessPuzzleLocale> i = supportedLocales.iterator(); i.hasNext(); ){
         ProcessPuzzleLocale locale = i.next();
         if( locale.getLanguage().equals( language ) )
            return locale;

      }
      return null;
   }

   public ProcessPuzzleLocale findLocaleByLanguageAndCountry( String language, String country ) {
      for( Iterator<ProcessPuzzleLocale> i = supportedLocales.iterator(); i.hasNext(); ){
         ProcessPuzzleLocale locale = i.next();
         if( locale.getLanguage().equals( language ) && locale.getCountry().equals( country ) )
            return locale;
      }
      return null;
   }

   public ProcessPuzzleLocale findLocaleByLanguageAndCountryAndVariant( String language, String country, String variant ) {
      for( Iterator<ProcessPuzzleLocale> i = supportedLocales.iterator(); i.hasNext(); ){
         ProcessPuzzleLocale locale = i.next();
         if( locale.getLanguage().equals( language ) && locale.getCountry().equals( country ) && locale.getVariant().equals( variant ) )
            return locale;
      }
      return null;
   }

   public ProcessPuzzleLocale findLocaleBySpecifier( String specifier ) {
      OpAssertion.ppAssert( specifier != null, "Specifier can't be null." );
      ProcessPuzzleLocale locale = ProcessPuzzleLocale.parse( specifier );
      for( Iterator<ProcessPuzzleLocale> iter = supportedLocales.iterator(); iter.hasNext(); ){
         ProcessPuzzleLocale supportedLocale = iter.next();
         if( supportedLocale.equals( locale ) )
            return supportedLocale;
      }
      return null;
   }

   public boolean isExist( String language ) {
      for( Iterator<ProcessPuzzleLocale> i = supportedLocales.iterator(); i.hasNext(); ){
         ProcessPuzzleLocale locale = i.next();
         if( locale.getLanguage().equals( language ) )
            return true;

      }
      return false;
   }

   // Private helper methods
   private void determineSourceUrls() {
      List<String> resourceBundleUrls = propertyContext.getPropertyList( PropertyKeys.INTERNALIZATION_RESOURCE_BUNDLE.getDefaultKey() );
      for( Iterator<String> iter = resourceBundleUrls.iterator(); iter.hasNext(); ){
         String aUrl = (String) iter.next();
         sourceUrls.add( aUrl );
      }
   }

   private void determineDefaultLocale() {
      String defaultLocaleSpecifier = propertyContext.getProperty( PropertyKeys.INTERNALIZATION_DEFAULT_LOCALE.getDefaultKey() );
      try{
         defaultLocale = ProcessPuzzleLocale.parse( defaultLocaleSpecifier );
      }catch( LocaleParseException e ){
         throw new InternalizationContextSetUpException( propertyContext, e );
      }
   }

   private void determineSupportedLocales() {
      List<String> supportedLocaleSpecifiers = propertyContext.getPropertyList( PropertyKeys.INTERNALIZATION_AVAILABLE_LOCALES.getDefaultKey() );
      Iterator<String> supportedLocalesIterator = supportedLocaleSpecifiers.iterator();
      while( supportedLocalesIterator.hasNext() ){
         String supportedLocaleSpecifier = (String) supportedLocalesIterator.next();
         ProcessPuzzleLocale supportedLocale = ProcessPuzzleLocale.parse( supportedLocaleSpecifier );
         supportedLocales.add( supportedLocale );
      }
   }

   private void loadSupportedLocales() {
      String definitionsPath = propertyContext.getProperty( PropertyKeys.INTERNALIZATION_LOCALE_DEFINITIONS.getDefaultKey() );
      try{
         LocaleLoader loader = new LocaleLoader( definitionsPath, this, measurementContext );
         for( Iterator<ProcessPuzzleLocale> i = supportedLocales.iterator(); i.hasNext(); ){
            loader.loadLocaleDefinition( i.next() );
         }
      }catch( ResourceNotFoundException e ) {
         throw new InternalizationContextSetUpException( propertyContext, e );
      }catch( LocaleDefinitionNotFoundException e ){
         throw new InternalizationContextSetUpException( propertyContext, e );
      }
   }

   private void loadResourceBundle( String bundleName ) {
      if( bundleName != null && !bundleName.equals( "" ) ){
         if( bundleName != null && defaultLocale != null && supportedLocales.size() >= 1 ){
            for( Iterator<ProcessPuzzleLocale> iter = supportedLocales.iterator(); iter.hasNext(); ){
               ProcessPuzzleLocale supportedLocale = iter.next();
               XMLResourceBundle bundle = new XMLResourceBundle( loader, bundleName );
               try{
                  bundle.loadResources( supportedLocale );
               }catch( InvalidResourcePathException e ){
                  throw new InternalizationContextSetUpException( propertyContext, e );
               }catch( Exception e ){
                  e.printStackTrace();
               }
               resourceBundles.put( supportedLocale, bundle );
            }
         }
      }
   }

   private String concatenateSourceUrls() {
      String concatenatedUrls = null;
      for( Iterator<String> iter = sourceUrls.iterator(); iter.hasNext(); ){
         String sourceUrl = iter.next();
         if( concatenatedUrls == null )
            concatenatedUrls = sourceUrl;
         else
            concatenatedUrls += XMLResourceBundle.DEFAULT_PATH_DELIMITER + sourceUrl;
      }
      return concatenatedUrls;
   }

}
