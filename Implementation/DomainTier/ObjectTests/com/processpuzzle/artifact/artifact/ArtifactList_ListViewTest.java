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
//      UmlModel umlModel1 = new UmlModel("AModel");
//      umlModel1.setResponsible(new Party("aladár"));
//      assetRep.addUmlModel(umlModel1);
//      
//      artifactList = new ArtifactList("ArtifactList", new ArtifactType("ArtifactListType"));
//      
//      ArtifactList_ListView listView = new ArtifactList_ListView();
//      
//      String query = "from Artifact a where a.name = 'AModel' and a.responsible.name = 'aladár'";
//      List l = (List)assetRep.find(query);
//      System.out.println(l);
//      
//      List listedArtifactsPropertyViews = listView.getListedArtifactsPropertyView(query);
//      System.out.println(listedArtifactsPropertyViews);
//      
//     
//     
//      List result = (List)assetRep.find(query);
//      assertNotNull(result);
//      assertNotNull(result.get(0));
//      Artifact artifact = (Artifact)result.get(0);
//      assertEquals(artifact.getName(), "AModel");
//      assertEquals(artifact.getResponsible().getName(), "aladár");
//      
   }
}