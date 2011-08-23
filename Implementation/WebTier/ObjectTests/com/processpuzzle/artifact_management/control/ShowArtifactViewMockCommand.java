/*
 * Created on 2006.05.15.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.artifact_management.control;

import com.processpuzzle.application.control.control.CommandDispatcher;
import com.processpuzzle.application.control.control.CommandInterface;
import com.processpuzzle.artifact.domain.AccessRightsView;
import com.processpuzzle.artifact.domain.PropertyView;
import com.processpuzzle.artifact.domain.PropertyViewSubClass;
import com.processpuzzle.artifact.domain.RelatedArtifactsListView;
import com.processpuzzle.artifact.domain.VersionsView;

/**
 * @author peter.krima
 *
 */
public class ShowArtifactViewMockCommand implements CommandInterface {
 
    private String targetPage;
    
    public void init(CommandDispatcher dispatcher) {

       String viewName = dispatcher.getProperties().getProperty("viewName");
       if (viewName.equals("Artifact_AccessRights")){
          AccessRightsView accessRightsView = new AccessRightsView(null,"AccessRightsView", null);
          dispatcher.getRequest().setAttribute("accessRightsView", accessRightsView);
          targetPage = accessRightsView.getType().getPresentationUri();
       }
//---
       if (viewName.equals("Artifact_RelatedArtifacts")){
           RelatedArtifactsListView relatedArtifactsView = new RelatedArtifactsListView(null,"RelatedArtifactsView", null);
           dispatcher.getRequest().setAttribute("relatedArtifactsView", relatedArtifactsView);
           targetPage = relatedArtifactsView.getType().getPresentationUri();
        }
//---
       if (viewName.equals("Artifact_Versions")){
           VersionsView versionsView = new VersionsView(null,"VersionsView", null);
           dispatcher.getRequest().setAttribute("versionsView", versionsView);
           targetPage = versionsView.getType().getPresentationUri();
        }
//---
       if (viewName.equals("Artifact_Properties")){
           PropertyView<?> propertyView = new PropertyViewSubClass(null);
           dispatcher.getRequest().setAttribute("PropertyView", propertyView);
           targetPage = propertyView.getType().getPresentationUri();
        }
       
    }    
    
    public String getName() {
        return "ShowArtifactViewMock";
     }

     public String execute(CommandDispatcher dispatcher) throws Exception {
        return targetPage;
     }
}