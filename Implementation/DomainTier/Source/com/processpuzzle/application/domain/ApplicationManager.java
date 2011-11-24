/*
Name: 
    - ApplicationManager

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

package com.processpuzzle.application.domain;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import com.processpuzzle.persistence.domain.EntityInstantiationException;

public class ApplicationManager {
   private String applicationStoragePath;
   private ResourceLoader resourceLoader;
   private ApplicationRepository applicationRepository;
   
   public ApplicationManager( String applicationStoragePath, ResourceLoader resourceLoader ) throws InstantiationException {
      this.applicationStoragePath = ( applicationStoragePath != null ) ? applicationStoragePath : "classpath: Configuration/Application.xml";
      this.resourceLoader = ( resourceLoader != null ) ? resourceLoader : new DefaultResourceLoader();
      
      applicationRepository = ApplicationRepository.getInstance( this.applicationStoragePath, this.resourceLoader );
   }

   //Public mutators
   public Application install( String applicationName, 
                               Class<? extends Application> applicationClass,
                               String configurationDescriptorPath ) throws ApplicationInstallationException {
      Application application = null;
      
      try{
         application = retrieveApplicationFromRepository( applicationName );
         if( application == null ) {
            deployApplicationRunTime( applicationName );
            application = instantiateNewApplication( applicationName, applicationClass, configurationDescriptorPath );
            application.install();
            addApplicationToRepository( application );
         } else {
            application.start();
            updateApplicationInRepository( application );
         }
      }catch( EntityInstantiationException e ){
         throw new ApplicationInstallationException( applicationName, e );
      }catch( ApplicationException e ){
         throw new ApplicationInstallationException( applicationName, e );
      }
      return application;
   }
   
   public void unInstall( Application application ) throws ApplicationUninstallationException {
      application.unInstall();
      removeApplicationFromRepository( application );
   }  
   
   public Application start( String applicationName ) throws ApplicationException, ConfusingApplicationStatusException {
      Application application = retrieveApplicationFromRepository( applicationName );
      if( application == null ) throw new ConfusingApplicationStatusException( applicationName, "not installed", "start" );
      
      application.start();
      updateApplicationInRepository( application );
      return application;
   }
   
   public void stop( Application application ) {
      application.stop();
      updateApplicationInRepository( application );
   }

   private void addApplicationToRepository( Application application ) {
      applicationRepository.add( application );
   }

   //Private helper methods
   private void deployApplicationRunTime( String applicationName ) {
      // TODO Implement application run-time deployment      
   }
   
   protected <A extends Application> A instantiateNewApplication( String applicationName, Class<A> applicationClass, String configurationDescriptorPath ) {
      A application = null;
      application = ApplicationFactory.create( applicationClass, configurationDescriptorPath );
      return application;
   }

   private Application retrieveApplicationFromRepository( String applicationName ) {
      Application application = applicationRepository.findByName( applicationName );
      return application;
   }

   private void removeApplicationFromRepository( Application application ) {
      applicationRepository.delete( application );
   }
   
   private void updateApplicationInRepository( Application application ) {
      applicationRepository.update( application );
   }
}
