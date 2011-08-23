/*
 * Created on Sep 14, 2006
 */
package com.processpuzzle.workflow.activity.artifact;

import java.util.Collection;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.artifact.domain.PropertyView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;
import com.processpuzzle.workflow.activity.domain.ResourceAllocation;

/**
 * @author zsolt.zsuffa
 */
public class ActionDataSheet_PropertyView extends PropertyView<ActionDataSheet<?>> {

   public ActionDataSheet_PropertyView( ActionDataSheet<?> artifact, String name, ArtifactViewType viewType ) {
      super( artifact, name, viewType );
   }

   public String getProtocolName() {
      return ProcessPuzzleContext.getInstance().getText(
            "ui.pantokrator.protocol." + parentArtifact.getAction().getProtocol().getName(), getPreferredLanguage() );
   }

   public String getActionName() {
      return parentArtifact.getAction().getName();
   }

   public String getSubject() {
      return parentArtifact.getAction().getProcessPlan().getSubject();
   }

   public String getCaseLabel() {
      return parentArtifact.getAction().getProcessPlan().getName();
   }

   public String getDesignation() {
      return parentArtifact.getAction().getDesignation();
   }

   public String getDescription() { 
      return parentArtifact.getDescription(); 
   }

   public Boolean getExecutable() {
      return parentArtifact.getExecutable();
   }

   public String getStatus() {
      return parentArtifact.getStatus();
   }

   public String getProtocol() {
      return parentArtifact.getProtocol();
   }

   public String getPriority() {
      return parentArtifact.getPriority();
   }

   public String getProjectedBegin() {
      return parentArtifact.getProjectedBegin();
   }

   public String getProjectedEnd() {
      return parentArtifact.getProjectedEnd();
   }

   public String getRealBegin() {
      return parentArtifact.getRealBegin();
   }

   public String getRealEnd() {
      return parentArtifact.getRealEnd();
   }

   public String getOwnerName() {
      return parentArtifact.getOwnerName();
   }

   public Collection<ResourceAllocation> getBookedResources() {
      return parentArtifact.getBookedResources();
   }

   public Collection<ResourceAllocation> getUsedResources() {
      return parentArtifact.getUsedResources();
   }
}
