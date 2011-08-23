/*
 * Created on Oct 12, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.workflow.activity.artifact;

import java.util.List;
import java.util.Map;

import com.processpuzzle.artifact.domain.CustomFormView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;
import com.processpuzzle.workflow.activity.domain.Action;

public class ActionDataSheet_Sequence extends CustomFormView<ActionDataSheet<?>> {

   public ActionDataSheet_Sequence( ActionDataSheet<?> artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );
   }

   public Map<String, List<Action<?>>> getPreviousActions() {
      return parentArtifact.getPreviousActions();
   }
   
   public Map<String, List<Action<?>>> getNextActions() {
      return parentArtifact.getNextActions();
   }

   @Override
   public void initializeView() {
   // TODO Auto-generated method stub

   }

}
