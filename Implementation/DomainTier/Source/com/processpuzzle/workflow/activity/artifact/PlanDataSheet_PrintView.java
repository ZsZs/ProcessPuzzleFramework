/*
 * Created on Oct 19, 2006
 */
package com.processpuzzle.workflow.activity.artifact;

import com.processpuzzle.artifact.domain.PrintView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;

/**
 * @author zsolt.zsuffa
 */
public class PlanDataSheet_PrintView extends PrintView<PlanDataSheet> {

   public PlanDataSheet_PrintView( PlanDataSheet artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );
   }

   @SuppressWarnings("unchecked")
   public String buildXml() {
      ActionDataSheet planDataSheet = (PlanDataSheet) parentArtifact;
      StringBuffer outputXml = new StringBuffer();
      PlanDataSheet_PropertyView propertyView = (PlanDataSheet_PropertyView) planDataSheet.getPropertyView();
      outputXml.append( "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" );
      outputXml.append( "<!DOCTYPE chars SYSTEM \"" + getCharacterEntitiesDtd() + "\">" );
      outputXml.append( "<propertyview>" );
      outputXml.append( "<actionName>" + propertyView.getActionName() + "</actionName>" );
      outputXml.append( "<description>" + propertyView.getDescription() + "</description>" );
      outputXml.append( "<priority>" + propertyView.getPriority() + "</priority>" );
      outputXml.append( "<projectedBegin>" + propertyView.getProjectedBegin() + "</projectedBegin>" );
      outputXml.append( "<projectedEnd>" + propertyView.getProjectedEnd() + "</projectedEnd>" );
      outputXml.append( "</propertyview>" );
      return outputXml.toString();
   }

   public void initializeView() {

   }
}
