/*
 * Created on Oct 12, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.workflow.activity.artifact;

import com.processpuzzle.artifact.domain.PrintView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;

/**
 * @author zsolt.zsuffa
 */
public class ActionDataSheet_PrintView extends PrintView<ActionDataSheet<?>> {

   public ActionDataSheet_PrintView( ActionDataSheet<?> artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );
   }

   public String buildXml() {
      StringBuffer outputXml = new StringBuffer();
      outputXml.append( "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" );
      outputXml.append( "<!DOCTYPE chars SYSTEM \"" + getCharacterEntitiesDtd() + "\">" );
      outputXml.append( "<propertyview>" );
      outputXml.append( "<actionName>" + parentArtifact.getActionName() + "</actionName>" );
      outputXml.append( "<description>" + parentArtifact.getDescription() + "</description>" );
      outputXml.append( "<priority>" + parentArtifact.getPriority() + "</priority>" );
      outputXml.append( "<projectedBegin>" + parentArtifact.getProjectedBegin() + "</projectedBegin>" );
      outputXml.append( "<projectedEnd>" + parentArtifact.getProjectedEnd() + "</projectedEnd>" );
      outputXml.append( "<realBegin>" + parentArtifact.getRealBegin() + "</realBegin>" );
      outputXml.append( "<realEnd>" + parentArtifact.getRealEnd() + "</realEnd>" );
      outputXml.append( "</propertyview>" );
      return outputXml.toString();
   }

   public void initializeView() {

   }
}
