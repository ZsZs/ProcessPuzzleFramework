package com.processpuzzle.address.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

public class CircleNumberConstraintViolationException extends ProcessPuzzleProgrammingException {

   private static final long serialVersionUID = 1L;
   private static String[] descriptions = { "The circle number must be in positive." };
   private String circleNumber = "";
   private int descriptionIndex;
   private static String defaultMessagePattern = "The circle number must be in positive.";

   public CircleNumberConstraintViolationException(String circleNumber, int descriptionIndex) {
      super(ExceptionHelper.defineMessage(CircleNumberConstraintViolationException.class, new Object[] { circleNumber, descriptionIndex },
            defaultMessagePattern));
      this.circleNumber = circleNumber;
      this.descriptionIndex = descriptionIndex;
   }

   public static String[] getDescriptions() {
      return descriptions;
   }

   public static void setDescriptions(String[] descriptions) {
      CircleNumberConstraintViolationException.descriptions = descriptions;
   }

   public String getCircleNumber() {
      return circleNumber;
   }

   public void setCircleNumber(String circleNumber) {
      this.circleNumber = circleNumber;
   }

   public int getDescriptionIndex() {
      return descriptionIndex;
   }

   public void setDescriptionIndex(int descriptionIndex) {
      this.descriptionIndex = descriptionIndex;
   }

}
