/*
 * Created on May 4, 2006
 *
 */
package com.processpuzzle.artifact.artifact;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.domain.Application;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

/**
 * @author zsolt.zsuffa
 */
public class ArtifactList_ListViewTest {
   @Mock private Application application;
   ArtifactList<?> artifactList = null;

   @Before
   public void setUp() throws Exception {
      MockitoAnnotations.initMocks( ArtifactList_ListViewTest.class );
      
      ProcessPuzzleContext configuration = new ProcessPuzzleContext( application, DomainTierTestConfiguration.APPLICATION_CONFIGURATION_DESCRIPTOR_PATH );
      configuration.setUp( Application.Action.start );
   }

   @After
   public void tearDown() throws Exception {}

   @Ignore
   @Test public final void testListedArtifactsPropertyViews () {
   }
}