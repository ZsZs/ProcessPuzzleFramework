/*
 * Created on Nov 29, 2006
 */
package com.processpuzzle.workflow.protocol.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

public class CrossParentControlFlowException extends ProcessPuzzleProgrammingException {

   private static final long serialVersionUID = 1920644240245977045L;

   private static String defaultMessagePattern = "A control flow can't bind two steps in different parents.";

   private String sourceParent = "";

   private String targetParent = "";

   public CrossParentControlFlowException(String sourceParent, String targetParent) {
      super(ExceptionHelper.defineMessage(CrossParentControlFlowException.class, new Object[] {sourceParent, targetParent}, defaultMessagePattern));
      this.sourceParent = sourceParent;
      this.targetParent = targetParent;
   }

   public String getSourceParent() {
      return sourceParent;
   }

   public String getTargetParent() {
      return targetParent;
   }
}
