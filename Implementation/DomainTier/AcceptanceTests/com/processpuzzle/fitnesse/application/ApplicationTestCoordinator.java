package com.processpuzzle.fitnesse.application;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.processpuzzle.application.domain.Application;
import com.processpuzzle.application.domain.ApplicationException;
import com.processpuzzle.application.domain.ApplicationFactory;
import com.processpuzzle.application.domain.ProcessPuzzleApplication;
import com.processpuzzle.litest.fitnesse.WorkflowFixtureSupport;

import fit.Parse;

public class ApplicationTestCoordinator extends WorkflowFixtureSupport {
   protected static Class<? extends Application> applicationClass = ProcessPuzzleApplication.class;
   private static final Logger logger = LoggerFactory.getLogger( ApplicationTestCoordinator.class );
   private static String configurationDescriptorPath;
   private Application application = null;
   private Multiset<String> pageRuns = HashMultiset.create();

   public ApplicationTestCoordinator() {
      super();
      logger.trace( ApplicationTestCoordinator.class.getSimpleName() + " was instantiated." );
   }
   
   @Override
   public void doTable( Parse table ) {
      logger.trace( "doTable()" );
      if( args.length > 0 ){
         configurationPath( args[0] );
      }
      super.doTable( table );
   }

   public void configurationPath( String configurationDescriptorPath ) {
      ApplicationTestCoordinator.configurationDescriptorPath = configurationDescriptorPath;
   }

   public void notifyOnTestPageSetUp( String testPageName ) throws ApplicationException {
      checkIfApplicationIsUp();
      pageRuns.add( testPageName );
   }

   public void notifyOnTestPageTearDown( String testPageName ) {
      pageRuns.remove( testPageName );
      checkIfApplicationShouldTearDown();
   }

   public void terminate() {
      uninstallApplication();
   }

   // Properties
   public Application getApplication() {
      return application;
   }

   public Integer getHitCountFor( String testPageName ) {
      return pageRuns.count( testPageName );
   }

   private void checkIfApplicationIsUp() throws ApplicationException {
      if( application == null ){
         installApplication();
      }
   }

   private void checkIfApplicationShouldTearDown() {
      if( pageRuns.size() == 0 ){
         application.unInstall();
      }
   }

   private void installApplication() throws ApplicationException {
      logger.debug( "Installing application: " + applicationClass.getName() );
      application = ApplicationFactory.create( applicationClass, configurationDescriptorPath );
      application.install();
   }

   private void uninstallApplication() {
      logger.debug( "Uninstalling application: " + applicationClass.getName() );
      application.unInstall();
   }

}
