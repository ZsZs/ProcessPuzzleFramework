package com.processpuzzle.address.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

public class ZipCodeConstraintViolationException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = 1L;
   private static String[] descriptions = { "The zip code must be in positive and less than 10000." };
   private String zipCode = "";
   private int descriptionIndex;
   private static String defaultMessagePattern = "The zip code must be in positive and less than 10000.";

   public ZipCodeConstraintViolationException(int descriptionIndex, String zipCode) {
      super( ExceptionHelper.defineMessage(ZipCodeConstraintViolationException.class, new Object[] {descriptionIndex, zipCode}, defaultMessagePattern));
      this.descriptionIndex = descriptionIndex;
      this.zipCode = zipCode;
   }

   public static String[] getDescriptions() {
      return descriptions;
   }

   public static void setDescriptions(String[] descriptions) {
      ZipCodeConstraintViolationException.descriptions = descriptions;
   }

   public int getDescriptionIndex() {
      return descriptionIndex;
   }

   public void setDescriptionIndex(int descriptionIndex) {
      this.descriptionIndex = descriptionIndex;
   }

   public String getZipCode() {
      return zipCode;
   }

   public void setZipCode(String zipCode) {
      this.zipCode = zipCode;
   }

   public String getDescription() {
      return descriptions[descriptionIndex];
   }
}
