/*
 * Created on Dec 1, 2006
 */
package com.processpuzzle.workflow.protocol.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleException;

public class SelfReferencedObjectFlowException extends ProcessPuzzleException {

   private static final long serialVersionUID = 5918821684850131623L;

   private static String defaultMessagePattern = "There was a trial to add self referenced object flow.";

   private String parent = "";

   private String sourceStep = "";

   private String targetStep = "";

   public SelfReferencedObjectFlowException(String parent, String sourceStep, String targetStep) {
      super(ExceptionHelper.defineMessage(SelfReferencedObjectFlowException.class, new Object[] {parent, sourceStep, targetStep}, defaultMessagePattern), null);
      this.parent = parent;
      this.sourceStep = sourceStep;
      this.targetStep = targetStep;
   }

   public String getParent() {
      return parent;
   }

   public String getSourceStep() {
      return sourceStep;
   }

   public String getTargetStep() {
      return targetStep;
   }
}
