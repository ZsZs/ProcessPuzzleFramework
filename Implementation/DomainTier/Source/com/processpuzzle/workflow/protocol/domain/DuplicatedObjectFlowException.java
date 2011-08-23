/*
 * Created on Nov 30, 2006
 */
package com.processpuzzle.workflow.protocol.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleException;

public class DuplicatedObjectFlowException extends ProcessPuzzleException {
   /**
    * 
    */
   private static final long serialVersionUID = -7115116456117817657L;

   private static String messageBody = "There was a trial to create an object flow with the same parameters.";

   private static String defaultMessagePattern = "There was a trial to create an object flow with the same parameters.";

   private ProtocolCallAction source = null;

   private ProtocolCallAction target = null;

   private ArtifactInstance instance = null;

   DuplicatedObjectFlowException(ProtocolCallAction source, ProtocolCallAction target, ArtifactInstance instance) {
      super(ExceptionHelper.defineMessage(DuplicatedObjectFlowException.class, new Object[] { source, target, instance },
            defaultMessagePattern), null);
      this.source = source;
      this.target = target;
      this.instance = instance;
   }

   public static String getMessageBody() {
      return messageBody;
   }

   public ArtifactInstance getInstance() {
      return instance;
   }

   public ProtocolCallAction getSource() {
      return source;
   }

   public ProtocolCallAction getTarget() {
      return target;
   }
}
