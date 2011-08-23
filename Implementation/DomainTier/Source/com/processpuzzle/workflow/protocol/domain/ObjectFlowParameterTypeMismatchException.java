/*
 * Created on Dec 1, 2006
 */
package com.processpuzzle.workflow.protocol.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleException;

public class ObjectFlowParameterTypeMismatchException extends ProcessPuzzleException {

   /**
    * 
    */
   private static final long serialVersionUID = 6782739610375440581L;

   private static String defaultMessagePattern= "Output and input parameters are mismatched in object flow.";

   private String parent = "";

   private String sourceStep = "";

   private String outputParameter = "";

   private String targetStep = "";

   private String inputParameter = "";

   public ObjectFlowParameterTypeMismatchException(String parent, String sourceStep, String outputParameter, String targetStep,
         String inputParameter) {
      super(ExceptionHelper.defineMessage(ObjectFlowParameterTypeMismatchException.class, new Object[] { parent, sourceStep,
            outputParameter, targetStep, inputParameter }, defaultMessagePattern), null);
      this.parent = parent;
      this.sourceStep = sourceStep;
      this.outputParameter = outputParameter;
      this.targetStep = targetStep;
      this.inputParameter = inputParameter;
   }

   public String getParent() {
      return parent;
   }

   public String getSourceStep() {
      return sourceStep;
   }

   public String getOutputParameter() {
      return outputParameter;
   }

   public String getTargetStep() {
      return targetStep;
   }

   public String getInputParameter() {
      return inputParameter;
   }
}
